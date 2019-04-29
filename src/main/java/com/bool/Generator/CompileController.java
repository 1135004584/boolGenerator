package com.bool.Generator;

import java.util.List;
import java.util.Map;

import com.bool.model.CompileModel;
import com.bool.model.Model;
import com.bool.model.Output;
import com.bool.model.Row;
import com.bool.utils.CommonUtil;
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
        Row row;
        for(int i=0;i<rowsList.size();i++)
        {
            row = (Row)rowsList.get(i);


        }

        return null;
    }
}