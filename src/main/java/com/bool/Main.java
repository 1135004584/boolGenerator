package com.bool;

import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.bool.Generator.CodeGenerator;
import com.bool.Generator.CommonData;

public class Main {

    public static void main(String[] args) {

        CodeGenerator codeGenerator = new CodeGenerator();
        try {
            codeGenerator.loadAllTemplet("templet/");//加载模型文件
            codeGenerator.loadAllRows("rows/");//加载行文件
            codeGenerator.loadAllOutput("output/");//加载输出配置文件
            System.out.println("生成中...");
            codeGenerator.test();
            System.out.println(CommonData.rowsMaps.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
