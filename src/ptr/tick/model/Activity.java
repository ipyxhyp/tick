package ptr.tick.model;

import java.util.Objects;

/**
 * Summary of server activity data
 * is server down - isServerOff
 * minutes - of server activity / inactivity
 *
 * */
public class Activity {

    private boolean isServerOff;
    private long minutes;

    public Activity(boolean isServerOff, long minutes) {
        this.isServerOff = isServerOff;
        this.minutes = minutes;
    }

    public Activity() {
    }

    public long getMinutes() {
        return minutes;
    }

    public void setMinutes(long minutes) {
        this.minutes = minutes;
    }

    public boolean isServerOff() {
        return isServerOff;
    }

    public void setServerOff(boolean serverOff) {
        isServerOff = serverOff;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Activity that = (Activity) o;
        return isServerOff == that.isServerOff &&
                minutes == that.minutes;
    }

    @Override
    public int hashCode() {

        return Objects.hash(isServerOff, minutes);
    }

    @Override
    public String toString() {
        return "Activity{" +
                "isServerOff=" + isServerOff +
                ", minutes=" + minutes +
                '}';
    }
}
