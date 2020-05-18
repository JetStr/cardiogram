package com.asu.sineok.app;

import com.asu.sineok.fileService.FileService;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.internal.chartpart.Chart;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Plotter {

    public static void plot(String mult, String freq, String seconds) {
        final List<XChartPanel<Chart>> chartPanels = new ArrayList();
        double[] yData = FileService.getValues(new File("base.txt"));
        double[] xData = new double[yData.length];
        double multiplier = 1;
        double h = 1 / (double) xData.length;
        Random random = new Random();
        if (mult != null && !"".equals(mult)) {
            mult = mult.replace(",", ".");
            multiplier = Double.parseDouble(mult);
            for (int i = 0; i < xData.length; i++) {
                yData[i] *= multiplier;
            }
        }
        if (freq != null && !"".equals(freq)) {
            h = 1 / Double.parseDouble(freq);
        }
        if (seconds != null && !"".equals(seconds)) {
            double[] bufferX = new double[xData.length];
            double[] bufferY = new double[yData.length];
            bufferX = xData;
            bufferY = yData;
            xData = new double[xData.length * Integer.parseInt(seconds)];
            yData = new double[yData.length * Integer.parseInt(seconds)];
            int length = 0;
            for (int i = 0; i < Integer.parseInt(seconds); i++) {
                for (int j = 0; j < bufferX.length; j++) {
                    xData[length] = bufferX[j] * (i + 1);
                    yData[length] = bufferY[j];
                    length++;
                }
            }
        }
        for (int i = 0; i < xData.length; i++) {
            xData[i] = h * i;
            yData[i] += random.nextDouble() * 0.1 - 0.5;
        }

        // Create Chart
        XYChart chart = QuickChart.getChart("",
                "X",
                "Y - значение сгинала",
                "Множитель: " + multiplier + "\n" +
                        "Частота дискретизации: " + (freq != null && !freq.isEmpty() ? freq : 600) + "\n" +
                        "Время моделирования, с: " + (seconds != null && !seconds.isEmpty() ? seconds : 1),
                xData,
                yData);

        // Show it
        final JFrame frame = new JFrame("Cardio");
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Файл");
        JMenuItem saveItem = new JMenuItem("Сохранить");
        fileMenu.add(saveItem);
        menuBar.add(fileMenu);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        XChartPanel<Chart> chartPanel = new XChartPanel(chart);
        chartPanels.add(chartPanel);
        frame.setJMenuBar(menuBar);
        frame.add(chartPanel);
        frame.pack();
        frame.setVisible(true);

        double[] finalXData = xData;
        double[] finalYData = yData;
        saveItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileopen = new JFileChooser();
                File saveDir = null;
                File workingDirectory = new File(System.getProperty("user.dir"));

                fileopen.setCurrentDirectory(workingDirectory);
                fileopen.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int ret = fileopen.showSaveDialog(null);
                if (ret == JFileChooser.APPROVE_OPTION) {
                    //file with data
                    saveDir = fileopen.getSelectedFile();
                }
                FileService.saveFile(finalXData, finalYData, saveDir);
            }
        });
    }

}
