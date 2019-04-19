package com.asu.sineok.fileService;

import java.io.*;

public class FileService {

    public static double[] getValues(File file) {
        String line;
        double[] values;
        int i = 0;
        int counter = 0;
        try(LineNumberReader lineNumberReader = new LineNumberReader(new FileReader(file))){
            while (lineNumberReader.readLine() != null){
                 counter++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        values = new double[counter];
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            while ((line = bufferedReader.readLine()) != null) {
                values[i] = Double.parseDouble(line);
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return values;
    }

    public static void saveFile(double[] x, double[] y, File saveDir) {
        File save = new File(saveDir + File.separator + "cardiogram.txt");


        try (FileWriter fileWriter = new FileWriter(save)){
            for (int i = 0; i < x.length; i++) {
                fileWriter.write(x[i]
                + "\t" + y[i] + "\n");
            }
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
