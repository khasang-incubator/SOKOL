package io.khasang.sokol.model;

import java.util.Date;

public class RequestGraphData {
    private int requestCount;
    private Date  requestDate;

    public int getRequestCount() {
        return requestCount;
    }

    public void setRequestCount(int requestCount) {
        this.requestCount = requestCount;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public RequestGraphData(Date requestDate, int requestCount)  {
        this.requestCount = requestCount;
        this.requestDate = requestDate;
    }
}
