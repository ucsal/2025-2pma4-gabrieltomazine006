package br.com.mariojp.figureeditor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.List;

class DrawingPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private static final int DEFAULT_SIZE = 60;

    private final List<Shape> shapes = new ArrayList<>();
    private FigureFactory figureFactory;

    DrawingPanel(FigureFactory factory) {
        this.figureFactory = factory;

        setBackground(Color.WHITE);
        setOpaque(true);
        setDoubleBuffered(true);

        var mouse = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int size = Math.max(Math.min(DEFAULT_SIZE, DEFAULT_SIZE), 10);
                Shape s;
                if (SwingUtilities.isLeftMouseButton(e)) {
                    
                    s = figureFactory.createEllipse(e.getPoint(), size);
                } else {
                   
                    s = figureFactory.createRectangle(e.getPoint(), size);
                }
                shapes.add(s);
                repaint();
            }
        };
        addMouseListener(mouse);
    }


    public void setFigureFactory(FigureFactory factory) {
        this.figureFactory = factory;
    }


    void clear() {
        shapes.clear();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

      
        for (Shape s : shapes) {
            Color fill, border;

            if (figureFactory instanceof BlueFactory bf) {
                fill = bf.getFillColor();
                border = bf.getBorderColor();
            } else if (figureFactory instanceof RedFactory rf) {
                fill = rf.getFillColor();
                border = rf.getBorderColor();
            } else {
             
                fill = Color.GRAY;
                border = Color.BLACK;
            }

            g2.setColor(fill);
            g2.fill(s);
            g2.setColor(border);
            g2.setStroke(new BasicStroke(1.5f));
            g2.draw(s);
        }

        g2.dispose();
    }
}
