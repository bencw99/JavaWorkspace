
public class lander 
{
	int t = 0;
	double fuel = 100;
	double xPosition;
	double yPosition;
	double zPosition;
	double xVelocity = 0;
	double zVelocity = 0;
	double yVelocity = 0;
	final double g = 5;
	boolean upThrust = false;
	boolean northThrust = false;
	boolean southThrust = false;
	boolean eastThrust = false;
	boolean westThrust = false;
	public void setX(double n)
	{
		xPosition = n;
	}
	public double getX()
	{
		return xPosition;
	}
	public void setY(double n)
	{
		yPosition = n;
	}
	public double getY()
	{
		return yPosition;
	}
	public void setZ(double n)
	{
		zPosition = n;
	}
	public double getZ()
	{
		return zPosition;
	}
	public void setXv(double v)
	{
		xVelocity = v;
	}
	public double getXv()
	{
		return xVelocity;
	}
	public void setYv(double v)
	{
		yVelocity = v;
	}
	public double getYv()
	{
		return yVelocity;
	}
	public void setZv(double v)
	{
		zVelocity = v;
	}
	public double getZv()
	{
		return zVelocity;
	}
	public void setT(int n)
	{
		t = n;
	}
	public int getT()
	{
		return t;
	}
	public void northThrustOn()
	{
		northThrust = true;
	}
	public void southThrustOn()
	{
		southThrust = true;
	}
	public void westThrustOn()
	{
		westThrust = true;
	}
	public void eastThrustOn()
	{
		eastThrust = true;
	}
	public void upThrustOn()
	{
		upThrust = true;
	}
	public void northThrustOff()
	{
		northThrust = false;
	}
	public void southThrustOff()
	{
		southThrust = false;
	}
	public void westThrustOff()
	{
		westThrust = false;
	}
	public void eastThrustOff()
	{
		eastThrust = false;
	}
	public void upThrustOff()
	{
		upThrust = false;
	}
	public void update()
	{
		t++;
		yVelocity -= g;
		if(northThrust)
		{
			yVelocity += 10;
			zVelocity += 10;
			fuel--;
		}
		if(southThrust)
		{
			yVelocity += 10;
			zVelocity -= 10;
			fuel--;
		}
		if(eastThrust)
		{
			yVelocity += 10;
			xVelocity += 10;
			fuel--;
		}
		if(westThrust)
		{
			yVelocity += 10;
			zVelocity -= 10;
			fuel--;
		}
		if(upThrust)
		{
			yVelocity += 10*Math.sqrt(2);
			fuel--;
		}
		xPosition += xVelocity;
		zPosition += zVelocity;
		yPosition += yVelocity;
	}
}
