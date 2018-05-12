package com.novaone.utils;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/***

* 得到中文首字母

* @author lxm_09

*/

public class ChinaInitial {

/**

* @param args

*/

public static void main(String[] args) {

String str="Bl";

System.out.println("中文首字母："+getPYIndexStr(str,true));

}

	/**
	 * 返回汉字首字母
	 * @param str
	 * @param bUpCase
	 * @return
	 */
   public static String getPYIndexStr(String str, boolean bUpCase){
	   String convert = "";  
       for (int j = 0; j < str.length(); j++) {  
           char word = str.charAt(j);
           // 提取汉字的首字母  
           String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);  
           if (pinyinArray != null) {  
               convert += pinyinArray[0].charAt(0);  
           } else {  
               convert += word;  
           }  
       } 
       if(bUpCase){
          return convert.toUpperCase();  	
       }
        return convert;


   }
   /** 
    * 将汉字转换为全拼 
    *  
    * @param src 
    * @return String 
    */  
   public static String getPinYin(String src) {  
       char[] t1 = null;  
       t1 = src.toCharArray();  
       String[] t2 = new String[t1.length];  
       // 设置汉字拼音输出的格式  
       HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();  
       t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);  
       t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);  
       t3.setVCharType(HanyuPinyinVCharType.WITH_V);  
       String t4 = "";  
       int t0 = t1.length;  
       try {  
           for (int i = 0; i < t0; i++) {  
               // 判断是否为汉字字符  
               if (Character.toString(t1[i]).matches("[\\u4E00-\\u9FA5]+")) {  
                   t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);// 将汉字的几种全拼都存到t2数组中  
                   t4 += t2[0];// 取出该汉字全拼的第一种读音并连接到字符串t4后  
               } else {  
                   // 如果不是汉字字符，直接取出字符并连接到字符串t4后  
                   t4 += Character.toString(t1[i]);  
               }  
           }  
       } catch (BadHanyuPinyinOutputFormatCombination e) {  
           e.printStackTrace();  
       }  
       return t4;  
   }  
   /** 
    * 将字符串转换成ASCII码 
    * @param cnStr  源字符串
    * @return String 
    */  
   public static String getCnASCII(String cnStr) {  
       StringBuffer strBuf = new StringBuffer();  
       // 将字符串转换成字节序列  
       byte[] bGBK = cnStr.getBytes();  
       for (int i = 0; i < bGBK.length; i++) {  
           // System.out.println(Integer.toHexString(bGBK[i] & 0xff));  
           // 将每个字符转换成ASCII码  
           strBuf.append(Integer.toHexString(bGBK[i] & 0xff)+" ");  
       } 
       return strBuf.toString();  
   }  
 
}