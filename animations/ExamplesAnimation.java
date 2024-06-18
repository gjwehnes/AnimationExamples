
public class ExamplesAnimation implements Animation {

	private int universeCount = 0;
	private Universe current = null;

	@Override
	public Universe switchUniverse(Object event) {

		universeCount++;
		
		if (universeCount == 1) {
			this.current = new SimpleSpriteUniverse();
		}
		else if (universeCount == 2) {
			this.current = new CollidingSpritesUniverse();
		}
		else if (universeCount == 3) {
			this.current = new JumpingSpriteUniverse();
		}
		else if (universeCount == 4) {
			this.current = new AnimatedSpritesUniverse();
		}
		else if (universeCount == 5) {
			this.current = new SateliteSpriteUniverse();
		}				
		else if (universeCount == 6) {
			this.current = new PatternedUniverse();
		}
		else if (universeCount == 7) {
			this.current = new MappedUniverse();
		}
		else if (universeCount == 8) {
			this.current = new MultipleBackgroundUniverse();
		}
		else if (universeCount == 9) {
			return new StarfieldUniverse();
		}
		else {
			this.current = null;
		}
		
		return this.current;

	}

	public Universe getCurrentUniverse() {
		return this.current;
	}

	
}
