package com.gourmet.clicker.clickpannel;

import javax.swing.*;
import javax.swing.plaf.basic.BasicSliderUI;
import java.awt.*;

public class Slider extends BasicSliderUI {

    private Color lineColor = Color.decode("#252C46");
    private Color fillColor = Color.decode("#C24ADF");

    public Slider(JSlider slider) {
        super(slider);
    }

    @Override
    protected Dimension getThumbSize() {
        return new Dimension(14, 14);
    }

    @Override
    public void paintTrack(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Rectangle trackBounds = trackRect;
        int trackLeft = trackBounds.x;
        int trackRight = trackBounds.x + trackBounds.width;
        int trackTop = trackBounds.y + trackBounds.height / 2 - 2;
        int trackHeight = 4;

        g2d.setColor(lineColor);
        g2d.fillRect(trackLeft, trackTop, trackRight - trackLeft, trackHeight);

        int fillWidth = thumbRect.x + thumbRect.width / 2 - trackLeft;

        g2d.setColor(fillColor);
        g2d.fillRect(trackLeft, trackTop, fillWidth, trackHeight);
    }

    @Override
    public void paintThumb(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Rectangle knobBounds = thumbRect;
        int w = knobBounds.width;
        int h = knobBounds.height;

        g2d.setColor(fillColor);
        g2d.fillOval(knobBounds.x, knobBounds.y, w, h);

        g2d.setColor(fillColor.darker());
        g2d.drawOval(knobBounds.x, knobBounds.y, w - 1, h - 1);
    }

    @Override
    protected void installDefaults(JSlider slider) {
        super.installDefaults(slider);
        slider.setFocusable(false);
    }

}