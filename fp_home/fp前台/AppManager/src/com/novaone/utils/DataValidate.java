package com.novaone.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataValidate {

	/**
	 * 验证邮箱
	 * 
	 * @param 待验证的字符串
	 * @return 如果是符合的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 */
	public static boolean isEmail(String str) {
		String regex = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
		return match(regex, str);
	}

	/**
	 * 验证IP地址
	 * 
	 * @param 待验证的字符串
	 * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 */
	public static boolean isIP(String str) {
		String num = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";
		String regex = "^" + num + "\\." + num + "\\." + num + "\\." + num
				+ "$";
		return match(regex, str);
	}

	/**
	 * 
	 * @Title: isUUID 
	 * @Description: 校验是否是uuid
	 * @param str
	 * @return
	 * @throws
	 */
	public static boolean isUUID(String str) {
		String regex = "^\\w{32}$";
		if(str==null) {
			return false;
		}else {
			return match(regex, str);
		}
	}
	
	/**
	 * 验证网址Url
	 * 
	 * @param 待验证的字符串
	 * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 */
	public static boolean IsUrl(String str) {
		String regex = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";
		return match(regex, str);
	}

	/**
	 * 验证电话号码
	 * 
	 * @param 待验证的字符串
	 * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 */
	public static boolean IsTelephone(String str) {
		String regex = "^(\\w-)/?\\d{6,8}$";
		return match(regex, str);
	}

	/**
	 * 验证输入密码条件(字符与数据同时出现)
	 * 
	 * @param 待验证的字符串
	 * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 */
	public static boolean IsPassword(String str) {
		String regex = "[A-Za-z]+[0-9]";
		return match(regex, str);
	}

	/**
	 * 验证输入密码长度 (6-18位)
	 * 
	 * @param 待验证的字符串
	 * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 */
	public static boolean IsPasswLength(String str) {
		String regex = "^\\d{6,18}$";
		return match(regex, str);
	}

	/**
	 * 验证输入邮政编号
	 * 
	 * @param 待验证的字符串
	 * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 */
	public static boolean IsPostalcode(String str) {
		String regex = "^\\d{6}$";
		return match(regex, str);
	}

	/**
	 * 验证输入手机号码
	 * 
	 * @param 待验证的字符串
	 * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 */
	public static boolean IsHandset(String str) {
		String regex = "^[1]+[3,5]+\\d{9}$";
		return match(regex, str);
	}

	/**
	 * 验证输入身份证号
	 * 
	 * @param 待验证的字符串
	 * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 */
	public static boolean IsIDcard(String str) {
		String regex = "(^\\d{18}$)|(^\\d{15}$)";
		return match(regex, str);
	}

	/**
	 * 验证输入两位小数
	 * 
	 * @param 待验证的字符串
	 * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 */
	public static boolean IsDecimal(String str) {
		String regex = "^[0-9]+(.[0-9]{2})?$";
		return match(regex, str);
	}

	/**
	 * 验证输入一年的12个月
	 * 
	 * @param 待验证的字符串
	 * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 */
	public static boolean IsMonth(String str) {
		String regex = "^(0?[[1-9]|1[0-2])$";
		return match(regex, str);
	}

	/**
	 * 验证输入一个月的31天
	 * 
	 * @param 待验证的字符串
	 * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 */
	public static boolean IsDay(String str) {
		String regex = "^((0?[1-9])|((1|2)[0-9])|30|31)$";
		return match(regex, str);
	}

	/**
	 * 验证日期时间
	 * 
	 * @param 待验证的字符串
	 * @return 如果是符合网址格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 */
	public static boolean isDate(String str) {
		// 严格验证时间格式的(匹配[2002-01-31], [1997-04-30],
		// [2004-01-01])不匹配([2002-01-32], [2003-02-29], [04-01-01])
		// String regex =
		// "^((((19|20)(([02468][048])|([13579][26]))-02-29))|((20[0-9][0-9])|(19[0-9][0-9]))-((((0[1-9])|(1[0-2]))-((0[1-9])|(1\\d)|(2[0-8])))|((((0[13578])|(1[02]))-31)|(((01,3-9])|(1[0-2]))-(29|30)))))$";
		// 没加时间验证的YYYY-MM-DD
		// String regex =
		// "^((((1[6-9]|[2-9]\\d)\\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\\d|3[01]))|(((1[6-9]|[2-9]\\d)\\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\\d|30))|(((1[6-9]|[2-9]\\d)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-))$";
		// 加了时间验证的YYYY-MM-DD 00:00:00
		String regex = "^((((1[6-9]|[2-9]\\d)\\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\\d|3[01]))|(((1[6-9]|[2-9]\\d)\\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\\d|30))|(((1[6-9]|[2-9]\\d)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-)) (20|21|22|23|[0-1]?\\d):[0-5]?\\d:[0-5]?\\d$";
		return match(regex, str);
	}

	/**
	 * 验证数字输入
	 * 
	 * @param 待验证的字符串
	 * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 */
	public static boolean IsNumber(String str) {
		String regex = "^[0-9]*$";
		return match(regex, str);
	}

	/**
	 * 验证非零的正整数
	 * 
	 * @param 待验证的字符串
	 * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 */
	public static boolean IsIntNumber(String str) {
		String regex = "^\\+?[1-9][0-9]*$";
		return match(regex, str);
	}

	/**
	 * 验证大写字母
	 * 
	 * @param 待验证的字符串
	 * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 */
	public static boolean IsUpChar(String str) {
		String regex = "^[A-Z]+$";
		return match(regex, str);
	}

	/**
	 * 验证小写字母
	 * 
	 * @param 待验证的字符串
	 * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 */
	public static boolean IsLowChar(String str) {
		String regex = "^[a-z]+$";
		return match(regex, str);
	}

	/**
	 * 验证输入字母
	 * 
	 * @param 待验证的字符串
	 * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 */
	public static boolean IsLetter(String str) {
		String regex = "^[A-Za-z]+$";
		return match(regex, str);
	}

	/**
	 * 验证输入汉字
	 * 
	 * @param 待验证的字符串
	 * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 */
	public static boolean IsChinese(String str) {
		String regex = "^[\u4e00-\u9fa5],{0,}$";
		return match(regex, str);
	}

	/**
	 * 验证输入字符串
	 * 
	 * @param 待验证的字符串
	 * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 */
	public static boolean IsLength(String str) {
		String regex = "^.{8,}$";
		return match(regex, str);
	}

	/**
	 * @param regex
	 *            正则表达式字符串
	 * @param str
	 *            要匹配的字符串
	 * @return 如果str 符合 regex的正则表达式格式,返回true, 否则返回 false;
	 */
	private static boolean match(String regex, String str) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}

	/**
	 * 是否是整数
	 * @param value
	 */
	public static boolean isIntege(String value){
		Matcher m=null;//操作符表达式
		boolean b=false;
		Pattern p = Pattern.compile("^-?[1-9]\\d*$");
		m=p.matcher(value);
		b=m.matches();
		return b;
	}
	
	/**
	 * 是否是正整数
	 * @param value
	 */
	public static boolean isIntege1(String value){
		
		Matcher m=null;//操作符表达式
		boolean b=false;
		Pattern p = Pattern.compile("^[1-9]\\d*$");
		m=p.matcher(value);
		b=m.matches();
		return b;
	}
	
	/**
	 * 是否是负整数
	 * @param value
	 */
	public static boolean isIntege2(String value){
		
		Matcher m=null;//操作符表达式
		boolean b=false;
		Pattern p = Pattern.compile("^-[1-9]\\d*$");
		m=p.matcher(value);
		b=m.matches();
		return b;
	}
	
	/**
	 * 是否是数字
	 * @param value
	 */
	public static boolean isNum(String value){
		
		Matcher m=null;//操作符表达式
		boolean b=false;
		Pattern p = Pattern.compile("^([+-]?)\\d*\\.?\\d+$");
		m=p.matcher(value);
		b=m.matches();
		return b;
	}
	
	/**
	 * 是否是正数（正整数 + 0）
	 * @param value
	 */
	public static boolean isNum1(String value){
		
		Matcher m=null;//操作符表达式
		boolean b=false;
		Pattern p = Pattern.compile("^[1-9]\\d*|0$");
		m=p.matcher(value);
		b=m.matches();
		return b;
	}
	
	/**
	 * 是否是负数（负整数 + 0）
	 * @param value
	 */
	public static boolean isNum2(String value){
		
		Matcher m=null;//操作符表达式
		boolean b=false;
		Pattern p = Pattern.compile("^-[1-9]\\d*|0$");
		m=p.matcher(value);
		b=m.matches();
		return b;
	}
	
	/**
	 * 是否是浮点数
	 * @param value
	 */
	public static boolean isDecmal(String value){
		
		Matcher m=null;//操作符表达式
		boolean b=false;
		Pattern p = Pattern.compile("^([+-]?)\\d*\\.\\d+$");
		m=p.matcher(value);
		b=m.matches();
		return b;
	}
	
	/**
	 * 是否是正浮点数
	 * @param value
	 */
	public static boolean isDecmal1(String value){
		
		Matcher m=null;//操作符表达式
		boolean b=false;
		Pattern p = Pattern.compile("^[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*$");
		m=p.matcher(value);
		b=m.matches();
		return b;
	}
	
	/**
	 * 是否是负浮点数
	 * @param value
	 */
	public static boolean isDecmal2(String value){
		
		Matcher m=null;//操作符表达式
		boolean b=false;
		Pattern p = Pattern.compile("^-([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*)$");
		m=p.matcher(value);
		b=m.matches();
		return b;
	}
	
	/**
	 * 是否是浮点数
	 * @param value
	 */
	public static boolean isDecmal3(String value){
		
		Matcher m=null;//操作符表达式
		boolean b=false;
		Pattern p = Pattern.compile("^-?([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*|0?.0+|0)$");
		m=p.matcher(value);
		b=m.matches();
		return b;
	}
	
	/**
	 * 是否是非负浮点数（正浮点数 + 0）
	 * @param value
	 */
	public static boolean isDecmal4(String value){
		
		Matcher m=null;//操作符表达式
		boolean b=false;
		Pattern p = Pattern.compile("^[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*|0?.0+|0$");
		m=p.matcher(value);
		b=m.matches();
		return b;
	}
	
	/**
	 * 是否是非正浮点数（负浮点数 + 0）
	 * @param value
	 */
	public static boolean isDecmal5(String value){
		
		Matcher m=null;//操作符表达式
		boolean b=false;
		Pattern p = Pattern.compile("^(-([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*))|0?.0+|0$");
		m=p.matcher(value);
		b=m.matches();
		return b;
	}
	
	/**
	 * 是否是颜色
	 * @param value
	 */
	public static boolean isColor(String value){
		
		Matcher m=null;//操作符表达式
		boolean b=false;
		Pattern p = Pattern.compile("^[a-fA-F0-9]{6}$");
		m=p.matcher(value);
		b=m.matches();
		return b;
	}
	
	/**
	 * 是否是url
	 * @param value
	 */
	public static boolean isUrl(String value){
		
		Matcher m=null;//操作符表达式
		boolean b=false;
		Pattern p = Pattern.compile("^http[s]?:\\/\\/([\\w-]+\\.)+[\\w-]+([\\w-./?%&=]*)?$");
		m=p.matcher(value);
		b=m.matches();
		return b;
	}
	
	/**
	 * 是否是中文
	 * @param value
	 */
	public static boolean isChinese(String value){
		
		Matcher m=null;//操作符表达式
		boolean b=false;
		Pattern p = Pattern.compile("^[\\u4E00-\\u9FA5\\uF900-\\uFA2D]+$");
		m=p.matcher(value);
		b=m.matches();
		return b;
	}
	
	/**
	 * 是否是ACSII字符
	 * @param value
	 */
	public static boolean isAscii(String value){
		
		Matcher m=null;//操作符表达式
		boolean b=false;
		Pattern p = Pattern.compile("^[\\x00-\\xFF]+$");
		m=p.matcher(value);
		b=m.matches();
		return b;
	}
	
	/**
	 * 是否是邮编
	 * @param value
	 */
	public static boolean isZipcode(String value){
		
		Matcher m=null;//操作符表达式
		boolean b=false;
		Pattern p = Pattern.compile("^\\d{6}$");
		m=p.matcher(value);
		b=m.matches();
		return b;
	}
	
	/**
	 * 是否是手机
	 * @param value
	 */
	public static boolean isMobile(String value){
		
		Matcher m=null;//操作符表达式
		boolean b=false;
		Pattern p = Pattern.compile("^(13|15)[0-9]{9}$");
		m=p.matcher(value);
		b=m.matches();
		return b;
	}
	
	/**
	 * 是否是ip地址
	 * @param value
	 */
	public static boolean isIp(String value){
		
		Matcher m=null;//操作符表达式
		boolean b=false;
		Pattern p = Pattern.compile("^(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)$");
		m=p.matcher(value);
		b=m.matches();
		return b;
	}
	
	/**
	 * 是否是非空
	 * @param value
	 */
	public static boolean isNotempty(String value){
		
		Matcher m=null;//操作符表达式
		boolean b=false;
		Pattern p = Pattern.compile("^\\S+$");
		m=p.matcher(value);
		b=m.matches();
		return b;
	}
	
	/**
	 * 是否是图片
	 * @param value
	 */
	public static boolean isPicture(String value){
		
		Matcher m=null;//操作符表达式
		boolean b=false;
		Pattern p = Pattern.compile("(.*)\\.(jpg|bmp|gif|ico|pcx|jpeg|tif|png|raw|tga)$");
		m=p.matcher(value);
		b=m.matches();
		return b;
	}
	
	/**
	 * 是否是压缩文件
	 * @param value
	 */
	public static boolean isRar(String value){
		
		Matcher m=null;//操作符表达式
		boolean b=false;
		Pattern p = Pattern.compile("(.*)\\.(rar|zip|7zip|tgz)$");
		m=p.matcher(value);
		b=m.matches();
		return b;
	}
	
	
	/**
	 * 是否是QQ号码
	 * @param value
	 */
	public static boolean isQq(String value){
		
		Matcher m=null;//操作符表达式
		boolean b=false;
		Pattern p = Pattern.compile("^[1-9]*[1-9][0-9]*$");
		m=p.matcher(value);
		b=m.matches();
		return b;
	}
	
	/**
	 * 是否是电话号码的函数(包括验证国内区号,国际区号,分机号)
	 * @param value
	 */
	public static boolean isTel(String value){
		
		Matcher m=null;//操作符表达式
		boolean b=false;
		Pattern p = Pattern.compile("^(([0\\+]\\d{2,3}-)?(0\\d{2,3})-)?(\\d{7,8})(-(\\d{3,}))?$");
		m=p.matcher(value);
		b=m.matches();
		return b;
	}
	
	/**
	 * 用来用户注册。匹配由数字、26个英文字母或者下划线组成的字符串
	 * @param value
	 */
	public static boolean isUsername(String value){
		
		Matcher m=null;//操作符表达式
		boolean b=false;
		Pattern p = Pattern.compile("^\\w+$");
		m=p.matcher(value);
		b=m.matches();
		return b;
	}
	
	/**
	 * 是否是字母
	 * @param value
	 */
	public static boolean isLetter(String value){
		
		Matcher m=null;//操作符表达式
		boolean b=false;
		Pattern p = Pattern.compile("^[A-Za-z]+$");
		m=p.matcher(value);
		b=m.matches();
		return b;
	}
	
	/**
	 * 是否是大写字母
	 * @param value
	 */
	public static boolean isLetter_u(String value){
		
		Matcher m=null;//操作符表达式
		boolean b=false;
		Pattern p = Pattern.compile("^[A-Z]+$");
		m=p.matcher(value);
		b=m.matches();
		return b;
	}
	
	/**
	 * 是否是大写字母
	 * @param value
	 */
	public static boolean isLetter_l(String value){
		
		Matcher m=null;//操作符表达式
		boolean b=false;
		Pattern p = Pattern.compile("^[a-z]+$");
		m=p.matcher(value);
		b=m.matches();
		return b;
	}
	
	/**
	 * 是否是价格
	 * @param value
	 */
	public static boolean isPrice(String value){
		
		Matcher m=null;//操作符表达式
		boolean b=false;
		Pattern p = Pattern.compile("^([1-9]{1}[0-9]{0,}(\\.[0-9]{0,2})?|0(\\.[0-9]{0,2})?|\\.[0-9]{1,2})$");
		m=p.matcher(value);
		b=m.matches();
		return b;
	}
	
}