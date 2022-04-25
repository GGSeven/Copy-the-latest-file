package cn.com.unary.exception;

/**
 * @Author Seven
 * @Date 2022/4/25
 */
public class FileException extends Exception
{
    public FileException(){

    }
    public FileException(String msg){
        super(msg);
    }
    public FileException(String msg,Throwable cause){
        super(msg,cause);
    }
    public FileException(Throwable cause){
        super(cause);
    }
}
