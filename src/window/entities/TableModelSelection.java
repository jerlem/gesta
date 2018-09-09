package window.entities;

import javax.swing.DefaultListSelectionModel;
import javax.swing.ListSelectionModel;

public class TableModelSelection extends DefaultListSelectionModel  {

	private static final long serialVersionUID = 1L;

	public TableModelSelection () {
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    @Override
    public void clearSelection() {
    }

    @Override
    public void removeSelectionInterval(int index0, int index1) {
    }


}
