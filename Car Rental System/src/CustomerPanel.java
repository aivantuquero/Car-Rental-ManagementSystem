import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import java.awt.Color;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JScrollPane;

public class CustomerPanel {

	public JFrame frame;
	private JTextField txtName;
	private JTextField txtContact;
	private JTextField txtAddress;
	private JTextField txtId;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerPanel window = new CustomerPanel();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public CustomerPanel() {
		initialize();
		Connect();
		table_load();
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
 
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
	
	  public void table_load()
	    {
	    	try 
	    	{
		    pst = con.prepareStatement("select custid as Customer_id, name, contact, address from customers");
		    rs = pst.executeQuery();
		    table.setModel(DbUtils.resultSetToTableModel(rs));
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
		frame.setBounds(100, 100, 909, 546);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Personal Information", TitledBorder.LEFT, TitledBorder.TOP, null, Color.WHITE));
		panel.setBounds(470, 79, 372, 365);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(23, 50, 44, 14);
		panel.add(lblNewLabel);
		
		txtName = new JTextField();
		txtName.setBounds(104, 50, 227, 20);
		panel.add(txtName);
		txtName.setColumns(10);
		
		JLabel lettercontact = new JLabel("Contact No.");
		lettercontact.setForeground(Color.WHITE);
		lettercontact.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lettercontact.setBounds(23, 98, 98, 14);
		panel.add(lettercontact);
		
		txtContact = new JTextField();
		txtContact.setColumns(10);
		txtContact.setBounds(104, 98, 227, 20);
		panel.add(txtContact);
		
		JLabel letteraddress = new JLabel("Address");
		letteraddress.setForeground(Color.WHITE);
		letteraddress.setFont(new Font("Tahoma", Font.PLAIN, 14));
		letteraddress.setBounds(23, 151, 62, 14);
		panel.add(letteraddress);
		
		txtAddress = new JTextField();
		txtAddress.setColumns(10);
		txtAddress.setBounds(104, 151, 227, 20);
		panel.add(txtAddress);
		
		
		JButton saveBtn = new JButton("Save");
		saveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String name, contact, address;
				
				name = txtName.getText();
				contact = txtContact.getText();
				address = txtAddress.getText();
				
				 try {
						pst = con.prepareStatement("insert into customers(name,contact,address)values(?,?,?)");
						pst.setString(1, name);
						pst.setString(2, contact);
						pst.setString(3, address);
						pst.executeUpdate();
						JOptionPane.showMessageDialog(null, "Record Addedddd!!!!!");
						table_load();
							           
						txtName.setText("");
						txtContact.setText("");
						txtAddress.setText("");
						txtName.requestFocus();
					   }
				 
					catch (SQLException e1) 
				        {
										
					e1.printStackTrace();
					}
				
				
				
				
			}
		});
		
		
		
		
		
		
		saveBtn.setForeground(new Color(255, 255, 255));
		saveBtn.setBackground(new Color(30, 144, 255));
		saveBtn.setBounds(242, 207, 89, 23);
		panel.add(saveBtn);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(23, 258, 196, 80);
		panel.add(panel_1);
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Search", TitledBorder.RIGHT, TitledBorder.TOP, null, Color.WHITE));
		panel_1.setBackground(Color.DARK_GRAY);
		panel_1.setLayout(null);
		
		txtId = new JTextField();
		txtId.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				
				 try {
			          
			                String id = txtId.getText();

			                pst = con.prepareStatement("select name, contact, address from customers where custid = ?");
			                pst.setString(1, id);
			                ResultSet rs = pst.executeQuery();

			            if(rs.next()==true)
			            {
			              
			                String name = rs.getString(1);
			                String contact = rs.getString(2);
			                String address = rs.getString(3);
			                
			                txtName.setText(name);
			                txtContact.setText(contact);
			                txtAddress.setText(address);
			                
			                
			            }   
			            else
			            {
			            	txtName.setText("");
			            	txtContact.setText("");
			                txtAddress.setText("");
			                 
			            }
			            


			        } 
				
				 catch (SQLException ex) {
			           
			        }
				
				
				
				
			}
		});
		txtId.setBounds(21, 38, 151, 20);
		panel_1.add(txtId);
		txtId.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Customer ID");
		lblNewLabel_1.setBounds(21, 21, 78, 17);
		panel_1.add(lblNewLabel_1);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton DeleteBtn = new JButton("Delete");
		DeleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
						
			    String custid;
			    
				custid  = txtId.getText();
				
				 try {
						pst = con.prepareStatement("delete from customers where custid =?");
				
			            pst.setString(1, custid);
			            pst.executeUpdate();
			            JOptionPane.showMessageDialog(null, "Record Deleted");
			            table_load();
			           
			            txtName.setText("");
			            txtContact.setText("");
			            txtAddress.setText("");
			            txtName.requestFocus();
					}
			
			        catch (SQLException e1) {
						
						e1.printStackTrace();
					}
						
			}
		});
		DeleteBtn.setBounds(44, 207, 89, 23);
		panel.add(DeleteBtn);
		
		JButton updateBtn = new JButton("Update");
		
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String name, contact, custId, address;
				
				
				name = txtName.getText();
				contact= txtContact.getText();
				address = txtAddress.getText();
				custId  = txtId.getText();
				
				 try {
						pst = con.prepareStatement("update customers set name= ?,contact=?,address=? where custid =?");
						pst.setString(1, name);
			            pst.setString(2, contact);
			            pst.setString(3, address);
			            pst.setString(4, custId);
			            pst.executeUpdate();
			            JOptionPane.showMessageDialog(null, "Record Updated!");
			            table_load();
			           
			            txtName.setText("");
			            txtContact.setText("");
			            txtAddress.setText("");
			            txtName.requestFocus();
					}
 
		            catch (SQLException e1) {
						
						e1.printStackTrace();
					}
				
				
			}
		});
		updateBtn.setForeground(Color.WHITE);
		updateBtn.setBackground(new Color(30, 144, 255));
		updateBtn.setBounds(143, 207, 89, 23);
		panel.add(updateBtn);
		
		JButton backBtn = new JButton("Back");
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dashboard dashdb = new dashboard();
				
				dashdb.frame.setVisible(true);;
				frame.dispose();
			}
		});
		backBtn.setBounds(20, 473, 89, 23);
		frame.getContentPane().add(backBtn);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(20, 79, 427, 365);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 427, 365);
		panel_2.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_2 = new JLabel("Customer Id             Name                          Contact No.              Address\r\n");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBounds(20, 60, 426, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Manage Customers");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setBounds(544, 29, 202, 36);
		frame.getContentPane().add(lblNewLabel_3);
	}
}
