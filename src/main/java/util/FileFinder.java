package util;


import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;

//Класс, отвечающий за поиск файла согласно введённым параметрам

public class FileFinder {
    private final ArrayList<File> result = new ArrayList<>();

    //Подготовительный для поиска метод. Проверяет наличие указанной для поиска директории (если она не
    // задана, то ищет файл в папке проекта), запускает метод поиска файла, после чего выводит все найденные файлы
    public ArrayList<File> searchFile(String root, String fileName, boolean needChild) {
        result.clear();
        if (root == null)
            root = Paths.get("").toAbsolutePath().toString();
        search(root, fileName, needChild);
        return result;
    }

    //Метод поиска файлов. Пробегается по всем файлам в заданной директории. Если видит папку при прописанном ключе -r,
    //то рекурсивно вызывает себя с указанием этой папки как директории.
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
