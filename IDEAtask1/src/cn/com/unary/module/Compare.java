package cn.com.unary.module;

import cn.com.unary.exception.FileException;
import cn.com.unary.exception.FileException2;
import cn.com.unary.utils.CreateFileUtil;
import cn.com.unary.utils.QuickSortUtil;
import com.una.common.log.DebugLogOper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Compare
{

    public List<String> latestVersion(String pathName) throws FileException,FileException2
    {
        String filteredPathName;
        FilterFile filterFile = new FilterFile();
        List<String> filteredFileList = filterFile.filteredFile(pathName);  //filteredFileList为过滤后的文件list
        List<String> filteredLastePath = new ArrayList<>();  //filteredPath用于保存过滤文件的路径
        DebugLogOper logOper =null;
        CreateFileUtil createFileUtil = new CreateFileUtil();
        try
        {
            logOper = new DebugLogOper(Compare.class);
            logOper.setConfig("log4j.xml");
            logOper.writeLogAlways(DebugLogOper.INFO,"进入："+this.getClass().getSimpleName()+" 中的 "+Thread.currentThread().getStackTrace()[1].getMethodName() + "方法");
        }
        catch (Exception e)
        {
            logOper.writeExceptionAlways(e);
            System.out.println("xml路径错误");
        }


        for (int i = 0; i < filteredFileList.size(); i++)     //对过滤后文件列表遍历
        {
            filteredPathName = pathName + "\\" + filteredFileList.get(i);   //获取每个模块的路径
            //System.out.println(filteredFileList.get(i));

            File[] list = createFileUtil.createFile(filteredPathName);
              if(list.length <= 0)
                {
                    System.out.println("进入文件夹为空");
                    logOper.writeLogAlways(DebugLogOper.ERROR, filteredFileList.get(i) + ": 为空文件");
                    throw new FileException(filteredFileList.get(i) + ": 为空文件");
                }


            int[] date = new int[Objects.requireNonNull(list).length]; //新建日期int型数组date存储转换后的数字

            for (int j = 0; j < list.length; j++)       //对模块的的版本号遍历，找到最新文件
            {
                String fileName = list[j].getName();
                //System.out.println(fileName);
                try
                {
                    date[j] = Integer.parseInt(fileName);   //将String类型的文件版本号转成int存入date数组
                }
                catch (Exception e)
                {
                    logOper.writeLogAlways(DebugLogOper.ERROR, filteredFileList.get(i) + " 中" + "错误文件名格式为：" + fileName);
                    throw new FileException2(filteredFileList.get(i) + "  中" + "错误文件名格式为：" + fileName,e.getCause());
                }
            }
            new QuickSortUtil().quickSort(date, 0, list.length - 1); //调用快速排序算法对date类排序

            //System.out.println(filteredFileList.get(i) + "data[0] = "+date[0]);   //测试用

            System.out.println(filteredFileList.get(i) + "  中" + "最新文件：" + date[date.length - 1]);  //date[list.length-1]中为最大数字，即最新版本
            filteredLastePath.add(filteredPathName + "\\" + date[list.length - 1]);
            // System.out.println(filteredPathName + "\\" + date[list.length-1]);  //需要拷贝的文件目录
        }
        try
        {
            for (String s : filteredLastePath)
            {
                logOper.writeLogAlways(DebugLogOper.INFO,s);
                System.out.println(s);
            }
        }
        catch (NullPointerException nullPointerE)
        {
            logOper.writeExceptionAlways(nullPointerE);
            System.out.println(nullPointerE.getCause());

        }
        return filteredLastePath;
    }

}
