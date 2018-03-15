package io.khasang.sokol.model;

public class MyPanelScore {

    long countNew;
    long countInProgress;
    long countClosed;

    public MyPanelScore() {
    }

    public MyPanelScore(long countNew, long countInProgress, long countClosed) {
        this.countNew = 0L;
        this.countInProgress = 0L;
        this.countClosed = 0L;
    }

    public long getCountNew() {
        return countNew;
    }

    public void setCountNew(long countNew) {
        this.countNew = countNew;
    }

    public long getCountInProgress() {
        return countInProgress;
    }

    public void setCountInProgress(long countInProgress) {
        this.countInProgress = countInProgress;
    }

    public long getCountClosed() {
        return countClosed;
    }

    public void setCountClosed(long countClosed) {
        this.countClosed = countClosed;
    }
}
