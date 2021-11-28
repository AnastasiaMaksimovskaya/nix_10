package ua.com.alevel;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MyCsvReader {

    public File file;

    public MyCsvReader(File file) {
        this.file = file;
    }

    public List<String[]> read() {
        List<String[]> read = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            while (bufferedReader.ready()) {
                read.add(bufferedReader.readLine().split(" "));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return read;
    }
}
