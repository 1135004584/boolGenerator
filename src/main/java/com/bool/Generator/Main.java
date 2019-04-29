package com.bool.Generator;

import java.io.File;

public class Main {
    public static void main(String[] args) {

        CodeGenerator codeGenerator = new CodeGenerator();
        try {
            codeGenerator.loadAllTemplet("templet/");//加载模型文件
            codeGenerator.loadAllRows("rows/");//加载行文件
            codeGenerator.loadAllOutput("output/");//加载输出配置文件
            System.out.println("\n编译中...");

            //开始编译


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
