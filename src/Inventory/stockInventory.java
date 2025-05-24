package Inventory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Helper.fileSystem.imageSystem;

public class stockInventory {

    public stockInventory() {
        buyCar(
            new stockDetails.transactDetails(
                new stockDetails.CarDetails(
                    stockDetails.CarStatus.AVAILABLE, 
                    imageSystem._scaleImage(imageSystem.ROLLS_ROYCE, 80, 60), 
                    imageSystem._scaleImage(imageSystem.ROLLS_ROYCE_PHANTOM, 80, 80), 
                    "Rolls Royce", 
                    "Rolls Royce Phantom", 
                    "Rolls Royce Company", 
                    null, 
                    "1000 x 980", 
                    400_000.00, 
                    800_000.00, 
                    900
                ), 
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), 
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")), 
                null, 
                null, 
                null, 
                null
            )
        );

        buyCar(
            new stockDetails.transactDetails(
                new stockDetails.CarDetails(
                    stockDetails.CarStatus.AVAILABLE, 
                    imageSystem._scaleImage(imageSystem.ROLLS_ROYCE, 80, 60), 
                    imageSystem._scaleImage(imageSystem.ROLLS_ROYCE_PHANTOM, 80, 80), 
                    "Rolls Royce", 
                    "Rolls Royce Phantom", 
                    "Rolls Royce Company", 
                    null, 
                    "1000 x 980", 
                    400_000.00, 
                    900_000.00, 
                    900
                ), 
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), 
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")), 
                null, 
                null, 
                null, 
                null
            )
        );

        buyCar(
            new stockDetails.transactDetails(
                new stockDetails.CarDetails(
                    stockDetails.CarStatus.AVAILABLE, 
                    imageSystem._scaleImage(imageSystem.BENTLEY, 80, 60), 
                    imageSystem._scaleImage(imageSystem.BENTLEY, 80, 80), 
                    "Bentley", 
                    "Bently XYZ", 
                    "Bentley Company", 
                    null, 
                    "1000 x 980", 
                    400_000.00, 
                    600_000.00, 
                    900
                ), 
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), 
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")), 
                null, 
                null, 
                null, 
                null
            )
        );
    }

    /*//////////////////////////////////////////////////////////////
                          Car Showroom Details
    //////////////////////////////////////////////////////////////*/    

    /**
     * store all the details of the car 
     * 
     * <p>
     * 
     * List<> does not override duplicated data
     * 
     * @see stockDetails.transactDetails
     * 
     */
    public List<stockDetails.transactDetails> carDetails = new ArrayList<>();

    /**
     * store all the available car logos
     * 
     * <p>
     * 
     * HashMap<> does override duplicated data
     * 
     */
    public HashMap<String, ImageIcon> carLogo = new HashMap<String, ImageIcon>();

    /*//////////////////////////////////////////////////////////////
                                Buy Car
    //////////////////////////////////////////////////////////////*/
    
    public void buyCar(stockDetails.transactDetails car) { 
        carLogo.put(car.carDetails.logoName, car.carDetails.carLogo);
        carDetails.add(car); 
    }

    /*//////////////////////////////////////////////////////////////
                                  Book
    //////////////////////////////////////////////////////////////*/
    
    public void bookCar(JLabel carName) {
        for (int i = 0; i < carDetails.size(); i++) {
            stockDetails.transactDetails car = carDetails.get(i);

            if (car.carDetails.carName.equals(carName.getText())) {
                car.carDetails.status = stockDetails.CarStatus.BOOKED;
                car.DateBookedAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                car.TimeBookedAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                return;
            }

        }
    }
    
    /*//////////////////////////////////////////////////////////////
                                 Cancer
    //////////////////////////////////////////////////////////////*/
    
    public void cancerBooking(JLabel carName) {
        for (int i = 0; i < carDetails.size(); i++) {
            stockDetails.transactDetails car = carDetails.get(i);

            if (car.carDetails.carName.equals(carName.getText())) {
                car.carDetails.status = stockDetails.CarStatus.AVAILABLE;
                car.DateBookedAt = null;
                car.TimeBookedAt = null;
                return;
            }

        }
    }

    /*//////////////////////////////////////////////////////////////
                                  Sold
    //////////////////////////////////////////////////////////////*/
    
    public void sellCar(JLabel carName) {
        for (int i = 0; i < carDetails.size(); i++) {
            stockDetails.transactDetails car = carDetails.get(i);

            if (car.carDetails.carName.equals(carName.getText())) {
                car.carDetails.status = stockDetails.CarStatus.SOLD;
                car.DateSold = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                car.TimeSold = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                return;
            }

        }
    }
    

}