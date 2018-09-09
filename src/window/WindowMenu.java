package window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.util.ArrayList;

import javax.swing.*;

import window.entities.*;
import window.entities.WindowMenuItem.menuItem;

public class WindowMenu implements IEvents, IWindowSettings {
	private Window parent;
	
	private ArrayList<WindowMenuItem> elements = new ArrayList<WindowMenuItem>();
	private ArrayList<String> categories = new ArrayList<String>();
	private ArrayList<Integer> categoriesCounts = new ArrayList<Integer>();

	public WindowMenu(Window w) {
		parent = w;
	}
	

	/**
	 * Create all menu Elements
	 *  
	 * @param params
	 */
	public void addMenu(String[] params) {
		// wrong number of arguments ?
		if (params.length %8 != 0) return;
		
		// Cycle 8 by 8 elements
		int p=0;
		while (p < params.length) {
			/** get the elements and add menu @see addMenuItem */			
			addMenuItem(new String[] { params[p], params[p+1], params[p+2], params[p+3], params[p+4],
						params[p+5],params[p+6],params[p+7]});
			p += 8;
		}
		// get categories only
		getCategories();
	}
	
	/***
	 * getCategories 
	 * 
	 * return unique categories (JMenu)
	 */
	private void getCategories() {
		categories.add(elements.get(0).getCategory());
		int count = 1;
		
		for (int i =1; i < elements.size(); i++) {
			String current = elements.get(i).getCategory();
			String last = elements.get(i-1).getCategory();
			if (current != last) {
				categories.add(current);
				count++;
			} else {
				categoriesCounts.add(count);
				count =0;
				
			}
		}
	}
	
	/**
	 * Create individual MenuItem
	 * 
	 * @param params
	 */
	private void addMenuItem(String[] params) {
		// menuItem { separator, enabled, category, caption, description, event, mnemonic, shortcut }
		Boolean separator = Boolean.parseBoolean(params[menuItem.separator.ordinal()]);
		Boolean enabled = Boolean.parseBoolean(params[menuItem.enabled.ordinal()]);
		String category = params[menuItem.category.ordinal()];
		String caption = params[menuItem.caption.ordinal()];
		String description = params[menuItem.description.ordinal()];
		String event = params[menuItem.event.ordinal()];
		//char mnemonic = params[menuItem.mnemonic.ordinal()].charAt(0);
		char mnemonic = Character.MIN_VALUE;
		String shortcut = params[menuItem.shortcut.ordinal()];
		
		elements.add(new WindowMenuItem(separator, enabled, category, caption, description, event, mnemonic, shortcut));
	}
	
	protected void createMenu(Window w) {
		// searching standard String Array
		String[] arrayCategories = categories.toArray(new String[0]);
		
		// make JMenuBar
		w.swingMenuBar = new JMenuBar();
		
		for (int i = 0; i < categories.size(); i++) {
			w.swingMenu.add(new JMenu(categories.get(i)));
			w.swingMenuBar.add(w.swingMenu.get(i));
		}
		
		for (int i= 0; i < elements.size(); i++) {
			String caption = elements.get(i).getCaption();
			String category = elements.get(i).getCategory();

			w.swingMenuItem.add( new JMenuItem(caption) );
			if (caption == "separator" || caption == "---") {
				// if separator just add JSeparator
				w.swingMenu.get(find(arrayCategories, category)).add(new JSeparator());
			} else {
				// adding the JmenuItem including :
				// mnemonic, enabled, accessibleDescription
				w.swingMenuItem.get(i).setMnemonic(elements.get(i).getMnemonic());
				// shortcut
				String sh = elements.get(i).getShortcut(); 
				if (sh.length() > 1 ) {
					if(sh.length() > 2){
						// we have a CTRL+(?) shortcut
						KeyStroke keyPressed = KeyStroke.getKeyStroke(sh.charAt(sh.length()-1), InputEvent.CTRL_MASK );
						w.swingMenuItem.get(i).setAccelerator(keyPressed);
					} else {
						// we have a F(?) Shortcut
						w.swingMenuItem.get(i).setAccelerator(KeyStroke.getKeyStroke(elements.get(i).getShortcut()));
					}
				}
				w.swingMenuItem.get(i).setEnabled(elements.get(i).getEnabled());
				w.swingMenuItem.get(i).getAccessibleContext().setAccessibleDescription(elements.get(i).getDescription());
				String evt = elements.get(i).getEvent();
				w.swingMenuItem.get(i).addActionListener( new ActionListener(){ 
											public void actionPerformed(ActionEvent e) {
												IEvents.onMenuSelected(evt, parent);
										} } );
				// attaching to JMenu
				w.swingMenu.get(find(arrayCategories, category)).add(w.swingMenuItem.get(i));
			}
		}
		
		
		w.setJMenuBar(w.swingMenuBar);
	}
	
	/*
	 * return the good Jmenu
	 * (stored as ArrayList<JMenu> swingMenu)
	 */
	private int find(String[] searchOn, String k) {
		for(int i = 0; i < searchOn.length; i++) {
			if (k.equals( searchOn[i] ) ) return i;
		}
		return -1;
	}
	
	/**
	 * toString
	 */
	@Override public String toString() {
		String o ="elements :\n";
		for( int i = 0; i < elements.size(); i++) {
			o += elements.get(i).toString();
		}
		o += "\ncategories\n"; 
		for (int i = 0; i < categories.size(); i++) {
			o += "\n " + categories.get(i).toString();
		}
		return o;
	}

	
}
