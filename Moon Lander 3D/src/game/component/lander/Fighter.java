package game.component.lander;

import java.awt.Graphics;

import physics.kinematics.Position;
import game.component.Active;
import graphics.panel.DisplayPanel;
import graphics.polygon.Point3D;
import graphics.polygon.Polygon3D;
import graphics.polyhedron.Polyhedron3D;

public class Fighter extends Active
{
	private static final Polygon3D[] defaultPolys = 
	{
		/** Back hexagonal base */
		new Polygon3D(new Point3D(0, 10, -5), new Point3D(0, 0, -11), new Point3D(0, -5, -8), new Point3D(0, -5, 8), new Point3D(0, 0, 11), new Point3D(0, 10, 5)),
		
		/** Front hexagonal base */
		new Polygon3D(new Point3D(20, 10, -5), new Point3D(20, 0, -11), new Point3D(20, -5, -8), new Point3D(20, -5, 8), new Point3D(20, 0, 11), new Point3D(20, 10, 5)),
		
		/** Hexagonal prism rectangles */
		new Polygon3D(new Point3D(0, 10, -5), new Point3D(20, 10, -5), new Point3D(20, 0, -11), new Point3D(0, 0, -11)),
		new Polygon3D(new Point3D(20, 0, -11), new Point3D(0, 0, -11), new Point3D(0, -5, -8), new Point3D(20, -5, -8)),
		new Polygon3D(new Point3D(0, -5, -8), new Point3D(20, -5, -8), new Point3D(20, -5, 8), new Point3D(0, -5, 8)),
		new Polygon3D(new Point3D(20, -5, 8), new Point3D(0, -5, 8), new Point3D(0, 0, 11), new Point3D(20, 0, 11)),
		new Polygon3D(new Point3D(0, 0, 11), new Point3D(20, 0, 11), new Point3D(20, 10, 5), new Point3D(0, 10, 5)),
		new Polygon3D(new Point3D(20, 10, 5), new Point3D(0, 10, 5), new Point3D(0, 10, -5), new Point3D(20, 10, -5)),
		
		/** Cockpit */
		new Polygon3D(new Point3D(20, 10, -5), new Point3D(20, 10, 5), new Point3D(20, 5, 8), new Point3D(20, 5, -8)),
		new Polygon3D(new Point3D(20, 10, -5), new Point3D(20, 10, 5), new Point3D(35, 5, 5), new Point3D(35, 5, -5)),
		new Polygon3D(new Point3D(20, 10, 5), new Point3D(20, 5, 8), new Point3D(35, 5, 5)),
		new Polygon3D(new Point3D(20, 10, -5), new Point3D(20, 5, -8), new Point3D(35, 5, -5)),
		
		/** Nose */
		new Polygon3D(new Point3D(35, 5, 5), new Point3D(35, 5, -5), new Point3D(80, 3, -4), new Point3D(80, 3, 4))
		
		/** Nose end */
		
		/** Wings */
		
		/** Boosters */
		
	};
	
	/** The default fighter shape */
	private static final Polyhedron3D defaultShape = new Polyhedron3D(defaultPolys);
	
	/** Default constructor
	 * 
	 */
	public Fighter()
	{
		super(defaultShape);
	}
	
	/** Parameterized constructor, initializes shape to given arguments and position to origin
	 * 
	 * @param shape	the Polyhedron3D the shape of this instance will be set to
	 */
	public Fighter(Polyhedron3D shape)
	{
		super(shape);
	}
	
	/** Parameterized constructor, initializes shape and position to given arguments
	 * 
	 * @param shape	the Polyhedron3D the shape of this instance will be set to
	 * @param pos	the Position this Component will be set to have
	 */
	public Fighter(Polyhedron3D shape, Position pos)
	{
		super(shape, pos);
	}
	
	public void draw(Graphics graphics)
	{
		getShape().translate(getPos().getX(), getPos().getY(), getPos().getZ()).getProjection(DisplayPanel.getView()).draw(graphics);
	}

	public void update()
	{
		
	}
}
