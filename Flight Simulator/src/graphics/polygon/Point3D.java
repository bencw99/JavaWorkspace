package graphics.polygon;

/** A class representing a point in 3D space
 * @author Benjamin Cohen-Wang
 */
public class Point3D
{
	/** The x-coordinate of the point in space */
	private double x;
	
	/** The y-coordinate of the point in space */
	private double y;
	
	/** The z-coordinate of the point in space */
	private double z;
	
	/** Default constructor, initializes point to coordinates (0, 0, 0)
	 * 
	 */
	public Point3D()
	{
		x = 0;
		y = 0;
		z = 0;
	}
	
	/** Parameterized constructor, initializes point coordinates to given parameters
	 * 
	 * @param x	The x-coordinate instance initialized to
	 * @param y	The y-coordinate instance initialized to
	 * @param z	The z-coordinate instance initialized to
	 */
	public Point3D(double x, double y, double z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/** Returns a translated instance of this Point3D
	 * 
	 * @param x	the x-coordinate translated by
	 * @param y	the y-coordinate translated by
	 * @param z	the z-coordinate translated by
	 * @return a version of this instance translated by given values for x, y, and z
	 */
	public Point3D translate(double x, double y, double z)
	{
		return new Point3D(this.x + x, this.y + y, this.z + z);
	}
	
	/** Returns a rotated instance of this Point3D about the x-axis through the given angle
	 * 
	 * @param turnAngle	the angle rotated through
	 * @return a version of this instance rotated the given angle about the x-axis
	 */
	public Point3D rotAboutX(double turnAngle)
	{
		double cos = Math.cos(turnAngle);
		double sin = Math.sin(turnAngle);
		
		return new Point3D(x, y*cos - z*sin, y*sin + z*cos);
	}
	
	/** Returns a rotated instance of this Point3D about the y-axis through the given angle
	 * 
	 * @param turnAngle	the angle rotated through
	 * @return a version of this instance rotated the given angle about the y-axis
	 */
	public Point3D rotAboutY(double turnAngle)
	{
		double cos = Math.cos(turnAngle);
		double sin = Math.sin(turnAngle);
		
		return new Point3D(z*cos - x*sin, y, z*sin + x*cos);
	}
	
	/** Returns a rotated instance of this Point3D about the z-axis through the given angle
	 * 
	 * @param turnAngle	the angle rotated through
	 * @return a version of this instance rotated the given angle about the z-axis
	 */
	public Point3D rotAboutZ(double turnAngle)
	{
		double cos = Math.cos(turnAngle);
		double sin = Math.sin(turnAngle);
		
		return new Point3D(x*cos - y*sin, x*sin + y*cos, z);
	}
	
	/**
	 * @return x-coordinate of instance
	 */
	public double getX()
	{
		return x;
	}

	/**
	 * @return y-coordinate of instance
	 */
	public double getY()
	{
		return y;
	}

	/**
	 * @return z-coordinate of instance
	 */
	public double getZ()
	{
		return z;
	}

	/**
	 * @param x the value the x-coordinate is set to
	 */
	public void setX(double x)
	{
		this.x = x;
	}

	/**
	 * @param y the value the y-coordinate is set to
	 */
	public void setY(double y)
	{
		this.y = y;
	}

	/**
	 * @param z the value the z-coordinate is set to
	 */
	public void setZ(double z)
	{
		this.z = z;
	}
	
	
}
