package game.updateable;
import java.awt.Graphics;
public interface Updatable
{
	public void init();
	public void update();
	public void draw(Graphics screen);
}
