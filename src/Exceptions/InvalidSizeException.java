package Exceptions;

public class InvalidSizeException extends Exception
{
    public InvalidSizeException()
    {
        super("De preferat ca marimile sa se poata imparti la 2");
    }
}