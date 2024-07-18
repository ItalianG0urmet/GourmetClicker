package com.gourmet.clicker.clickpannel;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

public class BackGroundPannel extends JPanel {
    private Color startColor = Color.decode("#1C2135"); // Grigio
    private Color endColor = Color.decode("#16182F"); // Nero

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        int width = getWidth();
        int height = getHeight();

        /* Crea e applica la grdient*/
        GradientPaint gradient = new GradientPaint(
                new Point2D.Double(0, 0),
                startColor,
                new Point2D.Double(0, height),
                endColor
        );

        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, width, height);
    }
}

    /*
    private Color backgroundColor;

    public BackGroundPannel() {
        // Imposta il colore di sfondo tramite HEX
        backgroundColor = Color.decode("#171C32"); // Colore verde secco
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(backgroundColor);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

     */
