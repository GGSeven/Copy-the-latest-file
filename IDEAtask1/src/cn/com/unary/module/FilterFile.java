package cn.com.unary.module;

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
    public List<String> getFilterList() //解析XML文件获得需要过滤的List文件filterFilelist
    {
        DebugLogOper logOper = null;
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

    public List filteredFile(String pathName) //创建过滤后的文件列表
    {

        CreateFileUtil createFileUtil = new CreateFileUtil();
        File[] list = createFileUtil.createFile(pathName);    //指定的文件
        List<String> filterFileList = getFilterList();  //传入需要过滤的文件list
        Query query = new Query();  //调用遍历模块
        List<String> filteredFileList;  //用于接收过滤后的文件list

        filteredFileList = query.fileQuery(list, filterFileList);
        return filteredFileList;

    }

}
