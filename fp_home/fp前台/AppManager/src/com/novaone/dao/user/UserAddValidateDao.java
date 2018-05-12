package com.novaone.dao.user;

import com.google.inject.ImplementedBy;
import com.novaone.dao.user.impl.UserAddValidateDaoImpl;
/**
 * 平台新增用户 用户名唯一性验证
 * @author 辉
 *
 */
@ImplementedBy(UserAddValidateDaoImpl.class)
public interface UserAddValidateDao {

}
