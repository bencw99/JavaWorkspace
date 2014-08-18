package physics.kinematics.vector;

import java.util.ArrayList;

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
	
	/** Adds the given vector to this instance
	 * 
	 * @param other	the vector to be added to this
	 */
	public void add(Vector other)
	{
		this.x += other.x;
		this.y += other.y;
		this.z += other.z;
	}
	
	/** Scales this vector by the given scalar
	 * 
	 * @param scalar	the scalar the vector is scaled by
	 */
	public void scale(double scalar)
	{
		x *= scalar;
		y *= scalar;
		z *= scalar;
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
	
	/** Adds the two given vectors
	 * 
	 * @param vectors	the array list of vectors being added
	 * @return	the sum of the two vectors
	 */
	public static Vector add(ArrayList<Vector> vectors)
	{
		Vector sum = new Vector();
		
		for(Vector vector : vectors)
		{
			sum.add(vector);
		}
		
		return sum;
	}
	
	/** Adds the given vectors
	 * 
	 * @param vectors	the array of vectors to be added
	 * @return	the sum of the two vectors
	 */
	public static Vector add(Vector ... vectors)
	{
		Vector sum = new Vector();
		
		for(Vector vector : vectors)
		{
			sum.add(vector);
		}
		
		return sum;
	}
	
	/** Subtracts the second given vector from the first
	 * 
	 * @param v1	the vector subtracted from
	 * @param v2	the vector subtracted
	 * @return	the second vector subtracted from the first
	 */
	public static Vector subtract(Vector v1, Vector v2)
	{
		return new Vector(v1.x - v2.x, v1.y - v2.y, v1.z - v2.z);
	}
}
