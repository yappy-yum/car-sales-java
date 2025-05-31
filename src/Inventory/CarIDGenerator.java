package Inventory;

public class CarIDGenerator {
    
    private String PRECISION = "C - ";
    private int CarId = 0;

    public String GenerateCarId() {
        return PRECISION.concat(String.valueOf(CarId ++));
    }

}
