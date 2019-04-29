package com.bool.Generator;

import java.util.List;
import java.util.Map;

import com.bool.model.CompileModel;
import com.bool.model.Model;
import com.bool.model.Output;
import com.bool.model.Row;
import com.bool.utils.CommonUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
public class CompileController {

    public static Map<String, List<Model>> modelsMap = CommonData.modelsMap;
    public static Map<String, List<Row>> rowsMaps = CommonData.rowsMaps;
    public static List<Output> outputsList = CommonData.outputsList;
    public static List<CompileModel> compileList = CommonData.compileList;

    
    /**
     * 编译单个页面
     * @param name rows节点的name属性
     * @param rowsList rows的所有子节点 包括append、var、if、row、for
     * @return
     */
    public CompileModel Compile(String name,List<Row> rowsList){
        CompileModel compileModel = new CompileModel();
        Row row;
        StringBuffer ret = new StringBuffer();
        
        for(int i=0;i<rowsList.size();i++)
        {
            row = (Row)rowsList.get(i);
            if("row".equals(row.getName()))//当元素类型为row
            {
                ret.append(getRetByRow(row));
            }else if("append".equals(row.getName()))//当元素类型为append
            {
                ret.append(getRetByAppend(row));
            }else if("var".equals(row.getName()))//当元素类型为var
            {
                ret.append(getRetByVar(row));
            }else if("if".equals(row.getName()))//当元素类型为if
            {
                ret.append(getRetByIf(row));
            }else if("for".equals(row.getName()))//当元素类型为for
            {
                ret.append(getRetByFor(row));
            }
        }

        compileModel.fileName(name+getSuffixByOutput(name));
        compileModel.setContent(ret.toString());
        compileModel.setOutputPath(getOutputPath(name));
        return compileModel;
    }


    private String getOutputPath(String ref) {
        for(Output output:outputsList)
        {
            
            List<String> refList = output.getRowsRef();
            for(String _ref:refList)
            {
                if(ref.equals(_ref))
                {
                    return output.getOutputPath();
                }
            }
        }
        return null;
    }

    //获取ref的后缀在output文件中查找
    private String getSuffixByOutput(String ref) {
        for(Output output:outputsList)
        {
            
            List<String> refList = output.getRowsRef();
            for(String _ref:refList)
            {
                if(ref.equals(_ref))
                {
                    return output.getSuffix();
                }
            }
        }
        return "";
    }

    private String getRetByFor(Row row) {

        return null;
    }

    private String getRetByIf(Row row) {

        return null;
    }

    private String getRetByVar(Row row) {

        return null;
    }

    private String getRetByAppend(Row row) {

        return null;
    }

    private String getRetByRow(Row row) {
        JSONArray properties = row.getProperties();
        JSONObject property;
        for(int i=0;i<properties.size();i++)
        {
            property = properties.getJSONObject(i);


        }

        return null;
    }
}