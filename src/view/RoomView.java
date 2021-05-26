package view;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.*;

import controller.RoomModel;
import exception.CreateRoomException;
import exception.InvalidCamera;
import exception.InvalidObject;


public class RoomView{ 
	
	JButton readfileButton = new JButton("Doc file");
	JButton viewfileButton = new JButton("Ket qua doc file");	
	JButton inputButton = new JButton("Check 1 diem");;
	JButton viewButton = new JButton("Xem camera");
	JButton exitButton = new JButton("Exit");
	public RoomView () {
		
	}
	
	
	public void run (RoomModel roomModel) {
		
		JFrame frame = new JFrame();
		
		// Draw frame
		frame.setLayout(null);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500,500);
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
					 
						  String url;
					      url = JOptionPane.showInputDialog(null, "Please input path of text: ",
					      "Input path", JOptionPane.INFORMATION_MESSAGE);
					      
					      // url = "./src/input.txt";
					      roomModel.readfile(url);
					      System.out.println("obj: " + roomModel.getNum_of_camera());
					      System.out.println("camera: " + roomModel.getNum_of_camera());

					      roomModel.getManagerCamera().printInfo();
					      roomModel.getManagerObject().printInfo();
						
					} catch (FileNotFoundException e1) {
					     // TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (CreateRoomException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (InvalidObject e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (InvalidCamera e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					}
			
		});
		frame.add(readfileButton);
		
		
		viewfileButton.setSize(300, 60);
		viewfileButton.setLocation(100,100);
		viewfileButton.setFocusPainted(false);
		viewfileButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed (ActionEvent e) {	
			
					try {
						showRoom s = new showRoom();
						s.createAndShow(roomModel);
						
						
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					}
			
		});
		frame.add(viewfileButton);
		
		inputButton.setSize(300, 60);
		inputButton.setLocation(100,180);
		inputButton.setFocusPainted(false);
		inputButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {	
				new checkInput(roomModel);
			}
		});
		frame.add(inputButton);
		
		viewButton.setSize(300, 60);
		viewButton.setLocation(100,260);
		viewButton.setFocusPainted(false);
		viewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new showRoomProjection(roomModel);
			}
		});
		frame.add(viewButton);
		
		exitButton.setSize(300, 60);
		exitButton.setLocation(100,340);
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
//	public static void main(String[] argv) {
//		JFrame frame = new JFrame();
//		new AppView(frame);
//	}
	
}
