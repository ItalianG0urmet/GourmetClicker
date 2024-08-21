package com.gourmet.clicker;

import java.awt.*;
import java.awt.event.InputEvent;
import java.security.SecureRandom;

public class StartClicker extends Thread{

    public void run() {
        Robot robot;
        try {robot = new Robot();} catch (AWTException e) {throw new RuntimeException(e);}
        Main.msNumber = 1;
        int delay;
        SecureRandom secureRandom = new SecureRandom();
        int secureRandomNumber;

        while (true) {
            while (Main.toggle){
                delay = 1000 / Main.msNumber;
                if(Main.msNumber >= 41){
                    secureRandomNumber = secureRandom.nextInt(40) + 1;
                    delay = 1000 / (Main.msNumber - secureRandomNumber );
                    robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                    try {Thread.sleep(secureRandomNumber % 90);} catch (InterruptedException ex) {throw new RuntimeException(ex);}
                    robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                    try {Thread.sleep(delay);} catch (InterruptedException ex) {throw new RuntimeException(ex);}
                } else {
                    robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                    robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                    try {Thread.sleep(delay);} catch (InterruptedException ex) {throw new RuntimeException(ex);}
                }
            }
            try {Thread.sleep(1);} catch (InterruptedException ex) {throw new RuntimeException(ex);}
        }
    }
}
