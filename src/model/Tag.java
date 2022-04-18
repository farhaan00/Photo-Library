package model;

/**
 * class for tag and its properties 
 * 
 * @author Farhaan Mohammad (fm412)
 * @author Siham Darr (sud7)
 * @version 1.0
 * @since 2022-04-13
 * */

import java.io.Serializable;

/**
 * class for tag and its properties 
 * 
 * @author Farhaan Mohammad (fm412)
 * @author Siham Darr (sud7)
 * @version 1.0
 * @since 2022-04-13
 * */
public class Tag implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7489694932761931203L;
	
	
	/**
	 * tagvalue
	 * */
	public String tagValue;
	/**
	 * tagname 
	 * */
	public  String tagName;
	 
	
	/**
	 * Tag method 
	 * @param value of tag
	 * @param name of tag
	 * */
	public Tag (String value, String name) {
		this.tagValue=value;
		this.tagName = name;
	}
	
	/**
	 * getter method 
	 * @return tagValue
	 * 
	 * */
	public String getTagValue() {
		return tagValue;
	}
	
	/**
	 * getter method 
	 * @return tagName
	 * 
	 * */
	public String getTagName() {
		return tagName;
	}
	
	/**
	 * setter method 
	 * @param name of tag
	 * @return tagName of new tag name
	 * 
	 * */
	public String setTagName(String name) {
		this.tagName=name;
		return tagName;
	}
	
	/**
	 * checker method 
	 * @param second tag 
	 * @return boolean whether both tags are same 
	 * */
	public boolean equals(Tag second) {
		if(tagName.equals(second.tagName) && tagValue.equals(second.tagValue)) {
			return true;
		}
		return false;
	}
	
	/**
	 * to string method 
	 * @return string of tag with value and name
	 * */
	public String toString() {
		return this.tagName + " - " + tagValue;
	}
	
}
