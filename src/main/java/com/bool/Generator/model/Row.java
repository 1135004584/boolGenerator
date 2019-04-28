package com.bool.Generator.model;

import java.util.Objects;

import net.sf.json.JSONArray;

//行文件
public class Row{
    private int id;
    private String name;//row名称
    private String namespace;
    private String ref;//引用
    private JSONArray properties;


    public Row() {
    }

    public Row(int id, String namespace, String ref, JSONArray properties) {
        this.id = id;
        this.namespace = namespace;
        this.ref = ref;
        this.properties = properties;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamespace() {
        return this.namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
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

    public Row namespace(String namespace) {
        this.namespace = namespace;
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
        return id == row.id && Objects.equals(namespace, row.namespace) && Objects.equals(ref, row.ref) && Objects.equals(properties, row.properties);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, namespace, ref, properties);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", namespace='" + getNamespace() + "'" +
            ", ref='" + getRef() + "'" +
            ", properties='" + getProperties() + "'" +
            "}";
    }

}