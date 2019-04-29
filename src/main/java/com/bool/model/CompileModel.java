package com.bool.model;

import java.util.Objects;

/**
 * 编译模型
 */
public class CompileModel{
    private String fileName;
    private String content;
    private String outputPath;


    public CompileModel() {
    }

    public CompileModel(String fileName, String content, String outputPath) {
        this.fileName = fileName;
        this.content = content;
        this.outputPath = outputPath;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOutputPath() {
        return this.outputPath;
    }

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }

    public CompileModel fileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public CompileModel content(String content) {
        this.content = content;
        return this;
    }

    public CompileModel outputPath(String outputPath) {
        this.outputPath = outputPath;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CompileModel)) {
            return false;
        }
        CompileModel compileModel = (CompileModel) o;
        return Objects.equals(fileName, compileModel.fileName) && Objects.equals(content, compileModel.content) && Objects.equals(outputPath, compileModel.outputPath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileName, content, outputPath);
    }

    @Override
    public String toString() {
        return "{" +
            " fileName='" + getFileName() + "'" +
            ", content='" + getContent() + "'" +
            ", outputPath='" + getOutputPath() + "'" +
            "}";
    }

}