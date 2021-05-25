package view;


// Tạo class Point có thuộc tính x, y, z

import model.*;
import controller.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ShowRoom extends JFrame{
    JButton btnRoom;
    JButton btnObj;
    JButton btnCamera;
  
    public ShowRoom() {
        
        
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
                String str = "";
                for(int i=0 ; i<num_of_camera; i++) {
                    str += "Camera " + (i+1) + ":\n"
                        + "("
                        + managerCamera.getCameras().get(i).getPoint().getX() + ", "
                        + managerCamera.getCameras().get(i).getPoint().getZ() + ", "
                        + managerCamera.getCameras().get(i).getPoint().getX() + ")\n"
                        + "Height angle: " + managerCamera.getCameras().get(i).getHeight_angle() + "\n"
                        + "Width angle: " + managerCamera.getCameras().get(i).getWidth_angle() + "\n\n";
                }
                
				JOptionPane.showMessageDialog(null, str);
			}
		});

        btnObj.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
                String str = "";
                
                for(int i = 0; i < num_of_obj ; i++) {
                    str += "Obj " + (i+1) + ":\n";
                    for(int j=0; j<8 ; j++){
                        str += "(" + managerObject.getObjects().get(i).getPoints()[j].getX() + ", "
                                + managerObject.getObjects().get(i).getPoints()[j].getY() + ", "
                                + managerObject.getObjects().get(i).getPoints()[j].getZ() + ")\n";
                    }
                }
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