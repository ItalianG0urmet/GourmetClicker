package com.gourmet.clicker;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.mouse.NativeMouseEvent;
import com.github.kwhat.jnativehook.mouse.NativeMouseInputListener;
import com.gourmet.clicker.clickpannel.ButtonClicker;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static com.sun.java.accessibility.util.AWTEventMonitor.addMouseListener;

public class KeyListenerClick implements NativeMouseInputListener {

    private ButtonClicker buttonClicker;

    public KeyListenerClick(ButtonClicker buttonClicker){
        this.buttonClicker = buttonClicker;
    }

    public void nativeMouseClicked(NativeMouseEvent e) {

    }

    public void nativeMousePressed(NativeMouseEvent e) {

    }

    public void nativeMouseReleased(NativeMouseEvent e) {
        if (e.getButton() == NativeMouseEvent.BUTTON3) {
            //System.out.println("Rotella del mouse cliccata!");
            Main.toggle = !Main.toggle;
            buttonClicker.updateButton();
            //System.out.println(Main.toggle);
        }
    }

    public void nativeMouseMoved(NativeMouseEvent e) {

    }

    public void nativeMouseDragged(NativeMouseEvent e) {

    }

    public static void deregisterHook() {
        try {
            GlobalScreen.unregisterNativeHook();
        } catch (NativeHookException ex) {
            System.err.println("There was a problem unregistering the native hook.");
            System.err.println(ex.getMessage());
        }
    }
}