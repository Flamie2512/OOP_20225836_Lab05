package hust.soict.hedspi.aims.screen;


import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Playable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CartScreenController {

    private Cart cart;
    private FilteredList<Media> filteredList;
    @FXML
    private TableView<Media> tblMedia;

    @FXML
    private TableColumn<Media, String> colMediaTitle;

    @FXML
    private TableColumn<Media, String> colMediacategory;

    @FXML
    private TableColumn<Media, Float> colMediaCost;
    
    @FXML
    private Button btnPlay;

    @FXML
    private Button btnRemove;
    
    @FXML
    private TextField tfFilter;
    
    @FXML
    private Label totalCost;
    @FXML
    private Button btnPlaceOrder;
    
    @FXML
    private RadioButton radioBtnFilterId;

    @FXML
    private RadioButton radioBtnFilterTitle;

    public CartScreenController(Cart cart) {
        super();
        this.cart = cart;
    }

    @FXML
    private void initialize() {
        colMediaTitle.setCellValueFactory(
            new PropertyValueFactory<Media, String>("title"));
        colMediacategory.setCellValueFactory(
            new PropertyValueFactory<Media, String>("category"));
        colMediaCost.setCellValueFactory(
            new PropertyValueFactory<Media, Float>("cost"));
        filteredList = new FilteredList<>(cart.getItemsOrdered(), _ -> true);
        tblMedia.setItems(filteredList);
        
        btnPlay.setVisible(false);
        btnRemove.setVisible(false);
        
        tblMedia.getSelectionModel().selectedItemProperty().addListener(
        		new ChangeListener<Media>() {
        			
        			@Override
        			public void changed(ObservableValue<? extends Media> observable, Media oldValue,
        					Media newValue) {
        				if(newValue != null) {
        					updateButtonBar(newValue);
        					 updateTotalCost();
        				}
        			}
        		});
        tfFilter.textProperty().addListener(new ChangeListener<String>() {
        	@Override
        	public void changed(ObservableValue<? extends String> observable, String oldValue,
        			String newValue) {
        		showFilteredMedia(newValue);
        	}
        });
    }
    
    void updateButtonBar(Media media) {
    	btnRemove.setVisible(true);
    	if(media instanceof Playable) {
    		btnPlay.setVisible(true);
    	}
    	else {
    		btnPlay.setVisible(false);
    	}
    }
    
    void showFilteredMedia(String newValue) {
    	filteredList.setPredicate(media -> {
    		if(newValue == null || newValue.isEmpty()) {
    			return true;
    		}
    		String lowerCaseFilter = newValue.toLowerCase();
    		
    		if(radioBtnFilterTitle.isSelected()) {
    			return media.getTitle() != null && media.getTitle().toLowerCase().contains(lowerCaseFilter);
    		} else if(radioBtnFilterId.isSelected()) {
    			return String.valueOf(media.getId()).contains(lowerCaseFilter);
    		}
    		return false;
    	});
    }
    
    void updateTotalCost() {
        float cost = cart.totalCost();
        totalCost.setText(String.format("%.2f $", cost));
    }
    
    @FXML
    void btnRemovePressed(ActionEvent event) {
    	Media media = tblMedia.getSelectionModel().getSelectedItem();
    	System.out.println("Presses");
    	cart.removeMedia(media);
    }
    
    @FXML
    void btnPlayPressed(ActionEvent event) {
    	Media media = tblMedia.getSelectionModel().getSelectedItem();
    	Stage playStage = new Stage();
        playStage.initModality(Modality.APPLICATION_MODAL); 
        playStage.setTitle("Playing Media");
        BorderPane layout = new BorderPane();

        Label titleLabel = new Label("Playing: " + media.getTitle());
        titleLabel.setStyle("-fx-font-size: 16px; -fx-alignment: center;");

        Button closeButton = new Button("Close");
        closeButton.setOnAction(_ -> playStage.close());

        VBox vbox = new VBox(10);
        vbox.setStyle("-fx-padding: 20; -fx-alignment: center;");
        vbox.getChildren().addAll(titleLabel, closeButton);
        layout.setCenter(vbox);
        try {
			((Playable) media).play();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
        Scene scene = new Scene(layout, 300, 200);
        playStage.setScene(scene);
        playStage.setResizable(false);
        playStage.showAndWait();
    }
    
    @FXML
    void btnPlaceOrderPressed(ActionEvent event) {
    	float cost = cart.totalCost();
    	totalCost.setText(String.format("%.2f $", cost));
    }
}




