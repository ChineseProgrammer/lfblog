package org.lf.blog.bean;
/**
 * Created by LF on 2021/02/20.
 */
public class Work {
    private Long id;
    private String title;
    private String brief;
    private Long uid;
    private Long workCode;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrief() { return brief; }

    public void setBrief(String brief) { this.brief = brief; }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getWorkCode() {
        return workCode;
    }

    public void setWorkCode(Long workCode) {
        this.workCode = workCode;
    }
}
