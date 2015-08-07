//View module to create GUI to display results

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Addressbookgui extends JFrame implements ActionListener {
	private static final long versionId = 1;
	private JLabel searchLabel;
	private JTextField searchField;
	private JButton searchButton;
	private JTextArea searchResult;

	private JScrollPane searchScroll;

	public Addressbookgui() {
		JFrame frame = new JFrame("Address Book");
		frame.setSize(940, 900);
		frame.setLocationRelativeTo(null); // if null, center the window
											// relative to screen
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		GridBagLayout layoutMgr = new GridBagLayout();
		GridBagConstraints layout = new GridBagConstraints(); // configuration
																// constants for
																// GridBagLayout

		JPanel panel = new JPanel(layoutMgr); // create JPanel and use requested
												// layout manager

		Insets padding = new Insets(10, 10, 10, 10); // set padding
														// top/left/bottom/right
		layout.insets = padding;

		searchLabel = new JLabel("Enter a last name: ");
		searchField = new JTextField(20);
		searchButton = new JButton("Search");
		searchResult = new JTextArea();
		searchScroll = new JScrollPane(searchResult); // make the search result
														// field scrollable

		layout.gridy = 1; // grid position y=0 (vertical)
		layout.gridx = 1; // grid position x=0 (horizontal)
		panel.add(searchLabel, layout); // place this component at x=0/y=0

		layout.gridx++; // grid position x=1 (horizontal)
		layout.fill = GridBagConstraints.HORIZONTAL; // if field is smaller than
														// area, fill
														// horizontaly
		layout.weightx = 1; // give all the extra space to this component
		panel.add(searchField, layout); // place this component at x=1/y=0

		layout.gridx++; // grid position x=2 (horizontal)
		layout.fill = GridBagConstraints.NONE; // if field smaller than area, do
												// not fill
		layout.weightx = 0; // do not give this field any additional space
		panel.add(searchButton, layout); // place this component at x=2/y=0

		frame.add(panel, BorderLayout.NORTH); // add the panel to top of frame
		frame.add(searchScroll); // add the scrollable result field
		frame.setVisible(true);

		searchButton.addActionListener(this); // register an actionListener for
												// button

	}

	// -------------------------------------------------------------------------
	// Define an actionPerformed method that will be called by JVM when action
	// is performed
	// Method receives an event object
	// -------------------------------------------------------------------------
	public void actionPerformed(ActionEvent evt) {

		Object source = evt.getSource(); // get a reference to the object
											// that caused the event

		// System.out.println(evt.getSource());

		if (source == searchButton) {

			String searchName = searchField.getText();
			System.out.println(searchName);
			String stringResult = Controller.findLname(searchName);
			searchResult.setText(null);

			searchResult.setText(stringResult);

		}

	}

}
