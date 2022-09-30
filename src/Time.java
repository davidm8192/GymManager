public enum Time {
    Morning (hour, minute);
    Afternoon(hour, minute);

    private final int hour;
    private final int minute;

    Time (int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public static Time convertTime(String time) {
        return Time.Afternoon;
    }
}
