package com.novaone.utils.img;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.imageio.ImageIO;
import org.apache.commons.io.IOUtils;
import com.alibaba.simpleimage.ImageFormat;
import com.alibaba.simpleimage.ImageRender;
import com.alibaba.simpleimage.ImageWrapper;
import com.alibaba.simpleimage.render.DrawTextItem;
import com.alibaba.simpleimage.render.DrawTextParameter;
import com.alibaba.simpleimage.render.DrawTextRender;
import com.alibaba.simpleimage.render.ReadRender;
import com.alibaba.simpleimage.render.ScaleParameter;
import com.alibaba.simpleimage.render.ScaleRender;
import com.alibaba.simpleimage.render.WatermarkParameter;
import com.alibaba.simpleimage.render.WatermarkRender;
import com.alibaba.simpleimage.render.WriteRender;

public class SimpleImageUtil {

	static float scale = 0.2f;
	static double dScale = 0.2;
	
	
	public static void scaleImage() {
		
	}
	
	/**
     * <p>Title: thumbnailImage</p>
     * <p>Description: 根据图片路径生成缩略图 </p>
     * @param imagePath    原图片路径
     * @param w            缩略图宽
     * @param h            缩略图高
     * @param resultImageName    不带后缀的图片名称
     * @param force        是否强制按照宽高生成缩略图(如果为false，则生成最佳比例缩略图)
     * @param fileC 后缀
     */
	public static void thumbnailImage(File imgFile, String outFilePath, int w, int h, String resultImageName, boolean force, String fileC){
	    if(imgFile.exists()){
            try {
                // ImageIO 支持的图片类型 : [BMP, bmp, jpg, JPG, wbmp, jpeg, png, PNG, JPEG, WBMP, GIF, gif]
                //String types = Arrays.toString(ImageIO.getReaderFormatNames());
                String suffix = "jpg";
                Image img = ImageIO.read(imgFile);
                if(!force){
                    // 根据原图与要求的缩略图比例，找到最合适的缩略图比例
                    int width = img.getWidth(null);
                    int height = img.getHeight(null);
                    if((width*1.0)/w < (height*1.0)/h){
                        if(width > w){
                            h = Integer.parseInt(new java.text.DecimalFormat("0").format(height * w/(width*1.0)));
                        }
                    } else {
                        if(height > h){
                            w = Integer.parseInt(new java.text.DecimalFormat("0").format(width * h/(height*1.0)));
                        }
                    }
                }
                BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
                Graphics g = bi.getGraphics();
                g.drawImage(img, 0, 0, w, h, Color.LIGHT_GRAY, null);
                g.dispose();
                // 将图片保存在原目录并加上前缀
                ImageIO.write(bi, suffix, new File(outFilePath + File.separator + resultImageName + fileC));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
        }
	}
	

	/**
	 * 根据给定的原图片及目标的目录及要切割的大小进行切割图片
	 * @param sourceDir		要切割图片的目录
	 * @param source		切割的图片的名称
	 * @param resultDir		放置切割后图片的位置目录
	 * @param maxWidth		最大的宽度
	 * @param maxHeight		最大的高度
	 */
	public static void scaleImage(File sourceDir, String source,
			String resultDir, String resultName, int maxWidth, int maxHeight) {
		
		

		/**设置切割后图片的参数**/
		ScaleParameter scaleParam = new ScaleParameter(maxWidth, maxHeight);					//设置图片的大小
		ImageFormat imageFormat = ImageFormat.JPEG;												//设置图片的格式

		/**进行切割**/
		
		InputStream in = null;
		OutputStream out = null;
		ImageRender wr = null;
		try {
			in = new FileInputStream(sourceDir + "/" + source);									//进行读取要切割的图片
			ImageRender rr = new ReadRender(in);
			
			ImageRender sr = new ScaleRender(rr, scaleParam);									//进行切割

			out = new FileOutputStream(new File(resultDir, resultName));						
			wr = new WriteRender(sr, out, imageFormat);

			wr.render();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(in);
			IOUtils.closeQuietly(out);
		}
	}


	
	public static void drawTextWatermark(File sourceDir, String imageName,
			String resultDir, String resultImageName, DrawTextItem... items)
			throws Exception {
		InputStream in = null;
		ImageRender fr = null;

		try {
			in = new FileInputStream(sourceDir + "/" + imageName);
			ImageRender rr = new ReadRender(in);

			DrawTextParameter dp = new DrawTextParameter();
			if (items != null) {
				for (DrawTextItem itm : items) {
					dp.addTextInfo(itm);
				}
			}
			DrawTextRender dtr = new DrawTextRender(rr, dp);

			fr = new WriteRender(dtr, new File(resultDir, resultImageName));
			fr.render();
		} finally {
			IOUtils.closeQuietly(in);
			if (fr != null) {
				fr.dispose();
			}
		}
	}

	/**
	 * 根据参数生成带水印或不带水印的大图
	 * 
	 * @param sourceDir    源图地址
	 * @param wmDir        水印目录
	 * @param resultDir    生成图片后的路径
	 * @param bg           源图文件名
	 * @param wm           水印文件名
	 * @param alpha        加水印的透明度
	 * @param resultName   生成后的图片的名称
	 * @param x            水印图片x位置
	 * @param y            水印图片y位置
	 * @param isAddWater   是否要加水印
	 * @throws Exception
	 */
	public static void drawImageWatermark(File sourceDir, File wmDir,
			String resultDir, String bg, String wm, float alpha, String resultName, int x, int y,boolean isAddWater)
			throws Exception {
		InputStream in = null;
		ImageRender sr = null;
		ImageRender wr = null;
//		WriteRender wr = null;
		OutputStream output = null;

		try {
			/**读取原目录的基数图片**/
			in = new FileInputStream(new File(sourceDir, bg));

			ImageRender rr = new ReadRender(in, true);
			
			sr = isAddWater == true ? new WatermarkRender(rr, createWatermarkParam(wmDir, wm, alpha, x, y)) : rr;
			
			write(sr, resultDir, resultName);
//			output = new FileOutputStream(new File(resultDir, resultName));
//            wr = new WriteRender(sr, output, ImageFormat.JPEG);
//            wr.render();
            
			//FileUtils.copyFile(new File(sourceDir+"/"+bg), new File(resultName));
		}catch(Exception e){
			e.printStackTrace();
		} finally {
			if (sr != null) {
				sr.dispose();
			}
			if (wr != null) {
			    wr.dispose();
			}
			IOUtils.closeQuietly(in);
			IOUtils.closeQuietly(output);
		}
	}

	private static WatermarkParameter createWatermarkParam(File dir,
			String imgPath, float alpha, int x, int y) throws Exception {
		InputStream in = null;
		ImageRender rr = null;
		ImageWrapper imageWrapper = null;
		try {
			in = new FileInputStream(new File(dir, imgPath));
			rr = new ReadRender(in, false);
			imageWrapper = rr.render();
		} finally {
			if (rr != null) {
				rr.dispose();
			}
			IOUtils.closeQuietly(in);
		}

		WatermarkParameter param = new WatermarkParameter(imageWrapper, alpha,
				x, y);

		return param;
	}

	/**
	 * 写文件
	 * @param sr           图片流
	 * @param resultDir    生成图片后的路径
	 * @param filename     生成图片后的名称
	 * @throws Exception
	 */
	public static void write(ImageRender sr, String resultDir, String filename)
			throws Exception {
		OutputStream output = null;
		ImageRender wr = null;

		try {
		    System.out.println("------生成大图--9-1-----resultDir="+resultDir+"===filename="+filename);
			output = new FileOutputStream(new File(resultDir, filename));
			System.out.println("------生成大图--9-2-----");
			wr = new WriteRender(sr, output, ImageFormat.JPEG);
			wr.render();
			System.out.println("------生成大图--9-3-----");
		} catch (Exception e) {
		    System.out.println("------生成大图--9-3异常-----");
			e.printStackTrace();
		} finally {
			if (wr != null) {
				wr.dispose();
			}
			IOUtils.closeQuietly(output);
		}
	}

}