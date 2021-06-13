import java.awt.EventQueue;
import java.sql.*;


import javax.swing.JFrame;
import javax.swing.JTable;
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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JScrollPane;

public class CarsPanel {

	public JFrame frame;
	private JTable table;
	private JTextField txtBrand;
	private JTextField txtModel;
	private JTextField txtStatus;
	private JTextField txtCost;
	private JPanel panel_1;
	private JTextField txtId;
	private JLabel lblCarId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CarsPanel window = new CarsPanel();
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
	public CarsPanel() {
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
		    pst = con.prepareStatement("SELECT cars.* ,customers.name AS \"Renter's Name\" FROM cars LEFT JOIN customers ON cars.custid = customers.custid;");
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
		frame.setBounds(100, 100, 849, 549);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 57, 813, 268);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Car Information", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		panel.setBounds(370, 336, 453, 163);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Brand");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(10, 29, 47, 14);
		panel.add(lblNewLabel);
		
		txtBrand = new JTextField();
		txtBrand.setBounds(67, 27, 126, 20);
		panel.add(txtBrand);
		txtBrand.setColumns(10);
		
		JLabel lblModel = new JLabel("Model");
		lblModel.setForeground(Color.WHITE);
		lblModel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblModel.setBounds(10, 58, 47, 14);
		panel.add(lblModel);
		
		txtModel = new JTextField();
		txtModel.setColumns(10);
		txtModel.setBounds(67, 56, 126, 20);
		panel.add(txtModel);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setForeground(Color.WHITE);
		lblStatus.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblStatus.setBounds(252, 54, 47, 14);
		panel.add(lblStatus);
		
		txtStatus = new JTextField();
		txtStatus.setColumns(10);
		txtStatus.setBounds(309, 52, 126, 20);
		panel.add(txtStatus);
		
		JLabel lblCostPerDay = new JLabel("Cost Per Day");
		lblCostPerDay.setForeground(Color.WHITE);
		lblCostPerDay.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCostPerDay.setBounds(211, 31, 85, 14);
		panel.add(lblCostPerDay);
		
		txtCost = new JTextField();
		txtCost.setColumns(10);
		txtCost.setBounds(309, 27, 126, 20);
		panel.add(txtCost);
		
		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		panel_1.setBackground(Color.DARK_GRAY);
		panel_1.setBounds(10, 83, 224, 71);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		txtId = new JTextField();
		txtId.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				 try {
			          
		                String id = txtId.getText();

		                pst = con.prepareStatement("select brand, model, status, costperday from cars where carid = ?");
		                pst.setString(1, id);
		                ResultSet rs = pst.executeQuery();

		            if(rs.next()==true)
		            {
		              
		                String brand = rs.getString(1);
		                String model = rs.getString(2);
		                String status = rs.getString(3);
		                String costperday = rs.getString(4);
		                
		                txtBrand.setText(brand);
						txtModel.setText(model);
						txtStatus.setText(status);
						txtCost.setText(costperday);
		                
		                
		            }   
		            else
		            {
		            	    txtBrand.setText("");
							txtModel.setText("");
							txtStatus.setText("");
							txtCost.setText("");
		                 
		            }
		            


		        } 
			
			 catch (SQLException ex) {
		           
		        }
				
			}
		});
		txtId.setColumns(10);
		txtId.setBounds(67, 29, 126, 20);
		panel_1.add(txtId);
		
		lblCarId = new JLabel("Car ID");
		lblCarId.setForeground(Color.WHITE);
		lblCarId.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCarId.setBounds(10, 31, 47, 14);
		panel_1.add(lblCarId);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String brand, model, status, costperday;
				
				brand = txtBrand.getText();
				model = txtModel.getText();
				status = txtStatus.getText();
				costperday = txtCost.getText();
				
				 try {
						pst = con.prepareStatement("insert into cars(brand,model,status, costperday)values(?,?,?,?)");
						pst.setString(1, brand);
						pst.setString(2, model);
						pst.setString(3, status);
						pst.setString(4, costperday);
						pst.executeUpdate();
						JOptionPane.showMessageDialog(null, "Record Added!");
						table_load();
							           
			            txtBrand.setText("");
						txtModel.setText("");
						txtStatus.setText("");
						txtCost.setText("");
						txtId.setText("");
						txtBrand.requestFocus();
					   }
				 
					catch (SQLException e1) 
				        {
										
					e1.printStackTrace();
					}
				
			}
		});
		btnSave.setBackground(new Color(30, 144, 255));
		btnSave.setForeground(Color.WHITE);
		btnSave.setBounds(244, 92, 89, 23);
		panel.add(btnSave);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String brand, model, status, costperday, carid;
				
				brand = txtBrand.getText();
				model = txtModel.getText();
				status = txtStatus.getText();
				costperday = txtCost.getText();
				carid = txtId.getText();
				
				
				 try {
						pst = con.prepareStatement("update cars set brand= ?,model=?,status=?,costperday=? where carid =?");
						pst.setString(1, brand);
			            pst.setString(2, model);
			            pst.setString(3, status);
			            pst.setString(4, costperday);
			            pst.setString(5, carid);
			            pst.executeUpdate();
			            JOptionPane.showMessageDialog(null, "Record Updated!");
			            table_load();
			           
			            txtBrand.setText("");
						txtModel.setText("");
						txtStatus.setText("");
						txtCost.setText("");
						txtId.setText("");
						txtBrand.requestFocus();
					}
 
		            catch (SQLException e1) {
						
						e1.printStackTrace();
					}
				
				
			}
		});
		btnUpdate.setForeground(Color.WHITE);
		btnUpdate.setBackground(new Color(30, 144, 255));
		btnUpdate.setBounds(244, 126, 89, 23);
		panel.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			    String carid;
			    
				carid  = txtId.getText();
				
				 try {
						pst = con.prepareStatement("delete from cars where carid =?");
				
			            pst.setString(1, carid);
			            pst.executeUpdate();
			            JOptionPane.showMessageDialog(null, "Record Deleted");
			            table_load();
			           
			            txtBrand.setText("");
						txtModel.setText("");
						txtStatus.setText("");
						txtCost.setText("");
						txtId.setText("");
						txtBrand.requestFocus();
					}
			
			        catch (SQLException e1) {
						
						e1.printStackTrace();
					}
				
			}
		});
		btnDelete.setForeground(Color.BLACK);
		btnDelete.setBackground(Color.WHITE);
		btnDelete.setBounds(343, 92, 89, 23);
		panel.add(btnDelete);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dashboard dashb = new dashboard();
				
				dashb.frame.setVisible(true);
				frame.dispose();
				
			}
		});
		btnBack.setForeground(Color.BLACK);
		btnBack.setBackground(Color.WHITE);
		btnBack.setBounds(10, 476, 89, 23);
		frame.getContentPane().add(btnBack);
		
		JLabel lblNewLabel_1 = new JLabel("Manage Cars");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBackground(Color.BLACK);
		lblNewLabel_1.setBounds(354, 23, 132, 23);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Mark As Available");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String carid;
				carid = txtId.getText();
				
				
				 try {
						pst = con.prepareStatement("update cars set status=?, custid = NULL where carid =?");
						pst.setString(1, "available");
			            pst.setString(2, carid);
			            pst.executeUpdate();
			            JOptionPane.showMessageDialog(null, "The car has been successfully marked as available!");
			            table_load();
			           
			            txtBrand.setText("");
						txtModel.setText("");
						txtStatus.setText("");
						txtCost.setText("");
						txtId.setText("");
						txtBrand.requestFocus();
					}
 
		            catch (SQLException e1) {
						
						e1.printStackTrace();
					}
				
			}
		});
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(30, 144, 255));
		btnNewButton.setBounds(194, 476, 166, 23);
		frame.getContentPane().add(btnNewButton);
	}
}
