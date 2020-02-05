package Data;

import java.util.ArrayList;

public class PurchaseInvoice extends Invoice{
    private ArrayList<CustomerPurchaseItem> items = new ArrayList<>();
    private boolean hasRentedItems;

    public ArrayList<CustomerPurchaseItem> getItems() {
        return items;
    }
    public void calculateTotalPrice(ArrayList<StockItem> stockItems){
        float totalPrice = 0;
        for(StockItem item:stockItems){
            int quantity = 1;
            for(CustomerPurchaseItem cpitem:items){
                if((item.getItemID()+"").equals(cpitem.getItemID())){
                    quantity = cpitem.getQuantity();
                }
            }
            float itemPrice = item.getCost();
            float itemVAT = item.getVAT();
            totalPrice = totalPrice + ((itemPrice + itemPrice*(itemVAT/100))*quantity);       
            this.totalPrice = totalPrice;
        }
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
