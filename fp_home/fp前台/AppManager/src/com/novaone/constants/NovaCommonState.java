package com.novaone.constants;

import com.novaone.common.util.JSONProcessor;
import net.sf.json.JSONObject;

import java.util.HashMap;

/**
 * @Description: 常量类
 *
 * @Title: NovaProjects
 * @Package com.novaone.constants
 * @author <a href="mailto:yucaixing@novacloud.com">于采兴</a>
 * @version V2.0 2015-4-7 16:16 新建
 */
public class NovaCommonState {

    /**年月日时分钞format**/
    public static String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    /**年月日format**/
    public static String DATE_FORMAT = "yyyy-MM-dd";
    /**分隔符**/
    public static String PARM_PRE_FIX = ":";
    /**返回json要调用的key**/
    public static String STR_RETURN_JSON = "ajaxreturnjson";


    /**后缀名.xlsx*/
    public static final String UPLOAD_FILE_SUFFIX_XLSX = ".xlsx";
    /**后缀名.xls*/
    public static final String UPLOAD_FILE_SUFFIX_XLS = ".xls";
    /**后缀名.docx*/
    public static final String UPLOAD_FILE_SUFFIX_DOCX = ".docx";
    /**后缀名.doc*/
    public static final String UPLOAD_FILE_SUFFIX_DOC = ".doc";

    /**
     * data模型中定义的名称，此处需要与sys_data表中的名称一至<br>
     * (应用于为角色分配菜单，因为是多表操作，所以无法从data模型中获取此值，所以在此写死)<br>
     * 会根据此值到sys_data表中找出其sql语句，便于查询当前系统的所有菜单，在前台展现<br>
     */
    public static String  DATA_MOUDEL_ROLE_MENU= "RoleMenuDao";
    
    public static String  DATA_MOUDEL_USER_MENU= "UserSubMenuDao";

    /**生成json所对应的key**/
    public static String KEY_CODE = "code";
    /**生成json所对应的key**/
    public static String KEY_RESULT = "result";
    /**生成json所对应key的默认值**/
    public static String VALUE_DEFAULT = "000";
    /**生成json所对应key的用来通知有错误**/
    public static String VALUE_ERROR = "error";


    /**
     * 处理service的返回值
     * @param returnInfo 要处理的字符串
     * @return json串
     */
    public static HashMap<String,Object> createJsonResult(String returnInfo) {
        HashMap<String,Object> json = new HashMap<String,Object>();
        json.put(KEY_CODE, VALUE_DEFAULT);
        json.put(KEY_RESULT, returnInfo);
        return json;
    }

    /**
     * 处理service的返回值
     * @param resultMap 要处理的HashMap
     * @return json串
     */
    public static HashMap<String,Object> createJsonResult(HashMap<String, Object> resultMap) {
        HashMap<String,Object> json = new HashMap<String,Object>();
        json.put(KEY_CODE, VALUE_DEFAULT);
        json.put(KEY_RESULT, resultMap);
        return json;
    }

    /**
     * 处理service的返回值
     * @param resultJson 要处理的json串
     * @return json串
     */
    public static HashMap<String,Object> createJsonResult(JSONObject resultJson) {
        HashMap<String,Object> json = new HashMap<String,Object>();
        json.put(KEY_CODE, VALUE_DEFAULT);
        json.put(KEY_RESULT, resultJson);
        return json;
    }

    /**
     * 处理service的返回值
     * @param returnInfo 要处理的字符串
     * @return json串
     */
    public static String createJsonResultStr(String returnInfo) {
        return JSONProcessor.MapToStr(createJsonResult(returnInfo));
    }
    /**
     * 处理service的返回值
     * @param resultMap 要处理的HashMap
     * @return json串
     */
    public static String createJsonResultStr(HashMap<String, Object> resultMap) {
        return JSONProcessor.MapToStr(resultMap);
    }
    /**
     * 处理service的返回值
     * @param resultJson 要处理的json串
     * @return json串
     */
    public static String createJsonResultStr(JSONObject resultJson) {
        return JSONProcessor.MapToStr(createJsonResult(resultJson));
    }

    public static void main(String[] args) {
        String mm = "[{\"result\":{\"rowCount\":10,\"table\":{\"rows\":[{\"id\":\"1\",\"name\":\"id\",\"parentid\":\"1\",\"displayname\":\"id\",\"valuetype\":\"String\",\"issave\":\"Y\",\"inputhelptype\":\"none\",\"valuelength\":\"10\",\"decimalnum\":\"0\",\"isreadonly\":\"Y\",\"issum\":\"N\"},{\"id\":\"2\",\"name\":\"name\",\"parentid\":\"1\",\"displayname\":\"名称\",\"valuetype\":\"String\",\"issave\":\"Y\",\"inputhelptype\":\"input\",\"valuelength\":\"20\",\"decimalnum\":\"0\",\"isreadonly\":\"N\",\"issum\":\"N\"},{\"id\":\"239\",\"name\":\"idfieldname\",\"parentid\":\"1\",\"displayname\":\"id字段\",\"valuetype\":\"String\",\"issave\":\"Y\",\"inputhelptype\":null,\"valuelength\":\"40\",\"isreadonly\":\"N\",\"issum\":\"N\"},{\"id\":\"240\",\"name\":\"sysmodule\",\"parentid\":\"1\",\"displayname\":\"所属模块\",\"valuetype\":\"String\",\"issave\":\"Y\",\"inputhelptype\":\"list\",\"inputhelpname\":\"sys.model.moduletype\",\"valuelength\":\"40\",\"isreadonly\":\"N\",\"issum\":\"N\",\"datapurviewfactor\":\"module\"},{\"id\":\"3\",\"name\":\"description\",\"description\":\"\",\"parentid\":\"1\",\"displayname\":\"描述\",\"valuetype\":\"String\",\"issave\":\"Y\",\"inputhelptype\":\"input\",\"valuelength\":\"100\",\"decimalnum\":\"0\",\"isreadonly\":\"N\",\"issum\":\"N\"},{\"id\":\"4\",\"name\":\"dstype\",\"description\":\"\",\"parentid\":\"1\",\"displayname\":\"数据源类型\",\"valuetype\":\"String\",\"issave\":\"Y\",\"inputhelptype\":\"list\",\"inputhelpname\":\"sys.inputhelp.dstype\",\"valuelength\":\"20\",\"decimalnum\":\"0\",\"isreadonly\":\"N\",\"issum\":\"N\"},{\"id\":\"5\",\"name\":\"dsexp\",\"description\":\"\",\"parentid\":\"1\",\"displayname\":\"数据源表达式\",\"valuetype\":\"String\",\"issave\":\"Y\",\"inputhelptype\":\"input\",\"valuelength\":\"500\",\"decimalnum\":\"0\",\"isreadonly\":\"N\",\"issum\":\"N\"},{\"id\":\"6\",\"name\":\"savetype\",\"description\":\"\",\"parentid\":\"1\",\"displayname\":\"保存地类型\",\"valuetype\":\"String\",\"issave\":\"Y\",\"inputhelptype\":\"list\",\"inputhelpname\":\"sys.inputhelp.savetype\",\"valuelength\":\"20\",\"decimalnum\":\"0\",\"isreadonly\":\"N\",\"issum\":\"N\"},{\"id\":\"7\",\"name\":\"savedest\",\"description\":\"\",\"parentid\":\"1\",\"displayname\":\"保存地名称\",\"valuetype\":\"String\",\"issave\":\"Y\",\"inputhelptype\":\"input\",\"valuelength\":\"50\",\"decimalnum\":\"0\",\"isreadonly\":\"N\",\"issum\":\"N\"},{\"id\":\"8\",\"name\":\"isusing\",\"description\":\"\",\"parentid\":\"1\",\"displayname\":\"是否启用\",\"valuetype\":\"Boolean\",\"issave\":\"Y\",\"inputhelptype\":\"input\",\"valuelength\":\"1\",\"decimalnum\":\"0\",\"isreadonly\":\"N\",\"issum\":\"N\"}],\"fields\":[{\"name\":\"id\",\"valueType\":\"String\"},{\"name\":\"name\",\"valueType\":\"String\"},{\"name\":\"description\",\"valueType\":\"String\"},{\"name\":\"parentid\",\"valueType\":\"String\"},{\"name\":\"displayname\",\"valueType\":\"String\"},{\"name\":\"valuetype\",\"valueType\":\"String\"},{\"name\":\"issave\",\"valueType\":\"Boolean\"},{\"name\":\"inputhelptype\",\"valueType\":\"String\"},{\"name\":\"inputhelpname\",\"valueType\":\"String\"},{\"name\":\"foreignkeyname\",\"valueType\":\"String\"},{\"name\":\"valuelength\",\"valueType\":\"Decimal\"},{\"name\":\"decimalnum\",\"valueType\":\"Decimal\"},{\"name\":\"isreadonly\",\"valueType\":\"Boolean\"},{\"name\":\"issum\",\"valueType\":\"Boolean\"},{\"name\":\"datapurviewfactor\",\"valueType\":\"String\"}]}},\"code\":\"000\"}]";
        HashMap<String, Object> resultMap = createJsonResult(mm);
        System.out.println(createJsonResultStr(resultMap));
    }
}
