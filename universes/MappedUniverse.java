import java.util.ArrayList;

public class MappedUniverse implements Universe {

	private boolean complete = false;
	private ArrayList<Background> backgrounds = null;
	private Background background = null;	
	private DisplayableSprite player1 = null;
	private ArrayList<DisplayableSprite> sprites = new ArrayList<DisplayableSprite>();

	public MappedUniverse () {

		background = new MappedBackground();
		ArrayList<DisplayableSprite> barriers = ((MappedBackground)background).getBarriers();
		backgrounds =new ArrayList<Background>();
		backgrounds.add(background);
		
		player1 = new CollidingSprite(MappedBackground.TILE_HEIGHT * 2, MappedBackground.TILE_WIDTH * 2, MappedBackground.TILE_HEIGHT * 0.9, MappedBackground.TILE_HEIGHT * 0.9);
		
		sprites.add(player1);
		sprites.addAll(barriers);
		
	}

	public double getScale() {
		return 1;
	}	
	
	public double getXCenter() {
		return this.player1.getCenterX();
	}

	public double getYCenter() {
		return this.player1.getCenterY();
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

	public ArrayList<Background> getBackgrounds() {
		return backgrounds;
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
	
	public void update(KeyboardInput keyboard, long actual_delta_time) {

		if (keyboard.keyDownOnce(27)) {
			complete = true;
		}
		
		for (int i = 0; i < sprites.size(); i++) {
			DisplayableSprite sprite = sprites.get(i);
			sprite.update(this, keyboard, actual_delta_time);
    	} 
	}

	public String toString() {
		return "MappedUniverse";
	}	

}
