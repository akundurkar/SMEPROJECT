package in.adcast.common;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;


public class ImageToThumbnail {
	


	public static byte[] getThumbnailFromImage(byte[] imageByteArray, int thumbnailWidthInPixels, int thumbnailHeightInPixels, String thumbnailImageType ) throws IOException
	{
		InputStream in = new ByteArrayInputStream(imageByteArray);
		BufferedImage originalImage = ImageIO.read(in);

		int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
		
		BufferedImage resizeImage = resizeImage(originalImage, type, thumbnailWidthInPixels, thumbnailHeightInPixels );
		
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		
		if(ImageIO.write((RenderedImage)resizeImage,thumbnailImageType, output)){
			System.out.println("Thumbnail Image successfully written to ByteArrayOutputStream");
			return output.toByteArray();
		}
		else{
			System.out.println("Thumbnail Image failed to write into ByteArrayOutputStream");
			throw new IOException();
		}
	}
	
		private static BufferedImage resizeImage(BufferedImage originalImage, int type, int imageWidth, int imageHeight){
		BufferedImage resizedImage = new BufferedImage(imageWidth, imageHeight, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, imageWidth, imageHeight, null);
		g.dispose();

		return resizedImage;
	    }
	 
	    private static BufferedImage resizeImageWithHint(BufferedImage originalImage, int type, int imageWidth, int imageHeight){
	 
		BufferedImage resizedImage = new BufferedImage(imageWidth, imageHeight, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, imageWidth, imageHeight, null);
		g.dispose();	
		g.setComposite(AlphaComposite.Src);
	 
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
		RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.setRenderingHint(RenderingHints.KEY_RENDERING,
		RenderingHints.VALUE_RENDER_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		RenderingHints.VALUE_ANTIALIAS_ON);
	 
		return resizedImage;
	    }
}
