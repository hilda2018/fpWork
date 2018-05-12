package com.fresh.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;

import com.nova.frame.utils.IOUtils;
import com.nova.frame.utils.JdbcUtils;
import com.nova.frame.utils.JsonUtils;
/**
 * dao相关公共函数类
 * @author chenjing
 * @date 2013-9-23
 */
public class DaoUtil{
	/**
	 * 生成自增主键
	 * @param seqName：数据库中建的序列名称
	 * 譬如：FCT_MEMBER表，对应的主键序列为SEQ_MEMBER,则传入参数为：xjjdw.SEQ_MEMBER
	 * @return:唯一自增主键值
	 */
	public static int getSeqAutomaticIndex(String seqName){
		String sql = "select "+seqName+".Nextval from dual";
		List<Integer> result = JdbcUtils.query(Integer.class, sql);
		if(result.size()==0){
			return 0;
		}else {
			return Integer.parseInt(""+(result.get(0)));
		}
	}
	/**
	 * blog字段转为string
	 * @param b
	 * @return
	 * @throws Exception
	 */
	public static String blobToString(Blob b) throws Exception{
		if(b==null){
			return "";
		}
		String blobString = new String(b.getBytes(1, (int) b.length()),"GBK");
		return blobString;
	}
	/**
	 * String 转为blob字段
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static Blob stringToBlob(String str)throws Exception{
		if(str==null){
			str = "";
		}
		Blob b = new SerialBlob(str.getBytes("GBK"));
		return b;
	}

	/**
	 * 保存信息到json
	 * @param areaList 地址清单
	 * @param path 保存路径
	 * @return
	 */
	public static <T> boolean saveDataToFile(List<T> list,String path) throws Exception{
		try{
			IOUtils.saveTo(path, new ByteArrayInputStream(JsonUtils.encode(list).getBytes()));
			return true;
		}catch(IOException e){
			return false;
		}
		
	}
}
 