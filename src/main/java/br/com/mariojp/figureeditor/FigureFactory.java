package br.com.mariojp.figureeditor;

import java.awt.Point;
import java.awt.Shape;

public interface FigureFactory {
    Shape createEllipse(Point p, int size);
    Shape createRectangle(Point p, int size);
}