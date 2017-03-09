package com.wu1g.framework.util.gif2png;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Gif2PngUtil {
	public String keyword = "";

	public static void log(String log) {
		System.out.println(log);
	}

	void transGifToPng(File srcFile,File desFile) {
		if (srcFile.isDirectory()) {
			File[] subFiles = srcFile.listFiles();
			int i;
			for (i = 0; i < subFiles.length; ++i) {
				transGifToPng(subFiles[i],desFile);
			}
		}
		if (srcFile.isFile()) {
			ArrayList<BufferedImage> srcImgList;
			BufferedImage desImg;
			try {
				if (srcFile.getName().contains(".gif")) {
					srcImgList=getBufferdImagesArraylist(srcFile);
					desImg=createPngImage(srcImgList);
					File outImage=new File(desFile,srcFile.getName().substring(0, srcFile.getName().getBytes().length-4)+".png");
					ImageIO.write(desImg, "png", outImage);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	void transGifToPngInDec(File srcFile,File desFile) {
		if (srcFile.isDirectory()) {
			File[] subFiles = srcFile.listFiles();
			int i;
			for (i = 0; i < subFiles.length; ++i) {
				transGifToPngInDec(subFiles[i],desFile);
			}
		}
		if (srcFile.isFile()) {
			GifDecoder srcDec;
			BufferedImage desImg;
			try {
				if (srcFile.getName().contains(".gif")) {
					srcDec=getdec(srcFile);
					desImg=createPngImage(srcDec);
					File outImage=new File(desFile,srcFile.getName().substring(0, srcFile.getName().getBytes().length-4)+".png");
					ImageIO.write(desImg, "png", outImage);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	private GifDecoder getdec(File file){
		GifDecoder result=new GifDecoder();
		try {
			result.read(new FileInputStream(file));
			return result;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	private BufferedImage createPngImage(GifDecoder dec){
		BufferedImage resultImage=null;
		int num=dec.getFrameCount();
		if(num==0){
			System.out.println("error!srcImgList.size()==0");
			return null;
		}
		int width=dec.getImage().getWidth();;
		int height=dec.getImage().getHeight();
		resultImage = new BufferedImage(width*num,
				height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = resultImage.createGraphics();
		resultImage = g2d.getDeviceConfiguration().createCompatibleImage(width*num, height, Transparency.TRANSLUCENT);
		g2d.dispose();
		g2d = resultImage.createGraphics();
		for(int i=0;i<num;i++){
			g2d.drawImage(dec.getFrame(i), i*width, 0, null);
		}
		g2d.dispose();
		return resultImage;
	}
	private ArrayList<BufferedImage> getBufferdImagesArraylist(File file){
		ArrayList < BufferedImage > images = new ArrayList<BufferedImage>();
		try{
			java.util.Iterator<ImageReader> imageReaders = ImageIO.getImageReadersByFormatName("gif");
			ImageReader imageReader = (ImageReader)imageReaders.next();
			imageReader.setInput(ImageIO.createImageInputStream(file));

			for(int i = 0;true;++i){
				try{
					BufferedImage aa=imageReader.read(i);
					images.add(aa);
				}catch (Exception e) {
					break;
				}
			}
			return images;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	private BufferedImage createPngImage(ArrayList<BufferedImage> srcImgList){
		BufferedImage resultImage=null;
		int num=srcImgList.size();
		if(num==0){
			System.out.println("error!srcImgList.size()==0");
			return null;
		}
		int width=128;//srcImgList.get(0).getWidth();
		int height=128;//srcImgList.get(0).getHeight();
		resultImage = new BufferedImage(width,height*num, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = resultImage.createGraphics();
		resultImage = g2d.getDeviceConfiguration().createCompatibleImage(width, height*num, Transparency.TRANSLUCENT);
		g2d.dispose();
		g2d = resultImage.createGraphics();
		for(int i=0;i<num;i++){
			/*新生成结果图片*/
			BufferedImage newImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
			newImg.getGraphics().drawImage(srcImgList.get(i).getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH), 0, 0, null);
			g2d.drawImage(newImg, 0, i*height, null);
		}
		g2d.dispose();
		return resultImage;
	}
}
