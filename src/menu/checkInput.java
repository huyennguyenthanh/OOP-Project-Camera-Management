package menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class checkInput {
	public checkInput() {
		JLabel status = new JLabel("");
		
		JFrame frame = new JFrame("Input pointer");
		frame.setSize(500, 400);
		frame.setLayout(null);
		
		JLabel l = new JLabel("Nhap diem ban muon xet: ");
		l.setSize(400, 40);
		l.setLocation(50, 40);
		frame.add(l);
		
		JLabel x = new JLabel("toa do x: ");
		x.setSize(100, 40);
		x.setLocation(50, 100);
		frame.add(x);
		
		JTextField xText = new JTextField();
		xText.setSize(100, 40);
		xText.setLocation(200, 100);
		frame.add(xText);
		
		JLabel y = new JLabel("toa do y: ");
		y.setSize(100, 40);
		y.setLocation(50, 160);
		frame.add(y);
		
		JTextField yText = new JTextField();
		yText.setSize(100, 40);
		yText.setLocation(200, 160);
		frame.add(yText);
		
		JLabel z = new JLabel("toa do z: ");
		z.setSize(100, 40);
		z.setLocation(50, 220);
		frame.add(z);
		
		JTextField zText = new JTextField();
		zText.setSize(100, 40);
		zText.setLocation(200, 220);
		frame.add(zText);
		
		JButton b = new JButton("Check");
		b.setSize(100, 40);
		b.setLocation(150, 280);
		b.setFocusPainted(false);
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				frame.setVisible(false);
				int x, y, z;
				if(!xText.getText().equals("") && !yText.getText().equals("") && !zText.getText().equals("")) {
					x=Integer.parseInt(xText.getText());
					y=Integer.parseInt(yText.getText());
					z=Integer.parseInt(zText.getText());
//					System.out.println(x+" - "+y+" - "+z;
					String data = x+" - "+y+" - "+z + "co thuoc v";
					status.setText(data);
					  //String str = "điểm thuộc v";
					 // JOptionPane.showMessageDialog(null, str);

					//xu ly thay hay ko -> data
				}else {
					String data = "khong duoc de trong";
					status.setText(data);
				}
			}
		});
		frame.add(b);
		
		status.setSize(400, 40);
		status.setLocation(50, 320);
		frame.add(status);
		
		frame.setVisible(true);
	}
	
	public static void main(String[] argv) {
		new checkInput();
	}
}