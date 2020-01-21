package exception;

public class ForbidenValueProvidedException extends RuntimeException{

    public ForbidenValueProvidedException() {
        super("You can provide such value as an argument");
    }
}
