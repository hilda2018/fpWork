package com.novaone.dao.system;

import com.nova.frame.utils.JdbcUtils;
import com.novaone.common.NovaSession;
import com.novaone.dao.db.impl.BaseDataDaoImpl;
import com.novaone.model.system.Org;
import net.sf.json.JSONObject;

import java.util.List;

/**
 * 部门操作dao实现层
 *
 * @author <a href="mailto:yucaixing@novacloud.com">于采兴</a>
 * @version V2.0 2015-05-08 16:50 新建
 * @Title: Novaone
 * @Package com.novaone.dao.system
 */
public class OrgDaoImpl extends BaseDataDaoImpl implements OrgDao {
    @Override
    protected void beforeSave(NovaSession session, JSONObject requestObj) throws Exception{
        //判断当前是插入操作还是修改操作
        JSONObject insertRowsObj = requestObj.getJSONObject("insert");
        JSONObject updateRowsObj = requestObj.getJSONObject("update");

        //如果是插入操作操作
        int insertRowCount = insertRowsObj.size();
        Object[] insertRowIds = insertRowsObj.keySet().toArray();
        for(int i = 0;i < insertRowCount; i++){
            String insertRowId = (String)insertRowIds[i];
            JSONObject insertRowObj = insertRowsObj.getJSONObject(insertRowId);
            insertRowObj.put("code", this.autoCreatOrgCode(insertRowObj));
            insertRowObj.put("sortindex", this.autoSortIndex(insertRowObj));
        }

        //如果是修改操作
        int updateRowCount = updateRowsObj.size();
        Object[] updateRowIds = updateRowsObj.keySet().toArray();
        for (int i = 0; i < updateRowCount; i++) {
            String updateRowId = (String) updateRowIds[i];
            JSONObject updateRowObj = updateRowsObj.getJSONObject(updateRowId);
            updateRowObj.put("code", this.autoCreatOrgCode(updateRowObj));
            updateRowObj.put("sortindex", this.autoSortIndex(updateRowObj));
        }
    }

    /**
     * 自动生成部门编码
     * @param insertOrUpdateRowObj 前台传入的参数
     * @return
     */
    private String autoCreatOrgCode(JSONObject insertOrUpdateRowObj){
        Object obj = null;
        //父id
        String superiordeptId = null;
        //编码
        String code = null;
        //编码
        obj =  insertOrUpdateRowObj.get("code");
        if(null != obj){
            code = obj.toString();
        }

        if(null != obj && !"".equals(code) && code.length() > 1){
            return code;
        }

        //获得父id
        obj = insertOrUpdateRowObj.get("parentid");
        if(null != obj){
            superiordeptId = obj.toString();
        }
        //生成编码
        return this.creatDepLayerCode(superiordeptId);
    }

    /**
     * 自动生成排序
     * @param insertOrUpdateRowObj 前台传入的参数
     * @return
     */
    private String autoSortIndex(JSONObject insertOrUpdateRowObj){
        Object obj = insertOrUpdateRowObj.get("sortindex");
        //自定义排序
        String sortindex = null;
        //父id
        String superiordeptId = null;

        if(null != obj){
            sortindex = obj.toString();
        }

        if(null != obj && !"".equals(sortindex) && sortindex.length() > 1){
            return sortindex;
        }

        //获得父id
        obj = insertOrUpdateRowObj.get("parentid");
        if(null != obj){
            superiordeptId = obj.toString();
        }

        //系统生成排序数字
        return this.creatSortIndex(superiordeptId);
    }

    /**
     * 根据父部门id获得最新的编码
     * @param superiordeptId 父部门id
     * @return 最新的编码
     */
    private synchronized String creatDepLayerCode(String superiordeptId){
        //如果添加是根节点
        String sql = "select max(code) from d_org where parentid is null";
        String layerCode = "";
        int size = 0;
        String max = "";

        //如果添加是根节点
        if(null == superiordeptId || "".equals(superiordeptId)){
            //size = JdbcUtils.count(sql).intValue();
            max = JdbcUtils.get(String.class, sql);
            max = null == max || "".equals(max) ? "0" : max.substring(max.length() - 3, max.length());
            size = Integer.parseInt(max);
            size = size+1>999?size:size+1;

            if(size < 10){
                layerCode += "00";
            }else if(size > 10 && size <100){
                layerCode += "0";
            }
            layerCode += size;
            return layerCode;
        }

        //获得父部门的部门编码
        sql = "select code  from d_org where id=?";
        layerCode = JdbcUtils.get(String.class, sql, superiordeptId);

        //获得父部门下的所有子部门个数
        sql = "select max(code) from d_org where id<>1 and parentid=?";
        max = JdbcUtils.get(String.class, sql, superiordeptId);
        max = null == max || "".equals(max) ? "0" : max.substring(max.length() - 3, max.length());
        size = Integer.parseInt(max);
        size = size+1>999?size:size+1;
        if(size < 10){
            layerCode += "00";
        }else if(size > 10 && size <100){
            layerCode += "0";
        }
        layerCode += size;
        return layerCode;
    }

    /**
     * 获得系统自动排序数字
     * @param superiordeptId
     * @return
     */
    private synchronized String creatSortIndex(String superiordeptId){
        String max_sort_idsql = "select max(sortindex) as num from d_org where parentid is null";
        Integer num = 0;
        String n = "0";
        //如果添加是根节点
        if(null == superiordeptId || "".equals(superiordeptId)){
            n = JdbcUtils.get(String.class, max_sort_idsql);
            num = n == null || "".equals(n)?0:Integer.parseInt(n);
            num += 1;
            return String.valueOf(num);
        }

        //添加的非根节点
        max_sort_idsql = "select max(sortindex) as num from d_org where parentid=?";
        n = JdbcUtils.get(String.class, max_sort_idsql, superiordeptId);
        num = n == null || "".equals(n)?0:Integer.parseInt(n);
        num += 1;
        return String.valueOf(num);
    }

    @Override
    public String getUserAllOrg(String orgId) {
        String orgIds = this.getData(orgId, orgId);
        return orgIds;
    }

    /**
     * 递归查询当前部门下的所有子部门id
     * @param orgFId 父id
     * @param orgSId 已查询出的部门id
     * @return
     */
    private String getData(String orgFId, String orgSId){
        String tempSql = "";
        orgSId = orgFId.equals(orgSId)?orgSId:orgFId + "," + orgSId;
        String ids = null == orgFId ? "" : "'" + orgFId.toString().replaceAll(",", "','")+"'";
        String sql = "select * from d_org d where d.parentid in("+ids+")";

        List<Org> list = JdbcUtils.query(Org.class, sql);
        if(list.isEmpty()){
           return orgSId;
        }
        for(Org o:list){
            tempSql += o.getId() + ",";
        }
        tempSql = tempSql.substring(0, tempSql.length()-1);

        return this.getData(tempSql, orgSId);
    }
}
