package com.novaone.utils;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.ImageIcon;
public class ImgCutUtil {

	/**  
     * 实现图像的等比缩放  
     * @param source  
     * @param targetW  
     * @param targetH  
     * @return  
	 * @throws IOException 
     */  
    public static BufferedImage resize(BufferedImage source, int targetW,int targetH) throws IOException {   
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
          
        
        
        ByteArrayOutputStream bs =new ByteArrayOutputStream();

        ImageOutputStream imOut =ImageIO.createImageOutputStream(bs);

        ImageIO.write(target,"jpg",imOut); //scaledImage1为BufferedImage，jpg为图像的类型


        InputStream is =new ByteArrayInputStream(bs.toByteArray()); 
        
        
        return target; 
    } 
            
    public static InputStream cut(File f, int w, int h) {
//    	FileInputStream fileInputStream = null;
    	ByteArrayInputStream is = null;
		try {
//			fileInputStream = new FileInputStream(new File("d:/test/1.jpg"));
			BufferedImage bufImg = ImageIO.read(f);
			Image temp = bufImg.getScaledInstance(w, h, bufImg.SCALE_SMOOTH);
			BufferedImage image = toBufferedImage(temp);
			
			 Graphics2D graph = image.createGraphics();
	            graph.drawImage(bufImg, 0, 0, w, h,Color.white, null);
	            graph.dispose();
			
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ImageIO.write(image, "jpg", os);
			
//			fileInputStream.read(os.toByteArray());
			is = new ByteArrayInputStream(os.toByteArray());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return is;
    }
    
    public static BufferedImage toBufferedImage(Image image) {
		if (image instanceof BufferedImage) {
			return (BufferedImage) image;
		}

		// This code ensures that all the pixels in the image are loaded
		image = new ImageIcon(image).getImage();

		// Determine if the image has transparent pixels; for this method's
		// implementation, see e661 Determining If an Image Has Transparent
		// Pixels
		// boolean hasAlpha = hasAlpha(image);

		// Create a buffered image with a format that's compatible with the
		// screen
		BufferedImage bimage = null;
		GraphicsEnvironment ge = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		try {
			// Determine the type of transparency of the new buffered image
			int transparency = Transparency.OPAQUE;
			/*
			 * if (hasAlpha) { transparency = Transparency.BITMASK; }
			 */

			// Create the buffered image
			GraphicsDevice gs = ge.getDefaultScreenDevice();
			GraphicsConfiguration gc = gs.getDefaultConfiguration();
			bimage = gc.createCompatibleImage(image.getWidth(null),
					image.getHeight(null), transparency);
		} catch (HeadlessException e) {
			// The system does not have a screen
		}

		if (bimage == null) {
			// Create a buffered image using the default color model
			int type = BufferedImage.TYPE_INT_RGB;
			// int type = BufferedImage.TYPE_3BYTE_BGR;//by wang
			/*
			 * if (hasAlpha) { type = BufferedImage.TYPE_INT_ARGB; }
			 */
			bimage = new BufferedImage(image.getWidth(null),
					image.getHeight(null), type);
		}

		// Copy image to buffered image
		Graphics g = bimage.createGraphics();

		// Paint the image onto the buffered image
		g.drawImage(image, 0, 0, null);
		g.dispose();

		return bimage;
	}
}
