package graphics.projection;

import java.awt.Graphics;
import java.util.ArrayList;

/** A class representing a projection of a 3D polyhedron
 * @author Benjamin Cohen-Wang
 */
public class PolyhedronProjection
{
	/** The array of Polygon Projections this instance is composed of */
	PolygonProjection[] projections;
	
	/** Parameterized constructor, initializes projection array of instance to given array of projections
	 * 
	 * @param projections	The PolygonProjection array this instance will be initialized to
	 */
	public PolyhedronProjection(PolygonProjection[] projections)
	{
		this.projections = projections;
	}
	
	/** Returns a rotated instance of PolyhedronProjection rotated about the origin through the given angle
	 * 
	 * @param angle	the angle rotated through
	 * @return an instance of PolyhedronProjection that is a rotated form of this instance
	 */
	public PolyhedronProjection rotate(double angle)
	{
		return rotate(new Point2D(0, 0), angle);
	}
	
	/** Returns a rotated instance of PolyhedronProjection rotated about the given point through the given angle
	 * 
	 * @param point	the point in the 2D graph rotated about
	 * @param angle	the angle rotated through
	 * @return an instance of PolyhedronProjection that is a rotated form of this instance
	 */
	public PolyhedronProjection rotate(Point2D point, double angle)
	{
		PolygonProjection[] rotatedProjections = new PolygonProjection[projections.length];
		
		for(int i = 0; i < projections.length; i ++)
		{
			rotatedProjections[i] = projections[i].rotate(point, angle);
		}
		
		return new PolyhedronProjection(rotatedProjections);
	}
	
	/** Draws this instance on the given graphics object
	 * 
	 * @param graphics	the graphics object this instance will be drawn on
	 */
	public void draw(Graphics graphics)
	{
		ArrayList<PolygonProjection> sortedPolygons = PolygonProjection.sort(projections);
		
		for(PolygonProjection projection : sortedPolygons)
		{
			projection.draw(graphics);
		}
	}
}
