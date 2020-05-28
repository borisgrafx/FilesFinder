package util;

import exceptions.FileNotExistsException;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FileFinderTest {

    @Test
    void searchFile() throws FileNotExistsException {

        //Проверка, сможет ли Finder найти список файлов, приведённых ниже
        ArrayList<File> expected = new ArrayList<>();
        expected.add(new File("D:\\FilesFinder\\FilesToFind\\dir 1\\dir 4\\dir 5\\dir 6\\findme.txt"));
        expected.add(new File("D:\\FilesFinder\\FilesToFind\\dir 2\\dir 3\\findme.txt"));
        expected.add(new File("D:\\FilesFinder\\FilesToFind\\dir 2\\findme.txt"));
        expected.add(new File("D:\\FilesFinder\\FilesToFind\\findme.txt"));
        FileFinder finder = new FileFinder();
        try {
            //Испытание ключи -d и -r
            ArrayList<File> files = finder.searchFile("D:\\FilesFinder\\FilesToFind", "findme.txt", true);
            assertArrayEquals(expected.toArray(), files.toArray());
            //Испытание отсутствие ключа -d
            files = finder.searchFile(null, "findme.txt", true);
            assertArrayEquals(expected.toArray(), files.toArray());
        } catch (FileNotExistsException e) {
            e.printStackTrace();
        }

        //Вызов исключения, оповещающего о несуществовании файла
        assertThrows(FileNotExistsException.class, () -> {
            ArrayList<File> files = finder.searchFile("D:\\FilesFinder\\FilesToFind", "gig.txt", false);
            assertArrayEquals(expected.toArray(), files.toArray());
            files = finder.searchFile(null, "findme.txt", true);
            assertArrayEquals(null, files.toArray());
        });

        //Вызов исключения, оповещающего о некорректном вводе данных
        assertThrows(IllegalArgumentException.class, () -> {
            Parser parser = new Parser();
            parser.parse(new String[] {"-d", "-r", "-r", "-d"});
        });

        //Проверка, портят ли пробелы в названии файла работу программы
        expected.clear();
        expected.add(new File("D:\\FilesFinder\\FilesToFind\\I have got a space in my name.txt"));
        //Проверка без ключа -d
        ArrayList<File> files = finder.searchFile(null, "I have got a space in my name.txt", true);
        assertArrayEquals(expected.toArray(), files.toArray());
        //Проверка с ключом -d
        files = finder.searchFile("D:\\FilesFinder\\FilesToFind", "I have got a space in my name.txt", true);
        assertArrayEquals(expected.toArray(), files.toArray());
    }
}