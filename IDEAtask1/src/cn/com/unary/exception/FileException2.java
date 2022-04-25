package cn.com.unary.exception;

/**
 * @Author Seven
 * @Date 2022/4/25
 */
public class FileException2 extends Exception
{
    public FileException2()
    {

    }
    public FileException2(String msg){
        super(msg);
    }
    public FileException2(String msg,Throwable cause){
        super(msg,cause);
    }
    public FileException2(Throwable cause){
        super(cause);
    }
}
