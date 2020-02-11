package Data;

import java.util.ArrayList;

public class RefillInvoice extends Invoice{
    private String supplierID;
    private String shipmentAddres;
    private ArrayList<PurchaseItem> items = new ArrayList<PurchaseItem>();

    public String getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

    public String getShipmentAddres() {
        return shipmentAddres;
    }

    public void setShipmentAddres(String shipmentAddress) {
        this.shipmentAddres = shipmentAddress;
    }
    
    public ArrayList<PurchaseItem> getItems(){
        return this.items;
    }
    
    public void setItems(ArrayList<PurchaseItem> items){
        this.items = items;
    }
    
    public void addItem(PurchaseItem item){
        for(int i = 0; i < items.size(); i++){
            if(items.get(i).getItemID().equals(item.getItemID())){
                items.get(i).setQuantity(items.get(i).getQuantity() + item.getQuantity());
                return;
            }
        }
        this.items.add(item);
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
