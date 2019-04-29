package com.bool.model;

import java.util.Map;
import java.util.Objects;

import net.sf.json.JSONArray;

/**
 * 模型文件
 */
public class Model
{
    private String modelName;
    private JSONArray properties;//the properties of mdoel
    private String source;//souce for replace

    public Model() {
    }


    public Model(String modelName, JSONArray properties, String source) {
        this.modelName = modelName;
        this.properties = properties;
        this.source = source;
    }

    public String getModelName() {
        return this.modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public JSONArray getProperties() {
        return this.properties;
    }

    public void setProperties(JSONArray properties) {
        this.properties = properties;
    }

    public String getSource() {
        return this.source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Model modelName(String modelName) {
        this.modelName = modelName;
        return this;
    }

    public Model properties(JSONArray properties) {
        this.properties = properties;
        return this;
    }

    public Model source(String source) {
        this.source = source;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Model)) {
            return false;
        }
        Model model = (Model) o;
        return Objects.equals(modelName, model.modelName) && Objects.equals(properties, model.properties) && Objects.equals(source, model.source);
    }

    @Override
    public int hashCode() {
        return Objects.hash(modelName, properties, source);
    }

    @Override
    public String toString() {
        return "{" +
            " modelName='" + getModelName() + "'" +
            ", properties='" + getProperties() + "'" +
            ", source='" + getSource() + "'" +
            "}";
    }

}