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


    private static List<>
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


        System.out.println("装载所有row文件成功!");
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
 //所有都是row
 private Row loadRow(Element e)
 {

     Row row = new Row();
     Row _row = row;
     List<Element> list_father = new ArrayList<>();//父容器
     List<List<Element>> list_child = new ArrayList<>();//子元素容器

     list_father.add(e);
     Row parentRow = row;
     Element element;
     int i;
     for(i=0;i<list_father.size();i++)//遍历父元素节点
     {
        element = list_father.get(i);
        loadRowProperties(row,element);//加载row的属性
        List<Element> list = new ArrayList<>();
        for(Element _ele : (List<Element>)element.elements())
        {
            list.add(_ele);
        }
        list_child.add(list);

        if(element == list_father.get(list_father.size()-1))//如果是最后一个元素，则表明当前父节点已经全部遍历，需要跳到下一级节点
        {
            list_father.clear();//清空
            list_father = list_child.get(i);
            i=0;
            if(row.getChildRow()!=null)
                row = row.getChildRow().get(0);
        }else{//跳到下一个节点
            row = parentRow.getChildRow().get(i);   
        }
     }

     
     return _row;
 }

 /**
  * 设置row的属性
  * @param row
  * @param e
  */
 private void loadRowProperties(Row row,Element e)
 {
    row.setName(e.getName());//装载row名,包括:row、append、var、if
    List<Attribute> listAttr = e.attributes();
    JSONArray properties = new JSONArray();
    JSONObject property;
    for(Attribute attr:listAttr)
    {
        if("id".equals(attr.getName()))
        {
            row.setId(Integer.parseInt(attr.getValue()));
        }else{
            property = new JSONObject();
            property.put(e.getName(), e.getStringValue());
            properties.add(property);
        }
    }
    row.setProperties(properties);//装载属性

    List<Element> list_ele = e.elements();
    if(list_ele.size()!=0)//如果有嵌套的情况
    {
        List<Row> list_row = new ArrayList<>();
        Row _row;
        for(Element ele:list_ele)
        {
            _row = new Row();
            _row.setName(ele.getName());//装载row名,包括:row、append、var、if
            listAttr = ele.attributes();
            properties = new JSONArray();
            for(Attribute attr:listAttr)
            {
                if("id".equals(attr.getName()))
                {
                    _row.setId(Integer.parseInt(attr.getValue()));
                }else{
                    property = new JSONObject();
                    property.put(ele.getName(), ele.getStringValue());
                    properties.add(property);
                }
            }
            _row.setProperties(properties);//装载属性
            list_row.add(_row);
        }
        row.setChildRow(list_row);//设置子元素
    }
 }


    /**
     * 加载所有的输出文件
     */
    @Override
    public void loadAllOutput(String path) throws Exception {
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

        System.out.println("装载输出文件成功!");
        System.out.println();
    }
}


