import java.awt.Image;

/*
 * This interface provides methods which are required for any sprite which is to be displayed by the interface.
 * Thus, all sprite classes in the animation project will need to implement it. As this is an interface, the
 * AnimationFrame can handle all sprites generically.
 * 
 * By design, a sprite is contained within a rectangle bounded by (minX, minY) in the top-left corner and (maxX, maxY) in the
 * bottom-right corner. These are exposed through corresponding accessor methods. The sprite will also have a width, height,
 * centerX, and centerY accessor methods. 
 * 
 * These should all correspond (i.e. maxX - minX == width, etc); the sprite itself can decide how to store these
 * values. Note that if you store the bounds, you can calculate the center and width/height, or vice versa. The intent
 * behind having both is that depending on context it may sometimes make sense to access the bounds (e.g. for collision detection)
 * while at other times it makes sense to have the center and width / height (e.g. when instantiating).
 *  
 * 
 */

public interface DisplayableSprite {

	public abstract Image getImage();
	
	public boolean getVisible();

	public double getMinX();

	public double getMaxX();

	public double getMinY();

	public double getMaxY();

	public double getHeight();

	public double getWidth();

	public double getCenterX();

	public double getCenterY();
	
	public boolean getDispose();
	
	public void setDispose(boolean dispose);

	public void update(Universe universe, KeyboardInput keyboard, long actual_delta_time);	
	
}
