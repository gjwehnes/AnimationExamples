import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class SpaceShipSprite implements DisplayableSprite {

	private static final int WIDTH = 120;
	private static final int HEIGHT = 120;
	
	private static Image[] rotatedImages = new Image[360];
	private AudioPlayer thrustSound = new AudioPlayer();
	private AudioPlayer bulletSound = new AudioPlayer();
	
	private double reloadTime = 0;
	private Image rotatedImage;

	private final double ACCELERATION = 400;          			//PIXELS PER SECOND PER SECOND 
	private final double ROTATION_SPEED = 180;	//degrees per second	
	private final double BULLET_VELOCITY = 751;
	private double currentAngle = 0;
	private int currentImageAngle = 0;
	
	private static Image image;	
	private double centerX = 0;
	private double centerY = 0;
	private double width = WIDTH;
	private double height = HEIGHT;
	private boolean dispose = false;	
	private double velocityX = 0;
	private double velocityY = 0;
	
	public SpaceShipSprite(double centerX, double centerY) {

		this.centerX = centerX;
		this.centerY = centerY;		

		Image image = null;
		try {
			image = ImageIO.read(new File("res/spaceship.png"));
		}
		catch (IOException e) {
			System.out.print(e.toString());
		}

		if (image != null) {
			for (int i = 0; i < 360; i++) {
				rotatedImages[i] = ImageRotator.rotate(image, i);			
			}
		}		
	}

	public Image getImage() {
		return rotatedImages[Math.floorMod((int)currentAngle, 360)];
	}
	
	//DISPLAYABLE
	
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


	public void update(Universe universe, long actual_delta_time) {
		
		KeyboardInput keyboard = KeyboardInput.getKeyboard();
		
		//LEFT	
		if (keyboard.keyDown(37)) {
			currentAngle -= (ROTATION_SPEED * (actual_delta_time * 0.001));
		}
		// RIGHT
		if (keyboard.keyDown(39)) {
			currentAngle += (ROTATION_SPEED * (actual_delta_time * 0.001));
		}
		//UP
		if (keyboard.keyDown(38)) {
			if (thrustSound.isPlayCompleted()) {
				thrustSound.playAsynchronous("res/thrust.wav");
			}
			double angleInRadians = Math.toRadians(currentAngle);
			velocityX += Math.cos(angleInRadians) * ACCELERATION * actual_delta_time * 0.001;
			velocityY += Math.sin(angleInRadians) * ACCELERATION * actual_delta_time * 0.001;
		}
		else {
			if (thrustSound.isPlayCompleted() == false) {
				thrustSound.setStop(true);
			}
		}
		// DOWN
		if (keyboard.keyDown(40)) {
		}
		//SPACE
		if (keyboard.keyDown(KeyboardInput.KEY_X)) {
			shoot(universe);	
		}

		//keep the angle within standard range
	    if (currentAngle >= 360) {
	    	currentAngle -= 360;
	    }
	    if (currentAngle < 0) {
	    	currentAngle += 360;
	    }	
	    	    
		//calculate new position assuming there are no changes in direction
	    double movement_x = (this.velocityX * actual_delta_time * 0.001);
	    double movement_y = (this.velocityY * actual_delta_time * 0.001);
	    this.centerX = this.centerX + movement_x;
	    this.centerY = this.getCenterY()  + movement_y;
	    
		reloadTime -= actual_delta_time;
	}
	
	public void shoot(Universe universe) {
		
		if (reloadTime <= 0) {
			/*
			 * An example of how to convert a standard angle and distance into x and y coordinates.
			 * Remember that the standard angle rotates clockwise, contrary to the mathematical 
			 * definition (see SimpleSpriteUniverse). The angle does need to be converted to
			 * radians, but the sine and cosine functions will work correctly with standard angles.
			 * 
			 * The cosine and sine function give the x and y coordinate for a circle of radius
			 * 1. Thus, to include a magnitude (i.e. a movement for a given distance at a given angle),
			 * we multiply the coordinates by the distance
			 */
			double angleInRadians = Math.toRadians(currentAngle);
			double bulletVelocityX = Math.cos(angleInRadians) * BULLET_VELOCITY;
			double bulletVelocityY = Math.sin(angleInRadians) * BULLET_VELOCITY;
			/*
			 * As the bullet's absolute velocity is relative to the spaceship's velocity
			 * add the latter to the former.
			 */
			bulletVelocityX += velocityX;
			bulletVelocityY += velocityY;
			
			double bulletCurrentX = this.getCenterX();
			double bulletCurrentY = this.getCenterY();

			BulletSprite bullet = new BulletSprite(bulletCurrentX, bulletCurrentY, bulletVelocityX, bulletVelocityY);
			universe.getSprites().add(bullet);
			if (bulletSound.isPlayCompleted()) {
				bulletSound.playAsynchronous("res/missile.wav");
			}
			
			reloadTime = 100;
			
		}
	}	
}
