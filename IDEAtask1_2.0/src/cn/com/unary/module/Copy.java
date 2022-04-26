package cn.com.unary.module;

import cn.com.unary.exception.FileCreateException;
import com.una.common.log.DebugLogOper;

import java.io.*;

public class Copy
{


    public void fileCopy(String sourceFilePath, String destinationFilePath) throws FileCreateException
    {
        File file = new File(sourceFilePath);   //创建源文件File对象
        File file1 = new File(destinationFilePath + "\\" + file.getParentFile().getName()); //创建 目标文件地址加上模块名 File对象，使之生成正确的目录
        Sour2DestCopy(file, file1, true);
    }

    private void Sour2DestCopy(File f, File nf, boolean flag)throws FileCreateException
    {
        DebugLogOper logOper = null;
        try
        {

            logOper = new DebugLogOper(Copy.class);
            logOper.setConfig("log4j.xml");
            logOper.writeLogAlways(DebugLogOper.INFO,"进入："+this.getClass().getSimpleName()+" 中的 "+Thread.currentThread().getStackTrace()[1].getMethodName() + "方法");

        } catch (Exception e)
        {
            System.out.println("xml路径错误");
        }
        // 判断是否存在
        if (f.exists())
        {
            // 判断是否是目录
            if (f.isDirectory())
            {
                if (flag)
                {
                    // 制定路径，以便原样输出
                    nf = new File(nf + "\\" + f.getName());
                    // 判断文件夹是否存在，不存在就创建
                    if (!nf.exists())
                    {
                        if(nf.mkdirs())
                        {
                            logOper.writeLogAlways(DebugLogOper.INFO,nf.getName()+"目录创建成功！");
                        }
                        else
                        {
                            logOper.writeLogAlways(DebugLogOper.ERROR,nf.getName()+"目录创建失败！");
                            throw new FileCreateException(nf.getName()+"目录创建失败！");
                        }

                    }
                }
                flag = true;
                // 获取文件夹下所有的文件及子文件夹
                File[] childernFile = f.listFiles();
                // 判断是否为null
                if (null != childernFile)
                {
                    for (File f2 : childernFile)
                    {
                        // 循环递归调用
                        Sour2DestCopy(f2, nf, flag);
                    }
                }
            }
            else
            {

                System.out.println("正在复制：" + f.getAbsolutePath());
                System.out.println("到：" + nf.getAbsolutePath() + "\\" + f.getName());

                // 获取输入流
                FileInputStream fis = null;
                // 获取输出流
                FileOutputStream fos = null;
                try
                {
                    fis = new FileInputStream(f);
                    fos = new FileOutputStream(nf + "/" + f.getName());
                } catch (FileNotFoundException fileNotE)
                {
                    logOper.writeExceptionAlways(fileNotE);
                    System.out.println(fileNotE.getCause());
                }
                byte[] b = new byte[1024];
                // 读取文件
                try
                {
                    while (fis.read(b) != -1)
                    {
                        // 写入文件，复制
                        fos.write(b);
                    }
                    fos.close();
                    fis.close();
                } catch (IOException IOE)
                {
                    logOper.writeExceptionAlways(IOE);
                    System.out.println(IOE.getCause());
                }

            }
        }
    }

}
