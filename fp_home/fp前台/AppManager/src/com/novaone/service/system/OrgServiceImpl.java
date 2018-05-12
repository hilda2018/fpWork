package com.novaone.service.system;

import com.google.inject.Inject;
import com.novaone.dao.system.OrgDao;
import org.apache.log4j.Logger;

/**
 * @author 于采兴 2015-09-06 12:11
 * @Description:TODO 描述 Novaone
 * @Package com.novaone.service.system
 */
public class OrgServiceImpl implements OrgService {

    private static Logger logger = Logger.getLogger(OrgService.class);
    @Inject
    private OrgDao orgDao;

    @Override
    public String getUserAllOrg(String orgId) {
        return orgDao.getUserAllOrg(orgId);
    }

}
