package graphics.projection;

import graphics.polygon.*;

/** A class representing a viewpoint in 3D space
 * @author Benjamin Cohen-Wang
 */
public class View
{
	/** The point viewed from */
	private Point3D viewPoint;
	
	/** The point viewed */
	private Point3D viewedPoint;
	
	/** The angle viewed at in the plane */
	private double turnAngle;
	
	/** The factor determining the rate of change in view size due to perspective */
	public static final double EYE_PLANE_DIST = 100.0;
	
	/** Parameterized constructor, initializes the point viewed to and from
	 * @param viewPoint	The point being viewed from
	 * @param viewedPoint	The point being viewed
	 */
	public View(Point3D viewPoint, Point3D viewedPoint)
	{
		this(viewPoint, viewedPoint, 0);
	}
	
	/** Parameterized constructor, initializes the point viewed to and from and turn angle
	 * @param viewPoint	The point being viewed from
	 * @param viewedPoint	The point being viewed
	 */
	public View(Point3D viewPoint, Point3D viewedPoint, double turnAngle)
	{
		this.viewPoint = viewPoint;
		this.viewedPoint = viewedPoint;
		this.turnAngle = turnAngle;
	}
	
	/** Translates the view point of instance by the given values
	 * 
	 * @param xInc	The value the x-coordinate of the view point is translated by
	 * @param yInc	The value the y-coordinate of the view point is translated by
	 * @param zInc	The value the z-coordinate of the view point is translated by
	 */
	public void translateView(double xInc, double yInc, double zInc)
	{
		viewPoint = viewPoint.translate(xInc, yInc, zInc);
	}
	
	/** Zooms in on the viewed Point by moving the view point towards it by an amount given by the magnification parameter
	 * 
	 * @param magnification	the factor the viewed point is magnified by
	 */
	public void zoom(double magnification)
	{
		//Complete code
	}
	
	/**
	 * @return the point viewed from
	 */
	public Point3D getViewPoint()
	{
		return viewPoint;
	}

	/**
	 * @return the point viewed
	 */
	public Point3D getViewedPoint()
	{
		return viewedPoint;
	}
	
	/**
	 * @return the turn angle
	 */
	public double getTurnAngle()
	{
		return turnAngle;
	}

	/**
	 * @param viewPoint the value the point viewed from is set to
	 */
	public void setViewPoint(Point3D viewPoint)
	{
		this.viewPoint = viewPoint;
	}

	/**
	 * @param viewedPoint the value the point viewed is set to
	 */
	public void setViewedPoint(Point3D viewedPoint)
	{
		this.viewedPoint = viewedPoint;
	}
	
	/**
	 * @param turnAngle	the angle the turn angle is set to
	 */
	public void setTurnAngle(double turnAngle)
	{
		this.turnAngle = turnAngle;
	}
}
