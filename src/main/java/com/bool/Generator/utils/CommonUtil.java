package com.bool.Generator.utils;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

/**
 * 通用工具类
 */
public class CommonUtil{


    //获取目录下所有的文件
    public static List<String> getAllFilePathsByPath(String directory)
    {
        List<String> pathList = new ArrayList<>();
        getPath(directory,pathList);

        return pathList;
    }

    //递归搜索并返回所有xml文件路径
    private static void getPath(String path,List<String> pathList)
    {
        File file = new File(path);
        File[] files = file.listFiles();//获取所有

        for(File f:files)
        {
            if(f.isDirectory())//如果是目录进行递归搜索
            {
                getPath(f.getAbsolutePath(),pathList);
            }else{   
                pathList.add(f.getAbsolutePath());
            }
        }
    }
}