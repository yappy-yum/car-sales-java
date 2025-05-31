package Inventory;

import javax.swing.ImageIcon;

/**
 * 
 * A little static sub-class to provide nested memory 
 * to store all the respective data 
 * 
 */
public class stockDetails {

    public enum CarStatus {
        AVAILABLE,
        BOOKED,
        SOLD
    }

    /*//////////////////////////////////////////////////////////////
                              Car Details
    //////////////////////////////////////////////////////////////*/    
 
    public static class CarDetails {

        public CarStatus status;

        public ImageIcon carLogo, carImage;
        public String logoName, carName;
        public String BoughtFrom, SellTo; // Akaun Belum Bayar & Akaun Belum Terima
        public double buyingPrice, sellingPrice;
        public String carLength;

        public int carHorsePower;

        public CarDetails(
            CarStatus status, 
            ImageIcon carLogo, 
            ImageIcon carImage, 
            String logoName, 
            String carName, 
            String BoughtFrom,
            String SellTo,
            String carLength, 
            double buyingPrice, 
            double sellingPrice, 
            int carHorsePower
        ) {
            this.status = status;
            this.carLogo = carLogo;
            this.carImage = carImage;
            this.logoName = logoName;
            this.BoughtFrom = BoughtFrom;
            this.SellTo = SellTo;
            this.carName = carName;
            this.carLength = carLength;
            this.buyingPrice = buyingPrice;
            this.sellingPrice = sellingPrice;
            this.carHorsePower = carHorsePower;
        }

    }

    /*//////////////////////////////////////////////////////////////
                                Buy/Sell
    //////////////////////////////////////////////////////////////*/    

    public static class transactDetails {

        public String carId;
        public CarDetails carDetails;
        public String DateBought, TimeBought;
        public String DateBookedAt, TimeBookedAt;
        public String DateSold, TimeSold;

        public transactDetails(
            String carId,
            CarDetails carDetails, 
            String DateBought, 
            String TimeBought, 
            String DateBookedAt,
            String TimeBookedAt,
            String DateSold, 
            String TimeSold
        ) {
            this.carId = carId;
            this.carDetails = carDetails;
            this.DateBought = DateBought;
            this.TimeBought = TimeBought;
            this.DateBookedAt = DateBookedAt;
            this.TimeBookedAt = TimeBookedAt;
            this.DateSold = DateSold;
            this.TimeSold = TimeSold;
            
        }

    }

}