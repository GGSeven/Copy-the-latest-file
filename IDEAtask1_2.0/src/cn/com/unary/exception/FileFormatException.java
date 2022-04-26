package cn.com.unary.exception;

/**
 * @Author Seven
 * @Date 2022/4/25
 */
public class FileFormatException extends Exception
{
    public FileFormatException()
    {

    }

    public FileFormatException(String msg)
    {
        super(msg);
    }

    public FileFormatException(String msg, Throwable cause)
    {
        super(msg, cause);
    }

    public FileFormatException(Throwable cause)
    {
        super(cause);
    }
}
