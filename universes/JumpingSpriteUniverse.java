import java.awt.Rectangle;
import java.util.ArrayList;

public class JumpingSpriteUniverse implements Universe {

	private boolean complete = false;	
	private DisplayableSprite player1 = null;
	private ArrayList<DisplayableSprite> sprites = new ArrayList<DisplayableSprite>();
	
	public JumpingSpriteUniverse () {
	
		player1 = new JumpingSprite(0,AnimationFrame.SCREEN_HEIGHT / 2 - 50);
		sprites.add(player1);
		
		for (int i = 0; i < 5; i++) {
			BouncingSprite sprite = new BouncingSprite(i * 100 - 200 , -200 , 200, 0);
			sprite.setAccelerationY(500);
			sprites.add(sprite);			
		}
		
		//top platform
		sprites.add(new BarrierSprite(-50,-114, 50, -100, true));
		//bottom platform
		sprites.add(new BarrierSprite(-150,84, 150, 100, true));
		//bottom
		sprites.add(new BarrierSprite(AnimationFrame.SCREEN_WIDTH / -2,AnimationFrame.SCREEN_HEIGHT / 2 - 16, AnimationFrame.SCREEN_WIDTH / 2, AnimationFrame.SCREEN_HEIGHT / 2, true));
		//left
		sprites.add(new BarrierSprite(AnimationFrame.SCREEN_WIDTH / -2,AnimationFrame.SCREEN_HEIGHT / -2, AnimationFrame.SCREEN_WIDTH / -2 + 16, AnimationFrame.SCREEN_HEIGHT / 2, true));
		//right
		sprites.add(new BarrierSprite(AnimationFrame.SCREEN_WIDTH / 2 - 16,AnimationFrame.SCREEN_HEIGHT / -2, AnimationFrame.SCREEN_WIDTH / 2, AnimationFrame.SCREEN_HEIGHT / 2, true));
	
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
		this.setXCenter(xCenter);
	}

	public void setYCenter(double yCenter) {
		this.setYCenter(yCenter);
	}
	
	public boolean isComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
		complete = true;
	}

	public ArrayList<Background> getBackgrounds() {
		return null;
	}	

	public DisplayableSprite getPlayer1() {
		return player1;
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
	
	public String toString() {
		return "JumpingSpriteUniverse";
	}	
	
}
