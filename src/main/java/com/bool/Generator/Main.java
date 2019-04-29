package com.bool.Generator;

import java.io.File;

public class Main {
    public static void main(String[] args) {

        CodeGenerator codeGenerator = new CodeGenerator();
        try {
            codeGenerator.loadAllTemplet("templet/");
            codeGenerator.loadAllRows("rows/");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // try {
        //     codeGenerator.loadTemplet("templet/layui.xml");
        // } catch (Exception e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }
    }
}
