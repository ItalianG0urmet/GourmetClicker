package com.gourmet.clicker;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.gourmet.clicker.clickpannel.BackGroundPannel;
import com.gourmet.clicker.clickpannel.ButtonClicker;
import com.gourmet.clicker.clickpannel.Slider;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;


public class Main {

    public static int msNumber;
    public static boolean toggle;


    public static void main(String[] args) {

        /* Key Lisener */
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            System.err.println("Error: lin-27, class-main");
            System.err.println(ex.getMessage());
            System.exit(1);
        }



        /* Funzioni iniziali */
        JFrame frame = new JFrame();
        frame.setUndecorated(true);
        frame.setSize(400, 300);
        BackGroundPannel backgroundPanel = new BackGroundPannel();
        backgroundPanel.setLayout(null);
        ImageIcon img = new ImageIcon(Objects.requireNonNull(Main.class.getResource("/Logo.png")));
        frame.setIconImage(img.getImage());


        /* Text Gourmet clicker*/
        JLabel label = new JLabel("Gourmet Clicker");
        label.setForeground(Color.decode("#A3A8BE"));
        label.setFont(new Font("Arial", Font.BOLD, 15));
        label.setBounds(7, 7, 200, 30);
        backgroundPanel.add(label);

        /* OFF/ON */
        JButton button = new ButtonClicker("OFF");
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setFont(new Font("Varela Round", Font.BOLD, 20));

        int buttonWidth = 200;
        int buttonHeight = 50;
        int buttonX = (frame.getWidth() - buttonWidth) / 2;
        int buttonY = (frame.getHeight() - buttonHeight) / 2 + 100;
        button.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);

        backgroundPanel.add(button);

        /* Slider and text */
        JSlider slider = new JSlider(JSlider.HORIZONTAL, 1, 100, 1);
        slider.setBackground(Color.decode("#171C32"));
        slider.setUI(new Slider(slider));
        slider.setBounds(50, 140, 300, 50);
        backgroundPanel.add(slider);

        JLabel sliderValueLabel = new JLabel("" + slider.getValue());
        sliderValueLabel.setForeground(Color.decode("#A3A8BE"));
        sliderValueLabel.setFont(new Font("Arial", Font.BOLD, 19));
        sliderValueLabel.setBounds(180, 110, 100, 20);
        slider.addChangeListener(e -> {
            if(slider.getValue() >= 41){
                sliderValueLabel.setText("Bypass");
            } else {
                sliderValueLabel.setText("" + slider.getValue());
            }
            msNumber = slider.getValue();
            //System.out.println(msNumber);
        });
        backgroundPanel.add(sliderValueLabel);


        /* Creazione del bottone chiusura */
        JButton closeButton = createCloseButton();
        closeButton.setBounds(frame.getWidth() - 50, 10, 40, 30);
        backgroundPanel.add(closeButton);

        frame.setContentPane(backgroundPanel);

        /* Move frame */
        final int[] mouseX = {0};
        final int[] mouseY = {0};
        MouseAdapter mouseAdapter = new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                mouseX[0] = e.getX();
                mouseY[0] = e.getY();
            }

            public void mouseDragged(MouseEvent e) {
                frame.setLocation(e.getXOnScreen() - mouseX[0], e.getYOnScreen() - mouseY[0]);
            }
        };

        /* Listeners */
        backgroundPanel.addMouseListener(mouseAdapter);
        backgroundPanel.addMouseMotionListener(mouseAdapter);
        button.addMouseListener(mouseAdapter);
        button.addMouseMotionListener(mouseAdapter);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* Finals */
        frame.setVisible(true);
        Thread thread = new StartClicker();
        thread.start();

        GlobalScreen.addNativeMouseListener(new KeyListenerClick((ButtonClicker) button));
        GlobalScreen.addNativeMouseMotionListener(new KeyListenerClick((ButtonClicker) button));
    }


    private static JButton createCloseButton() {
        JButton closeButton = new JButton("X");
        closeButton.setFont(new Font("Varela Round", Font.BOLD, 20));
        closeButton.setForeground(Color.decode("#C24ADF"));
        //closeButton.setBackground(new Color(0, 0, 0, 0)); // Colore trasparente
        closeButton.setFocusPainted(false);
        closeButton.setBorderPainted(false);
        closeButton.setContentAreaFilled(false);
        closeButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Runtime.getRuntime().addShutdownHook(new Thread(KeyListenerClick::deregisterHook));
                System.exit(0);
            }
        });

        // Imposta la dimensione del pulsante in base al testo "X"
        FontMetrics metrics = closeButton.getFontMetrics(closeButton.getFont());
        int width = metrics.stringWidth("x") + 10; // Aggiungi spazio pr migliorare il clic
        int height = metrics.getHeight() + 10;
        closeButton.setPreferredSize(new Dimension(width, height));

        return closeButton;
    }

}
