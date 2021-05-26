package view;


// Tạo class Point có thuộc tính x, y, z

import controller.*;



import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class showRoom extends JFrame{

	private static final long serialVersionUID = 1L;
	JButton btnRoom;
    JButton btnObj;
    JButton btnCamera;
  
    public showRoom() {
        
        
    }

    // tao giao dien

    public void createAndShow(RoomModel roomModel) {
        Container cp = getContentPane();
        cp.setLayout(new FlowLayout());
        // cp.add(new JLabel("Choose what you want to show"));
        btnRoom = new JButton("Room");
        btnObj = new JButton("Object");
        btnCamera = new JButton("Camera");

        btnRoom.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
                String str = "";
                
                str = roomModel.room.printInfo();
                
				JOptionPane.showMessageDialog(null, str);
			}
		});

        btnCamera.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
                String str = null;
                str = roomModel.getManagerCamera().printInfo();
                
				JOptionPane.showMessageDialog(null, str);
			}
		});

        btnObj.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
                String str = "";
                str = roomModel.getManagerObject().printInfo();
				JOptionPane.showMessageDialog(null, str);
			}
		});
        
        cp.add(btnRoom);
        cp.add(btnObj);
        cp.add(btnCamera);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Show information");
		setSize(400, 100);
		setVisible(true);
    }

}