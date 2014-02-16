import java.awt.Color;
import java.awt.Graphics;
public class MyLine 
{
	private int x1;
	private int x2;
	private int y1;
	private int y2;
	private Color myColor;
	public MyLine( int x1, int y1, int x2, int y2, Color color)
	{
		setX1(x1);
		setX2(x2);
		setY1(y1);
		setY2(y2);
		setMyColor(color);
	}
	public MyLine()
	{
		setX1(0);
		setX2(0);
		setY1(0);
		setY2(0);
		setMyColor(Color.black);
	}
	public void draw(Graphics screen)
	{
		screen.setColor(myColor);
		screen.drawLine(getX1(), getY1(), getX2(), getY2());
	}
	public int getX1() {
		return x1;
	}
	public void setX1(int x1) {
		this.x1 = x1;
		if(x1 < 0)
		{
			this.x1 = 0;
		}
	}
	public int getX2() {
		return x2;
	}
	public void setX2(int x2) {
		this.x2 = x2;
		if(x2 < 0)
		{
			this.x2 = 0;
		}
	}
	public int getY1() {
		return y1;
	}
	public void setY1(int y1) {
		this.y1 = y1;
		if(y1 < 0)
		{
			this.y1 = 0;
		}
	}
	public int getY2() {
		return y2;
	}
	public void setY2(int y2) {
		this.y2 = y2;
		if(y2 < 0)
		{
			this.y2 = 0;
		}
	}
	public Color getMyColor() {
		return myColor;
	}
	public void setMyColor(Color myColor) {
		this.myColor = myColor;
	}
}
