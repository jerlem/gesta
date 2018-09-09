package window.entities;

import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;


/**
 * Ressource
 * 
 * Store text and picture to be reused in Window class
 * 
 * @see Window.java
 * @see IWindowSettings.java
 * 
 * @author Jérôme Lemarchand, 2018
 * @version 0.1
 */
public class Ressource {

	private static final String textRessourceFileExtension = ".lng";
	private static final String textRessourceFileFolder = "src/ressources/";
	
	private static final String iconRessourceFileExtension = ".png";
	private static final String iconRessourceFileFolder = "src/ressources/images/";

	// for ressources
	private ArrayList<RessourceIcons> icons = new ArrayList<RessourceIcons>();
	private ArrayList<RessourceTexts> texts = new ArrayList<RessourceTexts>();
	
	private String ressourceLang;
	public Ressource(String lang) {
		ressourceLang = lang;
	}
	

	/***
	 * getRessource(String ressourceType, String name)
	 * 
	 * get a text ressource by its name
	 * 
	 * @return Object 
	 */
	public String getTextRessource(String k) {
		// search in ressources
		int index =-1;
		String result = "";
		for (int i =0; i < texts.size(); i++) {
			result = texts.get(i).name;
			if(result.equals(k)) return texts.get(i).text.replace("\\n", "\n");
			index++;
		}
		// get text stored 
		if (index > -1) return texts.get(index).text.replace("\\n", "\n");
		
		return "UNSET";
	}
	

	/**
	 * ImageIcon getIconRessource(String k)
	 * 
	 * get an ImageIcon ressource by its name
	 * 
	 * @param k
	 * @return
	 */
	public ImageIcon getIconRessource(String k) {
		String result = "";
		for (int i =0; i < icons.size(); i++) {
			result = icons.get(i).name;
			if(result.equals(k)) return icons.get(i).icon;
		}
		return null;
	}
	// convert ImageIcon to Image
	public Image getImageRessource(String k) {
		ImageIcon ic = getIconRessource(k);
		return ic.getImage();
	}
	
	// add multiple Icons
	public void addIcon(String name, String file) {
		icons.add(new RessourceIcons(name, new ImageIcon(iconRessourceFileFolder + file +iconRessourceFileExtension)));
	}
	 
	

	/**
	 * getLanguageRessource :
	 * 
	 * make all RessourceTexts based language .lng text file 
	 * text file in HtmlUrlEncoded format
	 * 
	 */
	public void loadTextRessources() {
		// open file and get data splited by &
		String dataFile = loadTextFile(ressourceLang).trim();
		dataFile = dataFile.substring(1, dataFile.length());
		String[] data = dataFile.split("&");
		
		// get key values
		for (int i = 0; i < data.length; i++) {
			int sep = data[i].lastIndexOf('=');
			String key = data[i].substring(0, sep).trim();
			String value = data[i].substring(sep+1, data[i].length()).trim();
			texts.add(new RessourceTexts(key, value));
		}
	}
	
	/**
	 * loadTextFile(String ressourceFile) 
	 *
	 * Open a file and get char by char content. 
	 * avoid specials chars like \r \t and \b (below 0x0d)
	 * 
	 * @param ressourceFile
	 * @return
	 */
	public String loadTextFile(String ressourceFile) 
	{
		// file object reader and string to return
		FileReader reader;
		File f = new File(textRessourceFileFolder+ ressourceFile + textRessourceFileExtension);
		String output ="";
		
		// Attempt opening the file
		try {
			reader = new FileReader (f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		
	    int c;
	    
	    // Attempt reading file char by char
		try {
			c = reader.read();
			 while (c != -1) {
				 // remove file special chars (\n \t \r)
				 if (c > 0x0d) output += (char) c;
		         c = reader.read();
		    }
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// closing 
		try {
			reader.close();
		} catch (IOException e) { e.printStackTrace(); }
		
		return output;
	}
	
	/**
	 * Tostring
	 * 
	 */
	@Override public String toString() {
		String output="";
		
		for(int i = 0; i < texts.size(); i++) {
			RessourceTexts elem = texts.get(i);
			output += "\nRessource Text  #" + i + " : " +elem.getName() +" -> " + elem.getText();
		}
		for (int i = 0; i < icons.size(); i++) {
			RessourceIcons elem = icons.get(i);
			output += "\nRessource Icon #"  + i + " : " +elem.getName() +" -> [image]";
		}
		
		return output;
	}
	
}
