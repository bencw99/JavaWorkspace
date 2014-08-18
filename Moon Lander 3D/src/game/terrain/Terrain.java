package game.terrain;

import game.Drawable;
import graphics.panel.DisplayPanel;
import graphics.polygon.Polygon3D;
import graphics.projection.Point2D;
import graphics.projection.PolygonProjection;
import graphics.projection.View;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/** A parent class for various types of terrains
 * @author Benjamin Cohen-Wang
 */
public abstract class Terrain implements Drawable
{
	/** The array representing the height at each node of the terrain */
	protected double[][] heights;
	
	/** The array of polygons used to represent the terrain between nodes */
	protected Polygon3D[][] polygons;
	
	/** The array list of polygon projections used to represent polygons viewed */
	protected ArrayList<PolygonProjection> projections;
	
	/** The array of Colors used to represent the color of each polygon in the array */
	protected Color[][] colors;
	
	/** The view through which this instance is viewed */
	protected View view;
	
	/** The maximum height for any point on the grid */
	protected double maxHeight;
	
	/** The height below which the secondary color is initially drawn */
	protected double secondaryHeight;
	
	/** The height below which water forms */
	protected double waterHeight;
	
	/** The factor determining the amount smoothed per call of smooth */
	public static final double SMOOTHING_FACTOR = 0.05;
	
	/** The factor determining the amount the terrain type is smoothed per call of smooth */
	public static final double TYPE_SMOOTHING_FACTOR = 0.05;
	
	/** The factor determining the amount color smoothed per call of colorSmooth */
	public static final double COLOR_SMOOTHING_FACTOR = 0.2;
	
	/** The array storing the colors of different parts of the terrain */
	public static final Color[] TERRAIN_TYPES = {new Color(210, 160, 90), new Color(30, 140, 10), new Color(28, 107, 160), new Color(255, 255, 255)};
	
	/** The index of TERRAIN_TYPES that holds the color for dirt */
	private static final int DIRT = 0;
	
	/** The index of TERRAIN_TYPES that holds the color for grass */
	private static final int GRASS = 1;
	
	/** The index of TERRAIN_TYPES that holds the color for water */
	private static final int WATER = 2;
	
	/** The index of TERRAIN_TYPES that holds the color for ice */
	private static final int ICE = 3;
	
	/** The index of TERRAIN_TYPES that holds the color for rocks */
	private static final int ROCK = 4;
	
	/** Parameterized constructor, initializes height array
	 * 
	 * @param length	the length of this instance
	 * @param width		the width of this instance
	 */
	public Terrain(int length, int width)
	{
		heights = new double[length][width];
		polygons = new Polygon3D[length - 1][width - 1];
		colors = new Color[length][width];
		projections = new ArrayList<PolygonProjection>();
	}
	
	/** Parameterized constructor, initializes height array and heights for water and different colors
	 * 
	 * @param length	the length of this instance
	 * @param width		the width of this instance
	 * @param maxHeight	the value the maximum height of the terrain will be set to
	 * @param secondaryHeight	the value the secondary color height of the terrain will be set to
	 * @param waterHeight	the value the water height of the terrain will be set to
	 */
	public Terrain(int length, int width,  double maxHeight, double secondaryHeight, double waterHeight)
	{
		heights = new double[length][width];
		polygons = new Polygon3D[length - 1][width - 1];
		colors = new Color[length][width];
		projections = new ArrayList<PolygonProjection>();
		this.maxHeight = maxHeight;
		this.secondaryHeight = secondaryHeight;
		this.waterHeight = waterHeight;
	}
	
	/** Sets the maximum, secondary and water heights to their default values
	 * 
	 */
	public void setDefaultAttributes()
	{
		this.maxHeight = getDefaultMaxHeight();
		this.secondaryHeight = getDefaultSecondaryHeight();
		this.waterHeight = getDefaultWaterHeight();
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
		for(int i = 0; i < 150; i ++)
		{
			typeSmooth();
		}
		water();
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
					colors[i][j] = TERRAIN_TYPES[GRASS];
				}
				else
				{
					colors[i][j] = TERRAIN_TYPES[DIRT];
				}
				
			}
		}
	}
	
	/** Smoothes the heights array and color distribution of instance method is invoked with
	 * 
	 */
	public void smooth()
	{
		this.smooth(0, 0, heights.length, heights[0].length);
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
		boolean wrapAround = wrapsAround();
		
		for(int i = xStart; i < xEnd; i ++)
		{
			for(int j = yStart; j < yEnd; j ++)
			{
				int adjacentHeightNum = 0;
				double adjacentHeightSum = 0;
				
				for(int k = i - 1; k <= i + 1; k ++)
				{
					if(wrapAround || (k != -1 && k != heights.length))
					{
						for(int l = j - 1; l <= j + 1; l ++)
						{
							if(wrapAround || (l != -1 && l != heights[0].length))
							{
								int wrapperK = (k + heights.length) % heights.length;
								int wrapperL = (l + heights[0].length) % heights[0].length;
								
								if(!(k == i && l == j))
								{
									adjacentHeightNum ++;
									adjacentHeightSum += heights[wrapAround ? wrapperK : k][wrapAround ? wrapperL : l];
								}
							}
						}
					}
				}
				double adjacentHeightAverage = adjacentHeightSum/adjacentHeightNum;
				heights[i][j] = (heights[i][j] + SMOOTHING_FACTOR*adjacentHeightAverage)/(1 + SMOOTHING_FACTOR);
			}
		}
	}
	
	/** Smoothes the types of terrains making up the grid
	 * 
	 */
	public void typeSmooth()
	{
		this.typeSmooth(0, 0, heights.length, heights[0].length);
	}
	
	/** Smoothes the types of terrains making up the grid
	 * 
	 * @param xStart	the starting x-index for the region to be smoothed
	 * @param yStart	the starting y-index for the region to be smoothed
	 * @param xEnd		the ending x-index for the region to be smoothed
	 * @param yEnd		the ending y-index for the region to be smoothed
	 */
	public void typeSmooth(int xStart, int yStart, int xEnd, int yEnd)
	{
		boolean wrapAround = wrapsAround();
		
		for(int i = xStart; i < xEnd; i ++)
		{
			for(int j = yStart; j < yEnd; j ++)
			{
				double[] typeCount = new double[TERRAIN_TYPES.length];
				
				for(int k = i - 1; k <= i + 1; k ++)
				{
					if(wrapAround || (k != -1 && k != heights.length))
					{
						for(int l = j - 1; l <= j + 1; l ++)
						{
							if(wrapAround || (l != -1 && l != heights[0].length))
							{
								int wrapperK = (k + heights.length) % heights.length;
								int wrapperL = (l + heights[0].length) % heights[0].length;
								
								for(int type = 0; type < TERRAIN_TYPES.length; type ++)
								{
									if(colors[wrapAround ? wrapperK : k][wrapAround ? wrapperL : l].equals(TERRAIN_TYPES[type]))
									{
										typeCount[type] ++;
										
										if(colors[i][j].equals(TERRAIN_TYPES[type]))
										{
											typeCount[type] += 0.8;
										}
									}
								}
							}
						}
					}	
				}
				
				double random = Math.random();
				double sum = 0;
				double typeCountSum = 0;
				
				for(double typeNum : typeCount)
				{
					typeCountSum += typeNum;
				}
				
				for(int type = 0; type < TERRAIN_TYPES.length; type ++)
				{
					double nextInc = typeCount[type]/typeCountSum;
					if(random > sum && random < sum + nextInc)
					{
						colors[i][j] = TERRAIN_TYPES[type];
					}
					sum += nextInc;
				}
			}
		}
	}
	
	/** Smoothes the colors of the grid
	 * 
	 */
	public void colorSmooth()
	{
		boolean wrapAround = wrapsAround();
		
		for(int i = 0; i < heights.length; i ++)
		{
			for(int j = 0; j < heights[0].length; j ++)
			{
				int adjacentNum = 0;
				int redSum = 0;
				int greenSum = 0;
				int blueSum = 0;

				for(int k = i - 1; k <= i + 1; k ++)
				{
					if(wrapAround || (k != -1 && k != heights.length))
					{
						for(int l = j - 1; l <= j + 1; l ++)
						{
							if(wrapAround || (l != -1 && l != heights[0].length))
							{
								if(!(k == i && l == j))
								{
									int wrapperK = (k + heights.length) % heights.length;
									int wrapperL = (l + heights[0].length) % heights[0].length;
									
									adjacentNum ++;
									redSum += colors[wrapAround ? wrapperK : k][wrapAround ? wrapperL : l].getRed();
									greenSum += colors[wrapAround ? wrapperK : k][wrapAround ? wrapperL : l].getGreen();
									blueSum += colors[wrapAround ? wrapperK : k][wrapAround ? wrapperL : l].getBlue();
								}
							}
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

	/** Colors water on the map below the water height
	 * 
	 */
	public void water()
	{
		this.water(waterHeight);
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
					colors[i][j] = TERRAIN_TYPES[WATER];
					heights[i][j] = height;
				}
			}
		}
	}
	
	/** Colors ice on the map
	 * 
	 */
	public void ice()
	{
		for(int i = 0; i < heights.length; i ++)
		{
			for(int j = (int)(heights[0].length*0.65); j < (int)(heights[0].length*0.85); j ++)
			{
					colors[i][j] = TERRAIN_TYPES[ICE];
			}
			
			for(int j = (int)(heights[0].length*0.35); j > (int)(heights[0].length*0.15); j --)
			{
					colors[i][j] = TERRAIN_TYPES[ICE];
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
	
	/** Properly highlights this instance based on the cursor location
	 * 
	 * @param cursorCoord	the location of the cursor on the screen
	 */
	public void highlight(Point2D coord)
	{
		for(PolygonProjection projection : projections)
		{
			projection.highlight(coord);
		}
	}
	
	/** Paints this instance on to the given graphics object
	 * 
	 * @param graphics	the graphics object this instance will be drawn on
	 */
	public void draw(Graphics graphics)
	{	
		loadProjections(DisplayPanel.getView());
		projections = PolygonProjection.sort(projections);
		for (int i = projections.size() - 1; i >= 0; i --) 
		{
	        projections.get(i).draw(graphics);
		}
	}
	
	/** Sets the polygons between terrain nodes using the height array 
	 * 
	 */
	protected abstract void loadPolys();
	
	/** Gets the factor determining whether or not this terrain wraps around
	 * 
	 * @return whether or not this terrain wraps around
	 */
	public abstract boolean wrapsAround();
	
	/** Gets the default maximum height for this terrain 
	 * 
	 * @return the default maximum height for this terrain
	 */
	protected abstract double getDefaultMaxHeight();
	
	/** Gets the default secondary color height for this terrain 
	 * 
	 * @return the default secondary color height for this terrain
	 */
	protected abstract double getDefaultSecondaryHeight();
	
	/** Gets the default water height for this terrain 
	 * 
	 * @return the default water height for this terrain
	 */
	protected abstract double getDefaultWaterHeight();
	
	/**
	 * @return the height array
	 */
	public double[][] getHeights()
	{
		return heights;
	}

	/**
	 * @return the polygon array
	 */
	public Polygon3D[][] getPolygons()
	{
		return polygons;
	}

	/**
	 * @return the projection array list
	 */
	public ArrayList<PolygonProjection> getProjections()
	{
		return projections;
	}

	/**
	 * @return the color array
	 */
	public Color[][] getColors()
	{
		return colors;
	}

	/**
	 * @return the view
	 */
	public View getView()
	{
		return view;
	}

	/**
	 * @param heights	the value the height array is set to
	 */
	public void setHeights(double[][] heights)
	{
		this.heights = heights;
	}

	/**
	 * @param heights	the value the polygon array is set to
	 */
	public void setPolygons(Polygon3D[][] polygons)
	{
		this.polygons = polygons;
	}

	/**
	 * @param heights	the value the projection array is set to
	 */
	public void setProjections(ArrayList<PolygonProjection> projections)
	{
		this.projections = projections;
	}

	/**
	 * @param heights	the value the color array is set to
	 */
	public void setColors(Color[][] colors)
	{
		this.colors = colors;
	}

	/**
	 * @param heights	the value the view is set to
	 */
	public void setView(View view)
	{
		this.view = view;
	}
}
