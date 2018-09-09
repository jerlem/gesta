package window;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class WindowEventAdapter extends WindowAdapter implements IEvents {
	
	@Override	public void windowOpened(WindowEvent e) 	 { IEvents.onWindowOpen(); }
	@Override   public void windowClosing(WindowEvent e)	 { IEvents.onWindowClose(); }
    @Override   public void windowIconified(WindowEvent e)   { IEvents.onWindowReduce(); }
    @Override	public void windowDeiconified(WindowEvent e) { IEvents.onWindowRestore(); } 
    @Override   public void windowActivated(WindowEvent e)   { IEvents.onWindowFocus(); }
    @Override   public void windowDeactivated(WindowEvent e) { IEvents.onWindowUnfocus(); }
    @Override   public void windowStateChanged(WindowEvent e){ IEvents.onWindowChanged(); }

}