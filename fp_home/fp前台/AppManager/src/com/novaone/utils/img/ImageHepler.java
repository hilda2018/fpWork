package com.novaone.utils.img;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import javax.imageio.ImageIO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



/**
 * 图片裁剪工具类
 * @author chengql
 * @date 2013-11-5
 */
public class ImageHepler {
	private static Log logger = LogFactory.getLog(ImageHepler.class);

	
	/**  
     * 实现图像的等比缩放  
     * @param source  
     * @param targetW  
     * @param targetH  
     * @return  
     */  
    private static BufferedImage resize(BufferedImage source, int targetW,int targetH) {   
        // targetW，targetH分别表示目标长和宽   
        int type = source.getType();   
        BufferedImage target = null;   
        double sx = (double) targetW / source.getWidth();   
        double sy = (double) targetH / source.getHeight();   
        // 这里想实现在targetW，targetH范围内实现等比缩放。如果不需要等比缩放   
        // 则将下面的if else语句注释即可   
        if (sx < sy) {   
            sx = sy;   
            targetW = (int) (sx * source.getWidth());   
        } else {   
            sy = sx;   
            targetH = (int) (sy * source.getHeight());   
        }   
        if (type == BufferedImage.TYPE_CUSTOM) { // handmade   
            ColorModel cm = source.getColorModel();   
            WritableRaster raster = cm.createCompatibleWritableRaster(targetW,targetH);   
            boolean alphaPremultiplied = cm.isAlphaPremultiplied();   
            target = new BufferedImage(cm, raster, alphaPremultiplied, null);   
        } else  
            target = new BufferedImage(targetW, targetH, type);   
        Graphics2D g = target.createGraphics();   
        // smoother than exlax:   
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BICUBIC);   
        g.drawRenderedImage(source, AffineTransform.getScaleInstance(sx, sy));   
        g.dispose();   
        return target;   
    }   
  
    /**  
     * 实现图像的等比缩放和缩放后的截取  
     * @param inFilePath 要截取文件的路径  
     * @param outFilePath 截取后输出的路径  
     * @param width 要截取宽度  
     * @param hight 要截取的高度  
     * @param proportion  
     * @throws Exception  
     */  
       
    public static void saveImageAsJpg(InputStream in, String outFilePath,String imgName,int width, int hight, boolean proportion)throws Exception {   
    	 File saveFile = new File(outFilePath+imgName);
    	 if(!saveFile.exists()){
    		 saveFile.mkdirs();
    	 }
         BufferedImage srcImage = ImageIO.read(in);   
         if (width > 0 || hight > 0){   
            // 原图的大小   
            int sw = srcImage.getWidth();   
            int sh = srcImage.getHeight();   
            // 如果原图像的大小小于要缩放的图像大小，直接将要缩放的图像复制过去   
            if (sw > width && sh > hight) {   
                srcImage = resize(srcImage, width, hight);   
            } else {   
                String fileName = saveFile.getName();   
                String formatName = fileName.substring(fileName.lastIndexOf('.') + 1);   
                ImageIO.write(srcImage, formatName, saveFile);   
                return;   
            }   
        }   
        // 缩放后的图像的宽和高   
        int w = srcImage.getWidth();   
        int h = srcImage.getHeight();   
        // 如果缩放后的图像和要求的图像宽度一样，就对缩放的图像的高度进行截取   
        if(w == width){   
            // 计算X轴坐标   
            int x = 0;   
            int y = h / 2 - hight / 2;   
            saveSubImage(srcImage, new Rectangle(x, y, width, hight), saveFile);   
        }   
        // 否则如果是缩放后的图像的高度和要求的图像高度一样，就对缩放后的图像的宽度进行截取   
        else if (h == hight){   
            // 计算X轴坐标   
            int x = w / 2 - width / 2;   
            int y = 0;   
            saveSubImage(srcImage, new Rectangle(x, y, width, hight), saveFile);   
        }   
        in.close();   
    }   
    /**  
     * 实现缩放后的截图  
     * @param image 缩放后的图像  
     * @param subImageBounds 要截取的子图的范围  
     * @param subImageFile 要保存的文件  
     * @throws IOException  
     */  
    private static void saveSubImage(BufferedImage image,  Rectangle subImageBounds, File subImageFile) throws IOException {   
        if (subImageBounds.x < 0 || subImageBounds.y < 0  
                || subImageBounds.width - subImageBounds.x > image.getWidth()   
                || subImageBounds.height - subImageBounds.y > image.getHeight()) {   
            System.out.println("Bad   subimage   bounds");   
            return;   
        }   
        BufferedImage subImage = image.getSubimage(subImageBounds.x,subImageBounds.y, subImageBounds.width, subImageBounds.height);   
        String fileName = subImageFile.getName();   
        String formatName = fileName.substring(fileName.lastIndexOf('.') + 1);   
        ImageIO.write(subImage, formatName, subImageFile);   
    }   
    
    /**
     * 删除图片
     * @param outFilePath 图片存放路径
     * @param imageName 要删除的文件名
     * @return
     */
    public static String deleteImages(String outFilePath,String imageName){
    	try {
			String fileName = imageName;
			if (imageName.indexOf("_big") != -1
					|| imageName.indexOf("_small") != -1
					|| imageName.indexOf("_middle") != -1) {
				fileName = imageName.substring(0, imageName.indexOf("_big"));
			}
			File imageDir = new File(outFilePath);
			File[] imageFiles = imageDir.listFiles();
			for (File file : imageFiles) {
				if (file.getName().equals(fileName + "_big.jpg")) {
					file.delete();
				} else if (file.getName().equals(fileName + "_small.jpg")) {
					file.delete();
				} else if (file.getName().equals(fileName + "_middle.jpg")) {
					file.delete();
				}
			}
			return "true";
		} catch (Exception e) {
			return "false";
		}
    }
    
    /**
     * 生成图片的逻辑
     * @param in
     * @param outFilePath 存放图片的目录
     * @param imgName 图片名称
     * @throws Exception
     */
    public static void generateImges(InputStream in, String outFilePath,String imgName, boolean isAddWater) throws Exception{
        //如果开启兼容模式
        if(ConfigUtil.isCompatible()){
            //如果只生成源图和缩略图
            generateImgesForThreeSize(in, outFilePath, imgName);
        }else{
            /**把上传的图片复制到服务器上**/
            File tempFile = updateFile(in, System.getProperty("user.home"), "temp.jpg");
            
            /**分别生成三个不同大小的图片**/
            generateImgesForThreeSize(System.getProperty("user.home"), "/temp.jpg", outFilePath, imgName, isAddWater);
            
            /**删除临时文件**/
            tempFile.delete();
        }

    }
    
    /**
     * 将浏览器的图片上传到服务器的指定目录下，并重新命名
     * @param in                输入流
     * @param path              服务器的指定目录
     * @param resultImageName   文件名
     * @throws Exception
     */
    private static File updateFile(InputStream in, String path, String resultImageName) throws Exception{
        /**把上传的图片复制到服务器上**/
        File tempFile = new File(path + "/" + resultImageName);
        OutputStream os = new FileOutputStream(tempFile);
        int bytesRead = 0;
        byte[] buffer = new byte[8192];
        while ((bytesRead = in.read(buffer, 0, 8192)) != -1) {
            os.write(buffer, 0, bytesRead);
        }
        os.close();
        in.close();
        return tempFile;
    }
    
    /**
     * 生成大图和缩略图
     * @param in 输入流
     * @param outFilePath 生成后图片的名称的路径
     * @param resultImageName 生成后图片的名称
     * @throws Exception
     */
    public static void generateImgesForThreeSize(InputStream in, String outFilePath, String resultImageName) throws Exception{
        /**验证当前的生成图片的输出路径是否存在，若不存在目录则进行生成**/
        File file = new File(outFilePath);
        if(!file.exists()) {
            file.mkdirs();
        }
        String logoedImageName = resultImageName + "_big.jpg";
        /**生成大图**/
        File tempFile = updateFile(in, outFilePath, logoedImageName);
        
        /**生成小图片**/
        SimpleImageUtil.thumbnailImage(tempFile, outFilePath, ConfigUtil.getSmallSize(), ConfigUtil.getSmallSize(),
                resultImageName, false, "_small.jpg");
        
        /**生成中图片**/
        SimpleImageUtil.thumbnailImage(tempFile, outFilePath, ConfigUtil.getMiddleSize(), ConfigUtil.getMiddleSize(),
                resultImageName, false, "_middle.jpg");
        
        // FileUtils.copyFile(new File(outFilePath+"/"+logoedImageName), new File(resultName));
    }
    
    /**
     * 为制定的路径及图片名称的图片生成大、中、小相应的图片
     * @param sourcePath				指定的文件路径
     * @param imageName					指定的图片的名称
     * @param outFilePath				生成后图片的名称的路径
     * @param resultImageName			生成后图片的名称
     */
    private static void generateImgesForThreeSize(String sourcePath, String imageName, String outFilePath, String resultImageName,boolean isAddWater) {
    	/**验证当前的生成图片的输出路径是否存在，若不存在目录则进行生成**/
    	File file = new File(outFilePath);
    	if(!file.exists()) {
    		file.mkdirs();
    	}
    	/**定义原图片的File的位置**/
		File sourceDir = new File(sourcePath);
		File outDir = new File(outFilePath);
		
		/**对原图进行打Logo图标，并生成大图片**/
        String logoedImageName = printLogoToImg(sourceDir, imageName, outFilePath, resultImageName, isAddWater);

        /** 生成小图片 **/
        try {
            SimpleImageUtil.scaleImage(outDir, logoedImageName, outFilePath, resultImageName + "_small.jpg", ConfigUtil
                    .getSmallSize(), ConfigUtil.getSmallSize());
        } catch (Exception e) {
            e.printStackTrace();
        }

        /** 生成中图片 **/
        try {
            SimpleImageUtil.scaleImage(outDir, logoedImageName, outFilePath, resultImageName + "_middle.jpg",
                    ConfigUtil.getMiddleSize(), ConfigUtil.getMiddleSize());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 给指定的图片打上Logo图标，成为大图片
     * @param sourceDir			临时图片的位置
     * @param imageName			临时图片的名称
     * @param outFilePath		生成图片后的路径
     * @param resultImageName	图片的名称
     * @return					生成打上Logo图片的名称
     */
    private static String printLogoToImg(File sourceDir, String imageName, String outFilePath, String resultImageName,boolean isAddWater) {
    	
        String logoedImageName = resultImageName + "_big.jpg";
    	
    	float alpha = 0.5f;
		int x = -1;
		int y = -1;

		try {
		    int sourceWidth = 0;
		    int sourceHeight = 0;
		    //读取要打Logo的图片相关信息
		    InputStream in = null;
		    Image sourceImage = null;
			
			/**读取logo图片相关信息**/
			Properties prop = new Properties();
			InputStream is = ImageHepler.class.getResourceAsStream("/system.properties");
			//水印目录
			String resourcePath = "";
			try {
			    /**读取要打Logo的图片相关信息**/
			    in = new FileInputStream(new File(sourceDir, imageName));																
			    sourceImage = ImageIO.read(in);
			    sourceWidth = sourceImage.getWidth(null);
			    sourceHeight = sourceImage.getHeight(null);
				prop.load(is);
				resourcePath = prop.getProperty("path").trim();
			} catch (Exception e) {
			    e.printStackTrace();
			}finally {
			    in.close();
			}
			
			File wmDir = new File(resourcePath+"/");	
			//水印文件名
			String wm = getLogoImageName(sourceWidth, sourceHeight);																	//取得Logo名称

			in = new FileInputStream(new File(wmDir, wm));																				//读取Logol图片
			Image watermarkImage = ImageIO.read(in);
			int watermarkWidth = watermarkImage.getWidth(null);
			int watermarkHeight = watermarkImage.getHeight(null);
			
			/**进行打水印**/
			int widthDiff = sourceWidth - watermarkWidth;
			int heightDiff = sourceHeight - watermarkHeight;

			if (x < 0 || x > widthDiff) {
				x = Math.abs(widthDiff);
			}

			if (y < 0 || y > heightDiff) {
				y = Math.abs(heightDiff);
			}
			in.close();
			//生成图片
			SimpleImageUtil.drawImageWatermark(sourceDir, wmDir, outFilePath, imageName, wm, alpha, logoedImageName, x, y,isAddWater);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
    	return logoedImageName;
    }
    
    /**
     * 根据给定的目标图片的宽度及其高度，取得其水印Logo的名称
     * @param sourceWidth		给定的目标的图片的宽度
     * @param sourceHeight		给定的目标的图片的高度
     * @return					取得的LOGO图片的名称
     */
    private static String getLogoImageName(int sourceWidth, int sourceHeight) {
    	
    	if(sourceWidth > 240 && sourceHeight > 80) {
    		return "logo.png";
    	} else if(sourceWidth > 120 && sourceHeight >40) {
    		return "logo_middle.png";
    	} else {
    		return "logo_small.png";
    	}
    }
       
}   




