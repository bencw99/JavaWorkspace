package graphics.projection;
/** A class representing a point on the screen in the projection of 3D space
 * @author Benjamin Cohen-Wang
 */
public class Point2D
{
	/** The x-coordinate of the point on the projection */
	private double x;
	
	/** The y-coordinate of the point on the projection */
	private double y;
	
	/** Default constructor, initializes point to coordinates (0, 0)
	 * 
	 */
	public Point2D()
	{
		x = 0;
		y = 0;
	}
	
	/** Parameterized constructor, initializes point coordinates to given parameters
	 * 
	 * @param x	The x-coordinate instance initialized to
	 * @param y	The y-coordinate instance initialized to
	 */
	public Point2D(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	
	/**
	 * @return x-coordinate of instance
	 */
	public double getX()
	{
		return x;
	}

	/**
	 * @return y-coordinate of instance
	 */
	public double getY()
	{
		return y;
	}
}
