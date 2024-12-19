package hust.soict.hedspi.aims.media;

import java.util.ArrayList;
import java.util.List;

public class Book extends Media{
    private List<String> authors = new ArrayList<String>();

    public Book(){}

    public Book(int id, String title, String category, float cost) {
        super(id, title, category, cost);
    }
    
    
    public Book(String title, String category, float cost) {
    	super(title, category, cost);
	}

	public void addAuthor(String author){
        if(!authors.contains(author)){
            authors.add(author);
        }
    }

    public void removeAuthor(String author){
        if(authors.contains(author)){
            authors.remove(author);
        }
    }
}
