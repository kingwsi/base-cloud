package com.example.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.bean.AuthUser;
import com.example.common.entity.member.Member;
import com.example.common.entity.member.MemberConvertMapper;
import com.example.common.entity.member.MemberVO;
import com.example.common.exception.CustomException;
import com.example.common.utils.TokenUtils;
import com.example.mapper.MemberMapper;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.util.Optional;

/**
 * description: 会员 Service <br>
 * date: 2021-04-13 <br>
 * author: ws <br>
 */
@Service
public class MemberService {

    private final MemberMapper memberMapper;

    private final MemberConvertMapper memberConvertMapper;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public MemberService(MemberMapper memberMapper, MemberConvertMapper memberConvertMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.memberMapper = memberMapper;
        this.memberConvertMapper = memberConvertMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    /**
     * 插入数据
     *
     * @param memberVO
     * @return
     */
    public boolean create(MemberVO memberVO) {
        return memberMapper.insert(memberConvertMapper.toMember(memberVO)) > 0;
    }

    /**
     * 删除一条记录
     *
     * @param id
     * @return
     */
    public boolean removeById(Integer id) {
        return memberMapper.deleteById(id) > 0;
    }

    /**
     * 获取分页信息
     *
     * @param page
     * @param vo
     * @return
     */
    public IPage<MemberVO> listOfPage(Page<MemberVO> page, MemberVO vo) {
        return memberMapper.selectPage(page, vo);
    }

    /**
     * 更新数据 id不能为空
     *
     * @param memberVO
     * @return
     */
    public boolean updateById(MemberVO memberVO) {
        Assert.isNull(memberVO.getId(), "ID不可为空");
        return memberMapper.updateById(memberConvertMapper.toMember(memberVO)) > 0;
    }

    public MemberVO getById(Integer id) {
        Member member = memberMapper.selectById(id);
        return memberConvertMapper.toMemberVO(member);
    }

    public String authByPwd(AuthUser authUser) {
        QueryWrapper<Member> memberQueryWrapper = new QueryWrapper<>();
        memberQueryWrapper.eq("mobile", authUser.getMobile());
        Member member = memberMapper.selectOne(memberQueryWrapper);

        Boolean authResult = Optional.ofNullable(member)
                .map(o -> bCryptPasswordEncoder.matches(authUser.getPassword(), o.getPassword())).orElse(false);
        if (!authResult) {
            throw new CustomException("用户名或密码错误！");
        }
        if (!"1".equals(member.getAccountStatus())) {
            throw new CustomException("账户不可用！");
        }
        return TokenUtils.createMemberToken(authUser);
    }

    public void register(AuthUser authUser) {
        if (StringUtils.isEmpty(authUser.getUsername()) ||
                StringUtils.isEmpty(authUser.getMobile()) ||
                StringUtils.isEmpty(authUser.getPassword())) {
            throw new CustomException("参数错误！");
        }
        LambdaQueryWrapper<Member> queryWrapper = Wrappers.lambdaQuery(Member.class)
                .eq(Member::getMobile, authUser.getMobile());
        Integer count = memberMapper.selectCount(queryWrapper);
        if (count > 0) {
            throw new CustomException("账户已存在！");
        }
        Member member = new Member();
        member.setMobile(authUser.getMobile());
        member.setNickName(authUser.getUsername());
        member.setPassword(bCryptPasswordEncoder.encode(authUser.getPassword()));
        member.setCreatedDate(Instant.now());
        memberMapper.insert(member);
    }

    public String authByMobile(AuthUser authUser) {
        return null;
    }

    public Member getMemberByOpenid(String openid) {
        return memberMapper.selectOne(Wrappers.lambdaQuery(Member.class)
                .eq(Member::getOpenid, openid));
    }
}
