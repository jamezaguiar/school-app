package server.exception;

public class StudentAlreadyExistsException extends Exception {
    public StudentAlreadyExistsException(String message) {
        super(message);
    }
}
