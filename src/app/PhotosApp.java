package app;

/**
 * main class 
 * 
 * @author Farhaan Mohammad (fm412)
 * @author Siham Darr (sud7)
 * @version 1.0
 * @since 2022-04-13
 * */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


/**
 * main class 
 * 
 * @author Farhaan Mohammad (fm412)
 * @author Siham Darr (sud7)
 * @version 1.0
 * @since 2022-04-13
 * */
public class PhotosApp extends Application {
	/**
	 * mainStage
	 * */
	public Stage mainStage;
	
   
    /**
     * start method
     * @param primaryStage loads scene
     * */
    @Override
    public void start(Stage primaryStage) {
		try {
			mainStage = primaryStage;
			
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/loginpage.fxml"));
			AnchorPane root = fxmlLoader.load();

			
			Scene scene = new Scene(root);
			mainStage.setResizable(false);
			mainStage.setTitle("Photo Library");
			mainStage.setScene(scene);
			mainStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    }
    
    
    /**
     * main method 
     * @param args arguments
     * */
    public static void main(String[] args) {
        launch(args);
    }
}