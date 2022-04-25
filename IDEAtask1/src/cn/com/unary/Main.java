package cn.com.unary;


import cn.com.unary.module.Compare;
import cn.com.unary.view.Window;
import com.una.common.log.DebugLogOper;


public class Main
{

    public static void main(String[] args)
    {
        DebugLogOper logOper =null;
        try
        {
            logOper = new DebugLogOper(Main.class);
            logOper.setConfig("log4j.xml");
            logOper.writeLogAlways(DebugLogOper.INFO,"成功找到log4j.xml文件！");
            logOper.writeLogAlways(DebugLogOper.INFO,"进入:Main类"+" 中的 "+Thread.currentThread().getStackTrace()[1].getMethodName() + "方法");

        }
        catch (Exception e)
        {
            logOper.writeExceptionAlways(e);
            System.out.println("xml路径错误！");
        }
        new Window();
    }
}
