package util;

import exceptions.FileNotExistsException;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FileFinderTest {

    @Test
    void searchFile() {
        ArrayList<File> expected = new ArrayList<>();
        expected.add(new File("D:\\IntelliJ IDEA Community Edition 2019.2.1\\FilesFinder\\FilesToFind\\dir 1\\dir 4\\dir 5\\dir 6\\findme.txt"));
        expected.add(new File("D:\\IntelliJ IDEA Community Edition 2019.2.1\\FilesFinder\\FilesToFind\\dir 2\\dir 3\\findme.txt"));
        expected.add(new File("D:\\IntelliJ IDEA Community Edition 2019.2.1\\FilesFinder\\FilesToFind\\dir 2\\findme.txt"));
        expected.add(new File("D:\\IntelliJ IDEA Community Edition 2019.2.1\\FilesFinder\\FilesToFind\\findme.txt"));
        FileFinder finder = new FileFinder();

        try {
            ArrayList<File> files = finder.searchFile("D:\\IntelliJ IDEA Community Edition 2019.2.1\\FilesFinder\\FilesToFind", "findme.txt", true);
            assertArrayEquals(expected.toArray(), files.toArray());
            files = finder.searchFile(null, "findme.txt", true);
            assertArrayEquals(expected.toArray(), files.toArray());
        } catch (FileNotExistsException e) {
            e.printStackTrace();
        }

        assertThrows(FileNotExistsException.class, () -> {
            ArrayList<File> files = finder.searchFile("D:\\IntelliJ IDEA Community Edition 2019.2.1\\FilesFinder\\FilesToFind", "gig.txt", false);
            assertArrayEquals(expected.toArray(), files.toArray());
            files = finder.searchFile(null, "findme.txt", true);
            assertArrayEquals(null, files.toArray());
        });
        assertThrows(IllegalArgumentException.class, () -> {
            Parser parser = new Parser();
            parser.parse(new String[] {"-d", "-r", "-r", "-d"});
        });
    }
}