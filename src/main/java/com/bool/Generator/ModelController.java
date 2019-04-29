package com.bool.Generator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.bool.model.Model;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ModelController
{

   
    // 装载模型文件
    public List<Object> loadModelList(String path) throws Exception
    {
        List<Object> modelList = new ArrayList();
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
                modelList.add(loadModel(e));//装载标记为model的组件
            }
        }
        
        return modelList;
    }
 
    /**
     * 加载标记为model的组件
     * 
     * @param modelList
     * @return
     */
    public Model loadModel(Element ele)
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

        return model;
    }

}
