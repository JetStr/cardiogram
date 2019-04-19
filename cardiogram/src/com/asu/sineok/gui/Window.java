package com.asu.sineok.gui;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    private JButton plotButton;
    private JTextField multiplier;
    private JTextField freq;
    private JTextField seconds;
    private JLabel multLabel;
    private JLabel freqLabel;
    private JLabel secondsLabel;
    private JPanel contentPanel;

    public Window(String name) {
        initComponents();
        setName(name);
    }

    private void initComponents() {
        setSize(300, 300);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        contentPanel = new JPanel();
        plotButton = new JButton("Построить");
        multiplier = new JTextField(5);
        freq = new JTextField(5);
        seconds =  new JTextField(5);
        multLabel = new JLabel("Множитель");
        freqLabel = new JLabel("Частота дискретизации");
        secondsLabel = new JLabel("Время, с.");
        contentPanel.setLayout(new GridBagLayout());
        contentPanel.add(multLabel, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 5, 0), 0, 0));
        contentPanel.add(multiplier, new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 5, 5, 0), 0, 0));
        contentPanel.add(freqLabel, new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 5, 0), 0, 0));
        contentPanel.add(freq, new GridBagConstraints(1, 1, 1, 1, 0, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 5, 5, 0), 0, 0));
        contentPanel.add(plotButton, new GridBagConstraints(0, 3, 2, 1, 0, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 5, 0), 0, 0));
        contentPanel.add(secondsLabel, new GridBagConstraints(0, 2, 2, 1, 0, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 5, 0), 0, 0));
        contentPanel.add(seconds, new GridBagConstraints(1, 2, 2, 1, 0, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 5, 5, 0), 0, 0));
        add(contentPanel);
        pack();
    }

    public JTextField getSeconds() {
        return seconds;
    }

    public JTextField getMultiplier() {
        return multiplier;
    }

    public JTextField getFreq() {
        return freq;
    }

    public JButton getPlotButton() {
        return plotButton;
    }

    public void setPlotButton(JButton plotButton) {
        this.plotButton = plotButton;
    }
}
