package com.dasol.util;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.springframework.util.FileCopyUtils;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifIFD0Directory;

public class UploadFileUtils {

	public static String uploadFile(String uploadPath, String originalFilename, byte[] fileData, int cno) 
			throws Exception {
		
		UUID uid = UUID.randomUUID();
		String savedName = uid.toString() + "_" + originalFilename;
		String savedPath = calPath(uploadPath, cno);
		File target = new File(uploadPath + savedPath, savedName);
		FileCopyUtils.copy(fileData, target);
//		String formatName = originalFilename.substring(originalFilename.lastIndexOf(".")+1);
		String uploadedFileName = makeThumbnail(uploadPath, savedPath, savedName);
		
		return uploadedFileName;
	}

	private static String makeThumbnail(String uploadPath, String savedPath, String fileName) throws Exception {
		
		File imgFile = new File(uploadPath + savedPath, fileName);
		Metadata metadata = ImageMetadataReader.readMetadata(imgFile);
		
		BufferedImage sourceImg = rotateImageForMobile(metadata, imgFile);
		
		int dw = 300, dh = 300;

		int ow = sourceImg.getWidth();
		int oh = sourceImg.getHeight();
		
		int nw = ow; 
		int nh = (ow * dh) / dw;

		if(nh > oh) { 
			nw = (oh * dw) / dh; 
			nh = oh; 
		}

		BufferedImage cropImg = Scalr.crop(sourceImg, (ow-nw)/2, (oh-nh)/2, nw, nh);
		BufferedImage destImg = Scalr.resize(cropImg, dw, dh);
		
//		BufferedImage destImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, 150);
		
		String thumbnailName = uploadPath + savedPath + File.separator + "s_" + fileName;
		
		File newFile = new File(thumbnailName);
		
		String formatName = fileName.substring(fileName.lastIndexOf(".")+1);
		
		ImageIO.write(destImg, formatName.toUpperCase(), newFile);
		
		return thumbnailName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}

	private static BufferedImage rotateImageForMobile(Metadata metadata, File imageFile) throws Exception {
		int orientation = 1;

		ExifIFD0Directory exifIFD0 = metadata.getFirstDirectoryOfType(ExifIFD0Directory.class);
		
		if(exifIFD0 != null) {
			orientation = exifIFD0.getInt(ExifIFD0Directory.TAG_ORIENTATION);
		}
		
		BufferedImage bi = ImageIO.read(imageFile);
		
		if (orientation == 6) { // 정위치
			return rotateImage(bi, 90);
		} else if (orientation == 1) { // 왼쪽으로 눞였을때
			return bi;
		} else if (orientation == 3) {// 오른쪽으로 눞였을때
			return rotateImage(bi, 180);
		} else if (orientation == 8) {// 180도
			return rotateImage(bi, 270);
		} else {
			return bi;
		}
	}

	private static BufferedImage rotateImage(BufferedImage orgImage, int radians) {
		BufferedImage newImage;
		if (radians == 90 || radians == 270) {
			newImage = new BufferedImage(orgImage.getHeight(), orgImage.getWidth(), orgImage.getType());
		} else if (radians == 180) {
			newImage = new BufferedImage(orgImage.getWidth(), orgImage.getHeight(), orgImage.getType());
		} else {
			return orgImage;
		}
		Graphics2D graphics = (Graphics2D) newImage.getGraphics();
		graphics.rotate(Math.toRadians(radians), newImage.getWidth() / 2, newImage.getHeight() / 2);
		graphics.translate((newImage.getWidth() - orgImage.getWidth()) / 2,
				(newImage.getHeight() - orgImage.getHeight()) / 2);
		graphics.drawImage(orgImage, 0, 0, orgImage.getWidth(), orgImage.getHeight(), null);

		return newImage;
	}

	
	private static String calPath(String uploadPath, int cno) {
		Calendar cal = Calendar.getInstance();
		
		String camingNo = File.separator + cno;
		String yearPath = camingNo + File.separator + cal.get(Calendar.YEAR);
		String monthPath = yearPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1);
		String datePath = monthPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE));
		
		makeDir(uploadPath, camingNo, yearPath, monthPath, datePath);
		
		return datePath;
	}

	private static void makeDir(String uploadPath, String... paths) {
		if(new File(paths[paths.length-1]).exists()) {
			return;
		}
		
		for (String path : paths) {
			File dirPath = new File(uploadPath + path);
			if(! dirPath.exists()) {
				dirPath.mkdir();
			}
		}
	}

	
}
