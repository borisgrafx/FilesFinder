package util;

import exceptions.FileNotExistsException;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileFinder {
    private ArrayList<File> result = new ArrayList<>();

    public ArrayList<File> searchFile(String root, String fileName, boolean needChild) throws FileNotExistsException {
        result.clear();
        if (root == null)
            root = Paths.get("").toAbsolutePath().toString();
        search(root, fileName, needChild);

        if (result.size() == 0)
            throw new FileNotExistsException("File not found!");
        return result;
    }

    private void search(String root, String fileName, boolean needChild) {
        File file = new File(root);
        File[] files = file.listFiles();
        if (files == null)
            return;

        for (File value : files) {
            if (value.isFile() && value.getName().equals(fileName))
                result.add(value);
            if (needChild && value.isDirectory()) {
                search(value.toString(), fileName, true);
            }
        }
    }
}
