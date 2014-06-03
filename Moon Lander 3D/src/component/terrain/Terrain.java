package component.terrain;

import java.awt.*;

import graphics.panel.*;
import graphics.polygon.*;
import graphics.projection.*;

/** A class representing a 3 dimensional terrain
 * @author Benjamin Cohen-Wang
 */
public class Terrain
{
	/** The array representing the height at each node of the terrain */
	private double[][] heights;
	
	/** The array of polygons used to represent the terrain between nodes */
	private Polygon3D[][] polygons;
	
	/** The point in 2D space for the initial x and y coordinates of the terrain */
	private final Point2D initialPoint = new Point2D();
	
	/** The distance between adjacent nodes */
	public static final double NODE_DIST = 10000;
	
	/** The factor determining the amount smoothed per call of smooth */
	public static final double SMOOTHING_FACTOR = 0.2;
	
	/** The base color for all terrain instances */
	public static final Color COLOR = new Color(200, 145, 15);
	
	/** Parameterized constructor, initializes height array
	 * 
	 * @param length	the length of this instance
	 * @param width		the width of this instance
	 */
	public Terrain(int length, int width)
	{
		heights = new double[length][width];
		polygons = new Polygon3D[length - 1][width - 1];
	}
	
	/** A method to quickly create and load the actual terrain, used after constructor is invoked
	 * 
	 */
	public void init()
	{
		generate();
		for(int i = 0; i < 10; i ++)
		{
			smooth();
		}
		loadPolys();
	}
	
	/** Randomly generates a terrain by filling up the height array with random values
	 * 
	 */
	public void generate()
	{
		for(int i = 0; i < heights.length; i ++)
		{
			for(int j = 0; j < heights[0].length; j ++)
			{
				heights[i][j] = 50 + 30000*Math.random();
			}
		}
	}
	
	/** Smoothes the heights array of instance method is invoked with
	 * 
	 */
	public void smooth()
	{
		for(int i = 0; i < heights.length; i ++)
		{
			for(int j = 0; j < heights[0].length; j ++)
			{
				double adjacentHeightSum = 0;
				for(int k = Math.max(i - 1, 0); k <= Math.min(i + 1, heights.length - 1); k ++)
				{
					for(int l = Math.max(j - 1, 0); l <= Math.min(j + 1, heights[0].length - 1); l ++)
					{
						if(!(k == i && l == j))
						{
							adjacentHeightSum += heights[k][l];
						}
					}
				}
				heights[i][j] = (heights[i][j] + SMOOTHING_FACTOR*adjacentHeightSum)/(1 + SMOOTHING_FACTOR);
			}
		}
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
				
				polygons[i][j] = new Polygon3D(points, COLOR);
			}
		}
	}
	
	/** Paints this instance on to the given graphics object
	 * 
	 * @param graphics	the graphics object this instance will be drawn on
	 */
	public void paint(Graphics graphics)
	{
		for(int i = 0; i < polygons.length; i ++)
		{
			for(int j = 0; j < polygons[0].length; j ++)
			{
				 polygons[i][j].getProjection(DisplayPanel.getView()).paint(graphics);
			}
		}
	}
}
