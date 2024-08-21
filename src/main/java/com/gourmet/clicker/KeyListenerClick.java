package com.gourmet.clicker;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.mouse.NativeMouseEvent;
import com.github.kwhat.jnativehook.mouse.NativeMouseInputListener;
import com.gourmet.clicker.clickpannel.ButtonClicker;

public class KeyListenerClick implements NativeMouseInputListener {

    private ButtonClicker buttonClicker;

    public KeyListenerClick(ButtonClicker buttonClicker){
        this.buttonClicker = buttonClicker;
    }

    public void nativeMouseReleased(NativeMouseEvent e) {
        if (e.getButton() != NativeMouseEvent.BUTTON3) return;
        Main.toggle = !Main.toggle;
        buttonClicker.updateButton();
    }

    public static void deregisterHook() {
        try {
            GlobalScreen.unregisterNativeHook();
        } catch (NativeHookException ex) {
            System.err.println(ex.getMessage());
        }
    }
}