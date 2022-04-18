package controller;


/**
 * class for viewing and changing selected photo and its details 
 * 
 * @author Farhaan Mohammad (fm412)
 * @author Siham Darr (sud7)
 * @version 1.0
 * @since 2022-04-13
 * */

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import model.Tag;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Album;
import model.Photo;
import model.User;

/**
 * class for viewing and changing selected photo and its details 
 * 
 * @author Farhaan Mohammad (fm412)
 * @author Siham Darr (sud7)
 * @version 1.0
 * @since 2022-04-13
 * */
public class PhotoController {

    @FXML
    private TextField TFcaption;

    @FXML
    private TextField albumCopy;

    @FXML
    private TextField albumMove;

    
    @FXML
    private Text captionText;
    
    @FXML
    private ListView<Tag> listOfTags;

    @FXML
    private Text photoName;

    @FXML
    private ImageView photoView;

  

    @FXML
    private TextField tagName;

    @FXML
    private TextField tagValue;
    
    @FXML
    private Text dateText;
    
    ArrayList<User> users;
    ListView<Photo> p;
	
	
	//private ListView<Tag> tags;
	private User user;
	private Album selectedAlbum;
	
	//private Photo photo;
	/**
	 * list of users
	 * */
	public static List<User> listsUsers = new ArrayList<>();
	/**
	 * list of photos
	 * */
	public static ArrayList<Photo> photosList = new ArrayList<>();
	/**
	 * current photo
	 * */
	public static Photo photo;
	/**
	 * album
	 * */
	public Album alb;
	//public ListView<Photo> p;
	private Photo selectedPhoto;
	
	
	SimpleDateFormat dateTimeFormat = new SimpleDateFormat("MM/dd/yyyy");
	
	/**
	 * start method
	 * @param users list of users
	 * @param selectedAlbum current album
	 * @param user curr user
	 *  @param p listview of photo
	 * */
	public void start(ArrayList<User>users,  Album selectedAlbum, User user,ListView<Photo> p) {
		this.users=users;
		this.p = p;
		this.user=user;
		this.selectedAlbum=selectedAlbum;
		
		//this.selectedPhoto=selectedPhoto;
		//this.photo=photo;
		//AlbumController.listOfPhotos
		//obsList = FXCollections.observableArrayList(p.getSelectionModel().getSelectedItem()); //gets the picture 
		//System.out.println(p.getSelectionModel().getSelectedItem().getTag());
		//System.out.println(obsList);
		
		listOfTags.setItems(FXCollections.observableArrayList(p.getSelectionModel().getSelectedItem().getTag()));
		//Tag s = new Tag( tagName.getText(), tagValue.getText());
		dateText.setText(dateTimeFormat.format(p.getSelectionModel().getSelectedItem().getDate().getTime()));
		Image image = new Image (photo.getFilePath());
		//photoName.setText(photo.getPhoto());
		photoView.setImage(image);
		//photoName.setText(photo.getFilePath().toString());
		//Photo selectedPhoto = photos.getSelectionModel().getSelectedItem();
		
	//listOfTags.setItems(FXCollections.observableArrayList(p.getSelectionModel().getSelectedItem().getTag()));
//		tags.getSelectionModel().select(0);
		
	captionText.setText(p.getSelectionModel().getSelectedItem().getCaption());
		
	}
    

	
	/**
	 * add tag method
	 * @param event users can add tag and it will be saved to the current photo
	 * */
    @FXML
    void addTagButton(ActionEvent event) throws Exception {
    	boolean dup = false;
    ArrayList<Tag> tagged = p.getSelectionModel().getSelectedItem().getTag();
// System.out.println(tagged);
    
 
    Tag names = new Tag(tagValue.getText(),tagName.getText());
  
    
    if(p.getSelectionModel().getSelectedItem()!= null) {
    	Photo pho = p.getSelectionModel().getSelectedItem();
    	if(pho.tagChecker(names)) {
    		Alert error = new Alert(AlertType.ERROR);
        	error.setTitle("Tag exists");
        	error.setContentText("Already Exists");
        	error.showAndWait();
        	dup=true;
    		
    		
    		
    		
    	}
    }
    //System.out.println(names);
    if(dup ==false) {
    	
    
    listOfTags.getItems().add(names);
    tagged.add(names);
    }
    //System.out.println(tagged.add(names));
    save(users);
    
  // listOfTags.getItems().add(names);
   
    //selectedPhoto.getTag().add(names);
    //System.out.println(selectedPhoto.getTag().add(names));
    
    	
    	
    	
    	
    	//tags.refresh();
    	
    	//tags.getSelectionModel().select(0);
    	
    	
    }

    
    /**
     * back to album method
     * @param event takes you back to the album to view other photos
     * */
    @FXML
    void backToAlbum(ActionEvent event) throws IOException {

    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/album.fxml"));
		Parent sceneManager = (Parent) loader.load();
		AlbumController albumController = loader.getController();
		albumController.start(selectedAlbum, user, users,selectedAlbum.getPhoto());
		Scene adminScene = new Scene(sceneManager);
		Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		appStage.setScene(adminScene);
		appStage.show();
    }

    
    /**
     * copy photo method
     * @param event user will be able to copy current photo to other albums under the same user 
     * */
    @FXML
    void movePhotoButton(ActionEvent event) throws Exception {
    	
    	String copyTo = albumCopy.getText();
    	
    	for(Album album: this.user.getAlbums()) {
    		if(album.getAlbum().equals(copyTo)) {
    			for(Photo photo2: album.getPhoto()) {
    				if((photo2.getFilePath().equals(photo.getFilePath())) && (photo2.getPhoto().equals(photo.getPhoto()))){
    					Alert error = new Alert(AlertType.ERROR);
    					error.setHeaderText("Error: Photo is already present");
    					error.showAndWait();
    					return;
    				}
    					
    				}
    			
    			Alert conf = new Alert(AlertType.CONFIRMATION);
    			conf.setHeaderText("Photo has been copied to: " + album.getAlbum());
    			conf.showAndWait();
    			album.getPhoto().add(photo);
    			save(users);
    			}
    		}
    	}
    
    /**
     * remove tag method
     * @param event user will be able to remove selected tag from photo 
     * */
    @FXML
    void removeTagButton(ActionEvent event) throws Exception {
    	int num = listOfTags.getSelectionModel().getSelectedIndex();
    	if(listOfTags.getSelectionModel().getSelectedItem()!=null) {
    		Alert rem = new Alert(AlertType.CONFIRMATION,"Are you sure you want to remove tag", ButtonType.YES,ButtonType.NO,ButtonType.CANCEL);
			rem.showAndWait();
			if(rem.getResult()==ButtonType.YES) {
				p.getSelectionModel().getSelectedItem().getTag().remove(num);
				save(users);
				 ObservableList<Tag> obsList = FXCollections.observableArrayList(p.getSelectionModel().getSelectedItem().getTag());
				listOfTags.setItems(obsList);
			}
    		
    		
    		
    	}
    	
    	
    	
    	
    	
    }
    
    /**
     * update caption method 
     * @param event user will be able to add/update caption to current photo
     * */
    @FXML
    void updateCaption(ActionEvent event) throws Exception {
    	
    	String caption = TFcaption.getText();
//    	Photo cap = p.getSelectionModel().getSelectedItem();
//    	String caption = cap.getCaption();
//    	captionText.setText(caption);
//    	save(users);
    	
    	if(caption!=null) {
    		captionText.setText(photo.setCaption(caption));
    		save(users);
    	}
   
    }

  

  
    /**
     * save method
     * @param users which gets saved to data.dat file
     * called after every change in other methods
     * @throws Exception exception
     * */
    public void save(List<User> users) throws Exception {
    	
    	try {
    		
			ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("data/data.dat"));
			o.writeObject(users);
			o.close();
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    
    
    
    
}
}


