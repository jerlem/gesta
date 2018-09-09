package window.entities;

import java.util.ArrayList;

public class Configuration {
		
	public String language;
	public int width;
	public int height;
	public ArrayList<String> sendToList = new ArrayList<String>();

	/**
	 * Configuration(String language, int width, int height, ArrayList<String> sendToList)
	 * 
	 * @param language
	 * @param width
	 * @param height
	 * @param sendToList
	 */
	public Configuration(String language, int width, int height, ArrayList<String> sendToList) {
		super();
		this.language = language;
		this.width = width;
		this.height = height;
		this.sendToList = sendToList;
	}
	
	
	
	
    public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public ArrayList<String> getSendToList() {
		return sendToList;
	}
	public void setSendToList(ArrayList<String> sendToList) {
		this.sendToList = sendToList;
	}


	/**
     *	ToString
     */
    @Override public String toString() {
		return "\nLang =" + language
			 + "\nWidth =" + height
			 + "\nHeight = "+ width;
    }
	


	
	
	

}
