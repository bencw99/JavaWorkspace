package game.terrain;

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
	private final Point2D initialPoint;
	
	/** The distance between adjacent nodes */
	public static final double NODE_DIST = 100;
	
	/** The factor determining the amount smoothed per call of smooth */
	public static final double SMOOTHING_FACTOR = 0.05;
	
	/** The base color for all terrain instances */
	public static final Color COLOR = new Color(210, 160, 90);
	
	/** The base color for all terrain instances */
	public static final Color SECONDARY_COLOR = new Color(95, 220, 15);
	
	/** Parameterized constructor, initializes height array
	 * 
	 * @param length	the length of this instance
	 * @param width		the width of this instance
	 */
	public Terrain(int length, int width)
	{
		this(length, width, new Point2D(0, 0));
	}
	
	/** Parameterized constructor, initializes height array
	 * 
	 * @param length	the length of this instance
	 * @param width		the width of this instance
	 */
	public Terrain(int length, int width, Point2D initialPoint)
	{
		heights = new double[length][width];
		polygons = new Polygon3D[length - 1][width - 1];
		this.initialPoint = initialPoint;
	}
	
	/** A method to quickly create and load the actual terrain, used after constructor is invoked
	 * 
	 */
	public void init()
	{
		generate();
		for(int i = 0; i < 100; i ++)
		{
			smooth();
		}
//		for(int i = 0; i < 100; i ++)
//		{
//			smooth(0, 0, 50, 150);
//		}
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
				heights[i][j] = 3000*Math.random();;
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
				double adjacentHeightNum = 0;
				double adjacentHeightSum = 0;
				for(int k = Math.max(i - 1, 0); k <= Math.min(i + 1, heights.length - 1); k ++)
				{
					for(int l = Math.max(j - 1, 0); l <= Math.min(j + 1, heights[0].length - 1); l ++)
					{
						if(!(k == i && l == j))
						{
							adjacentHeightNum ++;
							adjacentHeightSum += heights[k][l];
						}
					}
					
				}
				double adjacentHeightAverage = adjacentHeightSum/adjacentHeightNum;
				heights[i][j] = (heights[i][j] + SMOOTHING_FACTOR*adjacentHeightAverage)/(1 + SMOOTHING_FACTOR);
			}
		}
	}
	
	/** Smoothes the heights array of instance method is invoked with
	 * 
	 * @param xStart	the starting x-index for the region to be smoothed
	 * @param yStart	the starting y-index for the region to be smoothed
	 * @param xEnd		the ending x-index for the region to be smoothed
	 * @param yEnd		the ending y-index for the region to be smoothed
	 */
	public void smooth(int xStart, int yStart, int xEnd, int yEnd)
	{
		for(int i = xStart; i < xEnd; i ++)
		{
			for(int j = yStart; j < yEnd; j ++)
			{
				double adjacentHeightNum = 0;
				double adjacentHeightSum = 0;
				for(int k = Math.max(i - 1, 0); k <= Math.min(i + 1, heights.length - 1); k ++)
				{
					for(int l = Math.max(j - 1, 0); l <= Math.min(j + 1, heights[0].length - 1); l ++)
					{
						if(!(k == i && l == j))
						{
							adjacentHeightNum ++;
							adjacentHeightSum += heights[k][l];
						}
					}
					
				}
				double adjacentHeightAverage = adjacentHeightSum/adjacentHeightNum;
				heights[i][j] = (heights[i][j] + SMOOTHING_FACTOR*adjacentHeightAverage)/(1 + SMOOTHING_FACTOR);
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
	 * @return 
	 */
	public void draw(Graphics graphics)
	{
		
//		TreeMap<Double, PolygonProjection> polyMap = new TreeMap<Double, PolygonProjection>();
//		
//		for(int i = 0; i < polygons.length; i ++)
//		{
//			for(int j = 0; j < polygons[0].length; j ++)
//			{
//				PolygonProjection currentPoly = polygons[i][j].getProjection(DisplayPanel.getView());
//				polyMap.put(currentPoly.getPriority(), currentPoly);
//			}
//		}
//		
//		for (Map.Entry<Double, PolygonProjection> entry : polyMap.entrySet()) 
//		{
//	        entry.getValue().draw(graphics);
//		}
		
		for(int i = 0; i < polygons.length; i ++)
		{
			for(int j = 0; j < polygons[0].length; j ++)
			{
				 polygons[i][j].getProjection(DisplayPanel.getView()).draw(graphics);
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
	
	public String toString()
	{
		return "(" + initialPoint.getX() + ", " + initialPoint.getY() + ") to (" + (initialPoint.getX() + getSpaceWidth()) + ", " + (initialPoint.getY() + getSpaceLength()) + ")";
	}
}
