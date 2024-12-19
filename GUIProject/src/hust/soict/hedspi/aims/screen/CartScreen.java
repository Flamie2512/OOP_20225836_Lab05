package hust.soict.hedspi.aims.screen;



import java.io.IOException;

import javax.swing.JFrame;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.Book;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class CartScreen extends JFrame{
	private Cart cart;
	
	public CartScreen(Cart cart) {
		super();
		
		this.cart = cart;
		
		JFXPanel fxPanel = new JFXPanel();
		this.add(fxPanel);
		
		this.setTitle("Cart");
		this.setVisible(true);
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				try {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/hust/soict/hedspi/aims/screen/cart.fxml"));
					CartScreenController controller = 
							new CartScreenController(cart);
					loader.setController(controller);
					Parent root = loader.load();
					fxPanel.setScene(new Scene(root));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static void main(String[] args) {
		Cart C = new Cart();
		DigitalVideoDisc dvd1 = new DigitalVideoDisc("Inception", "Sci-Fi", "Christopher Nolan", 148, 19.99f);
		DigitalVideoDisc dvd2 = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 88, 15.99f);
		C.addMedia(dvd1);C.addMedia(dvd2);
		Book book1 = new Book(1, "The Great Gatsby", "Fiction", 10.99f);
        book1.addAuthor("F. Scott Fitzgerald");
        Book book2 = new Book(2, "To Kill a Mockingbird", "Drama", 7.99f);
        book2.addAuthor("Harper Lee");
        Book book3 = new Book(3, "1984", "Dystopian", 8.99f);
        book3.addAuthor("George Orwell");
        book3.addAuthor("Another Contributor");
        C.addMedia(book1);C.addMedia(book2);C.addMedia(book3);
        new CartScreen(C);
	}
}
