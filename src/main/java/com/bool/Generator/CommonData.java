package com.bool.Generator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bool.model.CompileModel;
import com.bool.model.Model;
import com.bool.model.Output;
import com.bool.model.Row;

public class CommonData {
// 模型文件map
    /**
     * String name
     * List<Model> model list
     */
    public static Map<String, List<Model>> modelsMap = new HashMap<>();

    // 行文件map
    /**
     * String namespace
     * List<Row> row list
     */
    public static Map<String, List<Row>> rowsMaps = new HashMap<>();

    //输出文件集合
    public static List<Output> outputsList = new ArrayList<>();
    
    //生成模型集合
    public static List<CompileModel> compileList = new ArrayList<>();
    
}