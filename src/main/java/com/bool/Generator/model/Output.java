package com.bool.Generator.model;

import java.util.List;
import java.util.Objects;

public class Output {
    private List<String> rowsRef;
    private String outputPath;


    public Output() {
    }

    public Output(List<String> rowsRef, String outputPath) {
        this.rowsRef = rowsRef;
        this.outputPath = outputPath;
    }

    public List<String> getRowsRef() {
        return this.rowsRef;
    }

    public void setRowsRef(List<String> rowsRef) {
        this.rowsRef = rowsRef;
    }

    public String getOutputPath() {
        return this.outputPath;
    }

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }

    public Output rowsRef(List<String> rowsRef) {
        this.rowsRef = rowsRef;
        return this;
    }

    public Output outputPath(String outputPath) {
        this.outputPath = outputPath;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Output)) {
            return false;
        }
        Output output = (Output) o;
        return Objects.equals(rowsRef, output.rowsRef) && Objects.equals(outputPath, output.outputPath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rowsRef, outputPath);
    }

    @Override
    public String toString() {
        return "{" +
            " rowsRef='" + getRowsRef() + "'" +
            ", outputPath='" + getOutputPath() + "'" +
            "}";
    }

}