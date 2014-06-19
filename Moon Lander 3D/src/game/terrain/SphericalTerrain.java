package game.terrain;

import graphics.polygon.Point3D;
import graphics.polygon.Polygon3D;
import graphics.projection.Point2D;
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
		this(length, width, radius, centerPoint, MAX_HEIGHT, SECONDARY_HEIGHT, WATER_HEIGHT);
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
		this.centerPoint = centerPoint;
		this.radius = radius;
		this.polygons = new Polygon3D[heights.length][heights[0].length];
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
				int endI = i == heights.length - 1 ? 0 : i + 1;
				int endJ = j == heights[0].length - 1 ? 0 : j + 1;
				Point3D point1 = getPoint(i, j);
				Point3D point2 = getPoint(i, endJ);
				Point3D point3 = getPoint(endI, endJ);
				Point3D point4 = getPoint(endI, j);
				
				Point3D[] points = {point1, point2, point3, point4};
				
				polygons[i][j] = new Polygon3D(points, colors[i][j]);
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
				
				double sameColorCount = 0;
				for(int k = i - 1; k <= i + 1; k ++)
				{
					for(int l = j - 1; l <= j + 1; l ++)
					{
						int kCoord = k == -1 ? heights.length - 1 : k;
						kCoord = kCoord == heights.length ? 0 : kCoord;
						
						int lCoord = l == -1 ? heights[0].length - 1 : l;
						lCoord = lCoord == heights[0].length ? 0 : lCoord;
						
						if(!(k == i && l == j))
						{
							adjacentHeightNum ++;
							adjacentHeightSum += heights[kCoord][lCoord];
						}
						
						if(colors[kCoord][lCoord].equals(colors[i][j]))
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
	
	/** Returns the 3D space point of the given height array node
	 * 
	 * @param i	the length node specifier
	 * @param j	the width node specifier
	 * @return the point in 3D space at the given node
	 */
	private Point3D getPoint(int i, int j)
	{
		double rho = radius + heights[i][j];
		double theta = Math.PI*i/heights.length;
		double phi = 2*Math.PI*j/heights[0].length;
		
		return new Point3D(centerPoint.getX() + rho*Math.cos(phi)*Math.cos(theta), centerPoint.getY() + rho*Math.cos(phi)*Math.sin(theta), centerPoint.getZ() + rho*Math.sin(phi));
	}
}
