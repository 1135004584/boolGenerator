package com.bool.Generator.interfaces;

public interface ICodeGenerator{
    /**
     * 会对path目录下的所有.xml进行装载,可以递归搜索
     * 
     * @param path
     * @throws Exception
     */
    public void loadAllTemplet(String path) throws Exception;

    /**
     * 加载所有的行文件
     * @param path
     * @throws Exception
     */
    public void loadAllRows(String path) throws Exception;

    /**
     * 加载所有的输出文件
     * @param path
     * @throws Exception
     */
    public void loadAllOutput(String path) throws Exception;

}