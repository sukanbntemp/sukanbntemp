package WinBuilder;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.AbstractListModel;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListCellRenderer;

import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;
import javax.swing.JInternalFrame;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import java.sql.Date;

public class Slot {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void slotPage() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Slot window = new Slot();
					window.frame.setUndecorated(true);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Slot() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 720, 419);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblClose = new JLabel("X");
		lblClose.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		lblClose.setHorizontalAlignment(SwingConstants.CENTER);
		lblClose.setForeground(Color.RED);
		lblClose.setBounds(668, 3, 46, 14);
		frame.getContentPane().add(lblClose);

		JPanel facilityPanel = new JPanel();
		facilityPanel.setBounds(10, 11, 663, 56);
		frame.getContentPane().add(facilityPanel);
		facilityPanel.setLayout(null);

		JLabel lblSelectFacility = new JLabel("Select Facility:");
		lblSelectFacility.setBounds(10, 11, 101, 34);
		lblSelectFacility.setFont(new Font("SUNDAY Personal use", Font.PLAIN, 14));
		facilityPanel.add(lblSelectFacility);

		Connection con = null;
		PreparedStatement getValues = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sukanbn", "root", "");
			getValues = con.prepareStatement("select facilityType from facility");
			rs = getValues.executeQuery();

			ArrayList<String> facilities = new ArrayList<String>();
			while (rs.next()) {
				facilities.add(rs.getString("facilityType"));
			}
			JComboBox facilitySelection = new JComboBox(facilities.toArray());
			facilitySelection.setSelectedIndex(-1);
			facilitySelection.setBackground(Color.WHITE);
			facilitySelection.setFont(new Font("SUNDAY Personal use", Font.PLAIN, 14));
			facilitySelection.setBounds(102, 11, 551, 34);
			facilityPanel.add(facilitySelection);

			JPanel datePanel = new JPanel();
			datePanel.setBounds(10, 78, 663, 56);
			frame.getContentPane().add(datePanel);
			datePanel.setLayout(null);

			JLabel lblSelectDate = new JLabel("Select Date:");
			lblSelectDate.setFont(new Font("SUNDAY Personal use", Font.PLAIN, 14));
			lblSelectDate.setBounds(10, 11, 101, 34);
			datePanel.add(lblSelectDate);

			JDateChooser dateChooser = new JDateChooser();
			dateChooser.setFont(new Font("SUNDAY Personal use", Font.PLAIN, 14));
			dateChooser.setBounds(102, 11, 258, 34);
			datePanel.add(dateChooser);

			JPanel sessionPanel = new JPanel();
			sessionPanel.setBounds(10, 145, 663, 56);
			frame.getContentPane().add(sessionPanel);
			sessionPanel.setLayout(null);

			JLabel lblSelectSession = new JLabel("Select Session:");
			lblSelectSession.setFont(new Font("SUNDAY Personal use", Font.PLAIN, 14));
			lblSelectSession.setBounds(10, 11, 101, 34);
			sessionPanel.add(lblSelectSession);

			JRadioButtonMenuItem rdbtnmntmNewRadioItem = new JRadioButtonMenuItem("Morning");
			rdbtnmntmNewRadioItem.setFont(new Font("SUNDAY Personal use", Font.PLAIN, 14));
			rdbtnmntmNewRadioItem.setBounds(106, 17, 133, 26);
			rdbtnmntmNewRadioItem.setActionCommand(rdbtnmntmNewRadioItem.getText());
			sessionPanel.add(rdbtnmntmNewRadioItem);

			JRadioButtonMenuItem rdbtnmntmNewRadioItem_1 = new JRadioButtonMenuItem("Afternoon");
			rdbtnmntmNewRadioItem_1.setFont(new Font("SUNDAY Personal use", Font.PLAIN, 14));
			rdbtnmntmNewRadioItem_1.setBounds(249, 17, 133, 26);
			rdbtnmntmNewRadioItem_1.setActionCommand(rdbtnmntmNewRadioItem_1.getText());
			sessionPanel.add(rdbtnmntmNewRadioItem_1);

			ButtonGroup group = new ButtonGroup();
			group.add(rdbtnmntmNewRadioItem);
			group.add(rdbtnmntmNewRadioItem_1);

			JPanel slotPanel = new JPanel();
			slotPanel.setBounds(10, 212, 663, 56);
			frame.getContentPane().add(slotPanel);
			slotPanel.setLayout(null);

			JLabel lblSelectSlot = new JLabel("Select Slot:");
			lblSelectSlot.setFont(new Font("SUNDAY Personal use", Font.PLAIN, 14));
			lblSelectSlot.setBounds(10, 11, 101, 34);
			slotPanel.add(lblSelectSlot);

			JComboBox timeSelection = new JComboBox();

			rdbtnmntmNewRadioItem.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					timeSelection.removeAllItems();
					timeSelection.addItem("8am - 9am");
					timeSelection.addItem("9am - 10am");
					timeSelection.addItem("10am - 11am");
					timeSelection.addItem("11am - 12pm");

				}
			});
			rdbtnmntmNewRadioItem_1.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {

					timeSelection.removeAllItems();
					timeSelection.addItem("2pm - 3pm");
					timeSelection.addItem("3pm - 4pm");
					timeSelection.addItem("4pm - 5pm");
					timeSelection.addItem("5pm - 6pm");
					timeSelection.addItem("6pm - 7pm");
					timeSelection.addItem("7pm - 8pm");
					timeSelection.addItem("8pm - 9pm");
					timeSelection.addItem("9pm - 10pm");

				}

			});

			timeSelection.setBackground(Color.WHITE);
			timeSelection.setFont(new Font("SUNDAY Personal use", Font.PLAIN, 14));
			timeSelection.setBounds(102, 12, 258, 34);
			slotPanel.add(timeSelection);

			JButton btnSubmit = new JButton("Submit");
			btnSubmit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Connection con_1 = null;
					PreparedStatement submitReservation = null;

					LocalDate convertDate = dateChooser.getDate().toInstant().atZone(ZoneId.of("Asia/Shanghai")).toLocalDate();
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						con_1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/sukanbn", "root", "");
						submitReservation = con_1.prepareStatement(
								"insert into reservations(userID, facilityNum, date, session, time) values ('"
										+ Users.getUserID() + "', '" + facilitySelection.getSelectedIndex() + "', '"
										+ convertDate + "', '" + group.getSelection().getActionCommand()
										+ "', '" + timeSelection.getSelectedItem() + "')");
						
						submitReservation.executeUpdate();
						
						JOptionPane.showMessageDialog(null, "Reservation has been made.");
						
						UserProfile refresh = new UserProfile();
						refresh.userProfile();
						Slot.this.frame.dispose();
						
					} catch (SQLException | ClassNotFoundException e1) {
						e1.printStackTrace();
					} finally {
						try {
							submitReservation.close();
						} catch (Exception ex) {
							ex.printStackTrace();
						}
						try {
							con_1.close();
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
				}

			});
			btnSubmit.setFont(new Font("SUNDAY Personal use", Font.PLAIN, 13));
			btnSubmit.setBounds(291, 37, 89, 23);

			JPanel submitPanel = new JPanel();
			submitPanel.add(btnSubmit);
			submitPanel.setBounds(10, 279, 663, 90);
			frame.getContentPane().add(submitPanel);
			submitPanel.setLayout(null);

		} catch (ClassNotFoundException |

				SQLException e1) {
			e1.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			try {
				getValues.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			try {
				con.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		JLabel lblBgPhoto_1 = new JLabel("");
		lblBgPhoto_1.setIcon(new ImageIcon(Slot.class.getResource("/resources/Asset 1.png")));
		lblBgPhoto_1.setBounds(241, -33, 704, 413);
		frame.getContentPane().add(lblBgPhoto_1);

	}
}