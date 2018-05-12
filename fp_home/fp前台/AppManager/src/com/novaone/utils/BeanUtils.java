package com.novaone.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.log4j.Logger;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;


/**
 * 
 * BeanUtils工具类
 * @类编号:
 * @类名称:BeanUtils
 * @内容摘要: //说明主要功能。
 * @author:陈自军
 * @创建日期:2016年8月11日 下午5:14:48
 * @修改人:
 * @修改日期:
 * @修改描述:简单描述修改的内容
 * @version 1.0.0
 *
 */
public abstract class BeanUtils
{
    
    /**
     * convert Utils Bean
     */
    public static final ConvertUtilsBean CONVERTUTILSBEAN = new ConvertUtilsBean();
    
    private static Logger logger = Logger.getLogger(BeanUtils.class);
    
    private static BeanUtilsBean beanUtilsBean = new BeanUtilsBean(CONVERTUTILSBEAN, new PropertyUtilsBean());
    
    private static  Splitter COMMA_SPLITTER = null;

    
    /** 
     * describe
     * @param bean bean
     * @return Map
     * @see [类、类#方法、类#成员]
     */
    @SuppressWarnings("rawtypes")
    public static Map describe(Object bean)
    {
        try
        {
            return beanUtilsBean.describe(bean);
        }
        catch (IllegalAccessException e)
        {
            handleReflectionException(e);
        }
        catch (InvocationTargetException e)
        {
            handleReflectionException(e);
        }
        catch (NoSuchMethodException e)
        {
            handleReflectionException(e);
        }
        catch (RuntimeException e)
        {
            handleReflectionException(e);
        }
        return null;
    }
    
    /**
     * 判断参数对象是否为空
     * 
     * @param o
     *            需要判断的对象
     * @return boolean true:对象为空 false:对象不为空
     */
    public static boolean isEmpty(Object o)
    {
        if (o == null)
        {
            return true;
        }
        if (o instanceof String)
        {
            if (((String)o).trim().length() == 0)
            {
                return true;
            }
        }
        else if (o instanceof Collection)
        {
            if (((Collection)o).isEmpty())
            {
                return true;
            }
        }
        else if (o.getClass().isArray())
        {
            if (java.lang.reflect.Array.getLength(o)==0)
            {
                return true;
            }
        }
        else if (o instanceof Map)
        {
            if (((Map)o).isEmpty())
            {
                return true;
            }
        }
        return false;
    }
    /**
     * 判断参数对象是否不为空
     * 
     * @param o
     *            需要判断的对象
     * @return boolean true:对象不为空 false:对象为空
     */
    public static boolean isNotEmpty(Object o){
    	return !isEmpty(o);
    }
    
    /**
     * handle Reflection Exception
     * 
     * @param e exception
     */
    private static void handleReflectionException(Exception e)
    {
        // ReflectionUtils.handleReflectionException(e);
      //  logger.error(RPTLogFinals.RPT_CODE, RPTLogFinals.RPT_FUNC_400, "00001", "handleReflectionException Fail.", e);
        logger.error("handleReflectionException Fail.", e);
    }
    
    /**
     * 判断输入参数是否可以转换为数字
     * 
     * 
     * @param o
     *            输入参数
     * @return boolean true:是数字 false:不是数字
     */
    public static boolean isNumber(Object o)
    {
        if (o == null)
        {
            return false;
        }
        if (o instanceof Number)
        {
            return true;
        }
        if (o instanceof String)
        {
            try
            {
                Double.parseDouble((String)o);
                return true;
            }
            catch (NumberFormatException e)
            {
                return false;
            }
        }
        return false;
    }
    /**
     * 把逗号分隔的字符id转换为sql中in查询条件
     * 01,02,03-->'01,'02','03'
     * @方法名称:getIds
     * @内容摘要: 把逗号分隔的字符id转换为sql中in查询条件
     * @param id
     * @param splitRex
     * @return 
     * String '01,'02','03'
     * @exception 
     * @author:陈自军
     * @创建日期:2016年8月2日-下午1:44:45
     */
	public static String getIdsAsString(String id,String splitRex){
		List<String> ids = getIdsAsList(id, splitRex);
		String idsJoin = Joiner.on("','").join(ids);
		return "".equals(idsJoin) ? null:"'"+idsJoin+"'";
	}
	/**
	 * 把逗号分隔的字符id转换为list集合
	 * @方法名称:getIdsAsList
	 * @内容摘要: ＜列出主要功能、调用说明、异常说明、业务逻辑等＞
	 * @param id
	 * @param splitRex
	 * @return 
	 * List<String>
	 * @exception 
	 * @author:陈自军
	 * @创建日期:2016年8月16日-下午5:31:07
	 */
    public static List<String> getIdsAsList(String id,String splitRex){
    	COMMA_SPLITTER = Splitter.on(splitRex)
			       .trimResults()//去掉结果前后两端空格
			       .omitEmptyStrings();//去掉结果中空值
		List<String> ids = COMMA_SPLITTER.splitToList(id);
		return ids;
    }
    public static void main(String[] args) {
		String id="1111,22";
		System.out.println(BeanUtils.getIdsAsString(id, ","));
	}
}
