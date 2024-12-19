package hust.soict.hedspi.aims.screen;

import javax.swing.BoxLayout;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Playable;

public class MediaStore extends JPanel{
	private Media media;
	private Cart cart;
	
	public MediaStore(Media media, Cart cart) {
		this.media = media;
		this.cart = cart;
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JLabel title = new JLabel(media.getTitle());
		title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 20));
		title.setAlignmentX(CENTER_ALIGNMENT);
		
		JLabel cost = new JLabel(""+media.getCost()+"$");
		cost.setAlignmentX(CENTER_ALIGNMENT);
		
		JPanel container = new JPanel();
		container.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		JButton addToCartButton = new JButton("Add to cart");
		addToCartButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cart.addMedia(media);
				JOptionPane.showMessageDialog(null, 
						 "The item \"" + media.getTitle() + "\" has been added to the cart.");
			}
		});
		container.add(addToCartButton);
		
		if(media instanceof Playable) {
			JButton playButton = new JButton("Play");
			playButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JDialog playDialog = new JDialog((Frame) null, "Playing Media", true);
                    playDialog.setSize(300, 200);
                    playDialog.setLayout(new BorderLayout());

                    JLabel titleLabel = new JLabel("Playing: " + media.getTitle(), SwingConstants.CENTER);
                    playDialog.add(titleLabel, BorderLayout.CENTER);
                    try {
						((Playable) media).play();
					} catch (Exception e1) {
						e1.printStackTrace();
					}

                    JButton closeButton = new JButton("Close");
                    closeButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            playDialog.dispose();
                        }
                    });

                    playDialog.add(closeButton, BorderLayout.SOUTH);
                    playDialog.setLocationRelativeTo(null); 
                    playDialog.setVisible(true);
                }
            });
            container.add(playButton);
		}
		
		this.add(Box.createVerticalGlue());
		this.add(title);
		this.add(cost);
		this.add(Box.createVerticalGlue());
		this.add(container);
		
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}
}
