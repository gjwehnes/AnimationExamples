import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class CollidingSprite implements DisplayableSprite {

	private static Image image;	
	private double centerX = 0;
	private double centerY = 0;
	private double width = 50;
	private double height = 50;
	private boolean dispose = false;	

	private final double VELOCITY = 200;	
	
	public CollidingSprite(double centerX, double centerY) {

		this.centerX = centerX;
		this.centerY = centerY;
		
		if (image == null) {
			try {
				image = ImageIO.read(new File("res/simple-sprite.png"));
				this.height = this.image.getHeight(null);
				this.width = this.image.getWidth(null);
			}
			catch (IOException e) {
				System.out.println(e.toString());
			}		
		}		
	}
	
	//overloaded constructor which allows universe to change aspects of the sprite
	public CollidingSprite(double centerX, double centerY, double height, double width) {
		this(centerX, centerY);
		
		this.height = height;
		this.width = width;
	}
	

	public Image getImage() {
		return image;
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


	public void update(Universe universe, KeyboardInput keyboard, long actual_delta_time) {
		
		double velocityX = 0;
		double velocityY = 0;
		
		//LEFT ARROW
		if (keyboard.keyDown(37)) {
			velocityX = -VELOCITY;
		}
		//UP ARROW
		if (keyboard.keyDown(38)) {
			velocityY = -VELOCITY;			
		}
		//RIGHT ARROW
		if (keyboard.keyDown(39)) {
			velocityX += VELOCITY;
		}
		// DOWN ARROW
		if (keyboard.keyDown(40)) {
			velocityY += VELOCITY;			
		}

		//calculate potential change in position based on velocity and time elapsed		
		double deltaX = actual_delta_time * 0.001 * velocityX;
		double deltaY = actual_delta_time * 0.001 * velocityY;
		
		//before changing position, check if the new position would result in a collision with another sprite
		//move only if no collision results
		if (checkCollisionWithBarrier(universe.getSprites(), deltaX, 0) == false) {
			this.centerX += deltaX;
		}
		//doing this check independently in each dimension allows a sprite to still move in the dimension it would
		//not be colliding in.  i.e. the sprite can 'slide' down a wall if moving diagonal instead of stopping completely
		//this behaviour can of course be changed.
		if (checkCollisionWithBarrier(universe.getSprites(), 0, deltaY) == false) {
			this.centerY += deltaY;
		}
	}

	private boolean checkCollisionWithBarrier(ArrayList<DisplayableSprite> sprites, double deltaX, double deltaY) {

		//deltaX and deltaY represent the potential change in position
		boolean colliding = false;

		for (DisplayableSprite sprite : sprites) {
			if (sprite instanceof BarrierSprite) {
				if (CollisionDetection.overlaps(this.getMinX() + deltaX, this.getMinY() + deltaY, 
						this.getMaxX()  + deltaX, this.getMaxY() + deltaY, 
						sprite.getMinX(),sprite.getMinY(), 
						sprite.getMaxX(), sprite.getMaxY())) {
					colliding = true;
					break;					
				}
			}
		}		
		return colliding;		
	}
}
