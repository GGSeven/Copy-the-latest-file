package cn.com.unary.controller;

import cn.com.unary.exception.FileCreateException;
import cn.com.unary.exception.FileFormatException;
import cn.com.unary.exception.FileIsNullException;
import cn.com.unary.module.Compare;
import cn.com.unary.module.Copy;
import cn.com.unary.module.FilterFile;

import java.util.List;

public class WindowsController
{
    private FilterFile filterFile = new FilterFile();
    private Copy copy = new Copy();
    private Compare compare = new Compare();

    public String fileController(String sourcePath, String targetPath)
    {
        try
        {
            List<String> m_filteredFileList = filterFile.filteredFile(sourcePath);  //m_filteredFileList为过滤后的文件list
            List<String> m_list = compare.latestVersion(sourcePath, m_filteredFileList);

            //filterFile.filteredFile(sourcePath);
            for (String s : m_list)
            {
                copy.fileCopy(s, targetPath);
            }
        }
        catch (FileIsNullException e1)
        {
            System.out.println("文件异常，请检查是否有空或者格式错误文件！");
            return e1.getMessage();
        }
        catch (FileFormatException e2)
        {
            System.out.println("文件异常，请检查是否有空或者格式错误文件！");
            return e2.getMessage();
        }
        catch (FileCreateException e3)
        {
            System.out.println("文件创建异常！");
            return e3.getMessage();

        }
        return "1";
    }

}
