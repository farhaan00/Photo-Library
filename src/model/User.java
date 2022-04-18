package model;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;


/**
 * class for tag and its properties 
 * 
 * @author Farhaan Mohammad (fm412)
 * @author Siham Darr (sud7)
 * @version 1.0
 * @since 2022-04-13
 * */
public class User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * username
	 * */
	private String username;
	
	//public ArrayList<Album> albums = new ArrayList<Album>();
//	private static final long serialVersionUID;

	/**
	 * list of albums under user 
	 * */
	ArrayList <Album> userAlbums;
	
	/**
	 * User method
	 * @param name name
	 * */
	public User(String name) {
		this.username = name;
		this.userAlbums= new ArrayList<Album>();
	}
	
	/**
	 * getter method
	 * @return username of user 
	 * */
	public String getUsername() {
		return username;
	}
	

	/**
	 * getter method 
	 * @return userAlbums albums that belong to user 
	 * */
	public ArrayList<Album> getAlbums () {
		return userAlbums;
	}
	
	
	/**
	 * checker method 
	 * @param second album 
	 * @return boolean to see if two album are the same 
	 * */
	public boolean checker(Album second) {
		for(Album album: userAlbums) {
			if(album.albumName.trim().equals(second.albumName.trim())){
				
			
			return true;
			}
		}
		return false;
	}
	
	/**
	 * to string method 
	 * @return this.username 
	 * */
	public String toString() {
		return this.username;
	}
	
	
	
	
	
	
	
}
