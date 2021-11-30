package ua.com.alevel.util;

import java.io.*;
import java.util.List;

public class UpdateAndDeleteByRewriting {
    private static final String FILE_PATH = ".\\src\\main\\java\\ua\\com\\alevel\\files\\";

    public static void deleteEntity(List<String> entityLine, File file) throws IOException {
        File buffer = new File(FILE_PATH + "buffer.csv");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            BufferedWriter writer = new BufferedWriter(new FileWriter(buffer));
            while (reader.ready()) {
                boolean flag = true;
                String current = reader.readLine();
                for (int i = 0; i < entityLine.size(); i++) {
                    if ((current.equals(entityLine.get(i)) || entityLine.get(i).equals(current + " ") || current.equals(entityLine.get(i) + " "))) {
                        flag = false;
                    }
                }
                if (flag) {
                    writer.write(current + "\n");
                }
            }
            writer.flush();
            reader.close();
            writer.close();
            file.delete();
            buffer.renameTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateEntity(List<String> entityLine, File file, String updated) throws IOException {
        File buffer = new File(FILE_PATH + "buffer.csv");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            BufferedWriter writer = new BufferedWriter(new FileWriter(buffer));
            while (reader.ready()) {
                boolean flag = true;
                String current = reader.readLine();
                for (int i = 0; i < entityLine.size(); i++) {
                    if ((current.equals(entityLine.get(i)) || entityLine.get(i).equals(current + " ") || current.equals(entityLine.get(i) + " "))) {
                        flag = false;
                    }
                }
                if (flag) {
                    writer.write(current + "\n");
                }
            }
            writer.write(updated);
            writer.flush();
            reader.close();
            writer.close();
            file.delete();
            buffer.renameTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
