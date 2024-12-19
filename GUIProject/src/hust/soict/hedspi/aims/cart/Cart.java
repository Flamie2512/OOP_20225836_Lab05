package hust.soict.hedspi.aims.cart;
import java.util.function.Predicate;

import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.media.Media;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;


public class Cart {
    public static final int MAX_NUMBERS_ORDERED = 20;
    private ObservableList<Media> itemsOrdered = FXCollections.observableArrayList();
    private FilteredList<Media> filteredItems = new FilteredList<>(itemsOrdered, p -> true);

    public void addMedia(Media e){
        if(itemsOrdered.size() < MAX_NUMBERS_ORDERED && e != null){
            itemsOrdered.add(e);
            System.out.println("The media has been added");
        } else System.out.println("The cart is almost full");
    }

    public void removeMedia(Media e){
        if(itemsOrdered.remove(e)){
            System.out.println("The media has been removed");
        } else {
        	System.out.println("Remove fail");
        }
    }


    public float totalCost(){
        float total = 0;
        for(Media e:itemsOrdered){
            total += e.getCost();
        }
        return total;
    }
    
    public void setFilter(Predicate<Media> predicate) {
        filteredItems.setPredicate(predicate);
    }

    public FilteredList<Media> getFilteredItems() {
        return filteredItems;
    }

    public void printCart() {
        System.out.println("***********************CART***********************");
        System.out.println("Ordered Items:");
        for (int i = 0; i < itemsOrdered.size(); i++) {
            System.out.println((i + 1) + ". " + itemsOrdered.get(i).toString());
        }
        System.out.println("Total cost: " + totalCost() + " $");
        System.out.println("***************************************************");
    }
    public void searchById(int id) {
        boolean found = false;
        for (Media e:itemsOrdered) {
            if (e.getId() == id) {
                System.out.println("Found DVD: " + e.toString());
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("No DVD found with ID: " + id);
        }
    }

    public void searchByTitle(String keywords) {
        boolean found = false;

        System.out.println("Searching for DVDs with keywords: \"" + keywords + "\"");
        for (Media e:itemsOrdered) {
            if(e instanceof DigitalVideoDisc) {
                DigitalVideoDisc dvd = (DigitalVideoDisc) e;
                if (dvd.isMatch(keywords)) {
                    System.out.println("Found DVD: " + e.toString());
                    found = true;
                }
            }
        }

        if (!found) {
            System.out.println("No DVD found matching the keywords: \"" + keywords + "\"");
        }
    }

    public void cartClear(){
        for(Media m : itemsOrdered){
            itemsOrdered.remove(m);
        }
    }

	public ObservableList<Media> getItemsOrdered() {
		return  itemsOrdered;
	}

	public String placeOrder() {
        if (itemsOrdered.size() == 0) {
            return "Your cart is empty!";
        } else {
            itemsOrdered.clear();
            return "Order created!\n" + "Now your cart will be empty!";
        }
    }


}
