package com.asu.sineok.app;

import com.asu.sineok.gui.Window;

public class App {

    public static void main(String[] args) {
        Window window = new Window("Кардиограмма");
        window.setVisible(true);

        new Controller(window).init();
    }
}
