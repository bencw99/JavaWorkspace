package game;

import java.awt.Graphics;

/** An interface for drawable entities of a game
 * 
 * @author Benjamin Cohen-Wang
 */
public interface Drawable
{
	/** Draws this instance on the given graphics object
	 * 
	 * @param graphics	the graphics object to be drawn on
	 */
	public abstract void draw(Graphics graphics);
}
