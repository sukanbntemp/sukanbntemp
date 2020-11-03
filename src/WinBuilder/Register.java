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
import java.awt.Component;
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

public class Register extends JFrame {

	private JPanel contentPane;
	private JTextField textUserName;
	private JTextField textField;
	private int xx;
	private int xy;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;

	/**
	 * Launch the application.
	 */
	public static void Register() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register frame = new Register();
					frame.setUndecorated(true);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Register() {
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 419);
		contentPane = new JPanel();
		contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e2) {

				xx = e2.getX();
				xy = e2.getY();

			}
		});
		contentPane.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg1) {

				int x = arg1.getXOnScreen();
				int y = arg1.getYOnScreen();
				Register.this.setLocation(x - xx, y - xy);
			}
		});
		contentPane.setBackground(SystemColor.window);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(5, 5, 335, 422);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel labelSukanBN = new JLabel("New label");
		labelSukanBN.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				xx = e.getX();
				xy = e.getY();

			}
		});
		labelSukanBN.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {

				int x = arg0.getXOnScreen();
				int y = arg0.getYOnScreen();
				Register.this.setLocation(x - xx, y - xy);

			}
		});
		labelSukanBN.setBounds(-12, 0, 524, 490);
		labelSukanBN.setVerticalAlignment(SwingConstants.TOP);
		panel.add(labelSukanBN);
		labelSukanBN.setIcon(new ImageIcon(Register.class.getResource("/resources/Asset 1.png")));

		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.setFont(new Font("SUNDAY Personal use", Font.PLAIN, 16));
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection con = null;
				PreparedStatement checkuser = null, ps = null;
				ResultSet rs = null;
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sukanbn", "root", "");
					String errors = "";
					checkuser = con.prepareStatement("select username from users");
					rs = checkuser.executeQuery();
					while (rs.next()) {
						if (textUserName.getText().equals(rs.getString("username"))) {
							errors += "Username is taken.\n";
							break;
						}
					}
					if (!textField.getText().matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")) {
						errors += "Invalid email address.\n";
					}
					if (!Arrays.equals(passwordField.getPassword(), passwordField_1.getPassword())) {
						errors += "Password does not match.";
					}
					if (errors != "") {
						JOptionPane.showMessageDialog(null, errors);
					} else {
						ps = con.prepareStatement("insert into users(username, email, password) values ('"
								+ textUserName.getText() + "', '" + textField.getText() + "', '"
								+ String.valueOf(passwordField.getPassword()) + "')");
						ps.executeUpdate();
						JOptionPane.showMessageDialog(null, "Account has been registered.");
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				} finally {
					try {
						rs.close();
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					try {
						checkuser.close();
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
			}

		});
		btnSignUp.setBackground(new Color(241, 57, 83));
		btnSignUp.setForeground(new Color(0, 0, 0));
		btnSignUp.setBounds(377, 348, 99, 27);
		contentPane.add(btnSignUp);

		textUserName = new JTextField();
		textUserName.setBounds(377, 89, 289, 31);
		contentPane.add(textUserName);
		textUserName.setColumns(10);

		JLabel lblNewLabel = new JLabel("USERNAME");
		lblNewLabel.setFont(new Font("SUNDAY Personal use", Font.PLAIN, 15));
		lblNewLabel.setBounds(377, 64, 78, 14);
		contentPane.add(lblNewLabel);

		JLabel lblEmail = new JLabel("EMAIL");
		lblEmail.setFont(new Font("SUNDAY Personal use", Font.PLAIN, 15));
		lblEmail.setBounds(377, 131, 78, 14);
		contentPane.add(lblEmail);

		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(377, 156, 289, 31);
		contentPane.add(textField);

		JLabel lblNewLabel_1_1 = new JLabel("PASSWORD");
		lblNewLabel_1_1.setFont(new Font("SUNDAY Personal use", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(377, 198, 132, 14);
		contentPane.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("REPEAT PASSWORD");
		lblNewLabel_1_1_1.setFont(new Font("SUNDAY Personal use", Font.PLAIN, 15));
		lblNewLabel_1_1_1.setBounds(377, 265, 132, 14);
		contentPane.add(lblNewLabel_1_1_1);

		JLabel lbl_close = new JLabel("X");
		lbl_close.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				System.exit(0);
			}
		});
		lbl_close.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_close.setForeground(new Color(241, 57, 83));
		lbl_close.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_close.setBounds(658, 11, 46, 14);
		contentPane.add(lbl_close);

		passwordField = new JPasswordField();
		passwordField.setBounds(377, 223, 289, 31);
		contentPane.add(passwordField);

		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(377, 290, 289, 31);
		contentPane.add(passwordField_1);

		JButton btnLogIn = new JButton("Log In");
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main login = new Main();
				login.main(null);
				Register.this.dispose();
			}
		});
		btnLogIn.setForeground(Color.BLACK);
		btnLogIn.setFont(new Font("SUNDAY Personal use", Font.PLAIN, 16));
		btnLogIn.setBackground(new Color(0, 255, 127));
		btnLogIn.setBounds(567, 348, 99, 27);
		contentPane.add(btnLogIn);
	}
}
