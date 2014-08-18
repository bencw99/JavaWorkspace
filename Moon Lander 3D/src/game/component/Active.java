package game.component;

import graphics.polyhedron.Polyhedron3D;

import java.util.ArrayList;

import physics.kinematics.Position;
import physics.kinematics.vector.*;

/** A class representing components that need to be updated
 * 
 * @author Benjamin Cohen-Wang
 */
public abstract class Active extends Component implements Updateable
{
	/** The velocity vector of this instance */
	private Velocity vel;
	
	/** The acceleration of this instance */
	private Acceleration acc;
	
	/** The forces applied on this instance */
	private ArrayList<Force> forces;
	
	/** Default constructor
	 * 
	 */
	public Active()
	{
		super();
	}
	
	/** Parameterized constructor, initializes shape to given arguments and position to origin
	 * 
	 * @param shape	the Polyhedron3D the shape of this instance will be set to
	 */
	public Active(Polyhedron3D shape)
	{
		super(shape);
	}
	
	/** Parameterized constructor, initializes shape and position to given arguments
	 * 
	 * @param shape	the Polyhedron3D the shape of this instance will be set to
	 * @param pos	the Position this Component will be set to have
	 */
	public Active(Polyhedron3D shape, Position pos)
	{
		super(shape, pos);
	}
	
	/** Updates this instance
	 * 
	 */
	public void update()
	{
		
	}
}
