package com.novaone.service.system;

import com.google.inject.ImplementedBy;

/**
 * Created by dell-pc on 2015/9/6.
 */
@ImplementedBy(OrgServiceImpl.class)
public interface OrgService {
    /**
     * 根据用户当前所属部门，查询出这些部门下的所有子部门id
     * @param orgId
     * @return
     */
    String getUserAllOrg(String orgId);
}
