package com.bool.Generator;

import java.io.File;

public class Main {
    public static void main(String[] args) {

        CodeGenerator codeGenerator = new CodeGenerator();
        try {
            codeGenerator.loadAllTemplet("templet/");
            codeGenerator.loadAllRows("rows/");
            codeGenerator.loadAllOutput("output/");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
