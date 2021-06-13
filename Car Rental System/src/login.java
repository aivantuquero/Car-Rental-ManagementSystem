import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class login {

	public JFrame frame;
	private JTextField txtusername;
	private JTextField txtpassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login window = new login();
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
	public login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setBounds(100, 100, 449, 381);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel("Car Rental System Login");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblNewLabel.setBounds(87, 52, 237, 23);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(112, 137, 63, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setBounds(112, 185, 63, 14);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		txtusername = new JTextField();
		txtusername.setBounds(178, 134, 116, 20);
		frame.getContentPane().add(txtusername);
		txtusername.setColumns(10);
		
		txtpassword = new JTextField();
		txtpassword.setColumns(10);
		txtpassword.setBounds(178, 182, 116, 20);
		frame.getContentPane().add(txtpassword);
		
		JButton loginBtn = new JButton("Login");
		loginBtn.setForeground(Color.WHITE);
		loginBtn.setBackground(new Color(0, 191, 255));
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String username, password;
				
				username = txtusername.getText();
				password = txtpassword.getText();
				
				if(username.equals("admin") && password.equals("admin")) {
					
					dashboard dashb = new dashboard();
					dashb.frame.setVisible(true);
					frame.dispose();
					
					System.out.println("success");
				}
				else {
					JOptionPane.showMessageDialog(null, "Invalid credentials!");
					
				}
				
			}
		});
		loginBtn.setBounds(152, 233, 89, 23);
		frame.getContentPane().add(loginBtn);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBounds(126, 86, 171, 14);
		frame.getContentPane().add(lblNewLabel_2);
	}
}
