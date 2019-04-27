package com.bool.Generator;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bool.Generator.model.Model;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class CodeGenerator {
    //模型文件列表
    private static List<Model> modelList = new ArrayList();

    //装载模型文件
    public void loadTemplet(String path) throws Exception
    {
        File file = new File(path);
        if(!file.exists())//如果找不到模型文件
            throw new Exception("model file not found!");
        
        Document doc = new SAXReader().read(file);
        Element element = doc.getRootElement();
        List<Element> list = element.elements();
        
        Model model;
        for(Element e:list)
        {
            if("model".equals(e.getName()))//如果该元素是model
            {
                loadModel(e);//装载标记为model的组件
            }
        }
        
    }
 
    /**
     * 加载标记为model的组件
     * @param modelList
     */
    private void loadModel(Element ele)
    {
        Model model = new Model();
        List<Attribute> attritube = ele.attributes();
        for(Attribute attr:attritube)
        {
            if("name".equals(attr.getName()))//设置name
            {
                model.setModelName(attr.getValue());
            }
        }

        //设置节点的属性节点
        Element eleProperties = ele.element("properties");

        List<Element> propertyEles = eleProperties.elements("property");

        JSONArray jsonArrayAttr = new JSONArray();
        JSONObject jsonProperty = null;
        for(Element e:propertyEles)
        {
            attritube = e.attributes();
            for(Attribute attr:attritube)
            {
                jsonProperty = new JSONObject();
                jsonProperty.put(attr.getName(),attr.getValue());
            }
            jsonArrayAttr.add(jsonProperty);
        }
        model.setProperties(jsonArrayAttr);//装载属性列表

        //获取源文本
        Element eleSource = ele.element("source-text");
        model.setSource(eleSource.getStringValue());
        //装载进model列表
        modelList.add(model);
    }
    public void generateToFile(String templetXmlFilePath,
                               String destFilePath)
    {

    }
}
