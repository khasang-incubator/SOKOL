package io.khasang.sokol.pojo;

public class RequestPojo {
    String title;
    String description;
    String idrequesttype;
    String iddepartment;

    public RequestPojo() {
    }

    public RequestPojo(String title, String description, String idrequesttype, String iddepartment) {
        this.title = title;
        this.description = description;
        this.idrequesttype = idrequesttype;
        this.iddepartment = iddepartment;
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

    public String getIdrequesttype() {
        return idrequesttype;
    }

    public void setIdrequesttype(String idrequesttype) {
        this.idrequesttype = idrequesttype;
    }

    public String getIddepartment() {
        return iddepartment;
    }

    public void setIddepartment(String iddepartment) {
        this.iddepartment = iddepartment;
    }
}
