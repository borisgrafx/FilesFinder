package exceptions;

public class FileNotExistsException extends Exception {
    public FileNotExistsException(String message) {
        super(message);
    }
}
