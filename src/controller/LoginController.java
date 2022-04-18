package controller;



import java.awt.Label;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Album;
import model.Photo;
import model.User;

/**
 * class for letting saved users/admin log in 
 * 
 * @author Farhaan Mohammad (fm412)
 * @author Siham Darr (sud7)
 * @version 1.0
 * @since 2022-04-13
 * */
public class LoginController {

    @FXML
    private TextField TFusername;
    
    @FXML
    private Label wrongLogin;

    @FXML
    private Button login;
    
    private ArrayList <User> userList = new ArrayList<>();
   

    /**
     * stock photots method
     * used to load photos of stock and stock album
     * @throws Exception exception
     * */
    public void stockPhotos() throws Exception{
    	File data = new File("data/data.dat");
    	
    //	System.out.println("here1");
    	if(!data.exists()) {
    		try {
    			
        		data.createNewFile();
        		User stock = new User("stock");
        		Album stockAlbum = new Album("Stock Album");
        		File photoFile = new File("data/stockphotos/stockphoto1.jpeg");
        		savePhoto(photoFile,stockAlbum);
        		
        		File photoFile2 = new File("data/stockphotos/stockphoto2.jpeg");
        		savePhoto(photoFile2,stockAlbum);
        		
        		File photoFile3 = new File("data/stockphotos/stockphoto3.jpeg");
        		savePhoto(photoFile3,stockAlbum);
        		
        		File photoFile4 = new File("data/stockphotos/stockphoto4.jpeg");
        		savePhoto(photoFile4,stockAlbum);
        		
        		
        		stock.getAlbums().add(stockAlbum);
        		List<User> users = new ArrayList<>();
        		users.add(stock);
        		
        		save(users);
        		
    		} catch(Exception ep) {
    			ep.printStackTrace();
    		}
    		
    	
    	}
    }
    
    /**
     * method for checking login
     * @param event when button is clicked the method checks if the user entered is valid 
     * @throws Exception exception
     * */
    @FXML
    public void buttonLogin(ActionEvent event) throws Exception {
    	
    	String username = TFusername.getText();
    	stockPhotos();
    
    try {
    	ObjectInputStream in = new ObjectInputStream(new FileInputStream("data/data.dat"));
		ArrayList<User> users = (ArrayList<User>) in.readObject();
    	
		in.close();
    	
    	User user1= null;
    	
    	for(User currentUser : users) {
    		if(currentUser.getUsername().equals(username)) {
    			user1 = currentUser;
    			break;
    		}
    	}
    	
    	
    	//takes you to admin page 
    	 if(username.equals("admin")) {
    		
    		 FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/adminpage.fxml"));
 			Parent sceneManager = (Parent) loader.load();
 			AdminController adminController = loader.getController();
 			Scene adminScene = new Scene(sceneManager);
 			Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
 			adminController.start(users); 
 			appStage.setScene(adminScene);
 			appStage.show();
    	}
    	 
    	 else if(user1!=null){
     		
     		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/library.fxml"));
 			Parent sceneManager = (Parent) loader.load();
 			LibraryController libraryController = loader.getController(); 
 			Scene libraryScene = new Scene(sceneManager);
 			Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
 			libraryController.start(users, user1);  
 			appStage.setScene(libraryScene);
 			appStage.show();
     	}
    	
    	 else if(username.equals("stock")) {
    		
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/library.fxml"));
    		Parent sceneManager = (Parent) loader.load();
    		LibraryController libraryController = loader.getController();
    		Scene adminScene = new Scene(sceneManager);
    		Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    		appStage.setScene(adminScene);
    		appStage.show();
    		
    	}
    	
    	else {	
    		
    	Alert erroralert = new Alert(AlertType.ERROR);
		erroralert.setHeaderText("Error");
		erroralert.setContentText("Invalid username entered!! Please enter a valid username");
		erroralert.showAndWait();
   
    	}
    }
    
    catch(Exception e) {
    	e.printStackTrace();
    }
   
    	
    }
    
    
    /**
     * save photo method 
     * @param photoFile file which is used to store in its respectful albums
     * @param stockAlbum album which is dealt with its respectful properties 
     * */
    public void savePhoto(File photoFile, Album stockAlbum) {
    	
    	String photoName = photoFile.getName();
    	Calendar photoDate = Calendar.getInstance();
    	photoDate.setTimeInMillis(photoFile.lastModified());
    	Photo newStockPhoto = new Photo(photoName, photoDate, photoFile.toURI().toString());
    	stockAlbum.getPhoto().add(newStockPhoto);
    	
    	
    }
    
    
    /**
     * save method
     * @param users which gets saved to data.dat file
     * called after every change in other methods
     * @throws Exception exception
     * */
    public void save(List<User> users) throws Exception {
    	
    	ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("data/data.dat"));
    	o.writeObject(users);
    	o.close();
    }
   
    
    
}
