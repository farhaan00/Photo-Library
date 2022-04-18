package controller;



import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Album;
import model.Photo;
import model.User;


/**
 * class which displays users albums 
 * 
 * @author Farhaan Mohammad (fm412)
 * @author Siham Darr (sud7)
 * @version 1.0
 * @since 2022-04-13
 * */
public class LibraryController {

    @FXML
    private TextField addAlbumText;

    @FXML
    private ListView<Album> listOfAlbums;

    @FXML
    private TextField renameAlbumText;

    @FXML
    private Text tDate;
    
    @FXML
    private Text numP;
    
    private ArrayList <User> users;
	private User user;
	
	/**
	 * list of albums
	 * */
	public ArrayList<Album> albumList = new ArrayList<Album>();
	/**
	 * observable list
	 * */
	public ObservableList<Album> obAlb;
	/**
	 * current user
	 * */
	public  User nowUser;
    
	
	SimpleDateFormat dateTimeFormat = new SimpleDateFormat("MM/dd/yyyy");
	
	
	/**
     * start method
     * @param users list of users
     * @param user current user
     *
     * 
     * */
    public void start(ArrayList<User> users, User user) {
		// TODO Auto-generated method stud
		
		this.users = users;
		this.nowUser =user; 
		obAlb = FXCollections.observableArrayList(nowUser.getAlbums());
		//System.out.println(obAlb);
		listOfAlbums.setItems(obAlb);
		
		
//		if(nowUser.getAlbums().size() > 0) {
//			listOfAlbums.getSelectionModel().select(0);
//			numP.setText("Number of Photos: " + nowUser.getAlbums().get(0).getPhoto().size());
//			
//			photoDetails();
//		}
		
		listOfAlbums.getSelectionModel().selectedItemProperty().addListener((v, oldVal, newVal) -> photoDetails());
		
		//System.out.println(nowUser.getAlbums());
	}
    
    
    /**
     * photo details method
     * used to show details of album and its specific details 
     * */
    public void photoDetails() {
    	Album select = listOfAlbums.getSelectionModel().getSelectedItem();
    	String firstDate = "";
    	String lastDate = "";
    	if(listOfAlbums.getSelectionModel().getSelectedItem()!=null) {
    		if(select.getPhoto().size()!= 0) {
    			Calendar date = select.getPhoto().get(0).getDate();
    			firstDate = dateTimeFormat.format(date.getTime());
    			lastDate = dateTimeFormat.format(date.getTime());
    			
    		}
    		ArrayList<Photo>tempList = select.getPhoto();
    		
    		for(Photo temp : tempList) {
    			Calendar s = temp.getDate();
    			
    			if(dateTimeFormat.format(s.getTime()).compareTo(firstDate) < 0) {
    				firstDate = dateTimeFormat.format(s.getTime());
    			}
    			if(dateTimeFormat.format(s.getTime()).compareTo(lastDate) > 0 ) {
    				lastDate = dateTimeFormat.format(s.getTime());

    			}
    			
    			if(select != null) {
    				tDate.setText("Date Range: \t" + firstDate + "-" + lastDate);
    				numP.setText("number of photos: " + select.getPhoto().size());
    			}
    		
    			
    		}
    		
    		if(select.getPhoto().size() < 1) {
				tDate.setText("Date Range: \t" + firstDate + "-" + lastDate);
				numP.setText("number of photos: 0");

    		}
    	
    		
    	}
    	
    	
    	
    	
    	
    }
    
    /**
     * rename album method
     * @param event when button is clicked, selected album will be renamed to whatever the user types
     * */
    @FXML
    void RenameAlbumButton(ActionEvent event) {
    	boolean dup=false;
    	
    	String addName = addAlbumText.getText().trim();
    	
    	ArrayList<Album> someALb = nowUser.getAlbums();
    	//boolean rename =false;
    	//ObservableList<Album> albums = listOfAlbums.getItems(); 
    	String albumName = addAlbumText.getText().trim();
    	Album temp = new Album(addName);
    		if(nowUser.checker(temp)) {
    			Alert alert = new Alert(AlertType.ERROR);
    			alert.setTitle("Album exists!! CANNOT RENAME!!");
    			alert.setContentText("");
    			alert.showAndWait();
    			dup=true;
    		}
    	
    	Album alb = listOfAlbums.getSelectionModel().getSelectedItem();
    	
    	Album selectedAlbum = listOfAlbums.getSelectionModel().getSelectedItem();
    	//String b = new Album(addAlbumText.getText());
    	if(alb!=null&& dup ==false ) {
    		
    	selectedAlbum.albumName = renameAlbumText.getText();
    	System.out.println(selectedAlbum.albumName);
    	save(users);
    	
    	obAlb = FXCollections.observableArrayList(nowUser.getAlbums());
    	listOfAlbums.setItems(obAlb);
    		
    		
    		
    	}
    	
    	
    	
    	
    	}
    
    

    /**
     * add album method
     * @param event when button is clicked, album is added to the list of albums
     * */
    @FXML
    void addAlbumButton(ActionEvent event) {
    	
    	boolean dup = false;
    	//ObservableList<Album> albums = listOfAlbums.getItems(); 
    	String albumName = addAlbumText.getText().trim();
      
		Album temp = new Album(albumName);
    	if(nowUser.checker(temp)) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Album exists!! CANNOT RENAME!!");
			alert.setContentText("");
			alert.showAndWait();
			dup=true;
		}
		//System.out.println("h");
		//Album a = new Album(addAlbumText.getText());
    	if(dup == false && (new Album(albumName) != null)) {
    	Alert add = new Alert(AlertType.CONFIRMATION,"Do you want to add", ButtonType.YES,ButtonType.NO,ButtonType.CANCEL);
    	  add.setTitle("ADD CONFIRMATION");
    	  add.showAndWait();
    	 // addAlbumText.setText("");
    	System.out.println((new Album(albumName)));
    	//albumList.add(a);
    	//System.out.println(nowUser.getAlbums().add(a));
    	nowUser.getAlbums().add(new Album(albumName));
    
    	listOfAlbums.getItems().add((new Album(albumName)));
    	
    	obAlb = FXCollections.observableArrayList(nowUser.getAlbums());
    	//listOfAlbums.getSelectionModel().select(a);
    	//listOfAlbums.refresh();
    	save(users);
    	addAlbumText.clear();
    	
    	 
    		
    		
    		
    		
    	}
    	
			
//		if(albumName == "") {
//    		Alert alert = new Alert (AlertType.ERROR);
//    		alert.setTitle("invalid album name");
//    		alert.setContentText("please enter in an album name");
//    		alert.showAndWait();
//    		return;
//    	}
		
    	
    	//listOfAlbums.getItems().add(new Album(albumName));
    }
    
    
    /**
     * delete album method
     * @param event when button is clicked, album is deleted from list of albums 
     * 
     * */
    @FXML
    void deleteAlbumButton(ActionEvent event) {
		int albums = listOfAlbums.getSelectionModel().getSelectedIndex();
		if(listOfAlbums.getSelectionModel().getSelectedItem() != null) {
			Alert rem = new Alert(AlertType.CONFIRMATION,"Are you sure you want to remove album?", ButtonType.YES,ButtonType.NO,ButtonType.CANCEL);
			rem.showAndWait();
			if(rem.getResult()==ButtonType.YES) {
				nowUser.getAlbums().remove(albums);
				save(users);
				obAlb = FXCollections.observableArrayList(nowUser.getAlbums());
				listOfAlbums.setItems(obAlb);
			}
			
		}else {
			if(listOfAlbums.getSelectionModel().getSelectedItem()==null) {
				Alert error = new Alert(AlertType.ERROR);
				error.setTitle("no album exists");
	    		error.setContentText("please select a album");
	    		error.showAndWait();
			}
		}
	//Alert rem = new Alert(AlertType.CONFIRMATION,"Are you sure you want to remove album?", ButtonType.YES,ButtonType.NO,ButtonType.CANCEL);
//		rem.setTitle("Delete");
//		rem.showAndWait();
		
		
//		if(rem.getResult()==ButtonType.YES){
//			albumList.remove(albums);
//			listOfAlbums.getItems().remove(albums);
//	    	//listOfAlbums.getSelectionModel().select(albums);
//			save(users);
//	    	listOfAlbums.refresh();
//
//		}
		
		
    }

    
    /**
     * logout method
     * @param event when button is clicked user logs out of the users library and is returned to the login page
     * */
    @FXML
    void logoutButton(ActionEvent event) throws IOException {

    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/loginpage.fxml"));
		Parent sceneManager = (Parent) loader.load();
		LoginController loginController = loader.getController();
		Scene adminScene = new Scene(sceneManager);
		Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		appStage.setScene(adminScene);
		appStage.show();
    }

    /**
     * search method
     * @param event when button is clicked, user will be taken to the search for photo page
     * */
    @FXML
    void searchButton(ActionEvent event) throws IOException {
    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/search.fxml"));
		Parent sceneManager = (Parent) loader.load();
		SearchController searchController = loader.getController();
		Scene adminScene = new Scene(sceneManager);
		Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		searchController.start(users, nowUser, listOfAlbums);
		appStage.setScene(adminScene);
		appStage.show();
    }

    
    /**
     * select album method
     * @param event when button is clicked, user enters into the selected album and all its contents
     * */
    @FXML
    void selectAlbumButton(ActionEvent event) {

    	Album selectedAlbum = listOfAlbums.getSelectionModel().getSelectedItem();
    	
    	if(selectedAlbum == null) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setHeaderText("no album ");
    		alert.showAndWait();
    	}
		
    	try{

		
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/album.fxml"));
		Parent sceneManager = (Parent) loader.load();
		AlbumController albumController = loader.getController();
		Scene adminScene = new Scene(sceneManager);
		Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		albumController.start(selectedAlbum, nowUser, users, selectedAlbum.getPhoto());
		appStage.setScene(adminScene);
		appStage.show();
		}
		catch(Exception e){
			e.printStackTrace();
		}
    	
    	
    	
    	
    }
    
    /**
     * save method
     * @param users which gets saved to data.dat file
     * called after every change in other methods
     * 
     * */
    public void save(List<User> users) {
    	//writes to file
		try{
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("data/data.dat"));
    	out.writeObject(users);
    	out.close();
    	
		}catch(Exception e){
			e.printStackTrace();
		}
    }

    
}
