package Comment;

import Inventory.stockDetails;

@FunctionalInterface
public interface UpdateCallback {
    
    void CarUpdates(stockDetails.transactDetails newCar);

}
