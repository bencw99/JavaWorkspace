package physics.kinematics;

/** A class representing positions in 3D space
 * 
 * @author Benjamin Cohen-Wang
 */
public class Position
{
	/** The x-coordinate of the position */
	private double x;
	
	/** The y-coordinate of the position */
	private double y;
	
	/** The z-coordinate of the position */
	private double z;
	
	/** Default constructor, initializes position to coordinates (0, 0, 0)
	 * 
	 */
	public Position()
	{
		x = 0;
		y = 0;
		z = 0;
	}
	
	/** Parameterized constructor, initializes position coordinates to given parameters
	 * 
	 * @param x	The x-coordinate instance initialized to
	 * @param y	The y-coordinate instance initialized to
	 * @param z	The z-coordinate instance initialized to
	 */
	public Position(double x, double y, double z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/** Returns a translated instance of this position
	 * 
	 * @param x	the x-coordinate translated by
	 * @param y	the y-coordinate translated by
	 * @param z	the z-coordinate translated by
	 * @return a version of this instance translated by given values for x, y, and z
	 */
	public Position translate(double x, double y, double z)
	{
		return new Position(this.x + x, this.y + y, this.z + z);
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
