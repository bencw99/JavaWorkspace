package appl.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.apache.commons.math.linear.RealMatrix;

import appl.graphics.TransformationMatrix.Type;
import appl.graphics.polygons.Polygon;
import appl.graphics.polygons.PolygonSet;

public abstract class Shape3D implements Iterable<Shape3D> {

    protected TransformationMatrix myTransform;
    
    public Shape3D() {
        myTransform = new TransformationMatrix();
    }

    public Shape3D scale(double scaleFactor) {
        myTransform = new TransformationMatrix(Type.SCALE, scaleFactor, scaleFactor, scaleFactor);
        // TODO: Move to origin before scaling, or scale points matrix
        return this;
    }
    
    public Shape3D rotate(double angleX, double angleY, double angleZ) {
        myTransform = (new TransformationMatrix(Type.ROTATE, angleX, angleY, angleZ)).applyTo(myTransform);
        // TODO: Move to origin before scaling, or scale points matrix
        // TODO: Fix implementation
        return this;
    }
    
    public Shape3D rotate(double vecX, double vecY, double vecZ, double pointX, double pointY, double pointZ, double angle) {
        myTransform = (new TransformationMatrix(Type.ROTATE, vecX, vecY, vecZ, pointX, pointY, pointZ, angle)).applyTo(myTransform);
        return this;
    }


    public Shape3D translate(double transX, double transY, double transZ) {
        myTransform = (new TransformationMatrix(Type.TRANSLATE, transX, transY, transZ)).applyTo(myTransform);
        // TODO: Move to origin before scaling, or scale points matrix
        return this;
    }

    public abstract double[] getCenter();
    public abstract Iterator<Shape3D> iterator();
    public abstract Iterator<Shape3D> sort(Canvas3D canvas3d, TransformationMatrix transMat);
    public abstract void draw(Graphics g, Canvas3D canvas3d, TransformationMatrix transMat);
}
