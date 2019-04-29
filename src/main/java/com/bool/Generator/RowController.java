package com.bool.Generator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.bool.model.Model;
import com.bool.model.Row;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
public class RowController
{
    
 // 装载行文件
 public List<Object> loadRowslList(String path) throws Exception
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
 public Row loadRow(Element e)
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
  public void loadRowProperties(Row row,Element e)
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

}