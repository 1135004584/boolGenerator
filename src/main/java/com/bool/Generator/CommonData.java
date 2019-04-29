package com.bool.Generator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bool.model.Output;

public class CommonData {
// 模型文件map
    /**
     * String name
     * List<Model> model list
     */
    public static Map<String, List<Object>> modelsMap = new HashMap<>();

    // 行文件map
    /**
     * String namespace
     * List<Row> row list
     */
    public static Map<String, List<Object>> rowsMaps = new HashMap<>();


    public static List<Output> outputsList = new ArrayList<>();
    
    
}