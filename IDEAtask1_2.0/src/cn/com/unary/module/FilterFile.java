package cn.com.unary.module;

import cn.com.unary.exception.FileCreateException;
import cn.com.unary.utils.CreateFileUtil;
import com.una.common.log.DebugLogOper;
import org.w3c.dom.Document;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class FilterFile
{
    private DebugLogOper logOper = null;
    public List<String> getFilterList() //解析XML文件获得需要过滤的List文件filterFilelist
    {
        try
        {
            logOper = new DebugLogOper(FilterFile.class);
            logOper.setConfig("log4j.xml");
            logOper.writeLogAlways(DebugLogOper.INFO,"进入："+this.getClass().getSimpleName()+" 中的 "+Thread.currentThread().getStackTrace()[1].getMethodName() + "方法");

        } catch (Exception e)
        {
            System.out.println("xml路径错误");
        }

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = null;
        try
        {
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException parseE)
        {
            logOper.writeExceptionAlways(parseE);
            System.out.println(parseE.getCause());
        }
        Document doc = null;
        try
        {
            doc = db.parse(new File("src/filter.xml"));
        } catch (SAXException SAXE)
        {
            logOper.writeExceptionAlways(SAXE);
            System.out.println(SAXE.getCause());
        } catch (IOException IOE)
        {
            logOper.writeExceptionAlways(IOE);
            System.out.println(IOE.getCause());
        }

//        Element element = document.getDocumentElement();
        NodeList nodeList = doc.getElementsByTagName("name");

        System.out.println("当前待过滤文件数：" + nodeList.getLength());

        List<String> filterFileList = new ArrayList<>();   //需要过滤的文件list
        for (int i = 0; i < nodeList.getLength(); i++)
        {
            Node node = nodeList.item(i);
            String filterFileName = node.getTextContent();
            filterFileList.add(filterFileName);
        }
        for (String s : filterFileList)
        {
            System.out.println("过滤文件名：" + s);
        }
        return filterFileList;
    }

    public List filteredFile(String pathName) throws FileCreateException//创建过滤后的文件列表
    {

        CreateFileUtil createFileUtil = new CreateFileUtil();
        File[] list = createFileUtil.createFile(pathName);    //指定的文件
        List<String> filterFileList = getFilterList();  //传入需要过滤的文件list
        List<String> filteredFileList = new ArrayList<>();  //用于接收过滤后的文件list

        try
        {
            logOper = new DebugLogOper(FilterFile.class);
            logOper.setConfig("log4j.xml");
            logOper.writeLogAlways(DebugLogOper.INFO," 进入："+this.getClass().getSimpleName()+" 中的 "+Thread.currentThread().getStackTrace()[1].getMethodName() + "方法");
        } catch (Exception e)
        {
            System.out.println("xml路径错误");
        }
        // File[] list =  new CreateFileUtil().createFile(pathName);
        // int countBefore = 0;
        int countAfter = 0;

        if (list == null)
        {
            System.out.println("目录不存在或它不是一个目录");
            throw new FileCreateException(pathName+"  目录不存在或它不是一个目录");
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
