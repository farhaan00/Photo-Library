package model;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * class for photo and its properties 
 * 
 * @author Farhaan Mohammad (fm412)
 * @author Siham Darr (sud7)
 * @version 1.0
 * @since 2022-04-13
 * */
public class Photo implements Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = -32432423423434324L;
	/**
	 * photo name
	 * */
	public String photoName;
	/**
	 * caption
	 * */
	private String photoCaption;
	/**
	 * date
	 * */
	private Calendar photoDate;
	/**
	 * tags
	 * */
	ArrayList<Tag> photoTags;
//private Image image;
//private String photo;
	/**
	 * filepath
	 * 
	 * */
	public String filePath;

	/**
	 * 
	 * tags
	 * */
	ArrayList<Tag> tags;

	
	/**
	 * photo method
	 * @param name of photo
	 * @param date of photo 
	 * @param filePath of photo 
	 * 
	 * */
	public Photo(String name, Calendar date, String filePath) {
		this.photoTags = new ArrayList<Tag>();
		this.photoName = name;
//	this.photoTags= new ArrayList<Tag>();
		// this.photoCaption = photoCaption;
		// this.photo=photo;

		this.photoDate = date;
		this.photoDate.set(Calendar.MILLISECOND, 0);
		this.filePath = filePath;
		this.tags = new ArrayList<Tag>();
		// this.photoTags = new ArrayList<Tag>();
	}

	/**
	 * getter method
	 * @return photoName name of photo
	 * */
	public String getPhoto() {
		return photoName;
	}

	/**
	 * setter method
	 * @param photoName of photo to change
	 * */
	public void setPhoto(String photoName) {
		this.photoName = photoName;
	}

	/**
	 * getter method
	 * @return photoCaption of photo
	 * */
	public String getCaption() {
		return photoCaption;
	}

	/**
	 * setter method for caption
	 * @param caption of photo 
	 * @return photoCaption new caption
	 * */
	public String setCaption(String caption) {
		this.photoCaption = caption;
		return photoCaption;
	}

	/**
	 * getter method for date
	 * @return photoDate of photo
	 * */
	public Calendar getDate() {
		return photoDate;
	}

	/**
	 * getter method for tags
	 * @return tags of photo through arraylist
	 * */
	public ArrayList<Tag> getTag() {
		// System.out.println(photoTags);
		if (this.tags == null) {
			tags = new ArrayList<Tag>();
		}
		return tags;
	}

	/**
	 * toString method
	 * @return this.photoName 
	 * */
	public String toString() {
		return this.photoName;
	}

	/**
	 * print tags method 
	 * @return string of tags in their by name and value 
	 * */
	public String printPhotoTags() {
		ArrayList<Tag> tags = this.getTag();

		for (Tag s : tags)
			return s.getTagName() + "|" + s.getTagValue() + " ";

		return "";
	}

	/**
	 * getter method 
	 * @return this.filePath
	 * */
	public String getFilePath() {
		return this.filePath;
	}

	/**
	 * setter method 
	 * @param fp filepath
	 * @return filepath 
	 * */
	public String setFilePath(String fp) {
		this.filePath = fp;
		return filePath;
	}

	/**
	 * same photo checker 
	 * @param second photo 
	 * @return boolean whether two photos are same
	 * */
	public boolean equals(Photo second) {
		if (photoName.equals(second.photoName)) {
			return true;
		}
		return false;
	}

	/**
	 * tag checker method 
	 * @param second tag
	 * @return boolean whether two tags are same 
	 * */
	public boolean tagChecker(Tag second) {
		for (Tag t : tags) {
			if (t.tagName.trim().equals(second.tagName.trim()) && t.tagValue.trim().equals(second.tagValue.trim())) {
				return true;
			}
		}
		return false;
	}

}
