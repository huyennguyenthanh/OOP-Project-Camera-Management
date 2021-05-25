package menu;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
public class demo{ 
	JButton readfileButton = new JButton("Ket qua doc file");
	JButton inputButton = new JButton("Check 1 diem");;
	JButton viewButton = new JButton("Xem camera");
	JButton exitButton = new JButton("Exit");
	
	public demo (JFrame frame) {
		// Draw frame
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500,400);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setTitle("Camera view");
		
		readfileButton.setSize(300, 60);
		readfileButton.setLocation(100,20);
		readfileButton.setFocusPainted(false);
		readfileButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed (ActionEvent e) {	
			
					try {
					Read read = new Read();
					} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					}
					}
			
		});
		frame.add(readfileButton);
		
		inputButton.setSize(300, 60);
		inputButton.setLocation(100,100);
		inputButton.setFocusPainted(false);
		inputButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {	
				new checkInput();
			}
		});
		frame.add(inputButton);
		
		viewButton.setSize(300, 60);
		viewButton.setLocation(100,180);
		viewButton.setFocusPainted(false);
		viewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new showView();
			}
		});
		frame.add(viewButton);
		
		exitButton.setSize(300, 60);
		exitButton.setLocation(100,260);
		exitButton.setFocusPainted(false);
		exitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		frame.add(exitButton);

		frame.setVisible(true);
		
	}
	public static void main(String[] argv) {
		JFrame frame = new JFrame();
		new demo(frame);
	}
	
}
