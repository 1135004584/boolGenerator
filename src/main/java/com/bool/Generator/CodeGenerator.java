package com.bool.Generator;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bool.Generator.interfaces.ICodeGenerator;
import com.bool.Generator.model.Model;
import com.bool.Generator.model.Row;
import com.bool.Generator.utils.*;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class CodeGenerator implements ICodeGenerator{
    // 模型文件map
    /**
     * String name
     * List<Model> model list
     */
    private static Map<String, List<Object>> modelsMap = new HashMap<>();

    // 行文件map
    /**
     * String namespace
     * List<Row> row list
     */
    private static Map<String, List<Object>> rowsMaps = new HashMap<>();

    // 装载所有模型文件
    /**
     * 会对path目录下的所有.xml进行装载,可以递归搜索
     * 
     * @param path
     * @throws Exception
     */
    @Override
    public void loadAllTemplet(String path) throws Exception {
        File file = new File(path);
        if (!file.isDirectory())// 如果不是目录
            throw new Exception("path is not a directory!!");

        List<String> xmlPathList = CommonUtil.getAllFilePathsByPath(path);
        List<Model> ModelList;
        System.out.println();
        for (String xmlPath : xmlPathList) {
            modelsMap.put(getTempletName(xmlPath),loadModelList(xmlPath));
            System.out.println("load -> "+xmlPath);
        }


        System.out.println("装载所有模型成功!");
        System.out.println();

    }

    // 获取model名称
    private String getTempletName(String path) throws DocumentException
    {
        File file = new File(path);
        Document doc = new SAXReader().read(file);
        Element element = doc.getRootElement();
        return element.attribute("name").getValue();
    }
    // 装载模型文件
    private List<Object> loadModelList(String path) throws Exception
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
    private Model loadModel(Element ele)
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

    /**
     * 加载所有行文件
     */
    @Override
    public void loadAllRows(String path) throws Exception {
        File file = new File(path);
        if (!file.isDirectory())// 如果不是目录
            throw new Exception("path is not a directory!!");

        List<String> xmlPathList = CommonUtil.getAllFilePathsByPath(path);
        List<Model> ModelList;
        System.out.println();
        for (String xmlPath : xmlPathList) {
            modelsMap.put(getTempletName(xmlPath),loadRowslList(xmlPath));
            System.out.println("load -> "+xmlPath);
        }


        System.out.println("装载所有模型成功!");
        System.out.println();
    }

    

 // 装载行文件
 private List<Object> loadRowslList(String path) throws Exception
 {
     List<Object> modelList = new ArrayList();
     File file = new File(path);
     if(!file.exists())//如果找不到模型文件
         throw new Exception("model file not found!");
     
     Document doc = new SAXReader().read(file);
     Element element = doc.getRootElement();
     List<Element> list = element.elements();
     
     for(Element e:list)
     {

        modelList.add(loadRow(e));//装载row的组件(row、append、var、if)
     }
     
     return modelList;
 }

 //加载行文件，包括:row、append、var、if
 private Row loadRow(Element e)
 {
     Row row = null;

    if("row".equals(e.getName()))//row要考虑递归结构
    {
        List<Attribute> listAttr;
        int i;
        JSONArray jsonattrProperties;
        JSONObject jsonProperty;
        jsonattrProperties = new JSONArray();
        listAttr = e.attributes();

        row = new Row();

        for(Attribute attr : listAttr)//设置row的属性
        {
            if("id".equals(attr.getName()))//设置row的id
            {
                row.setId(Integer.parseInt(attr.getStringValue()));
            }else if("ref".equals(attr.getName()))//设置row的ref
            {
                row.setRef(attr.getStringValue());
            }else
            {
                jsonProperty = new JSONObject();
                jsonProperty.put(attr.getName(), attr.getStringValue());
                jsonattrProperties.add(jsonProperty);
            }
        }
        row.setProperties(jsonattrProperties);
        

    }else if("append".equals(e.getName()))
    {
        row = new Row();

    }else if("var".equals(e.getName()))
    {
        row = new Row();

    }else if("if".equals(e.getName()))
    {
        row = new Row();

    }

    return row;
 }
}


