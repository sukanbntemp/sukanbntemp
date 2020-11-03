package WinBuilder;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;

import javax.swing.JPasswordField;

public class Main {

	private JFrame frame;
	private int xx;
	private int xy;
	private JTextField textField;
	private JPasswordField passwordField;
	private static String userID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
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
	public Main() {
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
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBounds(0, 0, 706, 382);
		frame.getContentPane().add(mainPanel);
		mainPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Main.class.getResource("/resources/Asset 1.png")));
		lblNewLabel.setBounds(-43, -18, 465, 503);
		mainPanel.add(lblNewLabel);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("SUNDAY Personal use", Font.PLAIN, 14));
		lblUsername.setBounds(417, 103, 85, 13);
		mainPanel.add(lblUsername);
		
		textField = new JTextField();
		textField.setBounds(417, 126, 279, 33);
		mainPanel.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(417, 197, 279, 33);
		mainPanel.add(passwordField);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("SUNDAY Personal use", Font.PLAIN, 14));
		lblPassword.setBounds(417, 174, 85, 13);
		mainPanel.add(lblPassword);
		
		JButton btnSignUp = new JButton("Register");
		btnSignUp.setFont(new Font("SUNDAY Personal use", Font.PLAIN,16));
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Register register = new Register();
				register.Register();;
				Main.this.frame.dispose();
			}
		});
		btnSignUp.setBackground(new Color(241, 57, 83));
		btnSignUp.setForeground(new Color(0,0,0));
		btnSignUp.setBounds(417, 297, 99, 27);
		mainPanel.add(btnSignUp);
		
		JButton btnLogIn = new JButton("Log In");
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection con = null;
				PreparedStatement getuser = null;
				ResultSet rs = null;
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sukanbn", "root", "");
					String errors = "";
					getuser = con.prepareStatement("select userID, password from users WHERE username = ('"
									+ textField.getText() + "')");
					rs = getuser.executeQuery();
					if(rs.next() == false) {
						errors += "Username is not registered.\n";
					} else if (!Arrays.equals(passwordField.getPassword(), rs.getString("password").toCharArray())) {
						errors += "Password does not match.";
					}
					if (errors != "") {
						JOptionPane.showMessageDialog(null, errors);
					} else {
						Users.setUserID(rs.getString("userID"));
						UserProfile profile = new UserProfile();
						profile.userProfile();
						Main.this.frame.dispose();
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				} finally {
					try {
						rs.close();
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					try {
						getuser.close();
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					try {
						con.close();
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}

		});
		btnLogIn.setForeground(Color.BLACK);
		btnLogIn.setFont(new Font("SUNDAY Personal use", Font.PLAIN, 16));
		btnLogIn.setBackground(new Color(0, 255, 12));
		btnLogIn.setBounds(597, 297, 99, 27);
		mainPanel.add(btnLogIn);
		mainPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e2) {
				
				xx = e2.getX();
				xy = e2.getY();
				
			}
		});
		mainPanel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg1) {
				
				int x = arg1.getXOnScreen();
				int y = arg1.getYOnScreen();
				//Main.this.setLocation(x - xx, y - xy);
			}
		});
	}
	
}
