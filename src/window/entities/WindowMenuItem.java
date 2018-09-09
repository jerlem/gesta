package window.entities;

/**
 * MenuElement Store JMenuItem elements
 * @author jerome Lemarchand
 *
 */
public class WindowMenuItem {
	public static final Boolean separatorLine = true;
	
	public static enum menuItem { separator, enabled, category, caption, description, event, mnemonic, shortcut; }

	
	//	public WindowMenuItem(String category, String caption, int shortcut, boolean enabled) {
	private Boolean separator, enabled;
	private String category, caption, description, event, shortcut;
	private char mnemonic;
	
	public WindowMenuItem(Boolean separator, Boolean enabled, String category, String caption, String description, 
			String event, char mnemonic, String shortcut) {
		super();
		this.separator = separator;
		this.enabled = enabled;
		this.category = category;
		this.caption = caption;
		this.description = description;
		this.event = event;
		this.mnemonic = mnemonic;
		this.shortcut = shortcut;
	}
	
	/**
	 * Gets
	 * 
	 */
	public static Boolean isSeparator() { return separatorLine; }
	public Boolean getSeparator() { return separator; }
	public Boolean getEnabled() { return enabled; }
	public String getCategory() { return category; }
	public String getCaption() { return caption; }
	public String getDescription() { return description; }
	public char getMnemonic() { return mnemonic; }
	public String getShortcut() { return shortcut; }
	public String getEvent() {return event; }
	
	// switch Enabled or not
	public void Toggle() { this.enabled = !this.enabled; }
	
	// to handle events
	public void setEvent(String evtName) { this.event = evtName; }
	
	/**
	 *	toString
	 *
	 *  @return String
	 */
	@Override public String toString() {
		return "\nMenu Element " +
				" separator = " + separator +
				"\n enabled = " + enabled +
				"\n category = " + category +
				"\n caption = " + caption +
				"\n description = " + description +
				"\n event = " + event +
				"\n mnemonic = " + mnemonic +
				"\n shortcut = " + shortcut;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WindowMenuItem other = (WindowMenuItem) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		return true;
	}


	
	
}
