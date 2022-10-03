public enum Location {
    BRIDGEWATER("08807", "SOMERSET"),
    EDISON("08837", "MIDDLESEX"),
    FRANKLIN("08873", "SOMERSET"),
    PISCATAWAY("08854", "MIDDLESEX"),
    SOMERVILLE("08876", "SOMERSET");

    private final String zipCode;
    private final String county;


    Location(String zipCode, String county) {
        this.zipCode = zipCode;
        this.county = county;
    }

    public String toString() {
        return this.name() + ", " + zipCode + ", " + county;
    }

    public String getZipCode() {
        return this.zipCode;
    }

    public String getCounty() {
        return this.county;
    }

}