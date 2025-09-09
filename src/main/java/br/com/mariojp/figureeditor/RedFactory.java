package br.com.mariojp.figureeditor;

import java.awt.Color;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class RedFactory implements FigureFactory{


	@Override
	public Shape createEllipse(Point p, int size) {
		 return new Ellipse2D.Double(p.x, p.y, size, size);
	
	}
	public Shape createRectangle(Point p, int size) {
		 return new Rectangle2D.Double(p.x, p.y, size, size);
	
	}
	
    public Color getFillColor() {
        return new Color(220,20,60); 
    }

    public Color getBorderColor() {
        return new Color(0,0,0,90); 
    }
}