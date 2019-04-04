package com.zpp.utils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QrCodeCreateUtil {
	public static boolean createQrCode(OutputStream outputStream,String content,int qrCodeSize,String imageFormat)throws WriterException,IOException {
		//设置二维码纠错级别
		HashMap<EncodeHintType,ErrorCorrectionLevel> hm=new HashMap<EncodeHintType,ErrorCorrectionLevel>();
		hm.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		QRCodeWriter qrCodeWriter=new QRCodeWriter();
		//创建比特矩阵的QR码编码字符串
		BitMatrix byteMatrix=qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, qrCodeSize, qrCodeSize,hm);
		//使bufferedImage勾画QRCode (matrixWidth 是行二维码像素点)
		int matrixWidth =byteMatrix.getWidth();
		BufferedImage image=new BufferedImage(matrixWidth-200, matrixWidth-200, BufferedImage.TYPE_INT_BGR);
		image.createGraphics();
		Graphics2D graphics=(Graphics2D) image.getGraphics();
		graphics.setColor(Color.white);
		graphics.fillRect(0, 0, matrixWidth, matrixWidth);
		graphics.setColor(Color.BLACK);
		
	}
	
	
	
}
