import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BlinkySprite implements DisplayableSprite {

	private static final double VELOCITY = 200;
	private static final int WIDTH = 50;
	private static final int HEIGHT = 50;
	private static final int PERIOD_LENGTH = 200;
	private static final int IMAGES_IN_CYCLE = 2;

	private static Image[] images;
	boolean isGhost = false;
	private long elapsedTime = 0;
		
	private double centerX = 0;
	private double centerY = 0;
	private double width = 50;
	private double height = 50;
	private boolean dispose = false;

	//an example of an enumeration, which is a series of constants in a list. this restricts the potential values of a variable
	//declared with that type to only those values within the set, thereby promoting both code safety and readability
	private Direction direction = Direction.UP;
	
	private enum Direction { DOWN(0), LEFT(1), UP(2), RIGHT(3);
		private int value = 0;
		private Direction(int value) {
			this.value = value; 
		} 
	};

	
	public BlinkySprite(double centerX, double centerY) {

		this.centerX =centerX;
		this.centerY = centerY;	
		this.width = WIDTH;
		this.height = HEIGHT;
		
		if (images == null) {
			try {
				images = new Image[8];
				for (int i = 0; i < 8; i++) {
					String path = String.format("res/blinky/blinky-%d.gif", i);
					images[i] = ImageIO.read(new File(path));
				}
			}
			catch (IOException e) {
				System.err.println(e.toString());
			}		
		}
	}

	public Image getImage() {
				
		//calculate how many periods of 200 milliseconds have elapsed since this sprite was instantiated?
		long period = elapsedTime / PERIOD_LENGTH;
		//calculate which image (aka 'frame') of the sprite animation should be shown out of the cycle of images
		int image = (int) (period % IMAGES_IN_CYCLE);
		//calculate index into array of all images. this is an arbitrary value, depending on how the image files are ordered
		int index = direction.value * IMAGES_IN_CYCLE + image;
						
		return images[index];
				
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
		
		elapsedTime += actual_delta_time;
		
		//set direction based on current location of sprite; this will cause the sprite to travel in a counter-clockwise circuit
		//near the borders of the visible universe; this is an example of movement rules based on internal state as opposed to collision detection 
		if ((this.centerY < -200) && direction == Direction.UP) {
			//at top edge, move left
			direction = Direction.LEFT;
		}		
		if ((this.centerY > 200) && direction == Direction.DOWN) {
			//at bottom edge, move right
			direction = Direction.RIGHT;
		}
		if ( (this.centerX < -350) && direction == Direction.LEFT) {
			//at left edge, move down
			direction = Direction.DOWN;
		}
		if ((this.centerX > 350) && direction == Direction.RIGHT) {
			//at right edge, move up
			direction = Direction.UP;
		}

		switch (direction) {
		case UP:
			this.centerY  -= actual_delta_time * 0.001 * VELOCITY;
			break;
		case DOWN:
			this.centerY  += actual_delta_time * 0.001 * VELOCITY;
			break;
		case LEFT:
			this.centerX -= actual_delta_time * 0.001 * VELOCITY;
			break;
		case RIGHT:
			this.centerX += actual_delta_time * 0.001 * VELOCITY;
			break;
		}

	}
}
