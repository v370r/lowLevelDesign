public final class House { // prevents extention - immutability after creation
    private final int windows;
    private final int doors;
    private final boolean hasGarage;
    private final boolean hasSwimmingPool;
    private final boolean hasGarden;

    // constructor needs to be private
    private House(HouseBuilder builder) {
        this.windows = builder.windows;
        this.doors = builder.doors;
        this.hasGarage = builder.hasGarage;
        this.hasSwimmingPool = builder.hasSwimmingPool;
        this.hasGarden = builder.hasGarden;
    }

    public String toString() {
        return "House [windows=" + windows + ", doors=" + doors + ", hasGarage=" + hasGarage
                + ", hasSwimmingPool=" + hasSwimmingPool + ", hasGarden=" + hasGarden + "]";
    }

    public static class HouseBuilder {
        private final int windows;
        private final int doors;
        private boolean hasGarage = false;
        private boolean hasSwimmingPool = false;
        private boolean hasGarden = false;

        public HouseBuilder(int windows, int doors) {
            this.windows = windows;
            this.doors = doors;
        }

        public HouseBuilder withGarage(boolean hasGarage) {
            this.hasGarage = hasGarage;
            return this;
        }

        public HouseBuilder withSwimmingPool(boolean hasSwimmingPool) {
            this.hasSwimmingPool = hasSwimmingPool;
            return this;
        }

        public HouseBuilder withGarden(boolean hasGarden) {
            this.hasGarden = hasGarden;
            return this;
        }

        public House build() {
            return new House(this);
        }

    }

}