package com.mainapp.furvent.mainapp.data;

public class EleveBean {
    private String name, firstName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EleveBean(String name, String firstName) {
        this.name = name;
        this.firstName = firstName;
    }
}
