package WinBuilder;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class UserProfile {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void userProfile() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserProfile window = new UserProfile();
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
	public UserProfile() {
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

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 704, 41);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblWelcome = new JLabel("Welcome, ");
		lblWelcome.setFont(new Font("SUNDAY Personal use", Font.PLAIN, 14));
		lblWelcome.setBounds(10, 0, 100, 41);
		panel.add(lblWelcome);

		// placeholder for name
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sukanbn", "root", "");
			ps = con.prepareStatement("select username from users WHERE userID = ('" + Users.getUserID() + "')");
			rs = ps.executeQuery();

			rs.next();
			String name = rs.getString("username");
			JLabel lblUserName = new JLabel(name + ".");
			lblUserName.setFont(new Font("SUNDAY Personal use", Font.PLAIN, 14));
			lblUserName.setBounds(74, 0, 106, 41);
			panel.add(lblUserName);

		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			try {
				ps.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			try {
				con.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			JButton btnNewButton = new JButton("Log Out");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Main login = new Main();
					login.main(null);;
					UserProfile.this.frame.dispose();
				}
			});
			btnNewButton.setFont(new Font("SUNDAY Personal use", Font.PLAIN, 12));
			btnNewButton.setBounds(612, 9, 82, 23);
			panel.add(btnNewButton);

			JLabel lblBgPhoto_1 = new JLabel("");
			lblBgPhoto_1.setBounds(-162, -208, 651, 449);
			panel.add(lblBgPhoto_1);

			JPanel dashboardPanel = new JPanel();
			dashboardPanel.setBounds(0, 29, 704, 340);
			frame.getContentPane().add(dashboardPanel);
			dashboardPanel.setLayout(null);

			JButton btnBook = new JButton("Book Slot");
			btnBook.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Slot slot = new Slot();
					slot.slotPage();
					UserProfile.this.frame.dispose();
				}
			});

			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sukanbn", "root", "");
				ps = con.prepareStatement(
						"SELECT facility.facilityType, facility.price, reservations.date, reservations.session, reservations.time FROM reservations INNER JOIN facility ON facility.facilityNum=reservations.facilityNum WHERE userID = '"
								+ Users.getUserID() + "'");
				rs = ps.executeQuery();
				Reservations userReservations = new Reservations(null, null, null, null, 0);

				String sv = null;
				ArrayList<String> savedArr = new ArrayList<String>();
				while (rs.next()) {
					userReservations.setDate(rs.getDate("reservations.date"));
					userReservations.setFacilityType(rs.getString("facility.facilityType"));
					userReservations.setPrice(rs.getDouble("facility.price"));
					userReservations.setSession(rs.getString("reservations.session"));
					userReservations.setTime(rs.getString("reservations.time"));

					sv = userReservations.savedValue();
					savedArr.add(sv);
				}

				String[] ar = new String[savedArr.size()];
				ar = savedArr.toArray(ar);
				JList list = new JList(ar);
				list.setFont(new Font("SUNDAY Personal use", Font.PLAIN, 14));
				list.setBounds(10, 62, 682, 148);
				dashboardPanel.add(list);
				btnBook.setFont(new Font("SUNDAY Personal use", Font.PLAIN, 14));
				btnBook.setBounds(78, 253, 171, 44);
				dashboardPanel.add(btnBook);

				JButton btnCancel = new JButton("Cancel Booking");
				btnCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String tempFacility = null, tempDate = null, tempTime = null, tempSession = null,
								tempPrice = null;
						String[] array = list.getSelectedValue().toString().trim().split("\\s*\\|\\s*");
						for (int i = 0; i < 5; i++) {
							tempFacility = array[0];
							tempDate = array[1];
							tempTime = array[2];
							tempSession = array[3];
							tempPrice = array[4];
						}

						Connection con_1 = null;
						PreparedStatement ps_1 = null;
						try {
							Class.forName("com.mysql.cj.jdbc.Driver");
							con_1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/sukanbn", "root", "");
							ps_1 = con_1.prepareStatement(
									"DELETE reservations.* FROM reservations INNER JOIN facility ON reservations.facilityNum = facility.facilityNum WHERE reservations.date = '"
											+ tempDate + "' AND reservations.time = '"
											+ tempTime + "' AND facility.facilityType = '"
											+ tempFacility + "'");
							ps_1.executeUpdate();
							JOptionPane.showMessageDialog(null, "Reservation has been cancelled.");
							
							UserProfile refresh = new UserProfile();
							refresh.userProfile();
							UserProfile.this.frame.dispose();
							
						} catch (ClassNotFoundException | SQLException e1) {
							e1.printStackTrace();
						} finally {
							try {
								ps_1.close();
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
				btnCancel.setFont(new Font("SUNDAY Personal use", Font.PLAIN, 14));
				btnCancel.setBounds(480, 253, 171, 44);
				dashboardPanel.add(btnCancel);

			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			} finally {
				try {
					rs.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				try {
					ps.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				try {
					con.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

			JLabel lblNewLabel = new JLabel("Reservation Status:");
			lblNewLabel.setFont(new Font("SUNDAY Personal use", Font.PLAIN, 14));
			lblNewLabel.setBounds(10, 37, 124, 14);
			dashboardPanel.add(lblNewLabel);

			JLabel lblBgPhoto = new JLabel("");
			lblBgPhoto.setBounds(242, -38, 651, 449);
			dashboardPanel.add(lblBgPhoto);
			lblBgPhoto.setIcon(new ImageIcon(UserProfile.class.getResource("/resources/Asset 1.png")));
		}
	}
}
