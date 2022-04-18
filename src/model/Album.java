package model;




import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * class for album and its properties 
 * 
 * @author Farhaan Mohammad (fm412)
 * @author Siham Darr (sud7)
 * @version 1.0
 * @since 2022-04-13
 * */
public class Album implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5286076993299808160L;
	/**
	 * albumName
	 * */
	public String albumName; 
	/**
	 * arraylist of photos
	 * */
	ArrayList<Photo> albumPhotos; 
	
	/**
	 * album method 
	 * @param name name of album
	 * used for Album instance
	 * */
	public Album(String name) {
		this.albumName = name;
		this.albumPhotos = new ArrayList<Photo>();
	}
	
	/**
	 * album name getter method 
	 * @return albumName name of album 
	 * */
	public String getAlbum () {
		return albumName;
	}
	
	/**
	 * albumName getter method 
	 * @return this.albumName name of album
	 * */
	public String getName() {
		return this.albumName;
	}
	
	/**
	 * album name setter method
	 * @param albumName name of album
	 * used to change name of album 
	 * */
	public void  setName(String albumName) {
	 this.albumName = albumName;
	 
	}
	
	/**
	 * album name setter method
	 * @param name name of album
	 * used to change name of album 
	 * @return albumName name of album
	 * */
	public String setAlbum(String name) {
		this.albumName= name;
		return albumName;
	}
	
	/**
	 * get photos method 
	 * @return photos in specific album
	 * */
	public ArrayList<Photo> getPhoto() {
		return albumPhotos;
		}
	
	/**
	 * add photo method 
	 * @param photo is added to album
	 * 
	 * */
	public void addPhoto(Photo photo) {
		albumPhotos.add(photo);
		}
	
	
	/**
	 * to string method 
	 * @return albumName album name in string 
	 * */
	public String toString() {
		return this.albumName;
	}
	
	/**
	 * photo checker method 
	 * @param second photo used to see if photo is present
	 * @return boolean to see whether it is true or not
	 * */
	public boolean photoChecker(Photo second) {
		for(Photo photo: albumPhotos) {
			if(photo.photoName.trim().equals(second.photoName.trim())){
				
			
			return true;
			}
		}
		return false;
	}
	
	/**
	 * photo checker method 
	 * @param second photo used to see if photo is present through its filepath
	 * @return boolean to see whether it is true or not
	 * */
	public boolean fPChecker(Photo second) {
		for(Photo photo: albumPhotos) {
			if(photo.filePath.trim().equals(second.filePath.trim())){
				
			
			return true;
			}
		}
		return false;
	}
	
}
