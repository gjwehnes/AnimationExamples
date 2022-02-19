import java.util.ArrayList;

public class MultipleBackgroundUniverse implements Universe {

	private boolean complete = false;	
	private Background mountainBackground = null;	
	private Background forestBackground = null;	
	private Background skyBackground = null;	
	private ArrayList<Background> backgrounds = null;
	private DisplayableSprite player1 = null;
	private ArrayList<DisplayableSprite> sprites = new ArrayList<DisplayableSprite>();

	public MultipleBackgroundUniverse () {

		skyBackground = new NightSkyBackground();
		mountainBackground = new MountainBackground();
		forestBackground = new ForestBackground();

		backgrounds = new ArrayList<Background>();
		backgrounds.add(skyBackground);
		backgrounds.add(mountainBackground);
		backgrounds.add(forestBackground);
		
		
		this.setXCenter(0);
		this.setYCenter(0);
		player1 = new SimpleSprite(00,0);
		sprites.add(player1);
			
	}

	public double getScale() {
		return 1;
	}

	public double getXCenter() {
		return 0;
	}

	public double getYCenter() {
		return 0;
	}

	public void setXCenter(double xCenter) {
	}

	public void setYCenter(double yCenter) {
	}

	public boolean isComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
		complete = true;
	}

	public DisplayableSprite getPlayer1() {
		return player1;
	}

	public ArrayList<DisplayableSprite> getSprites() {
		return sprites;
	}

	public boolean centerOnPlayer() {
		return true;
	}		

	@Override
	public ArrayList<Background> getBackgrounds() {
		return backgrounds;
	}	
	
	public void update(KeyboardInput keyboard, long actual_delta_time) {

		if (keyboard.keyDownOnce(27)) {
			complete = true;
		}
		
		this.skyBackground.setShiftX((int) (player1.getCenterX() * 1));
		this.skyBackground.setShiftY((int) (player1.getCenterY() * 1 - 200));
		this.mountainBackground.setShiftX((int) (player1.getCenterX() * 0.75));
		this.mountainBackground.setShiftY((int) (player1.getCenterY() * 1));
		this.forestBackground.setShiftX((int) (player1.getCenterX() * 0.5));
		this.forestBackground.setShiftY((int) (player1.getCenterY() * 1 + 20));
		
		for (int i = 0; i < sprites.size(); i++) {
			DisplayableSprite sprite = sprites.get(i);
			sprite.update(this, keyboard, actual_delta_time);
    	} 
		
		
	}

	public String toString() {
		return "MultiBackgroundUniverse";
	}
}
