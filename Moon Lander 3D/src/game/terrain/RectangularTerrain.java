package game.terrain;

import graphics.polygon.*;
import graphics.projection.*;

/** A class representing a 3 dimensional rectangular terrain
 * @author Benjamin Cohen-Wang
 */
public class RectangularTerrain extends Terrain
{	
	/** The point in 2D space for the initial x and y coordinates of the terrain */
	private final Point2D initialPoint;
	
	/** The distance between adjacent nodes */
	public static final double NODE_DIST = 100;
	
	/** Parameterized constructor, initializes height array
	 * 
	 * @param length	the length of this instance
	 * @param width		the width of this instance
	 */
	public RectangularTerrain(int length, int width)
	{
		this(length, width, new Point2D(0, 0));
	}
	
	/** Parameterized constructor, initializes height array and location of terrain in space
	 * 
	 * @param length	the length of this instance
	 * @param width		the width of this instance
	 * @param initialPoint	the top left corner x and y coordinates of the terrain
	 */
	public RectangularTerrain(int length, int width, Point2D initialPoint)
	{
		this(length, width, initialPoint, MAX_HEIGHT, SECONDARY_HEIGHT, WATER_HEIGHT);
	}
	
	/** Parameterized constructor, initializes height array, location of terrain in space, and heights for water and different colors
	 * 
	 * @param length	the length of this instance
	 * @param width		the width of this instance
	 * @param initialPoint	the top left corner x and y coordinates of the terrain
	 * @param maxHeight	the value the maximum height of the terrain will be set to
	 * @param secondaryHeight	the value the secondary color height of the terrain will be set to
	 * @param waterHeight	the value the water height of the terrain will be set to
	 */
	public RectangularTerrain(int length, int width, Point2D initialPoint, double maxHeight, double secondaryHeight, double waterHeight)
	{
		super(length, width, maxHeight, secondaryHeight, waterHeight);
		this.initialPoint = initialPoint;
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
				
				Point3D Point1 = new Point3D(initialPoint.getX() + i*NODE_DIST, initialPoint.getY() + j*NODE_DIST, heights[i][j]);
				Point3D Point2 = new Point3D(initialPoint.getX() + (i + 1)*NODE_DIST, initialPoint.getY() + j*NODE_DIST, heights[i + 1][j]);
				Point3D Point3 = new Point3D(initialPoint.getX() + (i + 1)*NODE_DIST, initialPoint.getY() + (j + 1)*NODE_DIST, heights[i + 1][j + 1]);
				Point3D Point4 = new Point3D(initialPoint.getX() + i*NODE_DIST, initialPoint.getY() + (j + 1)*NODE_DIST, heights[i][j + 1]);
				
				Point3D[] points = {Point1, Point2, Point3, Point4};
				
				polygons[i][j] = new Polygon3D(points, colors[i][j]);
			}
		}
	}
	
	/** Returns the width in 3D space of this terrain instance
	 * 
	 * @return the width in space of this terrain instance
	 */
	public double getSpaceWidth()
	{
		return NODE_DIST*heights.length;
	}
	
	/** Returns the length in 3D space of this terrain instance
	 * 
	 * @return the length in space of this terrain instance
	 */
	public double getSpaceLength()
	{
		return NODE_DIST*heights[0].length;
	}
}
