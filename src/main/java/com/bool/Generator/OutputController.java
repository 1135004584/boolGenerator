package com.bool.Generator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.bool.model.Model;
import com.bool.model.Output;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
public class OutputController
{
    public Output loadOutput(Element e)
    {
        Output output = new Output();
        List<String> rowsRef = new ArrayList<>();

        for(Element ele:(List<Element>)e.elements("page"))
        {
            rowsRef.add(ele.attributeValue("rowsRef"));
        }
        output.setRowsRef(rowsRef);
        output.setOutputPath(e.getStringValue());
        output.setSuffix(e.attributeValue("suffix"));
        return output;
    }

    /**
     * 解析单个的output xml文件
     * 
     * @param path
     * @return
     * @throws Exception
     */
    public List<Output> loadOutputList(String path) throws Exception
    {
        List<Output> outputList = new ArrayList();
        File file = new File(path);
        if(!file.exists())//如果找不到模型文件
            throw new Exception("model file not found!");
        
        Document doc = new SAXReader().read(file);
        Element element = doc.getRootElement();
        List<Element> list = element.elements();
        
        for(Element e:list)
        {
            outputList.add(loadOutput(e));//装载row的组件(row、append、var、if)
        }
        
        return outputList;
    }
}