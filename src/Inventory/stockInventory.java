package Inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.ImageIcon;

public class stockInventory {

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
     * @deprecated this variable has been deprecated, was mean for dynamic tables
     * 
     */
    @Deprecated
    public HashMap<String, ImageIcon> carLogo = new HashMap<String, ImageIcon>();

    /*//////////////////////////////////////////////////////////////
                                Buy Car
    //////////////////////////////////////////////////////////////*/
    
    public void buyCar(stockDetails.transactDetails car) { 
        // carLogo.put(car.carDetails.logoName, car.carDetails.carLogo);
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
    
    public void sellCar(String carId, String DateSold, String TimeSold, String Salesman) {
        for (int i = 0; i < carDetails.size(); i++) {
            stockDetails.transactDetails car = carDetails.get(i);

            if (car.carId.equals(carId)) {
                car.carDetails.status = stockDetails.CarStatus.SOLD;
                car.DateSold = DateSold;
                car.TimeSold = TimeSold;
                car.Salesman = Salesman;
                return;
            }

        }
    }

    /*//////////////////////////////////////////////////////////////
                               Search Car
    //////////////////////////////////////////////////////////////*/    

    /**
     * Search Car Details via Car ID
     * 
     * @see stockDetails.transactDetails
     * 
     * @param carId Car ID
     * @return {@link #stockDetails.transactDetails}
     * 
     */
    public stockDetails.transactDetails SearchCarViaID(String carId) {
        
        for (int i = 0; i < carDetails.size(); i++) {

            stockDetails.transactDetails car = carDetails.get(i);

            if (car.carId.equals(carId)) return car;

        }
        
        return null;
    }

    /*//////////////////////////////////////////////////////////////
                  Search Car Booked/Bought by Customer
    //////////////////////////////////////////////////////////////*/    

    /**
     * Search Car Details via Customer Username whom has booked/bought the car
     * 
     * <p>
     * 
     * This method will check the total cars, including its details (in List datatype) a 
     * customer has bought/sold
     * 
     * @see stockDetails.transactDetails
     * 
     * @param Username Customer Username 
     * @return {@link #stockDetails.transactDetails}
     * 
     */
    public List<stockDetails.transactDetails> SearchCarViaUsername(String Username) {
        List<stockDetails.transactDetails> lists = new ArrayList<>();

        for (stockDetails.transactDetails car : carDetails) 
            if (car.carDetails.SellTo != null && car.carDetails.SellTo.equals(Username)) 
                lists.add(car);

        return lists.isEmpty() ? null : lists;
    }

    /*//////////////////////////////////////////////////////////////
                                Deletion
    //////////////////////////////////////////////////////////////*/
    
    /**
     * Call this when customer account is deleted
     * 
     * <p>
     * 
     * if customer has prior booked a car, the car will be available again
     * 
     * <p>
     * 
     * if customer has prior bought a car, the car status will be changed to "username (Deleted)"
     * 
     * @param username Customer Username to be deleted
     * 
     */
    public void UserDeleted(String username, boolean _isUserDeletedIsSalesman) {

        for (stockDetails.transactDetails car : carDetails) 
        {

            if (!_isUserDeletedIsSalesman) 
            {
                if (car.carDetails.SellTo != null && car.carDetails.SellTo.equals(username)) 
                {

                    if (car.carDetails.status.equals(stockDetails.CarStatus.BOOKED)) 
                    {
                        car.carDetails.status = stockDetails.CarStatus.AVAILABLE;
                        car.carDetails.SellTo = null;
                        car.DateBookedAt = null;
                        car.TimeBookedAt = null;
                        car.Salesman = null;
                    }

                    if (car.carDetails.status.equals(stockDetails.CarStatus.SOLD)) 
                    {
                        car.carDetails.SellTo = username.concat(" (Deleted)");
                    }

                }

            } 
            
            if (_isUserDeletedIsSalesman)
            {

                if (car.Salesman != null && car.Salesman.equals(username)) 
                {
                    car.Salesman = username.concat(" (Deleted)"); 
                }

            }
        }

    }
    

}