package za.co.tangentsolutions.myemployeemanager.models;

import org.json.JSONException;
import org.json.JSONObject;

public class PositionModel {
    private int id;
    private String name;
    private String level;
    private int sort;

    public void setModel(JSONObject userJson) throws JSONException {
        setId(userJson.getInt("id"));
        setName(userJson.getString("name"));
        setLevel(userJson.getString("level"));
        setSort(userJson.getInt("sort"));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}