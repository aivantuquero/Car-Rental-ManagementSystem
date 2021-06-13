import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class dashboard {

	public JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					dashboard window = new dashboard();
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
	public dashboard() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 542, 443);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		
		JLabel lblNewLabel = new JLabel("Car Rental System Dashboard");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 25));
		lblNewLabel.setBounds(66, 26, 400, 77);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnCust = new JButton("Manage Customers");
		btnCust.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				CustomerPanel cP = new CustomerPanel();
				
				cP.frame.setVisible(true);;
				frame.dispose();
				
			}
		});
		btnCust.setBackground(new Color(30, 144, 255));
		btnCust.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnCust.setForeground(Color.WHITE);
		btnCust.setBounds(30, 114, 135, 63);
		frame.getContentPane().add(btnCust);
		
		JLabel label = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/bluecar.png")).getImage();
		Image newImage = img.getScaledInstance(325, 300, Image.SCALE_DEFAULT);
		label.setIcon(new ImageIcon(newImage));
		
		label.setBounds(185, 103, 331, 290);
		frame.getContentPane().add(label);
		
		JButton btnCars = new JButton("Manage Cars");
		btnCars.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				CarsPanel cp = new CarsPanel();
				
				cp.frame.setVisible(true);
				frame.dispose();
				
			}
		});
		btnCars.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnCars.setForeground(new Color(255, 255, 255));
		btnCars.setBackground(new Color(30, 144, 255));
		btnCars.setBounds(30, 209, 135, 63);
		frame.getContentPane().add(btnCars);
		
		JButton btnRent = new JButton("Rent a car");
		btnRent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				RentPanel rp = new RentPanel();
				rp.frame.setVisible(true);
				
				frame.dispose();
				
			}
		});
		btnRent.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnRent.setForeground(new Color(255, 255, 255));
		btnRent.setBackground(new Color(30, 144, 255));
		btnRent.setBounds(30, 299, 135, 63);
		frame.getContentPane().add(btnRent);
	}

}
