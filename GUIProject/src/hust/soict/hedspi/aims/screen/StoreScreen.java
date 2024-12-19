package hust.soict.hedspi.aims.screen;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.Book;
import hust.soict.hedspi.aims.media.CompactDisc;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Track;
import hust.soict.hedspi.aims.store.Store;

public class StoreScreen extends JFrame{
	private Store store;
	private Cart cart;
	public StoreScreen(Store store, Cart cart) {
		this.store = store;
		this.cart = cart;
		Container cp = getContentPane();
		
		cp.add(createNorth(), BorderLayout.NORTH);
		cp.add(createCenter(), BorderLayout.CENTER);
		
		setVisible(true);
		setTitle("Store");
		setSize(1024, 768);
	}
	
	public static void main(String[] args) {
		Store S = new Store();
		Cart C = new Cart();
		DigitalVideoDisc dvd1 = new DigitalVideoDisc("Inception", "Sci-Fi", "Christopher Nolan", 148, 19.99f);
		DigitalVideoDisc dvd2 = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 88, 15.99f);
		S.addMedia(dvd1);S.addMedia(dvd2);
		
		Book book1 = new Book(1, "The Great Gatsby", "Fiction", 10.99f);
        book1.addAuthor("F. Scott Fitzgerald");
        Book book2 = new Book(2, "To Kill a Mockingbird", "Drama", 7.99f);
        book2.addAuthor("Harper Lee");
        Book book3 = new Book(3, "1984", "Dystopian", 8.99f);
        book3.addAuthor("George Orwell");
        book3.addAuthor("Another Contributor");
        S.addMedia(book1);S.addMedia(book2);S.addMedia(book3);
        
        ArrayList<Track> tracks1 = new ArrayList<>();
        tracks1.add(new Track("Track 1", 5));
        tracks1.add(new Track("Track 2", 4));

        ArrayList<Track> tracks2 = new ArrayList<>();
        tracks2.add(new Track("Track A", 6));
        tracks2.add(new Track("Track B", 3));
        tracks2.add(new Track("Track C", 4));

        ArrayList<Track> tracks3 = new ArrayList<>();
        tracks3.add(new Track("Song X", 3));
        tracks3.add(new Track("Song Y", 5));

        ArrayList<Track> tracks4 = new ArrayList<>();
        tracks4.add(new Track("Melody 1", 7));
        tracks4.add(new Track("Melody 2", 6));
        tracks4.add(new Track("Melody 3", 8));

        CompactDisc cd1 = new CompactDisc(1, "Album 1", "Pop", 19.99f, 9, "Director A", "Artist A");
        cd1.addTrack(new Track("Intro", 2));
        cd1.addTrack(new Track("Outro", 3));

        CompactDisc cd2 = new CompactDisc("Director B", "Artist B", tracks1);

        CompactDisc cd3 = new CompactDisc("Artist C", tracks2);

        CompactDisc cd4 = new CompactDisc(45, "Director D", "Artist D", tracks4);
        S.addMedia(cd1);S.addMedia(cd2);S.addMedia(cd3);S.addMedia(cd4);
        
        
		new StoreScreen(S,C);
	}
	
	JPanel createNorth() {
		JPanel north = new JPanel();
		north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
		north.add(createMenuBar());
		north.add(createHeader());
		return north;
	}
	
	JMenuBar createMenuBar() {
		JMenu menu = new JMenu("Options");
		
		JMenu smUpdateStore = new JMenu("Update Store");
        JMenuItem smAddBook = new JMenuItem("Add Book");
        JMenuItem smAddCD = new JMenuItem("Add CD");
        JMenuItem smAddDVD = new JMenuItem("Add DVD");
        smUpdateStore.add(smAddBook);
        smUpdateStore.add(smAddCD);
        smUpdateStore.add(smAddDVD);   
		
		smAddBook.addActionListener(new btnMenuListener());
        smAddCD.addActionListener(new btnMenuListener());
        smAddDVD.addActionListener(new btnMenuListener());

		
		menu.add(smUpdateStore);
		JMenuItem viewStoreMenu = new JMenuItem("View store");
        JMenuItem viewCartMenu = new JMenuItem("View cart");
		menu.add(viewStoreMenu);
		menu.add(viewCartMenu);
		viewStoreMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StoreScreen(store, cart);
            }
        });
        viewCartMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CartScreen(cart);
            }
        });
		JMenuBar menuBar = new JMenuBar();
		menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		menuBar.add(menu);
		
		return menuBar;
	}
	
	JPanel createHeader() {
		JPanel header = new JPanel();
		header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));
		
		JLabel title = new JLabel("AIMS");
		title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 50));
		title.setForeground(Color.CYAN);
		
		JButton cartBtn = new JButton("View cart");
		cartBtn.setPreferredSize(new Dimension(100, 50));
		cartBtn.setMaximumSize(new Dimension(100, 50));
		cartBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CartScreen(cart);
            }
        });
		
		header.add(Box.createRigidArea(new Dimension(10, 10)));
		header.add(title);
		header.add(Box.createHorizontalGlue());
		header.add(cartBtn);
		header.add(Box.createRigidArea(new Dimension(10, 10)));
		
		return header;
	}
	
	JPanel createCenter() {
		JPanel center = new JPanel();
		center.setLayout(new GridLayout(3, 3, 2, 2));
		
		ArrayList<Media> mediaInStore = store.getItemsInStore();
		for(int i=0;i<9;i++) {
			MediaStore cell = new MediaStore(mediaInStore.get(i), cart);
			center.add(cell);
		}
		
		return center;
	}
	
	private class btnMenuListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			if (command.equals("Add Book")) {
				new AddBookToStoreScreen(store);
			} else if (command.equals("Add CD")) {
				new AddCompactDiscToStoreScreen(store);
			} else if (command.equals("Add DVD")) {
				new AddDigitalVideoDiscToStoreScreen(store);
			} 
		}
	}
	
	
	
}
