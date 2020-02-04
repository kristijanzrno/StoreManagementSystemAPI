package Data;

import java.util.ArrayList;

public class PurchaseInvoice extends Invoice{
    private ArrayList<CustomerPurchaseItem> items = new ArrayList<>();
    private boolean hasRentedItems;

    public ArrayList<CustomerPurchaseItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<CustomerPurchaseItem> items) {
        this.items = items;
    }

    public boolean getHasRentedItems() {
        return hasRentedItems;
    }

    public void setHasRentedItems(boolean hasRentedItems) {
        this.hasRentedItems = hasRentedItems;
    }
}
