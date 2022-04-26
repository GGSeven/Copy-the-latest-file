package cn.com.unary.exception;

/**
 * @Author Seven
 * @Date 2022/4/25
 */
public class FileIsNullException extends Exception
{
    public FileIsNullException()
    {

    }

    public FileIsNullException(String msg)
    {
        super(msg);
    }

    public FileIsNullException(String msg, Throwable cause)
    {
        super(msg, cause);
    }

    public FileIsNullException(Throwable cause)
    {
        super(cause);
    }
}
