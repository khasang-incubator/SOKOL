package io.khasang.sokol.pojo;

public class RequestPojo {
    String title;
    String description;
    String idrequesttype;
    String iddepartment;
    String pagenumber;
    String sortBy;
    String sortOrder;
    String sortOrderHeader;

    public RequestPojo() {
    }

    public RequestPojo(String title, String description, String idrequesttype, String iddepartment, String pagenumber, String sortBy, String sortOrder, String sortOrderHeader) {
        this.title = title;
        this.description = description;
        this.idrequesttype = idrequesttype;
        this.iddepartment = iddepartment;
        this.pagenumber = pagenumber;
        this.sortBy = sortBy;
        this.sortOrder = sortOrder;
        this.sortOrderHeader = sortOrderHeader;
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

    public String getPagenumber() {
        return pagenumber;
    }

    public void setPagenumber(String pagenumber) {
        this.pagenumber = pagenumber;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getSortOrderHeader() {
        return sortOrderHeader;
    }

    public void setSortOrderHeader(String sortOrderHeader) {
        this.sortOrderHeader = sortOrderHeader;
    }
}
