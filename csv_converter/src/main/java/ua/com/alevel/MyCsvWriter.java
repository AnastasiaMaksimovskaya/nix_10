package ua.com.alevel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class MyCsvWriter {
    public File file;

    public MyCsvWriter(File file) {
        this.file = file;
    }

    public void write(String... data) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file,true))) {
            for (int i = 0; i < data.length; i++) {
                bufferedWriter.write(data[i] + " ");
                if (i==data.length-1){
                    bufferedWriter.write("\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
