package org.myspring;

/**
 * @author Leo Liu
 * @create 19/11/2019
 */
public class City {

    private String name;

    private String description;

    public City() {
        System.out.println("city service");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
