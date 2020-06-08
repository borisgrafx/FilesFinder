package program;

import util.FileFinder;
import util.Parser;

import java.io.File;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        //Создание парсера для чтения введённой строки и её "расслоения" на ключи и имя нужного файла
        try {
            Parser parser = new Parser();
            parser.parse(args);
            String fileName = parser.getFileName();
            String path = parser.getPath();
            boolean needChild = parser.isNeedChild();
            //Вызов функции поиска. Вывод результата поиска
            FileFinder finder = new FileFinder();
            ArrayList<File> files = finder.searchFile(path, fileName, needChild);
            for (File file : files)
                System.out.println(file);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
