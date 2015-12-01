import javax.swing.*;
import java.awt.event.*;

public class MenuBarListener implements ActionListener {
	
	private Simulation sim;
	private int saveCounter;
	public static final String SAVE = "save";
	public static final String LOAD = "load";
	
	public MenuBarListener(Simulation s) {
		sim = s;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JMenuItem menuItem = (JMenuItem) e.getSource();
        String command = menuItem.getActionCommand();
		if (command.equals(SAVE)) {
			saveCounter++;
			sim.saveSim(saveCounter);
		}
	}
}
