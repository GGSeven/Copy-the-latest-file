package cn.com.unary.controller;

import cn.com.unary.exception.FileException;
import cn.com.unary.exception.FileException2;
import cn.com.unary.module.Compare;
import cn.com.unary.module.Copy;
import cn.com.unary.module.FilterFile;

import java.util.List;

public class WindowsController
{
    private FilterFile filterFile = new FilterFile();
    private Copy copy = new Copy();
    private Compare compare = new Compare();

    public void fileController(String sourcePath, String targetPath) throws FileException, FileException2
    {
        //filterFile.filteredFile(sourcePath);
        List<String> list = compare.latestVersion(sourcePath);
            for (String s : list)
            {
                copy.fileCopy(s, targetPath);
            }


    }

}
