package com.novaone.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import org.apache.commons.lang3.StringUtils;


public class OcrCommonInfo {

	public static final String OCR_MAIN_STATUS_NEW = "0";
	public static final String OCR_MAIN_STATUS_ARTIFICIAL = "1";
	public static final String OCR_MAIN_STATUS_PASS = "2";
	public static final String OCR_MAIN_STATUS_NOPASS = "3";
	
	public static final String OCR_BILL_STATUS_NEW = "0";//未上传
	public static final String OCR_BILL_STATUS_IDENTIFICATION = "1";//识别中
	public static final String OCR_BILL_STATUS_IDENTIFICATIONERROR = "2";//识别失败
	public static final String OCR_BILL_STATUS_HUMANSINTERPRET = "3";//人工解析
	public static final String OCR_BILL_STATUS_ADJUSTMENT = "4";//待调整
	public static final String OCR_BILL_STATUS_ADJUSTMENTING= "5";//人工调整中
	public static final String OCR_BILL_STATUS_ADJUSTMENTEND = "6";//调整结束
	public static final String OCR_BILL_STATUS_FILEDERROR = "7";//字段校验不通过
	public static final String OCR_BILL_STATUS_FILEDPASS = "8";//字段校验通过
	
	public static final String OCR_BILLTYPE_INVOICE = "0";
	public static final String OCR_BILLTYPE_PACKINGLIST = "1";
	public static final String OCR_BILLTYPE_SHIP = "2";
	public static final String OCR_BILLTYPE_AIR = "3";
	public static final String OCR_BILLTYPE_ORIGINAL = "4";
	public static final String OCR_BILLTYPE_COLD = "5";
	public static final String OCR_BILLTYPE_PLANT = "6";
	
	
	public static final String OCRFTPADDR = "120.26.85.21";
	public static final int OCRFTPPORT = 21;
	public static final String OCRFTPUSER = "ocrftp";
	public static final String OCRFTPPSWD = "Nova123456";
	public static final String OCRPOJNAME = "ocr";
	
	
	public static String returnBilltypeName(String billtype) {
		if(StringUtils.isEmpty(billtype)) {
			return null;
		}else if(billtype.equals(OCR_BILLTYPE_INVOICE)) {
			return "发票";
		}else if(billtype.equals(OCR_BILLTYPE_PACKINGLIST)) {
			return "箱单";
		}else if(billtype.equals(OCR_BILLTYPE_SHIP)) {
			return "海运提单";
		}else if(billtype.equals(OCR_BILLTYPE_AIR)) {
			return "空运提单";
		}else if(billtype.equals(OCR_BILLTYPE_ORIGINAL)) {
			return "原产证";
		}else if(billtype.equals(OCR_BILLTYPE_COLD)) {
			return "冷处理记录";
		}else if(billtype.equals(OCR_BILLTYPE_PLANT)) {
			return "植检证";
		}
		
		return null;
	}
	
	@SuppressWarnings({ "rawtypes"})
	public static List copy(List src)  throws IOException, ClassNotFoundException{
	    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();             
	    ObjectOutputStream out = new ObjectOutputStream(byteOut);             
	    out.writeObject(src);                    
	    ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());             
	    ObjectInputStream in =new ObjectInputStream(byteIn);             
	    List dest = (List) in.readObject();   
	    return dest;
    }
}
