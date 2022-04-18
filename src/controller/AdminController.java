package controller;



import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.User;

/**
 * class for adding and deleting users with admin control 
 * 
 * @author Farhaan Mohammad (fm412)
 * @author Siham Darr (sud7)
 * @version 1.0
 * @since 2022-04-13
 * */
public class AdminController {

    @FXML
    private TextField addUsername;

    @FXML
    private Button addUsers;

    @FXML
    private Button deleteUser;

    @FXML
    private Button logout;

    @FXML
    private ListView <User> usersList;
    
    private ArrayList <User> userList = new ArrayList<>();
    
    
    /**
     * start method
     * @param users which is an ArrayList of the users which is set to the listview 
     * 
     * */
    public void start(ArrayList<User>users) {
		this.userList = users;
		usersList.setItems(FXCollections.observableArrayList(userList));
		if(!userList.isEmpty()) {
			usersList.getSelectionModel().select(0);
		}
    }
    

    /**
     * add user method
     * @param event when button is clicked, user is added to list of users
     * */
    @FXML
    void addUserButton(ActionEvent event) throws IOException {
    	
    	if(addUsername.getText().equals("admin")) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("duplicate admin");
    		alert.setContentText("cannnot add another admin");
    		alert.showAndWait();
    		return;

    	}
    	
    	if(addUsername.getText().equals("")) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("invalid username");
    		alert.setContentText("please enter in a username");
    		alert.showAndWait();
    		return;
    	}
    
   
    	String newUser = addUsername.getText();
    	
    	for(int i =0; i<usersList.getItems().size(); i++) {
    		if(addUsername.getText().equals(usersList.getItems().get(i).toString())) {
    			Alert alert = new Alert(AlertType.ERROR);
        		alert.setTitle("Duplicate Item");
        		alert.setContentText("Please enter in a different username");
        		alert.showAndWait();
        		return;
    		}
    	}
    	
    	usersList.getItems().add(new User(newUser));
    	save();
    }
    

    
    /**
     * delete user method
     * @param event when button is clicked, user is deleted from the list of users
     * */
    @FXML
    void deleteUserButton(ActionEvent event) throws IOException {

    	if(usersList.getSelectionModel().getSelectedItem() != null) {
			User userSelected = usersList.getSelectionModel().getSelectedItem();
			Alert alert = new Alert(AlertType.CONFIRMATION, "delete " + userSelected + " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
			alert.setTitle("Delete user");
			alert.showAndWait();
			if (alert.getResult() == ButtonType.YES) {
				usersList.getItems().remove(userSelected);
				save();
				return;
			}	
		}
		else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("select a user to delete!");
			alert.showAndWait();
		}
    }
    	
    	

    /**
     * logout method
     * @param event when button is clicked user logs out of admin and is returned to the login page
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
		save();
		
    }
    
    /**
     * save method
     * called after every change to save changes to data.dat file
     * @throws IOException exception
     * */
    public void save() throws IOException{
    	
    		//writes to file
    		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("data/data.dat"));
        	out.writeObject(new ArrayList<> (Arrays.asList(usersList.getItems().toArray())));
        	out.close();
        	
    }
    
    
    
}


