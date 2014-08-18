package graphics.polygon;

import java.awt.Color;

import graphics.projection.*;

/** A class representing a polygon in 3D space
 * @author Benjamin Cohen-Wang
 */
public class Polygon3D
{
	/** The collection of points in 3D space comprising this 3D Polygon */
	private Point3D[] points;
	
	/** The color this polygon is projected with */
	private Color color;
	
	/** Parameterized constructor, initializes point array of instance to given array of points
	 * 
	 * @param points	the Point3D array this instance will be initialized to
	 */
	public Polygon3D(Point3D ... points)
	{
		this(Color.LIGHT_GRAY, points);
	}
	
	/** Parameterized constructor, initializes point array of instance to given array of points and color to given color
	 * 
	 * @param points	the Point3D array this instance will be initialized to
	 * @param color		the color of this Polygon3D
	 */
	public Polygon3D(Color color, Point3D ... points)
	{
		this.points = points;
		this.color = color;
	}
	
	/** Parameterized constructor, initializes point array of instance to array of point given by arrays of coordinates
	 * 
	 * @param xCoords	the x-coordinate array that point array of instance initialized to
	 * @param yCoords	the y-coordinate array that point array of instance initialized to
	 * @param zCoords	the z-coordinate array that point array of instance initialized to
	 * @param coordNum	the number of coordinates this polygon is composed of
	 */
	public Polygon3D(double[] xCoords, double[] yCoords, double[] zCoords, int coordNum)
	{
		this(xCoords, yCoords, zCoords, coordNum, Color.LIGHT_GRAY);
	}
	
	/** Parameterized constructor, initializes point array of instance to array of point given by arrays of coordinates and color to given color
	 * 
	 * @param xCoords	the x-coordinate array that point array of instance initialized to
	 * @param yCoords	the y-coordinate array that point array of instance initialized to
	 * @param zCoords	the z-coordinate array that point array of instance initialized to
	 * @param coordNum	the number of coordinates this polygon is composed of
	 */
	public Polygon3D(double[] xCoords, double[] yCoords, double[] zCoords, int coordNum, Color color)
	{
		points = new Point3D[coordNum];
		for(int i = 0; i < coordNum; i ++)
		{
			points[i] = new Point3D(xCoords[i], yCoords[i], zCoords[i]);
		}
		this.color = color;
	}
	
	/** Returns a translated instance of this Polygon3D
	 * 
	 * @param x	the x-coordinate translated by
	 * @param y	the y-coordinate translated by
	 * @param z	the z-coordinate translated by
	 * @return a version of this instance translated by given values for x, y, and z
	 */
	public Polygon3D translate(double x, double y, double z)
	{
		Point3D[] translatedPoints = new Point3D[points.length];
		
		for(int i = 0; i < translatedPoints.length; i ++)
		{
			translatedPoints[i] = points[i].translate(x, y, z);
		}
		
		return new Polygon3D(translatedPoints);
	}
	
	/** Returns a rotated instance of this Polygon3D about the x-axis through the given angle
	 * 
	 * @param turnAngle	the angle rotated through
	 * @return a version of this instance rotated the given angle about the x-axis
	 */
	public Polygon3D rotAboutX(double turnAngle)
	{
		Point3D[] rotatedPoints = new Point3D[points.length];
		
		for(int i = 0; i < points.length; i ++)
		{
			rotatedPoints[i] = points[i].rotAboutX(turnAngle);
		}
		
		return new Polygon3D(rotatedPoints);
	}
	
	/** Returns a rotated instance of this Polygon3D about the y-axis through the given angle
	 * 
	 * @param turnAngle	the angle rotated through
	 * @return a version of this instance rotated the given angle about the y-axis
	 */
	public Polygon3D rotAboutY(double turnAngle)
	{
		Point3D[] rotatedPoints = new Point3D[points.length];
		
		for(int i = 0; i < points.length; i ++)
		{
			rotatedPoints[i] = points[i].rotAboutY(turnAngle);
		}
		
		return new Polygon3D(rotatedPoints);
	}
	
	/** Returns a rotated instance of this Polygon3D about the z-axis through the given angle
	 * 
	 * @param turnAngle	the angle rotated through
	 * @return a version of this instance rotated the given angle about the z-axis
	 */
	public Polygon3D rotAboutZ(double turnAngle)
	{
		Point3D[] rotatedPoints = new Point3D[points.length];
		
		for(int i = 0; i < points.length; i ++)
		{
			rotatedPoints[i] = points[i].rotAboutZ(turnAngle);
		}
		
		return new Polygon3D(rotatedPoints);
	}
	
	/** Method returning projection of this polygon on to given plane
	 * 
	 * @param view	The View that this polygon is being looked at from
	 * @return the Projection object resulting from the projection of instance on to given plane 
	 */
	public PolygonProjection getProjection(View view)
	{
		Point3D viewPoint = view.getView();
		Point3D viewedPoint = view.getViewed();
		
		double xDisp = viewedPoint.getX() - viewPoint.getX();
		double yDisp = viewedPoint.getY() - viewPoint.getY();
		double zDisp = viewedPoint.getZ() - viewPoint.getZ();
		
		double xTurnAxis = yDisp;
		double yTurnAxis = -xDisp;
		
		double zTurnAngle = Math.atan2(yTurnAxis, xTurnAxis);
		double turnAngle = Math.acos(zDisp/Math.sqrt(Math.pow(xDisp, 2) + Math.pow(yDisp, 2) + Math.pow(zDisp, 2)));
		
		Polygon3D rotatedPoly;
		
		/* Translation of space to position rotation axis at origin */
		rotatedPoly = translate(-viewPoint.getX(), -viewPoint.getY(), -viewPoint.getZ());
		
		/* Rotation of space about z-axis to position rotation axis at x-axis */
		rotatedPoly = rotatedPoly.rotAboutZ(-zTurnAngle);
		
		/* Rotation of space around the x-axis through the angle that positions the view plane at z = k */
		rotatedPoly = rotatedPoly.rotAboutX(turnAngle);
		
		/* Rotation of space about z-axis to reverse the previous z-rotation */
		rotatedPoly = rotatedPoly.rotAboutZ(zTurnAngle);
		
		Point2D[] projectedPoints = new Point2D[points.length];
		Point3D[] rotatedPoints = rotatedPoly.getPoints();
		
		Point3D maxPoint = rotatedPoints[0];
		Point3D minPoint = rotatedPoints[0];
		
		double zSum = 0;
		
		for(int i = 0; i < points.length; i ++)
		{
			/* Deals with polygons behind view plane */
			if(rotatedPoints[i].getZ() < -5)
			{
				return new PolygonProjection(new Point2D[0]);
			}
			
			/* Projection perspective calculations */
			double perspectiveMod = view.getFocalLength()/(rotatedPoints[i].getZ() + view.getFocalLength());
			projectedPoints[i] = new Point2D(perspectiveMod*rotatedPoints[i].getX() + view.getWidth()/2, perspectiveMod*rotatedPoints[i].getY() + view.getHeight()/2);
			
			/* Projection incline calculations */
			if(rotatedPoints[i].getZ() > maxPoint.getZ())
			{
				maxPoint = rotatedPoints[i];
			}
			if(rotatedPoints[i].getZ() <= minPoint.getZ())
			{
				minPoint = rotatedPoints[i];
			}
			
			/* Projection priority calculations */
			zSum += rotatedPoints[i].getZ();
		}
		
		double priority = zSum/rotatedPoints.length;
		
		double incline = Math.abs(maxPoint.getZ() - minPoint.getZ())/Math.sqrt(Math.pow(maxPoint.getX() - minPoint.getX(), 2) + Math.pow(maxPoint.getY() - minPoint.getY(), 2)); 
		
		return ((new PolygonProjection(projectedPoints, priority, incline, color)).rotate(new Point2D(view.getWidth()/2, view.getHeight()/2), view.getTurnAngle()));
	}
	
	/**
	 * @return the array of 3D points comprising this 3D Polygon
	 */
	public Point3D[] getPoints()
	{
		return points;
	}
}
