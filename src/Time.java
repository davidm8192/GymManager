public enum Time {
    MORNING(9, 30),
    AFTERNOON(14, 0);

    private final int hour;
    private final int minute;

    Time (int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public String toString() {
        return hour + ": " + minute;
    }
}
