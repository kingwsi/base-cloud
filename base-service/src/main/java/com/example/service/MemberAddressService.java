package com.example.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.bean.RespCodeEnum;
import com.example.common.entity.memberaddress.MemberAddress;
import com.example.common.entity.memberaddress.MemberAddressConvertMapper;
import com.example.common.entity.memberaddress.MemberAddressVO;
import com.example.common.exception.CustomException;
import com.example.common.utils.TokenUtils;
import com.example.mapper.MemberAddressMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * description: 会员地址 Service <br>
 * date: 2021-07-09 <br>
 * author: ws <br>
 */
@Service
public class MemberAddressService {

    private final MemberAddressMapper memberaddressMapper;

    private final MemberAddressConvertMapper memberaddressConvertMapper;

    public MemberAddressService(MemberAddressMapper memberaddressMapper, MemberAddressConvertMapper memberaddressConvertMapper) {
        this.memberaddressMapper = memberaddressMapper;
        this.memberaddressConvertMapper = memberaddressConvertMapper;
    }

    /**
     * 新增地址数据
     * 检查
     *
     * @param memberaddressVO
     * @return
     */
    public boolean create(MemberAddressVO memberaddressVO) {
        Integer memberId = TokenUtils.getCurrentPrincipalId();
        memberaddressVO.setMemberId(memberId);
        List<MemberAddressVO> list = this.listByCurrentMember(memberId);
        if (list == null || list.isEmpty()) {
            // 新增一条并设置为默认
            MemberAddress memberAddress = memberaddressConvertMapper.toMemberAddress(memberaddressVO);
            memberAddress.setIsDefault(true);
            return memberaddressMapper.insert(memberAddress) > 1;
        }
        if (list.size() > 9) {
            throw new CustomException("地址数量超出上限，请删除不常用地址后再添加！");
        }
        if (memberaddressVO.getIsDefault() != null && memberaddressVO.getIsDefault()) {
            // 清除默认
            MemberAddress memberAddress = new MemberAddress();
            memberAddress.setIsDefault(false);
            memberaddressMapper.update(memberAddress, Wrappers.lambdaQuery(MemberAddress.class)
                    .eq(MemberAddress::getMemberId, memberId));
        }
        return memberaddressMapper.insert(memberaddressConvertMapper.toMemberAddress(memberaddressVO)) > 0;
    }

    /**
     * 删除一条记录
     *
     * @param id
     * @return
     */
    public boolean removeById(Integer id) {
        Integer memberId = TokenUtils.getCurrentPrincipalId();

        return memberaddressMapper.delete(Wrappers.lambdaQuery(MemberAddress.class)
                .eq(MemberAddress::getMemberId, memberId)
                .eq(MemberAddress::getId, id)) > 0;
    }

    /**
     * 获取分页信息
     *
     * @param page
     * @param vo
     * @return
     */
    public IPage<MemberAddressVO> listOfPage(Page<MemberAddressVO> page, MemberAddressVO vo) {
        return memberaddressMapper.selectPage(page, vo);
    }

    /**
     * 更新数据 id不能为空
     *
     * @param memberaddressVO
     * @return
     */
    public boolean updateById(MemberAddressVO memberaddressVO) {
        Integer memberId = TokenUtils.getCurrentPrincipalId();
        MemberAddress memberAddress = memberaddressConvertMapper.toMemberAddress(memberaddressVO);
        memberAddress.setLastUpdateDate(LocalDateTime.now());
        return memberaddressMapper.update(memberAddress, Wrappers.lambdaQuery(MemberAddress.class)
                .eq(MemberAddress::getMemberId, memberId)
                .eq(MemberAddress::getId, memberAddress.getId())
        ) > 0;
    }

    public MemberAddressVO getById(Integer id) {
        Integer memberId = TokenUtils.getCurrentPrincipalId();
        MemberAddress memberaddress = memberaddressMapper.selectOne(Wrappers.lambdaQuery(MemberAddress.class)
                .eq(MemberAddress::getMemberId, memberId)
                .eq(MemberAddress::getId, id));
        return memberaddressConvertMapper.toMemberAddressVO(memberaddress);
    }

    public List<MemberAddressVO> listByCurrentMember(Integer memberId) {
        List<MemberAddress> memberAddresses = memberaddressMapper.selectList(
                Wrappers.lambdaQuery(MemberAddress.class)
                        .eq(MemberAddress::getMemberId, memberId)
                        .orderByDesc(MemberAddress::getIsDefault)
                        .orderByDesc(MemberAddress::getCreatedDate));
        return memberaddressConvertMapper.toMemberAddressVOList(memberAddresses);
    }

    /**
     * 设为默认地址
     *
     * @param id
     */
    @Transactional(rollbackFor = Exception.class)
    public void setDefault(Integer id) {
        Integer memberId = TokenUtils.getCurrentPrincipalId();
        MemberAddress memberAddress = new MemberAddress();
        memberAddress.setIsDefault(false);
        memberaddressMapper.update(memberAddress, Wrappers.lambdaQuery(MemberAddress.class)
                .eq(MemberAddress::getMemberId, memberId));
        memberAddress = memberaddressMapper.selectById(id);

        if (memberAddress == null) {
            throw new CustomException(RespCodeEnum.NOT_FOUND_RECORD);
        }
        memberAddress.setIsDefault(true);
        memberaddressMapper.updateById(memberAddress);
    }

    /**
     * 获取用户地址
     *
     * @return
     */
    public MemberAddressVO getDefaultByMember(Integer memberId) {
        MemberAddress memberAddress = memberaddressMapper.selectOne(Wrappers.lambdaQuery(MemberAddress.class)
                .eq(MemberAddress::getIsDefault, true).eq(MemberAddress::getMemberId, memberId));
        if (memberAddress == null) {
            throw new CustomException("没有查询到可用地址");
        }
        return memberaddressConvertMapper.toMemberAddressVO(memberAddress);
    }
}
