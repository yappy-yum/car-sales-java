package Inventory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.ImageIcon;

import Components.initializer;
import Helper.fileSystem.imageSystem;

public class stockInventory {

    public stockInventory(initializer i) {
        buyCar(
            new stockDetails.transactDetails(
                i.CarIDGenerator.GenerateCarId(),
                new stockDetails.CarDetails(
                    stockDetails.CarStatus.AVAILABLE, 
                    imageSystem._scaleImage(imageSystem.ROLLS_ROYCE, 80, 80), 
                    imageSystem._scaleImage(imageSystem.ROLLS_ROYCE_PHANTOM, 250, 200), 
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
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss a")).toLowerCase(), 
                null, 
                null, 
                null, 
                null
            )
        );

        buyCar(
            new stockDetails.transactDetails(
                i.CarIDGenerator.GenerateCarId(),
                new stockDetails.CarDetails(
                    stockDetails.CarStatus.AVAILABLE, 
                    imageSystem._scaleImage(imageSystem.ROLLS_ROYCE, 80, 80), 
                    imageSystem._scaleImage(imageSystem.ROLLS_ROYCE_LUXE, 250, 200), 
                    "Rolls Royce", 
                    "Rolls Royce Luxe", 
                    "Rolls Royce Company", 
                    null, 
                    "1000 x 980", 
                    400_000.00, 
                    900_000.00, 
                    900
                ), 
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), 
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss a")).toLowerCase(), 
                null, 
                null, 
                null, 
                null
            )
        );

        buyCar(
            new stockDetails.transactDetails(
                i.CarIDGenerator.GenerateCarId(),
                new stockDetails.CarDetails(
                    stockDetails.CarStatus.AVAILABLE, 
                    imageSystem._scaleImage(imageSystem.BMW, 80, 80), 
                    imageSystem._scaleImage(imageSystem.BMW_I9, 250, 200), 
                    "BMW", 
                    "BMW I9", 
                    "BMW Company", 
                    null, 
                    "1000 x 980", 
                    300_000.00, 
                    700_000.00, 
                    900
                ), 
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), 
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss a")).toLowerCase(), 
                null, 
                null, 
                null, 
                null
            )
        );

        buyCar(
            new stockDetails.transactDetails(
                i.CarIDGenerator.GenerateCarId(),
                new stockDetails.CarDetails(
                    stockDetails.CarStatus.AVAILABLE, 
                    imageSystem._scaleImage(imageSystem.MERCEDES, 80, 80), 
                    imageSystem._scaleImage(imageSystem.MERCEDES_Z4, 250, 200), 
                    "Mercedes", 
                    "Mercedes Z4", 
                    "BMW Company", 
                    null, 
                    "1000 x 980", 
                    400_000.00, 
                    600_000.00, 
                    2900
                ), 
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), 
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss a")).toLowerCase(), 
                null, 
                null, 
                null, 
                null
            )
        );        

        buyCar(
            new stockDetails.transactDetails(
                i.CarIDGenerator.GenerateCarId(),
                new stockDetails.CarDetails(
                    stockDetails.CarStatus.AVAILABLE, 
                    imageSystem._scaleImage(imageSystem.AUDI, 80, 80), 
                    imageSystem._scaleImage(imageSystem.AUDI_TT, 250, 200), 
                    "Audi", 
                    "Audi TT", 
                    "Audi Company", 
                    null, 
                    "1000 x 980", 
                    400_000.00, 
                    600_000.00, 
                    2900
                ), 
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), 
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss a")).toLowerCase(), 
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
    
    public void bookCar(String carID, String user, String DateBookAt, String TimeBookAt) {
        for (int i = 0; i < carDetails.size(); i++) {
            stockDetails.transactDetails car = carDetails.get(i);

            if (car.carId.equals(carID)) {
                car.carDetails.status = stockDetails.CarStatus.BOOKED;
                car.carDetails.SellTo = user;
                car.DateBookedAt = DateBookAt;
                car.TimeBookedAt = TimeBookAt;
                return;
            }

        }
    }
    
    /*//////////////////////////////////////////////////////////////
                                 Cancer
    //////////////////////////////////////////////////////////////*/
    
    public void cancerBooking(String carID) {
        for (int i = 0; i < carDetails.size(); i++) {
            stockDetails.transactDetails car = carDetails.get(i);

            if (car.carId.equals(carID)) {
                car.carDetails.status = stockDetails.CarStatus.AVAILABLE;
                car.carDetails.SellTo = null;
                car.DateBookedAt = null;
                car.TimeBookedAt = null;
                return;
            }

        }
    }

    /*//////////////////////////////////////////////////////////////
                                  Sold
    //////////////////////////////////////////////////////////////*/
    
    public void sellCar(String carId, String DateSold, String TimeSold) {
        for (int i = 0; i < carDetails.size(); i++) {
            stockDetails.transactDetails car = carDetails.get(i);

            if (car.carId.equals(carId)) {
                car.carDetails.status = stockDetails.CarStatus.SOLD;
                car.DateSold = DateSold;
                car.TimeSold = TimeSold;
                return;
            }

        }
    }

    public stockDetails.transactDetails SearchCarViaID(String carId) {
        
        for (int i = 0; i < carDetails.size(); i++) {

            stockDetails.transactDetails car = carDetails.get(i);

            if (car.carId.equals(carId)) return car;

        }
        
        return null;
    }

    public List<stockDetails.transactDetails> SearchCarViaUsername(String Username) {
        List<stockDetails.transactDetails> lists = new ArrayList<>();

        for (stockDetails.transactDetails car : carDetails) if (car.carDetails.SellTo != null && car.carDetails.SellTo.equals(Username)) lists.add(car);

        return lists.isEmpty() ? null : lists;
    }

    public void UserDeleted(String username) {

        for (stockDetails.transactDetails car : carDetails) {
            if (car.carDetails.SellTo != null && car.carDetails.SellTo.equals(username)) {

                if (car.carDetails.status.equals(stockDetails.CarStatus.BOOKED)) {
                    car.carDetails.status = stockDetails.CarStatus.AVAILABLE;
                    car.carDetails.SellTo = null;
                    car.DateBookedAt = null;
                    car.TimeBookedAt = null;
                }

                if (car.carDetails.status.equals(stockDetails.CarStatus.SOLD)) {
                    car.carDetails.SellTo = username.concat(" (Deleted)");
                }

            }
        }

    }
    

}