package appl.graphics.polygons;

import java.awt.Color;

public class PolygonProperties {
    private Color myColor;
    
    public PolygonProperties(Color color) {
        myColor = color;
    }

    public void setColor(Color color) {
        myColor = color;        
    }

    public Color getColor() {
        return myColor;
    }

}
