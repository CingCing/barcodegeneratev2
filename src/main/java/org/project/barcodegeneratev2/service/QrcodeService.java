package org.project.barcodegeneratev2.service;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QrcodeService {
	public static byte[] getQRCodeImage(String text, int width, int height) throws WriterException, IOException {
	    QRCodeWriter qrCodeWriter = new QRCodeWriter();
	    BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
	    
	    ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
	    MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
	    byte[] pngData = pngOutputStream.toByteArray(); 
	    return pngData;
	}
	
	public static byte[] generateCode128(String text, int width, int height) throws WriterException, IOException {
	    QRCodeWriter qrCodeWriter = new QRCodeWriter();
	    BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.CODE_128, width, height);
	    
	    ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
	    MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
	    byte[] pngData = pngOutputStream.toByteArray(); 
	    return pngData;
	}
	
	public static byte[] generateQRCode(String text, int width, int height, char level) throws WriterException, IOException {
		Map<EncodeHintType, ErrorCorrectionLevel> hints = new HashMap<>();
		
		switch(level) {
		case 'L': hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L); break;
		case 'M': hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M); break;
		case 'Q': hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.Q); break;
		case 'H': hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H); break;
		}		
		
		QRCodeWriter qrCodeWriter = new QRCodeWriter();	    
	    BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height, hints);
	    
	    ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
	    MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
	    byte[] pngData = pngOutputStream.toByteArray(); 
	    return pngData;
	}
	
	public static void generateQRCodeImage(String text, int width, int height, String filePath)
            throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
    }
	
	public static void generateQRCodeImageOverlay(String text, int width, int height, String filePath, String Logo)
            throws WriterException, IOException {
		Map<EncodeHintType, ErrorCorrectionLevel> hints = new HashMap<>();
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);		
		
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        // Create a qr code with the url as content and a size of WxH px
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height, hints);
        
        // Load QR image
        BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(bitMatrix, getMatrixConfig());
        
        // Load logo image
        BufferedImage overly = getOverlay(Logo);       
        
        // Calculate the delta height and width between QR code and logo
        int deltaHeight = qrImage.getHeight() - overly.getHeight();
        int deltaWidth = qrImage.getWidth() - overly.getWidth();
        
        // Initialize combined image
        BufferedImage combined = new BufferedImage(qrImage.getWidth(), qrImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = (Graphics2D) combined.getGraphics();        
        
        // Write QR code to new image at position 0/0
        g.drawImage(qrImage, 0, 0, null);
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        
        // Write logo into combine image at position (deltaWidth / 2) and
        // (deltaHeight / 2). Background: Left/Right and Top/Bottom must be
        // the same space for the logo to be centered
        g.drawImage(overly,(int) Math.round(deltaWidth / 2), (int) Math.round(deltaHeight / 2), null);       
        
        // Write combined image as PNG to OutputStream
        ImageIO.write(combined, "png", pngOutputStream);
        
        byte[] pngData = pngOutputStream.toByteArray(); 
        //store image in file
        Files.copy(new ByteArrayInputStream(pngData), Paths.get(filePath + text + ".png"), StandardCopyOption.REPLACE_EXISTING);
//        return pngData;
    }
	
	public static byte[] generateQRCodeImageOverlayWebData(String text, int width, int height, byte[] imageByte)
            throws WriterException, IOException {
		Map<EncodeHintType, ErrorCorrectionLevel> hints = new HashMap<>();
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);		
		
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        // Create a qr code with the url as content and a size of WxH px
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height, hints);
        
        // Load QR image
        BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(bitMatrix, getMatrixConfig());
        
        // Load logo image
        BufferedImage overly = getOverlayByte(imageByte);      
        //resize image if its big
        if(overly.getHeight() > 100 || overly.getWidth() >100) {
        	overly = resizeImage(overly);
        }
        // Calculate the delta height and width between QR code and logo
        int deltaHeight = qrImage.getHeight() - overly.getHeight();
        int deltaWidth = qrImage.getWidth() - overly.getWidth();
        
        // Initialize combined image
        BufferedImage combined = new BufferedImage(qrImage.getWidth(), qrImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = (Graphics2D) combined.getGraphics();        
        
        // Write QR code to new image at position 0/0
        g.drawImage(qrImage, 0, 0, null);
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        
        // Write logo into combine image at position (deltaWidth / 2) and
        // (deltaHeight / 2). Background: Left/Right and Top/Bottom must be
        // the same space for the logo to be centered
        g.drawImage(overly,(int) Math.round(deltaWidth / 2), (int) Math.round(deltaHeight / 2), null);       
        
        // Write combined image as PNG to OutputStream
        ImageIO.write(combined, "png", pngOutputStream);
        
        byte[] pngData = pngOutputStream.toByteArray(); 
        //store image in file
//        Files.copy(new ByteArrayInputStream(pngData), Paths.get(filePath + text + ".png"), StandardCopyOption.REPLACE_EXISTING);
        
        return pngData;
    }
	
	private static BufferedImage getOverlay(String logoPath) throws IOException {
		File path = new File(logoPath);
        return ImageIO.read(path);
    }
	
	private static BufferedImage getOverlayByte(byte[] imageByte) throws IOException {
		InputStream in = new ByteArrayInputStream(imageByte);
        return ImageIO.read(in);
    }
	
	 private static BufferedImage resizeImage(BufferedImage originalImage){
			BufferedImage resizedImage = new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = resizedImage.createGraphics();
			g.drawImage(originalImage, 0, 0, 64, 64, null);
			g.dispose();				
			return resizedImage;
	 }
	
	private static MatrixToImageConfig getMatrixConfig() {
        // ARGB Colors
        // Check Colors ENUM
        return new MatrixToImageConfig(QrcodeService.Colors.BLACK.getArgb(), QrcodeService.Colors.WHITE.getArgb());
    }
	
	public enum Colors {
		/*
		 * int argb values
		 * blue:-12535088
		 * red:-1500093
		 * purple:-7712866
		 * orange:-741059
		 * white:-1
		 * BLACK:-16777216
		 * */
		
        BLUE(0xFF40BAD0),
        RED(0xFFE91C43),
        PURPLE(0xFF8A4F9E),
        ORANGE(0xFFF4B13D),
        WHITE(0xFFFFFFFF),
        BLACK(0xFF000000);

        private final int argb;

        Colors(final int argb){
            this.argb = argb;
        }

        public int getArgb(){
            return argb;
        }
    }
}
