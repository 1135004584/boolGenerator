package com.bool.Generator;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.bool.interfaces.ICodeGenerator;
import com.bool.model.CompileModel;
import com.bool.model.Model;
import com.bool.model.Output;
import com.bool.model.Row;
import com.bool.utils.*;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.bool.Generator.CommonData;
public class CodeGenerator implements ICodeGenerator{
  
    public static Map<String, List<Object>> modelsMap = CommonData.modelsMap;
    public static Map<String, List<Object>> rowsMaps = CommonData.rowsMaps;
    public static List<Output> outputsList = CommonData.outputsList;
    public static List<CompileModel> compileList = CommonData.compileList;

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
        ModelController modelController = new ModelController();
        for (String xmlPath : xmlPathList) {
            modelsMap.put(getTempletName(xmlPath),modelController.loadModelList(xmlPath));
            System.out.println("load -> "+xmlPath);
        }
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
        RowController rowController = new RowController();
        for (String xmlPath : xmlPathList) {
            modelsMap.put(getTempletName(xmlPath),rowController.loadRowslList(xmlPath));
            System.out.println("load -> "+xmlPath);
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
        OutputController outputController = new OutputController();
        for (String xmlPath : xmlPathList) {
            outputsList.addAll(outputController.loadOutputList(xmlPath));
            System.out.println("load -> "+xmlPath);
        }
    }

    //获取model名称
    private String getTempletName(String path) throws DocumentException
    {
        File file = new File(path);
        Document doc = new SAXReader().read(file);
        Element element = doc.getRootElement();
        if("".equals(element.attribute("name").getValue()))
        {
            return element.getName();
        }
        return element.attribute("name").getValue();
    }

    //开始编译所有
    public void startCompileAll()
    {
        Set<String> keyset = rowsMaps.keySet();
        Iterator<String> iter = keyset.iterator();
        CompileModel compileModel;
        CompileController compileController = new CompileController();

        while(iter.hasNext())
        {
            compileModel = compileController.Compile(iter.next(),
                                                rowsMaps.get(iter.next()));
            compileList.add(compileModel);
        }
    }

    //开始生成所有文件
    public void startGenerateAllFile()
    {

    }
 
}