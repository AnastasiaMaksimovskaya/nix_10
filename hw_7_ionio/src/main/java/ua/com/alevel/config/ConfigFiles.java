package ua.com.alevel.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ConfigFiles {

    public static void configFiles() {
        Path productFile = Paths.get(".\\src\\main\\java\\ua\\com\\alevel\\files\\product.csv");
        Path shopFile = Paths.get(".\\src\\main\\java\\ua\\com\\alevel\\files\\shop.csv");
        try {
            if (!Files.exists(productFile)) {
                Files.createFile(productFile);
            } else {
                Files.delete(productFile);
                Files.createFile(productFile);
            }
            if (!Files.exists(shopFile)) {
                Files.createFile(shopFile);
            } else {
                Files.delete(shopFile);
                Files.createFile(shopFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
