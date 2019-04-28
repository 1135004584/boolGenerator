package com.bool.Generator.model;

import java.util.Objects;

import net.sf.json.JSONArray;

//行文件
public class Row{
    private int id;
    private String ref;//引用
    private JSONArray properties;


    public Row() {
    }

    public Row(int id, String ref, JSONArray properties) {
        this.id = id;
        this.ref = ref;
        this.properties = properties;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRef() {
        return this.ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public JSONArray getProperties() {
        return this.properties;
    }

    public void setProperties(JSONArray properties) {
        this.properties = properties;
    }

    public Row id(int id) {
        this.id = id;
        return this;
    }

    public Row ref(String ref) {
        this.ref = ref;
        return this;
    }

    public Row properties(JSONArray properties) {
        this.properties = properties;
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
        return id == row.id && Objects.equals(ref, row.ref) && Objects.equals(properties, row.properties);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ref, properties);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", ref='" + getRef() + "'" +
            ", properties='" + getProperties() + "'" +
            "}";
    }


}