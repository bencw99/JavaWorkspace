package kinematics;

public class Velocity
{
	private double xVel;
	private double yVel;
	
	public Velocity()
	{
		this(0, 0);
	}
	
	public Velocity(double xVel, double yVel)
	{
		this.xVel = xVel;
		this.yVel = yVel;
	}

	public double getXVel()
	{
		return xVel;
	}

	public double getYVel()
	{
		return yVel;
	}

	public void setXVel(double xVel)
	{
		this.xVel = xVel;
	}

	public void setYVel(double yVel)
	{
		this.yVel = yVel;
	}
}
