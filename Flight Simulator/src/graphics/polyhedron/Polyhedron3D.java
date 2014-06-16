package graphics.polyhedron;

import graphics.polygon.*;

/** A class representing a polyhedron in 3D space
 * 
 * @author Benjamin Cohen-Wang
 */
public class Polyhedron3D
{
	/** The array of polygons in 3D space comprising this polyhedron */
	private Polygon3D[] polygons;
	
	/** Parameterized constructor, initializes this instance to the given array of polygons in 3D space
	 * 
	 * @param polygons	the array of polygons this instance will be set to contain
	 */
	public Polyhedron3D(Polygon3D[] polygons)
	{
		this.polygons = polygons;
	}
}
