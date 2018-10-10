package com.duia.mars.dao;

import com.duia.mars.core.Mapper;
import com.duia.mars.model.AuthorityUsers;

public interface AuthorityUsersMapper extends Mapper<AuthorityUsers> {
    public void updateUserPoint();
}