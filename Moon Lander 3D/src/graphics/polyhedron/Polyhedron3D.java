package graphics.polyhedron;

import java.awt.Color;

import graphics.polygon.*;
import graphics.projection.Point2D;
import graphics.projection.PolygonProjection;
import graphics.projection.PolyhedronProjection;
import graphics.projection.View;

/** A class representing a polyhedron in 3D space
 * 
 * @author Benjamin Cohen-Wang
 */
public class Polyhedron3D
{
	/** The array of polygons in 3D space comprising this polyhedron */
	private Polygon3D[] polygons;
	
	/** Default constructor
	 * 
	 */
	public Polyhedron3D()
	{
		this(new Polygon3D[0]);
	}
	
	/** Parameterized constructor, initializes this instance to the given array of polygons in 3D space
	 * 
	 * @param polygons	the array of polygons this instance will be set to contain
	 */
	public Polyhedron3D(Polygon3D[] polygons)
	{
		this.polygons = polygons;
	}
	
	/** Returns a translated instance of this Polyhedron3D
	 * 
	 * @param x	the x-coordinate translated by
	 * @param y	the y-coordinate translated by
	 * @param z	the z-coordinate translated by
	 * @return a version of this instance translated by given values for x, y, and z
	 */
	public Polyhedron3D translate(double x, double y, double z)
	{
		Polygon3D[] translatedPolys = new Polygon3D[polygons.length];

		for(int i = 0; i < translatedPolys.length; i ++)
		{
			translatedPolys[i] = polygons[i].translate(x, y, z);
		}
		
		return new Polyhedron3D(translatedPolys);
	}
	
	/** Returns a rotated instance of this Polyhedron3D about the x-axis through the given angle
	 * 
	 * @param turnAngle	the angle rotated through
	 * @return a version of this instance rotated the given angle about the x-axis
	 */
	public Polyhedron3D rotAboutX(double turnAngle)
	{
		Polygon3D[] rotatedPolys = new Polygon3D[polygons.length];
		
		for(int i = 0; i < polygons.length; i ++)
		{
			rotatedPolys[i] = polygons[i].rotAboutX(turnAngle);
		}
		
		return new Polyhedron3D(rotatedPolys);
	}
	
	/** Returns a rotated instance of this Polyhedron3D about the y-axis through the given angle
	 * 
	 * @param turnAngle	the angle rotated through
	 * @return a version of this instance rotated the given angle about the y-axis
	 */
	public Polyhedron3D rotAboutY(double turnAngle)
	{
		Polygon3D[] rotatedPolys = new Polygon3D[polygons.length];
		
		for(int i = 0; i < polygons.length; i ++)
		{
			rotatedPolys[i] = polygons[i].rotAboutY(turnAngle);
		}
		
		return new Polyhedron3D(rotatedPolys);
	}
	
	/** Returns a rotated instance of this Polyhedron3D about the z-axis through the given angle
	 * 
	 * @param turnAngle	the angle rotated through
	 * @return a version of this instance rotated the given angle about the z-axis
	 */
	public Polyhedron3D rotAboutZ(double turnAngle)
	{
		Polygon3D[] rotatedPolys = new Polygon3D[polygons.length];
		
		for(int i = 0; i < polygons.length; i ++)
		{
			rotatedPolys[i] = polygons[i].rotAboutZ(turnAngle);
		}
		
		return new Polyhedron3D(rotatedPolys);
	}
	
	/** Method returning projection of this polyhedron on to given plane
	 * 
	 * @param view	The View that this polygon is being looked at from
	 * @return the Projection object resulting from the projection of instance on to given plane 
	 */
	public PolyhedronProjection getProjection(View view)
	{
		PolygonProjection[] projections = new PolygonProjection[polygons.length];
		
		for(int i = 0; i < projections.length; i ++)
		{
			projections[i] = polygons[i].getProjection(view);
		}
		
		return new PolyhedronProjection(projections);
	}
	
	/**
	 * @return the array of 3D polygons comprising this 3D Polyhedron
	 */
	public Polygon3D[] getPolys()
	{
		return polygons;
	}
}
