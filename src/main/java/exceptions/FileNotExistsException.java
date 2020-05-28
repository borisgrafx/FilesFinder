package exceptions;

//Исключение, предупредающее о несуществовании файла

public class FileNotExistsException extends Exception {
    public FileNotExistsException(String message) {
        super(message);
    }
}