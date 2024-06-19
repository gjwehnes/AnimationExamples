
public class ExamplesAnimation implements Animation {

	private int universeCount = 0;
	private Universe current = null;

	@Override
	public Universe switchUniverse(Object event) {

		String type;
		try {
			type = event.toString();
		} catch (Exception e) {
			type = "";
		}
								
		
		if (type.equals(SimpleSpriteUniverse.class.toString())) {
			this.current = new SimpleSpriteUniverse();
		}
		else if (type.equals(CollidingSpritesUniverse.class.toString())) {
			this.current = new CollidingSpritesUniverse();
		}
		else if (type.equals(JumpingSpriteUniverse.class.toString())) {
			this.current = new JumpingSpriteUniverse();
		}
		else if (type.equals(AnimatedSpritesUniverse.class.toString())) {
			this.current = new AnimatedSpritesUniverse();
		}
		else if (type.equals(SateliteSpriteUniverse.class.toString())) {
			this.current = new SateliteSpriteUniverse();
		}				
		else if (type.equals(PatternedUniverse.class.toString())) {
			this.current = new PatternedUniverse();
		}
		else if (type.equals(MappedUniverse.class.toString())) {
			this.current = new MappedUniverse();
		}
		else if (type.equals(MultipleBackgroundUniverse.class.toString())) {
			this.current = new MultipleBackgroundUniverse();
		}
		else if (type.equals(StarfieldUniverse.class.toString())) {
			this.current = new StarfieldUniverse();
		}
		else {
			this.current = new SimpleSpriteUniverse();	
		}
		
		return this.current;

	}

	public Universe getCurrentUniverse() {
		return this.current;
	}

	
}
