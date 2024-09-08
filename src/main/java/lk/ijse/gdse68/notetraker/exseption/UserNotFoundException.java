package lk.ijse.gdse68.notetraker.exseption;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {}

    public UserNotFoundException(String massage) {}

    public UserNotFoundException(String massage, Throwable throwable) {}
}
