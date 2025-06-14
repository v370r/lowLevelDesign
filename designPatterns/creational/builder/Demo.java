public class Demo {
    public static void main(String[] args) {
        House brickHouse = new House.HouseBuilder(4, 1).build();
        System.out.println("Basic House: " + brickHouse.toString());

        House luxuryHouse = new House.HouseBuilder(12, 4)
                .withGarage(true)
                .withSwimmingPool(true)
                .withGarden(true)
                .build();
        System.out.println("Luxury House: " + luxuryHouse);
    }
}
