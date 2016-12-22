package com.demo;

/**
 * Created by Administrator on 2016/12/5.
 */
public class Medicine {

    private String id;
    private String name;
    private String function;

    public Medicine() {
        super();
    }

    public Medicine(String id, String name) {
        super();
        this.id = id;
        this.name = name;
    }
    public Medicine(String id, String name, String function) {
        super();
        this.id = id;
        this.name = name;
        this.function = function;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }
}
