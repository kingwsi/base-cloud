package com.example.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.bean.AuthUser;
import com.example.common.entity.customer.Customer;
import com.example.common.entity.customer.CustomerConvertMapper;
import com.example.common.entity.customer.CustomerVO;
import com.example.common.exception.CustomException;
import com.example.common.utils.TokenUtils;
import com.example.mapper.CustomerMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.Instant;
import java.util.Optional;

/**
 * <p>
 * 客户 service
 * </p>
 *
 * @author ws
 * @since 2020-12-29
 */
@Service
public class CustomerService {

    private final CustomerMapper customerMapper;

    private final CustomerConvertMapper customerConvertMapper;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public CustomerService(CustomerMapper customerMapper, CustomerConvertMapper customerConvertMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.customerMapper = customerMapper;
        this.customerConvertMapper = customerConvertMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    /**
     * 插入数据
     *
     * @param customerVO
     * @return
     */
    public boolean create(CustomerVO customerVO) {
        return customerMapper.insert(customerConvertMapper.toCustomer(customerVO)) > 0;
    }

    /**
     * 删除一条记录
     *
     * @param id
     * @return
     */
    public boolean removeById(Integer id) {
        return customerMapper.deleteById(id) > 0;
    }

    /**
     * 获取分页信息
     *
     * @param page
     * @param vo
     * @return
     */
    public IPage<CustomerVO> listOfPage(Page<CustomerVO> page, CustomerVO vo) {
        return customerMapper.selectPage(page, vo);
    }

    /**
     * 更新数据 id不能为空
     *
     * @param customerVO
     * @return
     */
    public boolean updateById(CustomerVO customerVO) {
        Assert.isNull(customerVO.getId(), "ID不可为空");
        return customerMapper.updateById(customerConvertMapper.toCustomer(customerVO)) > 0;
    }

    public CustomerVO getById(Integer id) {
        Customer customer = customerMapper.selectById(id);
        return customerConvertMapper.toCustomerVO(customer);
    }

    public String authByPwd(AuthUser authUser) {
        QueryWrapper<Customer> customerQueryWrapper = new QueryWrapper<>();
        customerQueryWrapper.eq("mobile", authUser.getMobile());
        Customer customer = customerMapper.selectOne(customerQueryWrapper);

        Boolean authResult = Optional.ofNullable(customer)
                .map(o -> bCryptPasswordEncoder.matches(authUser.getPassword(), o.getPassword())).orElse(false);
        if (!authResult) {
            throw new CustomException("用户名或密码错误！");
        }
        if (!"1".equals(customer.getAccountStatus())) {
            throw new CustomException("账户不可用！");
        }
        return TokenUtils.createCustomerToken(authUser);
    }

    public void register(AuthUser authUser) {
        Customer customer = new Customer();
        customer.setMobile(authUser.getMobile());
        customer.setNickName(authUser.getUsername());
        customer.setPassword(bCryptPasswordEncoder.encode(authUser.getPassword()));
        customer.setCreatedDate(Instant.now());
        customerMapper.insert(customer);
    }
}
