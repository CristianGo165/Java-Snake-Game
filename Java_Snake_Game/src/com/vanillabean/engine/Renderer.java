package com.vanillabean.engine;

import java.awt.image.DataBufferInt;

import com.vanillabean.engine.gfx.Font;
import com.vanillabean.engine.gfx.Image;
import com.vanillabean.engine.gfx.ImageTile;

public class Renderer {
	private int pixelWidth, pixelHeight;
	private int[] pixel;
	private Font font = Font.STANDARD;

	public Renderer(GameContainer gc) {
		pixelWidth = gc.getWidth();
		pixelHeight = gc.getHeight();

		// Edits Pixel Data of Image Being Rendered
		pixel = ((DataBufferInt) gc.getWindow().getImage().getRaster().getDataBuffer()).getData();
	}

	public void clear() {
		for (int i = 0; i < pixel.length; i++) {
			pixel[i] = 0;
		}
	}
	
	public void setPixel(int x, int y, int value) {
		if((x < 0 || x>= pixelWidth || y < 0 || y >= pixelHeight) || value == 0xffff00ff) {
			return;
		}
		
		pixel[x + y * pixelWidth] = value;
	}
	
	public void drawText(String text, int offsetX, int offsetY, int color) {
		//Only for fonts with only upper case
		
		//Image fontImage = font.getFontImage();
		
		text = text.toUpperCase();
		
		int offset = 0;
		
		for(int i = 0; i< text.length(); i++){
			int unicode = text.codePointAt(i) - 32;
			
			for(int y = 0; y < font.getFontImage().getHeight(); y++) {
				for(int x = 0; x < font.getWidths()[unicode]; x++) {
					if(font.getFontImage().getPixel()[(x + font.getOffsets()[unicode])+y*font.getFontImage().getWidth()] == 0xffffffff) {
						setPixel(x+ offsetX + offset, y + offsetY, color);
					}
				}
			}
			offset += font.getWidths()[unicode];
		}
	}

	public void drawImage(Image image, int offsetX, int offsetY) {
		
		//Don't Render
		if(offsetX < -image.getWidth()) return;
		if(offsetY < -image.getHeight()) return;
		
		if(offsetX >= pixelWidth) return;
		if(offsetY >= pixelHeight) return;
		
		int newX = 0;
		int newY = 0;
		
		//newWith and newHeight are for the clipping code
		int newWidth = image.getWidth();
		int newHeight = image.getHeight();
		
		
		
		//Clipping
		if(newX+ offsetX < 0) {
			newX -= offsetX;
		}
		
		if(newY+ offsetY < 0) {
			newY -= offsetY;
		}
		
		if(newWidth + offsetX > pixelWidth) {
			newWidth -= (newWidth+offsetX - pixelWidth);
		}
		
		if(newHeight + offsetY > pixelHeight) {
			newHeight -= (newHeight+offsetY - pixelHeight);
		}
		//Clipping
		
		for(int y = newY ; y < newHeight; y++) {
			for(int x = newX; x < newWidth; x++) {
				setPixel(x + offsetX, y + offsetY, image.getPixel()[x + y * image.getWidth()]);
			}
		}
	}
	
	public void drawImageTile(ImageTile image, int offsetX, int offsetY, int tileX, int tileY) {

		//Don't Render
		if(offsetX < -image.getTileWidth()) return;
		if(offsetY < -image.getTileHeight()) return;
		
		if(offsetX >= pixelWidth) return;
		if(offsetY >= pixelHeight) return;
		
		int newX = 0;
		int newY = 0;
		
		//newWith and newHeight are for the clipping code
		int newWidth = image.getTileWidth();
		int newHeight = image.getTileHeight();
		
		
		
		//Clipping
		if(newX+ offsetX < 0) {
			newX -= offsetX;
		}
		
		if(newY+ offsetY < 0) {
			newY -= offsetY;
		}
		
		if(newWidth + offsetX > pixelWidth) {
			newWidth -= (newWidth+offsetX - pixelWidth);
		}
		
		if(newHeight + offsetY > pixelHeight) {
			newHeight -= (newHeight+offsetY - pixelHeight);
		}
		//Clipping
		
		for(int y = newY ; y < newHeight; y++) {
			for(int x = newX; x < newWidth; x++) {
				setPixel(x + offsetX, y + offsetY, image.getPixel()[(x + tileX * image.getTileWidth()) + (y + tileY * image.getTileHeight()) * image.getWidth()]);
			}
		}
	}
}