package game.terrain;

import java.awt.*;
import java.util.ArrayList;

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
	
	/** The array list of polygon projections used to represent polygons viewed */
	private ArrayList<PolygonProjection> projections;
	
	/** The array of Colors used to represent the color of each polygon in the array */
	private Color[][] colors;
	
	/** The view through which this instance is viewed */
	private View view;
	
	/** The point in 2D space for the initial x and y coordinates of the terrain */
	private final Point2D initialPoint;
	
	/** The maximum height for any point on the grid */
	private final double maxHeight;
	
	/** The height below which the secondary color is initially drawn */
	private final double secondaryHeight;
	
	/** The height below which water forms */
	private final double waterHeight;
	
	/** The default maximum height for all terrains */
	private static final double MAX_HEIGHT = 3000;
	
	/** The default secondary height for all grids */
	private static final double SECONDARY_HEIGHT = 1450;
	
	/** The default water height for all grids */
	private static final double WATER_HEIGHT = 1400;
	
	/** The distance between adjacent nodes */
	public static final double NODE_DIST = 100;
	
	/** The factor determining the amount smoothed per call of smooth */
	public static final double SMOOTHING_FACTOR = 0.05;
	
	/** The factor determining the amount color smoothed per call of colorSmooth */
	public static final double COLOR_SMOOTHING_FACTOR = 0.2;
	
	/** The base color for all terrain instances */
	public static final Color COLOR = new Color(210, 160, 90);
	
	/** The base color for all terrain instances */
	public static final Color SECONDARY_COLOR = new Color(30, 140, 10);
	
	/** The color for water on the terrain */
	public static final Color WATER_COLOR = new Color(28, 107, 160);
	
	/** Parameterized constructor, initializes height array
	 * 
	 * @param length	the length of this instance
	 * @param width		the width of this instance
	 */
	public Terrain(int length, int width)
	{
		this(length, width, new Point2D(0, 0));
	}
	
	/** Parameterized constructor, initializes height array and location of terrain in space
	 * 
	 * @param length	the length of this instance
	 * @param width		the width of this instance
	 * @param initialiPoint	the top left corner x and y coordinates of the terrain
	 */
	public Terrain(int length, int width, Point2D initialPoint)
	{
		this(length, width, initialPoint, MAX_HEIGHT, SECONDARY_HEIGHT, WATER_HEIGHT);
	}
	
	/** Parameterized constructor, initializes height array, location of terrain in space, and heights for water and different colors
	 * 
	 * @param length	the length of this instance
	 * @param width		the width of this instance
	 * @param initialiPoint	the top left corner x and y coordinates of the terrain
	 * @param maxHeight	the value the maximum height of the terrain will be set to
	 * @param secondaryHeight	the value the secondary color height of the terrain will be set to
	 * @param waterHeight	the value the water height of the terrain will be set to
	 */
	public Terrain(int length, int width, Point2D initialPoint, double maxHeight, double secondaryHeight, double waterHeight)
	{
		heights = new double[length][width];
		polygons = new Polygon3D[length - 1][width - 1];
		colors = new Color[length][width];
		projections = new ArrayList<PolygonProjection>();
		this.initialPoint = initialPoint;
		this.maxHeight = maxHeight;
		this.secondaryHeight = secondaryHeight;
		this.waterHeight = waterHeight;
	}
	
	/** A method to quickly create and load the actual terrain, used after constructor is invoked
	 * 
	 */
	public void create()
	{
		generate();
		for(int i = 0; i < 100; i ++)
		{
			smooth();
		}
		water(waterHeight);
		for(int i = 0; i < 10; i ++)
		{
			colorSmooth();
		}
		loadPolys();
		loadProjections(DisplayPanel.getView());
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
				double random = maxHeight*Math.random();
				heights[i][j] = random;
				if((int)(random) < secondaryHeight)
				{
					colors[i][j] = SECONDARY_COLOR;
				}
				else
				{
					colors[i][j] = COLOR;
				}
				
			}
		}
	}
	
	/** Smoothes the heights array and color distribution of instance method is invoked with
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
				
				double sameColorCount = 0;
				for(int k = Math.max(i - 1, 0); k <= Math.min(i + 1, heights.length - 1); k ++)
				{
					for(int l = Math.max(j - 1, 0); l <= Math.min(j + 1, heights[0].length - 1); l ++)
					{
						if(!(k == i && l == j))
						{
							adjacentHeightNum ++;
							adjacentHeightSum += heights[k][l];
						}
						
						if(colors[k][l].equals(colors[i][j]))
						{
							sameColorCount ++;
						}
					}
					
				}
				double adjacentHeightAverage = adjacentHeightSum/adjacentHeightNum;
				heights[i][j] = (heights[i][j] + SMOOTHING_FACTOR*adjacentHeightAverage)/(1 + SMOOTHING_FACTOR);
				
				if(Math.random() > sameColorCount/adjacentHeightNum)
				{
					colors[i][j] = changeColor(colors[i][j]);
				}
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
	
	/** Smoothes the colors of the grid
	 * 
	 */
	public void colorSmooth()
	{
		for(int i = 0; i < heights.length; i ++)
		{
			for(int j = 0; j < heights[0].length; j ++)
			{
				int adjacentNum = 0;
				int redSum = 0;
				int greenSum = 0;
				int blueSum = 0;

				for(int k = Math.max(i - 1, 0); k <= Math.min(i + 1, heights.length - 1); k ++)
				{
					for(int l = Math.max(j - 1, 0); l <= Math.min(j + 1, heights[0].length - 1); l ++)
					{
						if(!(k == i && l == j))
						{
							adjacentNum ++;
							redSum += colors[k][l].getRed();
							greenSum += colors[k][l].getGreen();
							blueSum += colors[k][l].getBlue();
						}
					}
					
				}
				
				int newRed = (int)(((double)colors[i][j].getRed() + COLOR_SMOOTHING_FACTOR*redSum/(double)adjacentNum)/(1 + COLOR_SMOOTHING_FACTOR));
				int newGreen = (int)(((double)colors[i][j].getGreen() + COLOR_SMOOTHING_FACTOR*greenSum/(double)adjacentNum)/(1 + COLOR_SMOOTHING_FACTOR));
				int newBlue = (int)(((double)colors[i][j].getBlue() + COLOR_SMOOTHING_FACTOR*blueSum/(double)adjacentNum)/(1 + COLOR_SMOOTHING_FACTOR));
				
				colors[i][j] = new Color(newRed, newGreen, newBlue);
			}
		}
	}
	
	/** Colors water on the map below the given height
	 * 
	 * @param height	the height below which there is water
	 */
	public void water(double height)
	{
		for(int i = 0; i < heights.length; i ++)
		{
			for(int j = 0; j < heights[0].length; j ++)
			{
				if(heights[i][j] < height)
				{
					colors[i][j] = WATER_COLOR;
					heights[i][j] = height;
				}
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
				
				polygons[i][j] = new Polygon3D(points, colors[i][j]);
			}
		}
	}
	
	/** Loads the projections into the projections array list from the given view with this instance's polygons
	 * 
	 * @param view	the view the projections will be projected on
	 */
	public void loadProjections(View view)
	{
		projections.clear();
		
		for(int i = 0; i < polygons.length; i ++)
		{
			for(int j = 0; j < polygons[0].length; j ++)
			{
				 projections.add(polygons[i][j].getProjection(view));
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
//		loadProjections(DisplayPanel.getView());
//		PolygonProjection.sort(projections);
//		for (int i = projections.size() - 1; i >= 0; i --) 
//		{
//	        projections.get(i).draw(graphics);
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
	
	private static Color changeColor(Color color)
	{
		if(color.equals(SECONDARY_COLOR))
		{
			return COLOR;
		}
		else
		{
			return SECONDARY_COLOR;
		}
	}
}
