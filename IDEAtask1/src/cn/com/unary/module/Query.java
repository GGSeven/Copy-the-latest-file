package cn.com.unary.module;


import cn.com.unary.utils.CreateFileUtil;
import com.una.common.log.DebugLogOper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Query
{
    public Query()
    {
    }

    public Query(String pathName)
    {
        CreateFileUtil createFileUtil = new CreateFileUtil();
        File[] list = createFileUtil.createFile(pathName);
        int countBefore = 0;
        if (list == null)
        {
            System.out.println("目录不存在或它不是一个目录");
        }
        else
        {
            for (int i = 0; i < list.length; i++)
            {
                String fileName = list[i].getName();
                System.out.println(fileName);
                countBefore++;
            }
            System.out.println("总文件数量：" + countBefore);
        }
    }
    public List<String> fileQuery(File[] list, List<String> filterFileList) //传入文件列表和待过滤的文件列表，后返回过滤后的文件列表
    {
        DebugLogOper logOper = null;
        try
        {
            logOper = new DebugLogOper(Query.class);
            logOper.setConfig("log4j.xml");
            logOper.writeLogAlways(DebugLogOper.INFO,"进入："+this.getClass().getSimpleName()+" 中的 "+Thread.currentThread().getStackTrace()[1].getMethodName() + "方法");
        } catch (Exception e)
        {
            System.out.println("xml路径错误");
        }
        // File[] list =  new CreateFileUtil().createFile(pathName);
        // int countBefore = 0;
        int countAfter = 0;
        List<String> filteredFileList = new ArrayList<>();  //过滤后的文件list

        if (list == null)
        {
            System.out.println("目录不存在或它不是一个目录");
        } else
        {
            for (File file : list)   //遍历目录
            {
                String fileName = file.getName();
                if (!filterFileList.contains(fileName))
                {
                    //    System.out.println("过滤后文件名:" + fileName + "\t");
                    filteredFileList.add(fileName);
                    countAfter++;
                }
            }
            System.out.println("过滤后数量：" + countAfter);
        }
        return filteredFileList;
    }
}
