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
		new Polygon3D(new Point3D(0, -50, 100), new Point3D(0, -110, 0), new Point3D(0, -80, -50), new Point3D(0, 80, -50), new Point3D(0, 110, 0), new Point3D(0, 50, 100)),
		
		/** Front hexagonal base */
		new Polygon3D(new Point3D(200, -50, 100), new Point3D(200, -110, 0), new Point3D(200, -80, -50), new Point3D(200, 80, -50), new Point3D(200, 110, 0), new Point3D(200, 50, 100)),
		
		/** Hexagonal prism rectangles */
		new Polygon3D(new Point3D(0, -50, 100), new Point3D(200, -50, 100), new Point3D(200, -110, 0), new Point3D(0, -110, 0)),
		new Polygon3D(new Point3D(200, -110, 0), new Point3D(0, -110, 0), new Point3D(0, -80, -50), new Point3D(200, -80, -50)),
		new Polygon3D(new Point3D(0, -80, -50), new Point3D(200, -80, -50), new Point3D(200, 80, -50), new Point3D(0, 80, -50)),
		new Polygon3D(new Point3D(200, 80, -50), new Point3D(0, 80, -50), new Point3D(0, 110, 0), new Point3D(200, 110, 0)),
		new Polygon3D(new Point3D(0, 110, 0), new Point3D(200, 110, 0), new Point3D(200, 50, 100), new Point3D(0, 50, 100)),
		new Polygon3D(new Point3D(200, 50, 100), new Point3D(0, 50, 100), new Point3D(0, -50, 100), new Point3D(200, -50, 100)),
		
		/** Cockpit */
		new Polygon3D(new Point3D(200, -50, 100), new Point3D(200, 50, 100), new Point3D(200, 80, 50), new Point3D(200, -80, 50)),
		new Polygon3D(new Point3D(200, -50, 100), new Point3D(200, 50, 100), new Point3D(350, 50, 50), new Point3D(350, -50, 50)),
		new Polygon3D(new Point3D(200, 50, 100), new Point3D(200, 80, 50), new Point3D(350, 50, 50)),
		new Polygon3D(new Point3D(200, -50, 100), new Point3D(200, -80, 50), new Point3D(350, -50, 50)),
		
		/** Nose */
		new Polygon3D(new Point3D(350, 50, 50), new Point3D(350, -50, 50), new Point3D(800, -40, 30), new Point3D(800, 40, 30)),
		
		/** Nose end */
		
		/** Wings */
		new Polygon3D(new Point3D(0, 110, 0), new Point3D(200, 110, 0),  new Point3D(230, 500, 70), new Point3D(120, 500, 70)),
		new Polygon3D(new Point3D(0, 110, 0), new Point3D(200, 110, 0),  new Point3D(230, 500, -70), new Point3D(120, 500, -70)),
		new Polygon3D(new Point3D(0, -110, 0), new Point3D(200, -110, 0),  new Point3D(230, -500, 70), new Point3D(120, -500, 70)),
		new Polygon3D(new Point3D(0, -110, 0), new Point3D(200, -110, 0),  new Point3D(230, -500, -70), new Point3D(120, -500, -70)),
		
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
