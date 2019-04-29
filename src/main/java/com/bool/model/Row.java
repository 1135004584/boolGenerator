package com.bool.model;

import java.util.List;
import java.util.Objects;

import net.sf.json.JSONArray;

//行文件
public class Row{
    private String name;
    private JSONArray properties;
    private List<Row> childRow;


    public Row() {
    }


    public Row(String name, JSONArray properties, List<Row> childRow) {
        this.name = name;
        this.properties = properties;
        this.childRow = childRow;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JSONArray getProperties() {
        return this.properties;
    }

    public void setProperties(JSONArray properties) {
        this.properties = properties;
    }

    public List<Row> getChildRow() {
        return this.childRow;
    }

    public void setChildRow(List<Row> childRow) {
        this.childRow = childRow;
    }

    public Row name(String name) {
        this.name = name;
        return this;
    }

    public Row properties(JSONArray properties) {
        this.properties = properties;
        return this;
    }

    public Row childRow(List<Row> childRow) {
        this.childRow = childRow;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Row)) {
            return false;
        }
        Row row = (Row) o;
        return Objects.equals(name, row.name) && Objects.equals(properties, row.properties) && Objects.equals(childRow, row.childRow);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, properties, childRow);
    }

    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", properties='" + getProperties() + "'" +
            ", childRow='" + getChildRow() + "'" +
            "}";
    }

}