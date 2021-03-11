package com.kdb.scroogescale.vo;

import java.io.Serializable;

public class Factor implements Serializable {
    private String id;
    private String name;

    public Factor() {
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
