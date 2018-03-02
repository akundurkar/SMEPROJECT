package in.adcast.common;

import java.io.IOException;

public class ScaleImage {

	public static byte[] ScaleDown(byte[] imageByteArray, int thumbnailWidthInPixels, int thumbnailHeightInPixels, String thumbnailImageType ) throws IOException{
		byte[] scaledImage = ImageToThumbnail.getThumbnailFromImage(imageByteArray, thumbnailWidthInPixels, thumbnailHeightInPixels,"jpeg");
		return scaledImage;
	}
}
