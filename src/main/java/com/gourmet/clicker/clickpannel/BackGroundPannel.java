package com.gourmet.clicker.clickpannel;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

public class BackGroundPannel extends JPanel {

    private Color startColor = Color.decode("#1C2135");
    private Color endColor = Color.decode("#16182F");
    private int width;
    private int height;

    /**
     * This function will create the gradient
     * for the background.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        width = getWidth();
        height = getHeight();

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