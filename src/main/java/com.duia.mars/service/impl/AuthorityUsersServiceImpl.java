package com.duia.mars.service.impl;

import com.duia.mars.core.AbstractService;
import com.duia.mars.dao.AuthorityUsersMapper;
import com.duia.mars.model.AuthorityUsers;
import com.duia.mars.service.AuthorityUsersService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2017/09/21.
 */
@Service
@Transactional
public class AuthorityUsersServiceImpl extends AbstractService<AuthorityUsers> implements AuthorityUsersService {
    @Resource
    private AuthorityUsersMapper authorityUsersMapper;

    @Override
    public void updateUserPoint() throws Exception {
        authorityUsersMapper.updateUserPoint();
    }
}
