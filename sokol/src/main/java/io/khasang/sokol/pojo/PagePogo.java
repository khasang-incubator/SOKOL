package io.khasang.sokol.pojo;

/**
 * Created by AnTur on 09.05.2017.
 */
public class PagePogo {
    String pageNumber;
    String sortBy;
    String sortOrder;
    String sortOrderHeader;

    public PagePogo(String pageNumber, String sortBy, String sortOrder, String sortOrderHeader) {
        this.pageNumber = pageNumber;
        this.sortBy = sortBy;
        this.sortOrder = sortOrder;
        this.sortOrderHeader = sortOrderHeader;
    }

    public PagePogo() {
    }

    public String getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
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
