package game.component;

import physics.kinematics.Position;
import physics.kinematics.ReferenceFrame;
import game.Drawable;
import graphics.polyhedron.*;
import graphics.projection.View;

/** A class representing a component of a game
 * 
 * @author Benjamin Cohen-Wang
 */
public abstract class Component implements Drawable
{
	/** The Polyhedron3D representing the shape of this component */
	private Polyhedron3D shape;
	
	/** The position of this component */
	private Position pos;
	
	/** The reference frame of this component */
	private ReferenceFrame frame;
	
	/** The view through which this component is viewed */
	private View view;
	
	/** Default constructor
	 * 
	 */
	public Component()
	{
		this(new Polyhedron3D());
	}
	
	/** Parameterized constructor, initializes shape to given arguments and position to origin
	 * 
	 * @param shape	the Polyhedron3D the shape of this instance will be set to
	 */
	public Component(Polyhedron3D shape) 
	{
		this(shape, new Position());
	}
	
	/** Parameterized constructor, initializes shape and position to given arguments
	 * 
	 * @param shape	the Polyhedron3D the shape of this instance will be set to
	 * @param pos	the Position this Component will be set to have
	 */
	public Component(Polyhedron3D shape, Position pos)
	{
		this.shape = shape;
		this.pos = pos;
		frame = new ReferenceFrame(this.pos);
	}
	
	/** Parameterized constructor, initializes shape, view and position to given arguments
	 * 
	 * @param shape	the Polyhedron3D the shape of this instance will be set to
	 * @param pos	the Position this Component will be set to have
	 * @param view	the View that this instance will be set to have
	 */
	public Component(Polyhedron3D shape, Position pos, View view)
	{
		this.shape = shape;
		this.pos = pos;
		frame = new ReferenceFrame(this.pos);   
	}

	/**
	 * @return	the shape of this
	 */
	public Polyhedron3D getShape()
	{
		return shape;
	}

	/**
	 * @return	the position of this
	 */
	public Position getPos()
	{
		return pos;
	}

	/**
	 * @return	the reference frame of this
	 */
	public ReferenceFrame getFrame()
	{
		return frame;
	}

	/**
	 * @return	the view of this
	 */
	public View getView()
	{
		return view;
	}
}
