package util;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FileFinderTest {

    @Test
    void main() {

        //Проверка, сможет ли Finder найти список файлов, приведённых ниже
        ArrayList<File> expected = new ArrayList<>();
        expected.add(new File(Paths.get("").toAbsolutePath().toString() + "\\FilesToFind\\dir 1\\dir 4\\dir 5\\dir 6\\findme.txt"));
        expected.add(new File(Paths.get("").toAbsolutePath().toString() + "\\FilesToFind\\dir 2\\dir 3\\findme.txt"));
        expected.add(new File(Paths.get("").toAbsolutePath().toString() + "\\FilesToFind\\dir 2\\findme.txt"));
        expected.add(new File(Paths.get("").toAbsolutePath().toString() + "\\FilesToFind\\findme.txt"));
        FileFinder finder = new FileFinder();

        //Испытание ключей -d и -r
        ArrayList<File> files = finder.searchFile(Paths.get("").toAbsolutePath().toString(), "findme.txt", true);
        assertArrayEquals(expected.toArray(), files.toArray());
        //Испытание отсутствия ключа -d
        files = finder.searchFile(null, "findme.txt", true);
        assertArrayEquals(expected.toArray(), files.toArray());

        //Вызов исключения, оповещающего о некорректном вводе данных
        assertThrows(IllegalArgumentException.class, () -> {
            Parser parser = new Parser();
            parser.parse(new String[]{"-d", "-r", "-r", "-d"});
        });

        //Проверка, портят ли пробелы в названии файла работу программы
        expected.clear();
        expected.add(new File(Paths.get("").toAbsolutePath().toString() + "\\FilesToFind\\I have got a space in my name.txt"));
        //Проверка без ключа -d
        files = finder.searchFile(null, "I have got a space in my name.txt", true);
        assertArrayEquals(expected.toArray(), files.toArray());
        //Проверка с ключом -d
        files = finder.searchFile(Paths.get("").toAbsolutePath().toString() + "\\FilesToFind", "I have got a space in my name.txt", true);
        assertArrayEquals(expected.toArray(), files.toArray());
    }
}