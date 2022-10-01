public enum Constants {
    INITIAL_CAPACITY(4),
    GROWTH_RATE(4),
    NOT_FOUND(-1);

    private final int constant;

    Constants(int constant) {
        this.constant = constant;
    }

    public int getConstant() {
        return constant;
    }

}
