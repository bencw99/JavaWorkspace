package graphics.projection;

import java.awt.*;

/** A class representing a 2D Polygon
 * @author Benjamin Cohen-Wang
 */
public class PolygonProjection
{
	/** The collection of points on the screen comprising this projection */
	private Point2D[] points;
	
	/** The color this projection is drawn with */
	Color color;
	
	/** The drawing priority of this polygon */
	private double priority;
	
	/** The incline at which the polygon represented by this projection is */
	private double incline;
	
	/** Parameterized constructor, initializes point array of instance to given array of points and the priority to 0
	 * 
	 * @param points	The Point2D array this instance will be initialized to
	 */
	public PolygonProjection(Point2D[] points)
	{
		this(points, 0, 0, Color.LIGHT_GRAY);
	}
	
	/** Parameterized constructor, initializes point array of instance to given array of points and the priority to the given priority
	 * 
	 * @param points	the Point2D array this instance will be initialized to
	 * @param priority	the priority this instance will be initialized to have
	 * @param incline	the incline this instance will be set to
	 * @param color		the color this projection is drawn with
	 */
	public PolygonProjection(Point2D[] points, double priority, double incline, Color color)
	{
		this.points = points;
		this.priority = priority;
		this.incline = incline;
		this.color = color;
	}
	
	/** Returns a rotated instance of PolygonProjection rotated about the given point through the given angle
	 * 
	 * @param point	the point in the 2D graph rotated about
	 * @param angle	the angle rotated through
	 * @return an instance of PolygonProjection that is a rotated form of this instance
	 */
	public PolygonProjection rotate(Point2D point, double angle)
	{
		Point2D[] rotatedPoints = new Point2D[points.length];
		
		for(int i = 0; i < points.length; i ++)
		{
			double xCoord = points[i].getX();
			double yCoord = points[i].getY();
			double cos = Math.cos(angle);
			double sin = Math.sin(angle);
			rotatedPoints[i] = new Point2D(xCoord*cos - yCoord*sin, xCoord*sin + yCoord*cos);
		}
		
		return new PolygonProjection(rotatedPoints);
	}
	
	/** Draws this instance on to the given Graphics object
	 * 
	 * @param graphics	the graphics object drawn on
	 */
	public void paint(Graphics graphics)
	{
		Color currentColor = graphics.getColor();
		
		graphics.setColor(color);
		
		int[] xPoints = new int[points.length];
		int[] yPoints = new int[points.length];
		
		for(int i = 0; i < points.length; i ++)
		{
			xPoints[i] = (int)points[i].getX();
			yPoints[i] = (int)points[i].getY();
		}
		
		graphics.fillPolygon(xPoints, yPoints, points.length);
		
		graphics.setColor(Color.BLACK);
		
		graphics.drawPolygon(xPoints, yPoints, points.length);
		
		graphics.setColor(currentColor);
	}
	
	/**
	 * @return the array of 2D points comprising this PolygonProjection
	 */
	public Point2D[] getPoints()
	{
		return points;
	}
	
	/**
	 * @return the priority of this instance
	 */
	public double getPriority()
	{
		return priority;
	}
	
	/**
	 * @return the incline of this instance
	 */
	public double getIncline()
	{
		return incline;
	}
}
