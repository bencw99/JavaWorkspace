package game.terrain;

import java.awt.Color;
import java.util.ArrayList;

import graphics.polygon.Point3D;
import graphics.polygon.Polygon3D;
import graphics.projection.Point2D;
import graphics.projection.PolygonProjection;
import graphics.projection.View;

public class SphericalTerrain extends Terrain
{
	/** The radius of this planet terrain instance */
	private double radius;
	
	/** The center point of this planet terrain instance */
	private Point3D centerPoint;
	
	/** Parameterized constructor, initializes height array
	 * 
	 * @param length	the length of this instance
	 * @param width		the width of this instance
	 * @param radius	the radius of this instance
	 */
	public SphericalTerrain(int length, int width, double radius)
	{
		this(length, width, radius, new Point3D(0, 0, 0));
	}
	
	/** Parameterized constructor, initializes height array and location of terrain in space
	 * 
	 * @param length	the length of this instance
	 * @param width		the width of this instance
	 * @param radius	the radius of this instance
	 * @param centerPoint	the center point in space of this terrain
	 */
	public SphericalTerrain(int length, int width, double radius, Point3D centerPoint)
	{
		super(length, width);
		this.polygons = new Polygon3D[length][width];
		this.centerPoint = centerPoint;
		this.radius = radius;
		setDefaultAttributes();
	}
	
	/** Parameterized constructor, initializes height array, location of terrain in space, and heights for water and different colors
	 * 
	 * @param length	the length of this instance
	 * @param width		the width of this instance
	 * @param radius	the radius of this instance
	 * @param centerPoint	the center point in space of this terrain
	 * @param maxHeight	the value the maximum height of the terrain will be set to
	 * @param secondaryHeight	the value the secondary color height of the terrain will be set to
	 * @param waterHeight	the value the water height of the terrain will be set to
	 */
	public SphericalTerrain(int length, int width, double radius, Point3D centerPoint, double maxHeight, double secondaryHeight, double waterHeight)
	{
		super(length, width, maxHeight, secondaryHeight, waterHeight);
		this.polygons = new Polygon3D[length][width];
		this.centerPoint = centerPoint;
		this.radius = radius;
	}
	
	/** Sets the polygons between terrain nodes using the height array 
	 * 
	 */
	public void loadPolys()
	{
		for(int i = 0; i < heights.length; i ++)
		{
			for(int j = 0; j < heights[0].length; j ++)
			{	
				int endI = (i + 1) % (heights.length - 1);
				int endJ = (j + 1) % (heights[0].length - 1);
				Point3D point1 = getPoint(i, j);
				Point3D point2 = getPoint(i, endJ);
				Point3D point3 = getPoint(endI, endJ);
				Point3D point4 = getPoint(endI, j);
				
				Point3D[] points = {point1, point2, point3, point4};
				
				polygons[i][j] = new Polygon3D(colors[i][j], points);
			}
		}
	}
	
	/** Returns the 3D space point of the given height array node
	 * 
	 * @param i	the length node specifier
	 * @param j	the width node specifier
	 * @return the point in 3D space at the given node
	 */
	private Point3D getPoint(int i, int j)
	{
		double rho = radius + heights[i][j];
		double theta = Math.PI*i/(heights.length - 1);
		double phi = 2*Math.PI*j/(heights[0].length - 1);
		
		return new Point3D(centerPoint.getX() + rho*Math.cos(phi)*Math.cos(theta), centerPoint.getY() + rho*Math.cos(phi)*Math.sin(theta), centerPoint.getZ() + rho*Math.sin(phi));
	}
	
	/** Gets the factor determining whether or not this terrain wraps around
	 * 
	 * @return whether or not this terrain wraps around
	 */
	public boolean wrapsAround()
	{
		return true;
	}
	
	/** Gets the default maximum height for this terrain 
	 * 
	 * @return the default maximum height for this terrain
	 */
	protected double getDefaultMaxHeight()
	{
		return 40*Math.PI*radius/Math.sqrt(heights.length*heights[0].length/2);
	}

	/** Gets the default secondary color height for this terrain 
	 * 
	 * @return the default secondary color height for this terrain
	 */
	protected double getDefaultSecondaryHeight()
	{
		return getDefaultMaxHeight()*0.49;
	}

	/** Gets the default water height for this terrain 
	 * 
	 * @return the default water height for this terrain
	 */
	protected double getDefaultWaterHeight()
	{
		return getDefaultMaxHeight()*0.48;
	}
}
