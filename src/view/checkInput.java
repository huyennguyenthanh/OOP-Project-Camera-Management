package view;

import javax.swing.*;

import controller.RoomModel;
import model.space.Point;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class CheckInput {
	public CheckInput(RoomModel roomModel) {
		JLabel status = new JLabel("");
		
		JFrame frame = new JFrame("Input pointer");
		frame.setSize(500, 500);
		frame.setLayout(null);
		
		JLabel l = new JLabel("Nhap diem ban muon xet: ");
		l.setSize(400, 40);
		l.setLocation(50, 40);
		frame.add(l);
		
		JLabel x = new JLabel("Toa do x (m): ");
		x.setSize(100, 40);
		x.setLocation(50, 100);
		frame.add(x);
		
		JTextField xText = new JTextField();
		xText.setSize(100, 40);
		xText.setLocation(200, 100);
		frame.add(xText);
		
		JLabel y = new JLabel("Toa do y (m): ");
		y.setSize(100, 40);
		y.setLocation(50, 160);
		frame.add(y);
		
		JTextField yText = new JTextField();
		yText.setSize(100, 40);
		yText.setLocation(200, 160);
		frame.add(yText);
		
		JLabel z = new JLabel("Toa do z (m): ");
		z.setSize(100, 40);
		z.setLocation(50, 220);
		frame.add(z);
		
		JTextField zText = new JTextField();
		zText.setSize(100, 40);
		zText.setLocation(200, 220);
		frame.add(zText);
		
		JTextField text = new JTextField();
		JButton b = new JButton("Check");
		b.setSize(100, 40);
		b.setLocation(150, 280);
		b.setFocusPainted(false);
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				float x, y, z;
				//frame.setVisible(false);
				if(!xText.getText().equals("") && !yText.getText().equals("") && !zText.getText().equals("")) {
					x = Float.parseFloat(xText.getText()) * 100;
					y = Float.parseFloat(yText.getText()) * 100;
					z = Float.parseFloat(zText.getText()) * 100;

					
					String data = null;
					Point point = new Point(x,y,z);

					data = "abcbccb";

					try {
						
					
					boolean check1 = roomModel.is_point_in_obj_all(point);
					boolean check2 = roomModel.is_overcast_by_obj(point);

					if (check1)
					{
						data = "Điểm nằm trong object. Không thể thấy điểm.\n";
					}
					else if (check2)
					{
						data = "Điểm không nằm trong object nào\n";
						data += "Điểm không nằm trong vùng nhìn thấy của cam.\n";
					}
					else
					{
						data = "Điểm không nằm trong object nào\n";
						data += "Điểm nằm trong vùng nhìn thấy của cam.\n";
					}
						
					
	
					
					
					}
					catch (Exception ex)
					{
						
					}
					System.out.print(data);
					status.setText(data);

				}else {
					String data = "Please input before!";
					status.setText(data);
					
				}
			}
		});
		frame.add(b);
//		text.setSize(200, 100);
//		text.setLocation(50, 320);
		status.setSize(500, 100);
		status.setLocation(50, 320);
		frame.add(status);
		//frame.add(text);
		
		
		frame.setVisible(true);
	}

}