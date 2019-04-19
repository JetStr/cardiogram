package com.asu.sineok.app;

import com.asu.sineok.gui.Window;

public class Controller {
    private Window window;

    Controller(Window window) {
        this.window = window;
    }

    void init() {
        window.getPlotButton().addActionListener(e -> {
               Plotter.plot(window.getMultiplier().getText(), window.getFreq().getText(), window.getSeconds().getText());
        });
    }

}
