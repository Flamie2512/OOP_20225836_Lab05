package hust.soict.hedspi.aims.store;

import hust.soict.hedspi.aims.media.Media;

import java.util.ArrayList;
import java.util.Objects;

public class Store {
    private ArrayList<Media> itemsInStore = new ArrayList<Media>();

    public void addMedia(Media e) {
        if (e != null) {
            itemsInStore.add(e);
            System.out.println("Media added to the store");
        } else {
            System.out.println("Cannot add a null Media to the store.");
        }
    }

    public void removeMedia(int id) {
        for (Media m : itemsInStore) {
            if(m.getId() == id) {
                itemsInStore.remove(m);
                System.out.println("Media with ID " + id + "removed from the store");
                return;
            }
        }
        System.out.println("No Media found with ID: " + id);

    }

    public void printStore() {
        System.out.println("********************STORE********************");
        if (itemsInStore.isEmpty()) {
            System.out.println("The store is empty.");
        } else {
            System.out.println("Available DVDs:");
            for (Media dvd : itemsInStore) {
                System.out.println(dvd.toString());
            }
        }
        System.out.println("*********************************************");
    }

    public Media getMediaByTitle(String title){
        for(Media m : itemsInStore){
            if(Objects.equals(m.getTitle(), title)){
                return m;
            }
        }
        return null;
    }

	public ArrayList<Media>  getItemsInStore() {
		return itemsInStore;
	}
}
