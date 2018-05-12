package com.novaone.dao.user;

import com.google.inject.ImplementedBy;
import com.novaone.dao.user.impl.UserLinksDaoImpl;

@ImplementedBy(UserLinksDaoImpl.class)
public interface UserLinksDao {

}
