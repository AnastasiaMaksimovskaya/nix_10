package ua.com.alevel;

import ua.com.alevel.config.ConfigFiles;
import ua.com.alevel.controller.MainController;

public class IoNioMain {

    public static void main(String[] args) {
        new ConfigFiles().configFiles();
        new MainController().run();
    }
}