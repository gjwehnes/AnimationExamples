import java.util.ArrayList;

/*
 * Each Universe implements the Universe interface. This allows all other classes to be able to generically access important
 * information such as the scale, center, and the list of backgrounds and sprites that it contains.
 */
public class SimpleSpriteUniverse implements Universe {

	private boolean complete = false;	
	private DisplayableSprite player1 = null;
	private ArrayList<DisplayableSprite> sprites = new ArrayList<DisplayableSprite>();

	public SimpleSpriteUniverse () {

		/*
		 * Constructors truly "create" the universe.
		 */
		
		player1 = new SimpleSprite(0,0);
		ImageSprite background = new ImageSprite(-407, -307, 410, 308, "res/backgrounds/grid.jpg");

		/*
		 * The order in which sprites are added to the sprites list matters. Sprites added earlier will have a lower
		 * index and will be rendered earlier. Sprites added later will thus appear 'in front' of sprites added earlier.
		 */
		sprites.add(background);
		sprites.add(player1);
		
	}

	/*
	 * The 'scale at which a universe is rendered is normally determined by the gui, as it likely depends on the size
	 * of the frame in which it is rendered. However, it may still make sense for the universe to determine scale through
	 * this accessor. Currently the gui ignores the value and sets the scale based on user input but it could be used..
	 */
	public double getScale() {
		return 1;
	}

	/*
	 * The logical x and y coordinate determine where the gui should center. That is, centerX and centerY will correspond
	 * to the middle of the frame. As this is hard-coded here, the player1 sprite will move around the screen when its
	 * centerX and centerY are changed.
	 */
	public double getXCenter() {
		return 0;
	}

	public double getYCenter() {
		return 0;
	}

	/*
	 * In some cases, you may want the gui to determine the logical x and y coordinates, in which case these mutators
	 * can be used.
	 */
	public void setXCenter(double xCenter) {
	}

	public void setYCenter(double yCenter) {
	}
	
	public boolean isComplete() {
		return complete;
	}

	/*
	 * In some cases, you may want the gui to terminate a universe, in which case this mutators can be used.
	 */
	public void setComplete(boolean complete) {
		complete = true;
	}

	public ArrayList<Background> getBackgrounds() {
		return null;
	}	

	public ArrayList<DisplayableSprite> getSprites() {
		return sprites;
	}

	public boolean centerOnPlayer() {
		return false;
	}		

	public void update(Animation animation, long actual_delta_time) {
		
		for (int i = 0; i < sprites.size(); i++) {
			DisplayableSprite sprite = sprites.get(i);
			sprite.update(this, actual_delta_time);
    	} 

	}

	private double calculateStandardAngle(double xA, double yA, double xB, double yB) {
		
		// An example of how to calculate angles in java that corresponds to the
		// mathematical unit circle. This is somewhat complicated because
		// the y dimension increases towards the bottom of the screen, the standard
		// angle is reversed from the mathematical definition. 0 is still to the right,
		// but positive angles indicate clockwise rotation. 90 is the bottom of the screen,
		// 180 is the left, and 270 is the top.
		// 
		// Thus, quadrants are reversed. Top right is I, bottom right is II,
		// bottom left is III, top left is IV

		// As a further complication, methods in the Math class work with radians, which
		// are not necessarily intuitive
		
		double x = xB - xA;
		double y = yB - yA;
		
		// calculate tan ratio
		double tan = y / x;
		// calculate reference angle (thetaR)
		// note the conversion from radians to degrees
		// reference angle is always positive; the Math.abs method removes the sign.
		double thetaR = Math.abs( Math.toDegrees(Math.atan(tan)));
		
		//based on the quadrant, reference angle will give us standard angle (theta)
		double theta;

		if (x >= 0 && y >= 0) {
			//quadrant I
			theta = thetaR;			
		} else if (x < 0 && y >= 0) {
			//quadrant II
			theta = 180 - thetaR;		
		} else if (x < 0 && y < 0) {
			//quadrant III
			theta = 180 + thetaR;		
		} else {
			//quadrant IV
			theta = 360 - thetaR;		
		}
		
		return theta;
	}
	
	public String toString() {		
		return String.format("mouseX: %3.2f; mouseY: %3.2f; standard angle: %5.1f; buttons: %s%s",
				MouseInput.logicalX,
				MouseInput.logicalY,
				calculateStandardAngle(
						this.player1.getCenterX(),
						this.player1.getCenterY(),
						MouseInput.logicalX,
						MouseInput.logicalY),
				MouseInput.leftButtonDown ? "X" : "-",
				MouseInput.rightButtonDown ? "X" : "-");
	}


}
