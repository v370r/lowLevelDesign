public enum VehicleType {
    HANDICAPPED("handicapped"), COMPACT("compact"), LARGE("large"), MOTORCYCLES("motorcycles");

    private final String type;

    VehicleType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}