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
	
	/** The width of the view plane of this view */
	private int width;
	
	/** The height of the view plane of this view */
	private int height;
	
	/** The factor determining the rate of change in view size due to perspective */
	public static final double EYE_PLANE_DIST = 700.0;
	
	/** Parameterized constructor, initializes the point viewed to and from
	 * @param viewPoint	the point being viewed from
	 * @param viewedPoint	the point being viewed
	 */
	public View(Point3D viewPoint, Point3D viewedPoint)
	{
		this(viewPoint, viewedPoint, 0);
	}
	
	/** Parameterized constructor, initializes the point viewed to and from and turn angle
	 * @param viewPoint	the point being viewed from
	 * @param viewedPoint	the point being viewed
	 * @param turnAngle	the turn angle of this view
	 */
	public View(Point3D viewPoint, Point3D viewedPoint, double turnAngle)
	{
		this(viewPoint, viewedPoint, turnAngle, 500, 500);
	}
	
	/** Parameterized constructor, initializes the point viewed to and from and turn angle
	 * @param viewPoint	the point being viewed from
	 * @param viewedPoint	the point being viewed
	 * @param turnAngle	the turn angle of this view
	 * @param width	the value the width of this will be set to
	 * @param height	the value the height of this will be set to
	 */
	public View(Point3D viewPoint, Point3D viewedPoint, double turnAngle, int width, int height)
	{
		this.viewPoint = viewPoint;
		this.viewedPoint = viewedPoint;
		this.turnAngle = turnAngle;
		this.width = width;
		this.height = height;
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
	
	/** Translates the viewed point of instance by the given values
	 * 
	 * @param xInc	The value the x-coordinate of the viewed point is translated by
	 * @param yInc	The value the y-coordinate of the viewed point is translated by
	 * @param zInc	The value the z-coordinate of the viewed point is translated by
	 */
	public void translateViewed(double xInc, double yInc, double zInc)
	{
		viewedPoint = viewedPoint.translate(xInc, yInc, zInc);
	}
	
	/** Zooms in on the viewed Point by moving the view point towards it by an amount given by the magnification parameter
	 * 
	 * @param magnification	the factor the viewed point is magnified by
	 */
	public void zoom(double magnification)
	{
		double xInc = (magnification - 1)*(viewedPoint.getX() - viewPoint.getX());
		double yInc = (magnification - 1)*(viewedPoint.getY() - viewPoint.getY());
		double zInc = (magnification - 1)*(viewedPoint.getZ() - viewPoint.getZ());
		viewPoint = viewPoint.translate(xInc, yInc, zInc);
	}
	
	/**
	 * @return the point viewed from
	 */
	public Point3D getView()
	{
		return viewPoint;
	}

	/**
	 * @return the point viewed
	 */
	public Point3D getViewed()
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
	 * @return the width
	 */
	public int getWidth()
	{
		return width;
	}

	/**
	 * @return the height
	 */
	public int getHeight()
	{
		return height;
	}

	/**
	 * @param viewPoint the value the point viewed from is set to
	 */
	public void setView(Point3D viewPoint)
	{
		this.viewPoint = viewPoint;
	}

	/**
	 * @param viewedPoint the value the point viewed is set to
	 */
	public void setViewed(Point3D viewedPoint)
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
	
	/**
	 * @param width the value the width is set to
	 */
	public void setWidth(int width)
	{
		this.width = width;
	}

	/**
	 * @param height the value the height is set to
	 */
	public void setHeight(int height)
	{
		this.height = height;
	}
}
