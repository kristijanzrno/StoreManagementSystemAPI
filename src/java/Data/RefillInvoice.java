package Data;

import java.util.ArrayList;

public class RefillInvoice extends Invoice{
    private String supplierID;
    private String shipmentAddress;
    private ArrayList<PurchaseItem> items;

    public String getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

    public String getShipmentAddress() {
        return shipmentAddress;
    }

    public void setShipmentAddress(String shipmentAddress) {
        this.shipmentAddress = shipmentAddress;
    }
    
    public ArrayList<PurchaseItem> getItems(){
        return this.items;
    }
    
    public void setItems(ArrayList<PurchaseItem> items){
        this.items = items;
    }
    
     public void calculateTotalPrice(ArrayList<StockItem> stockItems){
        float totalPrice = 0;
        for(StockItem item:stockItems){
            int quantity = 1;
            for(PurchaseItem cpitem:items){
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
}
