package game.terrain;

import graphics.polygon.*;
import graphics.projection.*;

/** A class representing a 3 dimensional rectangular terrain
 * @author Benjamin Cohen-Wang
 */
public class RectangularTerrain extends Terrain
{	
	/** The point in 2D space for the initial x and y coordinates of the terrain */
	private final Point3D initialPoint;
	
	/** The distance between adjacent nodes */
	public final double nodeDist;
	
	/** Parameterized constructor, initializes height array
	 * 
	 * @param length	the length of this instance
	 * @param width		the width of this instance
	 */
	public RectangularTerrain(int length, int width)
	{
		this(length, width, new Point3D(0, 0, 0), 100);
	}
	
	/** Parameterized constructor, initializes height array and location of terrain in space
	 * 
	 * @param length	the length of this instance
	 * @param width		the width of this instance
	 * @param initialPoint	the top left corner x and y coordinates of the terrain
	 * @param nodeDist	the inter node distance of this instance
	 */
	public RectangularTerrain(int length, int width, Point3D initialPoint, double nodeDist)
	{
		super(length, width);
		this.initialPoint = initialPoint;
		this.nodeDist = nodeDist;
		setDefaultAttributes();
	}
	
	/** Parameterized constructor, initializes height array, location of terrain in space, and heights for water and different colors
	 * 
	 * @param length	the length of this instance
	 * @param width		the width of this instance
	 * @param initialPoint	the top left corner x and y coordinates of the terrain
	 * @param nodeDist	the inter node distance of this instance
	 * @param maxHeight	the value the maximum height of the terrain will be set to
	 * @param secondaryHeight	the value the secondary color height of the terrain will be set to
	 * @param waterHeight	the value the water height of the terrain will be set to
	 */
	public RectangularTerrain(int length, int width, Point3D initialPoint, double nodeDist, double maxHeight, double secondaryHeight, double waterHeight)
	{
		super(length, width, maxHeight, secondaryHeight, waterHeight);
		this.initialPoint = initialPoint;
		this.nodeDist = nodeDist;
	}
	
	/** Sets the polygons between terrain nodes using the height array 
	 * 
	 */
	public void loadPolys()
	{
		for(int i = 0; i < heights.length - 1; i ++)
		{
			for(int j = 0; j < heights[0].length - 1; j ++)
			{
				
				Point3D Point1 = new Point3D(initialPoint.getX() + i*nodeDist, initialPoint.getY() + j*nodeDist, initialPoint.getZ() + heights[i][j]);
				Point3D Point2 = new Point3D(initialPoint.getX() + (i + 1)*nodeDist, initialPoint.getY() + j*nodeDist, initialPoint.getZ() + heights[i + 1][j]);
				Point3D Point3 = new Point3D(initialPoint.getX() + (i + 1)*nodeDist, initialPoint.getY() + (j + 1)*nodeDist, initialPoint.getZ() + heights[i + 1][j + 1]);
				Point3D Point4 = new Point3D(initialPoint.getX() + i*nodeDist, initialPoint.getY() + (j + 1)*nodeDist, initialPoint.getZ() + heights[i][j + 1]);
				
				Point3D[] points = {Point1, Point2, Point3, Point4};
				
				polygons[i][j] = new Polygon3D(colors[i][j], points);
			}
		}
	}
	
	/** Returns the width in 3D space of this terrain instance
	 * 
	 * @return the width in space of this terrain instance
	 */
	public double getSpaceWidth()
	{
		return nodeDist*heights.length;
	}
	
	/** Returns the length in 3D space of this terrain instance
	 * 
	 * @return the length in space of this terrain instance
	 */
	public double getSpaceLength()
	{
		return nodeDist*heights[0].length;
	}

	/** Gets the factor determining whether or not this terrain wraps around
	 * 
	 * @return whether or not this terrain wraps around
	 */
	public boolean wrapsAround()
	{
		return false;
	}
	
	/** Gets the default maximum height for this terrain 
	 * 
	 * @return the default maximum height for this terrain
	 */
	protected double getDefaultMaxHeight()
	{
		return 30*nodeDist;
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
		return getDefaultMaxHeight()*0.475;
	}
}
