import javax.swing.*;

import java.awt.*;
import java.awt.Dialog.ModalityType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseMotionAdapter;


public class AnimationExamplesFrame extends AnimationFrame{

	JComboBox cboUniverse;	

	public AnimationExamplesFrame(Animation animation) {
		
		super(animation);

		cboUniverse  = new JComboBox();
		cboUniverse.setBounds(800,20,192,32);
		cboUniverse.addItem("SimpleSprite");
		cboUniverse.addItem("CollidingSpritesUniverse");
		cboUniverse.addItem("JumpingSpriteUniverse");
		cboUniverse.addItem("AnimatedSpritesUniverse");
		cboUniverse.addItem("SateliteSpriteUniverse");
		cboUniverse.addItem("PatternedUniverse");
		cboUniverse.addItem("MappedUniverse");
		cboUniverse.addItem("MultipleBackgroundUniverse");
		cboUniverse.addItem("StarfieldUniverse");
		cboUniverse.setFocusable(false);
		getContentPane().add(cboUniverse);
		getContentPane().setComponentZOrder(cboUniverse, 0);
		cboUniverse.addItemListener(new ItemListener() {			
			@Override
			public void itemStateChanged(ItemEvent e) {
				cboUniverse_itemStateChanged(e);
			}
		});			
				
	}
	
	private void cboUniverse_itemStateChanged(ItemEvent e) {
	
		/*
		 * In Java Swing, you have to be very careful with events, as one
		 * user action can trigger multiple events. In this case, changing
		 * the value of a combobox with trigger both a DESELECTED and 
		 * SELECTED event
		 */
		if (e.getStateChange() == ItemEvent.SELECTED) {
					
			if (this.cboUniverse.getSelectedIndex() == 0) {
				this.animation.switchUniverse(SimpleSpriteUniverse.class.toString());		
			}
			else if (cboUniverse.getSelectedIndex() == 1) {
				this.animation.switchUniverse(CollidingSpritesUniverse.class.toString());		
			}
			else if (cboUniverse.getSelectedIndex() == 2) {
				this.animation.switchUniverse(JumpingSpriteUniverse.class.toString());		
			}
			else if (cboUniverse.getSelectedIndex() == 3) {
				this.animation.switchUniverse(AnimatedSpritesUniverse.class.toString());		
			}
			else if (cboUniverse.getSelectedIndex() == 4) {
				this.animation.switchUniverse(SateliteSpriteUniverse.class.toString());		
			}
			else if (cboUniverse.getSelectedIndex() == 5) {
				this.animation.switchUniverse(PatternedUniverse.class.toString());		
			}
			else if (cboUniverse.getSelectedIndex() == 6) {
				this.animation.switchUniverse(MappedUniverse.class.toString());		
			}
			else if (cboUniverse.getSelectedIndex() == 7) {
				this.animation.switchUniverse(MultipleBackgroundUniverse.class.toString());		
			}
			else if (cboUniverse.getSelectedIndex() == 8) {
				this.animation.switchUniverse(StarfieldUniverse.class.toString());		
			}
			else {			
			}
			
			this.universe = animation.getCurrentUniverse();
			this.sprites = universe.getSprites();
			this.backgrounds = universe.getBackgrounds();
			this.scale = universe.getScale();
		}
	}
		
}