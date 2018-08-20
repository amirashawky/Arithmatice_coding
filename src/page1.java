import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;

public class page1 {

	private static JFrame frame;
	private static JFileChooser filechoose;
	private static StringBuilder Builder;
	private String filename ;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					page1 window = new page1();
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
	public page1() {
		initialize();
	}
     
	public static void open() {
		frame.setVisible(true);
	}
     
	/**
	 * Initialise the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 100, 0));
		filechoose = new JFileChooser ();
		Builder = new StringBuilder ();
		frame.setBounds(100, 100, 507, 384);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		

		
		final JTextArea textArea = new JTextArea();
		textArea.setBackground(Color.WHITE);
		textArea.setBounds(10, 63, 276, 36);
		frame.getContentPane().add(textArea);
		
		JButton btnBrowes = new JButton("Attach");
		btnBrowes.setBackground(Color.GRAY);
		btnBrowes.setForeground(Color.DARK_GRAY);
		btnBrowes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				filechoose.showOpenDialog(null);
				File f = filechoose.getSelectedFile();
			    filename = f.getAbsolutePath();
				for (int i =0 ; i <filename.length() ; i++)
				{
					if( filename.charAt(i) == (char)92 )
					{
						filename = filename.substring(0,i+1) + "\\" +filename.substring(i+1)	;
						i++;
					}
				}
				textArea.setText(filename);
				
			}
		});
		btnBrowes.setBounds(57, 148, 102, 36);
		frame.getContentPane().add(btnBrowes);
		
		JButton btnCompress = new JButton("Compress");
		btnCompress.setBackground(Color.GRAY);
		btnCompress.setForeground(Color.DARK_GRAY);
		btnCompress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				arithmatic L =new arithmatic();
				String txt = L.read_from_file(filename);
				try {
					L.Compress(txt);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				 JOptionPane.showMessageDialog(frame, "Success Compress");	
			}
		});
		btnCompress.setBounds(313, 43, 127, 64);
		frame.getContentPane().add(btnCompress);
		
		JButton btnNewButton = new JButton("Decompress");
		btnNewButton.setForeground(Color.DARK_GRAY);
		btnNewButton.setBackground(Color.GRAY);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(true) ;
				
				arithmatic L = new arithmatic() ;
				
				String code="";
				
				code = L.read_from_file(filename);
				 try {
					 L.De_Compress();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				};
				JOptionPane.showMessageDialog(frame, "Success Decompress");
				//textArea.setText(Text);
				
			}
		});
		btnNewButton.setBounds(313, 177, 119, 64);
		frame.getContentPane().add(btnNewButton);
		
	}
}
