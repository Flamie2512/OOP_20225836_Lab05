package src.hust.soict.hedspi.aims.screen;

import java.io.IOException;

import javax.swing.JFrame;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import src.hust.soict.hedspi.aims.cart.Cart;
import src.hust.soict.hedspi.aims.store.Store;

public class AddCDToStoreScreen extends AddItemToStoreScreen {
	public AddCDToStoreScreen(Store store, Cart cart) {
		super(store, cart);

		JFrame frame = this;

		JFXPanel fxPanel = new JFXPanel();
		this.add(fxPanel);
		this.setTitle("Add CD to store");
		this.setVisible(true);
		Platform.runLater(new Runnable() {

			@Override
			public void run() {

				try {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("addCDToStore.fxml"));
					AddCDToStoreScreenController controller = new AddCDToStoreScreenController(store, cart, frame);
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
		new AddCDToStoreScreen(new Store(), new Cart());
	}
}