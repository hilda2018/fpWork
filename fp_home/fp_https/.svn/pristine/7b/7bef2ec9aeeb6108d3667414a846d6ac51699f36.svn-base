package com.fresh.service.impl;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.fresh.dao.RegistrationDao;
import com.fresh.model.DRole;
import com.fresh.model.DUser;
import com.fresh.model.DUserss;
import com.fresh.model.Duserrole;
import com.fresh.model.RegistrationDTO;
import com.fresh.model.User;
import com.fresh.model.UsersBelong;
import com.fresh.service.RegistrationService;
import com.fresh.utils.Md5Encoder;
import com.google.inject.Inject;
import com.nova.frame.utils.JdbcUtils;

public class RegistrationServiceImpl implements RegistrationService {
    @Inject
    private RegistrationDao registrationDao;

    private static final String CODE_SUPPLIER = "0.1";// 供应商（仅信息展示）

    private static final String CODE_BUYER = "1.3";// 采购商（自助注册）

    @Override
    public ConcurrentHashMap<String, Object> saveRegistrationInfo(JSONArray insArray) {
        ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<String, Object>();
        map.put("success", true);
        map.put("msg", "Your registration is successful");
        try {
            JdbcUtils.beginTransaction();
            for (Object object : insArray) {
                JSONObject o = JSONObject.fromObject(object);
                RegistrationDTO registration = (RegistrationDTO) JSONObject.toBean(o, RegistrationDTO.class);
                // // 判断Username是否重复
                // Long count = registrationDao.queryUserCount(registration);
                // if (count > 0) {// 有重复
                // map.put("success", false);
                // map.put("msg", "Your username has been occupied ！");
                // } else {
                String username = registration.getUsername();
                String password = registration.getPassword();
                String companyname = registration.getCompanyname();
                String country = registration.getCountry();
                String contactperson = registration.getContactperson();
                String email = registration.getEmail();
                String phonenum = registration.getPhonenum();
                String companytype = registration.getCompanytype();
                String code = "";
                if ("0".equals(companytype)) {
                    code = CODE_SUPPLIER;
                    phonenum = "";
                } else if ("1".equals(companytype)) {
                    code = CODE_BUYER;
                    email = "";
                }

                DUser dUser = new DUser();
                dUser.setId(UUID.randomUUID().toString());
                dUser.setCode(username);
                dUser.setName(username);
                dUser.setPassword(Md5Encoder.encodePassword(password));
                dUser.setIsusing("Y");
                dUser.setParentid("0");
                dUser.setEmail(email);
                dUser.setPhonenum(phonenum);
                dUser.setContactperson(contactperson);
                dUser.setIsregistration("0");
                dUser.setIsuserinsert("1");
                registrationDao.saveDuser(dUser);

                User user = new User();
                user.setUserid(UUID.randomUUID().toString());
                user.setUsername(username);
                user.setPassword(Md5Encoder.encodePassword(password));
                user.setCountry(country);
                user.setEmail(email);
                user.setPhonenum(phonenum);
                user.setCompanyenname(companyname);
                user.setIsdel("N");
                user.setIsmerge("Y");
                user.setIsuserinsert("1");
                user.setComparetype("0");
                registrationDao.saveUser(user);

                DUserss dusers = new DUserss();
                dusers.setId(UUID.randomUUID().toString());
                dusers.setDuserid(dUser.getId());
                dusers.setUsersid(user.getUserid());
                dusers.setIsuse("Y");
                registrationDao.saveDusers(dusers);

                UsersBelong usersBelong = new UsersBelong();
                usersBelong.setBelongid(UUID.randomUUID().toString());
                usersBelong.setUserid(user.getUserid());
                usersBelong.setCompanytype(companytype);
                registrationDao.saveUsersBelong(usersBelong);

                DRole role = registrationDao.queryDroleByCode(code);
                Duserrole duserrole = new Duserrole();
                duserrole.setId(UUID.randomUUID().toString());
                duserrole.setUserid(dUser.getId());
                duserrole.setRoleid(role.getId());
                registrationDao.saveDuserrole(duserrole);
            }
            // }
            JdbcUtils.commitTransaction();
        } catch (

        Exception e) {
            map.put("success", false);
            map.put("msg", "save error！");
            JdbcUtils.rollbackTransaction();
            e.printStackTrace();
        }
        return map;
    }

    @Override
    public String queryAllCountry() {
        List<RegistrationDTO> registrations = registrationDao.queryAllCountry();
        JSONArray registrationsJson = JSONArray.fromObject(registrations);
        return registrationsJson.toString();
    }

    @Override
    public String checkUsername(String username) {
        RegistrationDTO user = new RegistrationDTO();
        user.setUsername(username);
        Long count = registrationDao.queryUserCount(user);
        return count + "";
    }

    @Override
    public String checkUserCompany(String companyname) {
        RegistrationDTO user = new RegistrationDTO();
        user.setCompanyname(companyname);
        Long count = registrationDao.queryCompanyUserCount(user);
        return count + "";
    }
}
