package com.gourmet.clicker.clickpannel;

import com.gourmet.clicker.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonClicker extends JButton {


    public ButtonClicker(String text) {
        super(text);
        setContentAreaFilled(false);
        setBackground(Color.decode("#252C46"));

        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.toggle = !Main.toggle; // Inverti lo stato booleano
                updateButton(); // Aggiorna l'aspetto del bottone
            }
        });
    }

    public void updateButton() {
        if (Main.toggle) {
            setText("Toggle ON");
            setForeground(Color.decode("#C24ADF"));
        } else {
            setText("Toggle OFF");
            setForeground(Color.decode("#434344"));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40); // Aumentato il raggio dell'arco a 40 per un bordo più grande
        super.paintComponent(g);
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getForeground()); // Colore del bordo
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 40, 40); // Aumentato il raggio dell'arco a 40 per un bordo più grande
        g2.dispose();
    }

    @Override
    public boolean contains(int x, int y) {
        int w = getWidth();
        int h = getHeight();
        int arc = 30;
        return (x >= 0 && x <= w && y >= 0 && y <= h);
    }
}