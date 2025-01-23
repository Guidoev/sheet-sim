package data;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.io.FileWriter;
import java.io.IOException;

public class Frame implements ActionListener{
	
	private JFrame frame = new JFrame("Sheet Sim");
	private JPanel panel = new JPanel();
	private JTextField widthField = new JTextField();
	private JTextField heightField = new JTextField();
	private JButton button = new JButton("Crea Coperta");
	private JLabel label = new JLabel("Inserisci le dimensioni della coperta:");
	private JLabel errorLabel = new JLabel("");
	private JLabel widthLabel = new JLabel("Larghezza:");
	private JLabel heightLabel = new JLabel("Lunghezza:");
	private int width=0, height=0;
	private Font plainFont = new Font("Consolas", Font.PLAIN, 20);
	private Font boldFont = new Font("Consolas", Font.BOLD, 20);
	private Font errorFont = new Font("Consolas", Font.ITALIC, 20);
	private GridBagConstraints con = new GridBagConstraints();
	
	
	
	public void show(int defaultWidth, int defaultHeight) {
		frame.setPreferredSize(new Dimension(500,500));
		frame.setLocation(700, 200);
		frame.setResizable(false);
		panel.setLayout(new GridBagLayout());
		widthField.setText(defaultWidth + "");
		heightField.setText(defaultHeight + "");
		
		con.gridwidth = 2;
		con.weighty = 1;
				
		con.fill = GridBagConstraints.HORIZONTAL;
		con.ipady = 50;
		con.gridx = 0;
		con.gridy = 0;
		con.gridwidth = 2;
		label.setFont(boldFont);
		widthLabel.setFont(boldFont);
		heightLabel.setFont(boldFont);
		widthField.setFont(plainFont);
		heightField.setFont(plainFont);
		errorLabel.setFont(errorFont);
		errorLabel.setForeground(Color.RED);
		panel.add(label, con);
		
		con.fill = GridBagConstraints.HORIZONTAL;
		con.ipady = 20;
		con.ipadx = 20;
		con.gridx = 0;
		con.gridy = 1;
		con.gridwidth=1;
		panel.add(widthLabel, con);

		con.fill = GridBagConstraints.HORIZONTAL;
		con.gridx = 1;
		con.gridy = 1;
		panel.add(widthField, con);
		
		con.fill = GridBagConstraints.HORIZONTAL;
		con.gridx = 0;
		con.gridy = 2;
		panel.add(heightLabel, con);

		con.fill = GridBagConstraints.HORIZONTAL;
		con.gridx = 1;
		con.gridy = 2;
		panel.add(heightField, con);
		
		con.fill = GridBagConstraints.HORIZONTAL;
		con.gridx = 0;
		con.gridy = 3;
		con.gridwidth = 2;
		con.anchor = GridBagConstraints.CENTER;
		panel.add(errorLabel, con);
		
		con.fill = GridBagConstraints.HORIZONTAL;
		con.gridx = 0;
		con.gridy = 4;
		con.gridwidth=2;
		panel.add(button, con);
		
		button.addActionListener(this);
		frame.add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		width=Integer.parseInt(widthField.getText());
		height=Integer.parseInt(heightField.getText());
		if(width<100 && height<100) {
			errorLabel.setText("");
			new Boot(width,height);		
			try {
			      FileWriter writer = new FileWriter("src/res/save.txt");
			      writer.write(width + ", " + height + System.getProperty( "line.separator" ) + Launcher.fileName);
			      writer.close();
			    } catch (IOException ex) {
			      System.out.println("An error occurred.");
			      ex.printStackTrace();
			    }
		} else {
			errorLabel.setText("Inserire valori inferiori a 100");
		}
		
	}
	
}
