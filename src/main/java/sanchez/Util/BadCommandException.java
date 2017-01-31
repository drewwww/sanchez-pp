package sanchez.Util;

public class BadCommandException extends Exception
{
    public BadCommandException()
    {
        super();
    }

    public BadCommandException(String msg)
    {
        super(msg);
    }

    public BadCommandException(Throwable cause)
    {
        super(cause);
    }

    public BadCommandException(String msg, Throwable cause)
    {
        super(msg, cause);
    }
}
