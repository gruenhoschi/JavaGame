package com.mine.minefront.graphics;

import com.mine.minefront.Game;

public class Render3D extends Render {

	public double[] zBuffer;
	private double renderDistance = 4000;

	public Render3D(int width, int height) {
		super(width, height);
		zBuffer = new double[width * height];
	}

	public void floor(Game game) {

		double ceilingPosition = 8;
		double floorPosition = 8;
		double forward = game.controls.z;
		double right = game.controls.x;

		double rotation = game.controls.rotation;
		double cosine = Math.cos(rotation);
		double sine = Math.sin(rotation);

		for (int y = 0; y < height; y++) {
			double ceiling = (y + -height / 2.4) / height;

			double z = floorPosition / ceiling;

			if (ceiling < 0) {
				z = ceilingPosition / -ceiling;
			}

			for (int x = 0; x < width; x++) {
				double depth = (x - width / 2.0) / height;
				depth *= z;
				double xx = depth * cosine + z * sine;
				double yy = z * cosine - depth * sine;
				int xPix = (int) (xx + right);
				int yPix = (int) (yy + forward);
				zBuffer[x + y * height] = z;

				pixels[x + y * width] = Texture.floor.pixels[(xPix & 7) + (yPix & 7) *8] ;

				if (z > 200) {
					pixels[x + y * width] = 0;
				}

			}

		}
	}

	public void renderDistanceLimited() {
		for (int i = 0; i < width * height; i++) {
			int colour = pixels[i];

			int brigthness = (int) (renderDistance / (zBuffer[i]));

			if (brigthness < 0) {
				brigthness = 0;
			}

			if (brigthness > 255) {
				brigthness = 255;
			}

			int r = (colour >> 16) & 0xff;
			int g = (colour >> 8) & 0xff;
			int b = (colour) & 0xff;

			r = r * brigthness / 255;
			g = g * brigthness / 255;
			b = b * brigthness / 255;
			pixels[i] = r << 16 | g << 8 | b;

		}
	}

}
