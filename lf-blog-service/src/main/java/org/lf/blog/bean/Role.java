package org.lf.blog.bean;

/**
 * Created by LF on 2021/02/20.
 */
public class Role {
    private Long id;
    private String name;

    public Role() {
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role(Long id, String name) {

        this.id = id;
        this.name = name;
    }
}
