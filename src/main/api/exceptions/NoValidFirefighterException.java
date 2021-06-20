package main.api.exceptions;

public class NoValidFirefighterException extends RuntimeException{
    public NoValidFirefighterException() {
        super("No firefighter is available!");
    }
}
