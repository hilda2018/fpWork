package com.novaone.utils.img;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigUtil {

    /**开启兼容模式**/
	private static boolean isCompatible;
	//缩略图大小
	private static int smallSize;
	//中图大小
	private static int middleSize;
	private static boolean isAddWater4Member;
	private static boolean isAddWater4Product;
	private static boolean isAddWater4Shop;
	private static boolean isAddWater4ShopHonor;
	private static boolean isAddWater4Brand;
	private static boolean isAddWater4News;
	private static boolean isAddWater4Logistics;
	private static boolean isAddWater4About;
	//富文本attached
	private static boolean isAddWater4Attached;
	
	static{
		Properties prop = new Properties();
		InputStream is = ImageHepler.class.getResourceAsStream("/imgConfig.properties");
		try {
			prop.load(is);
			isCompatible = Boolean.parseBoolean(prop.getProperty("isCompatible").trim());
			smallSize = Integer.parseInt(prop.getProperty("smallSize").trim());
			middleSize = Integer.parseInt(prop.getProperty("middleSize").trim());
			isAddWater4Member = Boolean.parseBoolean(prop.getProperty("isAddWater4Member").trim());
			isAddWater4Product = Boolean.parseBoolean(prop.getProperty("isAddWater4Product").trim());
			isAddWater4Shop = Boolean.parseBoolean(prop.getProperty("isAddWater4Shop").trim());
			isAddWater4ShopHonor = Boolean.parseBoolean(prop.getProperty("isAddWater4ShopHonor").trim());
			isAddWater4Brand = Boolean.parseBoolean(prop.getProperty("isAddWater4Brand").trim());
			isAddWater4News = Boolean.parseBoolean(prop.getProperty("isAddWater4News").trim());
			isAddWater4Logistics = Boolean.parseBoolean(prop.getProperty("isAddWater4Logistics").trim());
			isAddWater4About = Boolean.parseBoolean(prop.getProperty("isAddWater4About").trim());
			isAddWater4Attached = Boolean.parseBoolean(prop.getProperty("isAddWater4Attached").trim());
		} catch (IOException e) {
		    isCompatible = true;
		    smallSize = 120;
		    middleSize = 360;
			isAddWater4Member = false;
			isAddWater4Product = false;
			isAddWater4Shop = false;
			isAddWater4ShopHonor = false;
			isAddWater4Brand = false;
			isAddWater4News = false;
			isAddWater4Logistics = false;
			isAddWater4About = false;
			isAddWater4Attached = false;
			e.printStackTrace();
		}
	}
	
	public static int getSmallSize() {
        return smallSize;
    }
	public static int getMiddleSize() {
	    return middleSize;
	}
	
	public static boolean isCompatible() {
		return isCompatible;
	}
	public static boolean isAddWater4Member() {
	    return isAddWater4Member;
	}
	public static boolean isAddWater4Product() {
		return isAddWater4Product;
	}
	public static boolean isAddWater4Shop() {
		return isAddWater4Shop;
	}
	public static boolean isAddWater4ShopHonor() {
		return isAddWater4ShopHonor;
	}
	public static boolean isAddWater4Brand() {
		return isAddWater4Brand;
	}
	public static boolean isAddWater4News() {
		return isAddWater4News;
	}
	public static boolean isAddWater4Logistics() {
		return isAddWater4Logistics;
	}
	public static boolean isAddWater4About() {
		return isAddWater4About;
	}
	public static boolean isAddWater4Attached() {
		return isAddWater4Attached;
	}
	
}
