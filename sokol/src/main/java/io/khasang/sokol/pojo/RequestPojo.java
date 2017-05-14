package io.khasang.sokol.pojo;

public class RequestPojo {
    String title;
    String description;
    String requestTypeid;
    String departmentid;

    public RequestPojo() {
    }

    public RequestPojo(String title, String description, String requestTypeid, String departmentid) {
        this.title = title;
        this.description = description;
        this.requestTypeid = requestTypeid;
        this.departmentid = departmentid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRequestTypeid() {
        return requestTypeid;
    }

    public void setRequestTypeid(String requestTypeid) {
        this.requestTypeid = requestTypeid;
    }

    public String getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(String departmentid) {
        this.departmentid = departmentid;
    }
}
