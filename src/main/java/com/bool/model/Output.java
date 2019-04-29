package com.bool.model;

import java.util.List;
import java.util.Objects;

public class Output {
    private List<String> rowsRef;
    private String outputPath;
    private String suffix;


    public Output() {
    }

    public Output(List<String> rowsRef, String outputPath, String suffix) {
        this.rowsRef = rowsRef;
        this.outputPath = outputPath;
        this.suffix = suffix;
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

    public String getSuffix() {
        return this.suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public Output rowsRef(List<String> rowsRef) {
        this.rowsRef = rowsRef;
        return this;
    }

    public Output outputPath(String outputPath) {
        this.outputPath = outputPath;
        return this;
    }

    public Output suffix(String suffix) {
        this.suffix = suffix;
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
        return Objects.equals(rowsRef, output.rowsRef) && Objects.equals(outputPath, output.outputPath) && Objects.equals(suffix, output.suffix);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rowsRef, outputPath, suffix);
    }

    @Override
    public String toString() {
        return "{" +
            " rowsRef='" + getRowsRef() + "'" +
            ", outputPath='" + getOutputPath() + "'" +
            ", suffix='" + getSuffix() + "'" +
            "}";
    }

}