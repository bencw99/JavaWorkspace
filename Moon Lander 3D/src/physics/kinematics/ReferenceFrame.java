package physics.kinematics;

import physics.kinematics.vector.*;



/** A class representing a frame of reference
 * 
 * @author Benjamin Cohen-Wang
 */
public class ReferenceFrame
{
	/** The origin for this frame of reference */
	private Position origin;
	
	/** The velocity of this frame of reference */
	private Velocity vel;
	
	/** The acceleration of this frame of reference */
	private Acceleration acc;
	
	/** Default constructor, initializes instance to originate at space origin
	 * 
	 */
	public ReferenceFrame()
	{
		this(new Position(0, 0, 0));
	}
	
	/** Parameterized constructor, initializes instance to originate at given point
	 * 
	 */
	public ReferenceFrame(Position origin)
	{
		this.origin = origin;
	}
	
	/** Updates this reference frame
	 * 
	 */
	public void update()
	{
		
	}
}
