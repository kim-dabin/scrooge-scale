package com.kdb.scroogescale.vo;

public class Question {
    private int id;
    private String factorId;
    private String contents;

    public Question() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFactorId() {
        return factorId;
    }

    public void setFactorId(String factorId) {
        this.factorId = factorId;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}
