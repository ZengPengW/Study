package com.zpp.utils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QrCodeCreateUtil {
	public static boolean createQrCode(OutputStream outputStream, String content, int qrCodeSize, String imageFormat)
			throws WriterException, IOException {
		// ���ö�ά�������
		HashMap<EncodeHintType, ErrorCorrectionLevel> hm = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
		hm.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		// �������ؾ����QR������ַ���
		BitMatrix byteMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, qrCodeSize, qrCodeSize, hm);
		// ʹbufferedImage����QRCode (matrixWidth ���ж�ά�����ص�)
		int matrixWidth = byteMatrix.getWidth();
		BufferedImage image = new BufferedImage(matrixWidth - 200, matrixWidth - 200, BufferedImage.TYPE_INT_BGR);
		image.createGraphics();
		Graphics2D graphics = (Graphics2D) image.getGraphics();
		graphics.setColor(Color.white);
		graphics.fillRect(0, 0, matrixWidth, matrixWidth);
		// ʹ�ñ��ؾ��󻭲��ұ���ͼ��
		graphics.setColor(Color.BLACK);

		for (int i = 0; i < matrixWidth; i++) {
			for (int j = 0; j < matrixWidth; j++) {
				if (byteMatrix.get(i, j)) {
					graphics.fillRect(i - 100, j - 100, 1, 1);
				}
			}
		}
		return ImageIO.write(image, imageFormat, outputStream);

	}
	
	//��ȡ��ά����Ϣ
	 public static String readQrCode(InputStream inputStream) throws IOException{  
		          //���������л�ȡ�ַ�����Ϣ
		          BufferedImage image = ImageIO.read(inputStream);  
		          //��ͼ��ת��Ϊ������λͼԴ
		          LuminanceSource source = new BufferedImageLuminanceSource(image);  
		          BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));  
		          QRCodeReader reader = new QRCodeReader();  
		          Result result = null ;  
		          try {
		           result = reader.decode(bitmap);  
		          } catch (ReaderException e) {
		              e.printStackTrace();  
		          }
		         return result.getText();  
		      }

	 
}
