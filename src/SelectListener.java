import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Listener for the select button
 * 
 * @author Garrett Steele and Monty Dhanani
 *
 */
public class SelectListener implements ActionListener {

	private Simulation sim;
	private JList<User> userList;
	private JFrame list;
	private RankingGUI rg;

	public SelectListener(Simulation sim) {
		this.sim = sim;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		list = new JFrame("List of Users");

		DefaultListModel<User> addList = new DefaultListModel<User>();
		for (User u : sim.getAllUser()) {
			addList.addElement(u);
		}
		userList = new JList<User>(addList);
		list.add(userList);
		list.pack();
		list.setVisible(true);

		userList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		MouseListener mouseListener = new MouseAdapter() {
			public void mouseClicked(MouseEvent mouseEvent) {
				JList theList = (JList) mouseEvent.getSource();
				if (mouseEvent.getClickCount() == 2) {
					sim.setGraphable(userList.getSelectedValue());
					list.dispose();
				}
				rg = new RankingGUI();
			}
		};
		userList.addMouseListener(mouseListener);

	}

}
