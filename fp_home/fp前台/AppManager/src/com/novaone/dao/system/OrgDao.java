package com.novaone.dao.system;

import com.google.inject.ImplementedBy;

/**
 * 操作部门dao层接口
 *
 * @author <a href="mailto:yucaixing@novacloud.com">于采兴</a>
 * @version V2.0 2015-05-08 16:49 新建
 * @Title: Novaone
 * @Package com.novaone.dao.system
 */
@ImplementedBy(OrgDaoImpl.class)
public interface OrgDao {
    /**
     * 根据用户当前所属部门，查询出这些部门下的所有子部门id
     * @param orgId
     * @return
     */
    String getUserAllOrg(String orgId);
}
