import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;

import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RentPanel {

	public JFrame frame;
	private JTextField txtCarId;
	private JTextField txtCustId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RentPanel window = new RentPanel();
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
	public RentPanel() {
		initialize();
		Connect();
		tablecust_load();
		tablecars_load();
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTable tablecust;
	private JTable tablecars;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JButton btnNewButton_1;
 
	public void Connect()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/rentcar_db", "root","password");
        }
        catch (ClassNotFoundException ex) 
        {
          ex.printStackTrace();
        }
        catch (SQLException ex) 
        {
        	   ex.printStackTrace();
        }

    }
	
	  public void tablecust_load()
	    {
	    	try 
	    	{
		    pst = con.prepareStatement("SELECT * from customers");
		    rs = pst.executeQuery();
		    tablecust.setModel(DbUtils.resultSetToTableModel(rs));
		} 
	    	catch (SQLException e) 
	    	 {
	    		e.printStackTrace();
		  } 
	    }
	  
	  public void tablecars_load()
	    {
	    	try 
	    	{
		    pst = con.prepareStatement("SELECT carid as Car_ID, brand, model, status, costperday from cars where status = \"available\"; ");
		    rs = pst.executeQuery();
		    tablecars.setModel(DbUtils.resultSetToTableModel(rs));
		} 
	    	catch (SQLException e) 
	    	 {
	    		e.printStackTrace();
		  } 
	    }
	  

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setBounds(100, 100, 828, 527);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 48, 365, 215);
		frame.getContentPane().add(scrollPane);
		
		tablecust = new JTable();
		tablecust.setBackground(Color.WHITE);
		scrollPane.setViewportView(tablecust);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(405, 48, 397, 215);
		frame.getContentPane().add(scrollPane_1);
		
		tablecars = new JTable();
		scrollPane_1.setViewportView(tablecars);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Rent A Car", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(282, 288, 217, 156);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Car ID");
		lblNewLabel.setBounds(45, 32, 46, 14);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setForeground(Color.WHITE);
		
		JLabel lblCustomerId = new JLabel("Customer ID");
		lblCustomerId.setBounds(10, 74, 71, 14);
		panel.add(lblCustomerId);
		lblCustomerId.setForeground(Color.WHITE);
		lblCustomerId.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		txtCustId = new JTextField();
		txtCustId.setBounds(101, 72, 86, 20);
		panel.add(txtCustId);
		txtCustId.setColumns(10);
		
		JButton btnNewButton = new JButton("Book now");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String carId, custId;
				
				carId = txtCarId.getText();
				custId = txtCustId.getText();
				
				 try {
						pst = con.prepareStatement("UPDATE cars SET custid = ?, lastrentdate = now(), status = \"booked\" WHERE carid = ?;");
						pst.setString(1, custId);
						pst.setString(2, carId);
						pst.executeUpdate();
						JOptionPane.showMessageDialog(null, "Successfully Booked!");
						tablecust_load();
						tablecars_load();
							           
			            txtCarId.setText("");
						txtCustId.setText("");
					   }
				 
					catch (SQLException e1) 
				        {
										
					e1.printStackTrace();
					}
				
			}
		});
		btnNewButton.setBounds(61, 122, 97, 23);
		panel.add(btnNewButton);
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(30, 144, 255));
		
		txtCarId = new JTextField();
		txtCarId.setBounds(101, 30, 86, 20);
		panel.add(txtCarId);
		txtCarId.setColumns(10);
		
		lblNewLabel_1 = new JLabel("Registered Customers");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(126, 28, 129, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Available Cars");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(561, 28, 91, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dashboard dashb = new dashboard();
				dashb.frame.setVisible(true);
				frame.dispose();
				
			}
		});
		btnNewButton_1.setBounds(10, 454, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
	}
}
