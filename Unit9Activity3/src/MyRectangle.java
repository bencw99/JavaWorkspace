import java.awt.Color;
import java.awt.Graphics;
public class MyRectangle
{
	private int x1;
	private int x2;
	private int y1;
	private int y2;
	private Color myColor;
	private boolean flag;
	public MyRectangle( int x1, int y1, int x2, int y2, Color color, boolean flag)
	{
		//initializes elements
		setX1(x1);
		setX2(x2);
		setY1(y1);
		setY2(y2);
		setMyColor(color);
		setFlag(flag);
	}
	public MyRectangle()
	{
		//initializes to 0 and black
		setX1(0);
		setX2(0);
		setY1(0);
		setY2(0);
		setMyColor(Color.black);
		setFlag(false);
	}
	public void draw(Graphics screen)
	{
		//draws rectangle
		screen.setColor(myColor);
		if(flag)
			screen.fillRect(getUpperLeftX(),getUpperLeftY(),getWidth(), getHeight());
		else
			screen.drawRect(getUpperLeftX(),getUpperLeftY(),getWidth(), getHeight());
	}
	//setters and getters for class. For getters, element must be initialized
	public int getUpperLeftX()
	{
		return Math.min(x1, x2);
	}
	public int getUpperLeftY()
	{
		return Math.min(y1, y2);
	}
	public int getWidth()
	{
		return Math.abs(x1 - x2);
	}
	public int getHeight()
	{
		return Math.abs(y1 - y2);
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
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
}
