import com.peak2peakmedia.model.GroceryItem;

/**
 * Created by colinhill on 2/1/16.
 */
public class GroceryList {
    private GroceryItem[] itemOrders = new GroceryItem[10];

    public GroceryList(){}

    public GroceryList(GroceryItem[] itemOrders){
        this.itemOrders = itemOrders;
    }

public void addItem(GroceryItem item){
    int flag = 0;

    for (int i = 0; i < itemOrders.length; i++){
        if (flag ==0) {
            if (itemOrders[i] == null) {
                itemOrders[i] = item;
                flag++;
            }
            if (itemOrders[9] != null) {
                System.out.println("The list is full - Please create a new list.");
                flag++;
            }
        }
    }
}

    public double getTotalCost(){
        double totalCost = 0;

        for (GroceryItem item: itemOrders) {
            totalCost += item.getUnitTotalPrice();
        }
        return totalCost;
    }

    @Override
    public String toString() {

        for (GroceryItem item : itemOrders) {
            System.out.println(item);
        }
        System.out.printf("\n\nTotal Cost: \t\t\t\t\t $%.2f\n", getTotalCost());
        return "";
    }
}
