package ptr.tick.model;

import java.time.LocalTime;
import java.util.Objects;

/**
 * Model for parsed file entry records
 *
 * */
public class DataRecord {

    private int code;
    private LocalTime startTime;
    private boolean isDown;

    public DataRecord() {
    }

    public DataRecord(int code, LocalTime startTime, boolean isDown) {
        this.code = code;
        this.startTime = startTime;
        this.isDown = isDown;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public boolean isDown() {
        return isDown;
    }

    public void setDown(boolean down) {
        isDown = down;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DataRecord that = (DataRecord) o;
        return code == that.code &&
                isDown == that.isDown &&
                Objects.equals(startTime, that.startTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(code, startTime, isDown);
    }

    @Override
    public String toString() {
        return "DataRecord{" +
                "code=" + code +
                ", startTime=" + startTime +
                ", isDown=" + isDown +
                '}';
    }
}
