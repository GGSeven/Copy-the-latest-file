package cn.com.unary.utils;

import java.io.File;

public class CreateFileUtil
{
    public File[] createFile(String path)
    {
        File file = new File(path);
        return file.listFiles(File::isDirectory);
    }
}
