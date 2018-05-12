package com.fresh.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.fresh.common.base.action.BaseAction;
import com.fresh.service.RegistrationService;
import com.fresh.utils.JsonResponse;
import com.google.inject.Inject;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 * 项目名称：fresh_port_shop_cn    
 * 类名称：RegistrationAction    
 * 类描述：    注册
 * 创建人：xiadecheng    
 * 创建时间：2017年10月26日 下午1:23:24    
 * 修改人：xiadecheng    
 * 修改时间：2017年10月26日 下午1:23:24    
 * 修改备注：    
 * @version     
 *
 */
@Action("registrationAction")
@Results({ @Result(name = "registration", location = "registration/index.jsp") })
public class RegistrationAction extends BaseAction {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Inject
    private RegistrationService registrationService;

    /**
     * 
     * registration -  注册页面初始化跳转
     * @return
     * @return  String  
     * @Exception 异常对象
     * @author xiadecheng
     * @date 2017年10月26日 下午1:23:38
     *
     */
    public String registration() {
        return "registration";
    }

    /**
     * 
     * saveRegistrationInfo -  保存注册信息
     * @return  void  
     * @Exception 异常对象
     * @author xiadecheng
     * @date 2017年10月26日 下午1:31:35
     *
     */
    public void saveRegistrationInfo() {
        PrintWriter out = null;
        JSONObject obj = new JSONObject();
        try {
            String registration = request.getParameter("json");
            JSONArray insArray = JSONArray.fromObject(registration);
            Map<String, Object> map = registrationService.saveRegistrationInfo(insArray);

            response.setContentType("text/html; charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            out = response.getWriter();
            obj.put("success", map.get("success"));
            obj.put("msg", map.get("msg"));
            out.write(obj.toString());
            out.flush();
        } catch (IOException e) {
            out.write("save error!" + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            out.write("save error!" + e.getMessage());
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    /**
     * 
     * queryAllCountry -  获取所有的国家
     * @return  void  
     * @Exception 异常对象
     * @author xiadecheng
     * @date 2017年10月26日 下午2:16:03
     *
     */
    public void queryAllCountry() {
        try {
            String jsonList = registrationService.queryAllCountry();
            JsonResponse.write(jsonList, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	/**
     * 
     * checkUsername -  校验username是否重复
     * @return  void  
     * @Exception 异常对象
     * @author xiadecheng
     * @date 2017年10月27日 下午8:25:20
     *
     */
    public void checkUsername() {
        try {
            String username = request.getParameter("username");
            String jsonList = registrationService.checkUsername(username);
            JsonResponse.write(jsonList, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 
     * checkUsername -  校验username是否重复
     * @return  void  
     * @Exception 异常对象
     * @author xiadecheng
     * @date 2017年10月27日 下午8:25:20
     *
     */
    public void checkUserCompany() {
        try {
            String company = request.getParameter("company");
            String jsonList = registrationService.checkUserCompany(company);
            JsonResponse.write(jsonList, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
