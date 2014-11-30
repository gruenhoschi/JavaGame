package com.mine.minefront.input;

public class Controller {

	public double x, y, z, rotation, xa, za, rotationa;
	public static boolean turnLeft = false;
	public static boolean turnRight = false;

	public void tick(boolean forward, boolean back, boolean left,
			boolean right, boolean jump) {
		double rotationSpeed = 0.02;
		double walkSpeed = 0.1;
		double jumpHeight = 0.5;
		double xMove = 0;
		double zMove = 0;

		if (forward) {
			zMove++;
		}
		if (back) {
			zMove--;
		}
		if (left) {
			xMove--;
		}
		if (right) {
			xMove++;
		}
		if (turnLeft) {
			rotationa -= rotationSpeed;
		}
		if (turnRight) {
			rotationa += rotationSpeed;
		}
		if (jump) {
				y += jumpHeight;
		}

		xa += (xMove * Math.cos(rotationSpeed) + zMove
				* Math.sin(rotationSpeed) * walkSpeed);
		za += (zMove * Math.cos(rotationSpeed) - xMove
				* Math.sin(rotationSpeed) * walkSpeed);

		x += xa;
		y *= 0.9;
		z += za;
		xa *= 0.01;
		za *= 0.01;
		rotation += rotationa;
		rotationa *= 0.5;

	}

}
