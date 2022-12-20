import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class JumpingSprite implements DisplayableSprite {

	
	//PIXELS PER SECOND PER SECOND
	private final double ACCCELERATION_X = 300;
	private final double ACCCELERATION_Y = 600;
	private final double MAX_VELOCITY_X = 300;
	private final double DEACCELERATION_X = 300;
	private final double MINIMUM_X_VELOCITY = 1;
	private final double INITIAL_JUMP_VELOCITY = 600;

	private boolean isJumping = false;
	
	//required for advanced collision detection
	private CollisionDetection collisionDetection;
	private VirtualSprite virtual = new VirtualSprite();

	private static Image image;
	private double centerX = 0;
	private double centerY = 0;
	private double width = 50;
	private double height = 50;
	private boolean dispose = false;	
	private double velocityX = 000;        	//PIXELS PER SECOND
	private double velocityY = 0;          	//PIXELS PER SECOND
	
	public JumpingSprite(double centerX, double centerY) {

		super();
		this.centerX = centerX;
		this.centerY = centerY;		

		collisionDetection = new CollisionDetection();

		//change behaviour of bounces, so that only 50% of energy is 'preserved' in horizontal bounce and 0% of energy is preserved in vertical bounce
		collisionDetection.setBounceFactorX(0.5);
		collisionDetection.setBounceFactorY(0);
		
		if (image == null) {
			try {
				image = ImageIO.read(new File("res/simple-sprite.png"));
			}
			catch (IOException e) {
				System.err.println(e.toString());
			}		
		}
		
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

		//behaviour is dependant on whether the sprite is on the 'ground' or not. 
		boolean onGround = isOnGround(universe);

		//design is to only allow change of x velocity while on ground
		if (onGround) {

			if (keyboard.keyDown(32)) {
				isJumping = true;
				this.velocityY -= INITIAL_JUMP_VELOCITY;
				onGround = false;
			}
			// RIGHT
			if (keyboard.keyDown(39)) {
				//velocityX will increase by a constant amount, up to a maximum
				velocityX += actual_delta_time * 0.001 * ACCCELERATION_X;
				if (velocityX > MAX_VELOCITY_X) {
					velocityX = MAX_VELOCITY_X;
				}
			}
			// LEFT
			else if (keyboard.keyDown(37)) {
				//velocityX will decrease by a constant amount, down to a minimum
				velocityX -= actual_delta_time * 0.001 * ACCCELERATION_X;
				if (velocityX < - MAX_VELOCITY_X) {
					velocityX = - MAX_VELOCITY_X;
				}
			}
			else {
				//if not moving left or right, then velocity will deaccelerate
				//note the use of a practical limit to zero the movement; otherwise, velocity would never be exactly zero
				if (Math.abs(this.velocityX) > MINIMUM_X_VELOCITY) {
					this.velocityX -= actual_delta_time * 0.001 *  DEACCELERATION_X * Math.signum(this.velocityX);
				}
				else {
					this.velocityX = 0;
				}
//				this.velocityX = this.velocityX * FRICTION_FACTOR_X;
			}
		}
		else {
			
		}
		
		//sprite will use 2D bounce calculation; note that this will include all sprites in the universe, not just BarrierSprites
		collisionDetection.calculate2DBounce(virtual, this, universe.getSprites(), velocityX, velocityY, actual_delta_time);
		this.centerX = virtual.getCenterX();
		this.centerY = virtual.getCenterY();
		this.velocityX = virtual.getVelocityX();
		this.velocityY = virtual.getVelocityY();			

		if (onGround == true) {
			this.velocityY = 0;
		} else {
			this.velocityY = this.velocityY + ACCCELERATION_Y * 0.001 * actual_delta_time;
		}

	}
	
	private boolean isOnGround(Universe universe) {
		boolean onGround = false;
		for (DisplayableSprite sprite: universe.getSprites()) {
			//does the bottom of this sprite touch the top of another sprite?
			boolean bottomColiding = this.getMaxY() >= (sprite.getMinY()) && this.getMaxY() <= sprite.getMinY();
			//is this sprite at least partially overlapping another sprite in the x dimension?
			boolean withinRange = this.getMaxX() > sprite.getMinX() && this.getMinX() < sprite.getMaxX();
			if (bottomColiding && withinRange) {
				onGround = true;
				break;
			}
		}
		return onGround;
	}
		
}
