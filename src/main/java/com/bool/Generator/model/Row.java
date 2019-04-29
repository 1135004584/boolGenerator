package com.bool.Generator.model;

import java.util.List;
import java.util.Objects;

import net.sf.json.JSONArray;

//行文件
public class Row{
    private int id;
    private String name;
    private JSONArray properties;
    private List<Row> childRow;


    public Row() {
    }

    public Row(int id, String name, JSONArray properties, List<Row> childRow) {
        this.id = id;
        this.name = name;
        this.properties = properties;
        this.childRow = childRow;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Row id(int id) {
        this.id = id;
        return this;
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
        return id == row.id && Objects.equals(name, row.name) && Objects.equals(properties, row.properties) && Objects.equals(childRow, row.childRow);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, properties, childRow);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", properties='" + getProperties() + "'" +
            ", childRow='" + getChildRow() + "'" +
            "}";
    }

}