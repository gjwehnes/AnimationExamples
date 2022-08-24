import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PinballSprite implements DisplayableSprite {

	private static Image image;	
	private double centerX = 0;
	private double centerY = 0;
	private double width = 50;
	private double height = 50;
	private boolean dispose = false;	

	private static final int WIDTH = 100;
	private static final int HEIGHT = 100;

	//PIXELS PER SECOND PER SECOND
	private double accelerationX = 0;
	private double accelerationY = 0;		
	private double velocityX = 0;
	private double velocityY = 0;
	
	public PinballSprite(double centerX, double centerY) {

		super();
		this.centerX = centerX;
		this.centerY = centerY;	

		if (image == null) {
			try {
				image = ImageIO.read(new File("res/pinball.png"));
			}
			catch (IOException e) {
				System.err.println(e.toString());
			}
		}

		this.height = HEIGHT;
		this.width = WIDTH;

	}

	//DISPLAYABLE
	public Image getImage() {
		return image;
	}
		
	public boolean getVisible() {
		return true;
	}
	
	public double getMinX() {
		return centerX - (width / 2);
	}

	public double getMaxX() {
		return centerX + (width / 2);
	}

	public double getMinY() {
		return centerY - (height / 2);
	}

	public double getMaxY() {
		return centerY + (height / 2);
	}

	public double getHeight() {
		return height;
	}

	public double getWidth() {
		return width;
	}

	public double getCenterX() {
		return centerX;
	};

	public double getCenterY() {
		return centerY;
	};
		
	public boolean getDispose() {
		return dispose;
	}

	public void setDispose(boolean dispose) {
		this.dispose = dispose;
	}

	//Allow other objects to get / set velocity and acceleration
	public double getAccelerationX() {
		return accelerationX;
	}

	public void setAccelerationX(double accelerationX) {
		this.accelerationX = accelerationX;
	}

	public double getAccelerationY() {
		return accelerationY;
	}

	public void setAccelerationY(double accelerationY) {
		this.accelerationY = accelerationY;
	}

	public double getVelocityX() {
		return velocityX;
	}

	public void setVelocityX(double velocityX) {
		this.velocityX = velocityX;
	}

	public double getVelocityY() {
		return velocityY;
	}

	public void setVelocityY(double velocityY) {
		this.velocityY = velocityY;
	}

	public void update(Universe universe, KeyboardInput keyboard, long actual_delta_time) {
		
		//bouncing sprites do not just check for collision with other sprites, but also calculate their rebound
		//velocity if they do collide. note the use of a separate class that provides both this rebound calculation
		//and the regular motion
//		collisionDetection.calculate2DBounce(bounce, this, universe.getSprites(), velocityX, velocityY, actual_delta_time);
//		this.centerX = bounce.newX + (width / 2);
//		this.centerY = bounce.newY + (width / 2);
//		this.velocityX = bounce.newVelocityX;
//		this.velocityY = bounce.newVelocityY;			
//
//		this.velocityX = this.velocityX + accelerationX * 0.001 * actual_delta_time;
//		this.velocityY = this.velocityY + accelerationY * 0.001 * actual_delta_time;
//	
	}

}

