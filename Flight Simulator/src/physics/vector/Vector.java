package physics.vector;

/** A class representing a vector
 * 
 * @author Benjamin Cohen-Wang
 */
public class Vector
{
	/** The x displacement of this vector */
	private double x;
	
	/** The y displacement of this vector */
	private double y;
	
	/** The z displacement of this vector */
	private double z;
	
	/** Default constructor, initializes vector to magnitude zero
	 * 
	 */
	public Vector()
	{
		this(0, 0, 0);
	}
	
	/** Parameterized constructor, initializes vector fields to given values
	 * 
	 * @param x	the value the x displacement is set to
	 * @param y	the value the y displacement is set to
	 * @param z	the value the z displacement is set to
	 */
	public Vector(double x, double y, double z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**
	 * @return x of instance
	 */
	public double getX()
	{
		return x;
	}

	/**
	 * @return y of instance
	 */
	public double getY()
	{
		return y;
	}

	/**
	 * @return z of instance
	 */
	public double getZ()
	{
		return z;
	}

	/**
	 * @param x the value the x is set to
	 */
	public void setX(double x)
	{
		this.x = x;
	}

	/**
	 * @param y the value the y is set to
	 */
	public void setY(double y)
	{
		this.y = y;
	}

	/**
	 * @param z the value the z is set to
	 */
	public void setZ(double z)
	{
		this.z = z;
	}
}
