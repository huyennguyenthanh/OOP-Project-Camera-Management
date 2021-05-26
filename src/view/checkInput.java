package view;

import javax.swing.*;

import controller.RoomModel;
import model.space.Point;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class checkInput {
	public checkInput(RoomModel roomModel) {
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
					x=Float.parseFloat(xText.getText());
					y=Float.parseFloat(yText.getText());
					z=Float.parseFloat(zText.getText());

					
					String data = null;
					Point point = new Point(x,y,z);
					
					data = "abcbccb";
					
					boolean check1 = roomModel.is_point_in_obj(point);
					boolean check2 = roomModel.is_point_in_cam(point);
					boolean check3 = roomModel.is_overcast_by_obj(point);
					System.out.print("/nis in obj : " + check1);
					System.out.print("/nis in cam : " + check2);
					System.out.print("/nis overcast :" +  check3);
					
					
					if (check1)
					{
						data = "Điểm nằm trong object. Không thể thấy điểm.\n";
					}
					else if (check3)
					{
						data = "Điểm không nằm trong object \nĐiểm bị che khuất bởi object khác\n"
								+ "Không thể thấy điểm.\n";
					}
					else if (check2)
					{
						data = "Điểm không nằm trong object\n"
								+ "Không bị che khuất bởi vật khác\n"
								+ "Nằm trong vùng nhìn thấy của cam.\n";
					}
					else {
						data = "Điểm không nằm trong object\n"
								+ "Không bị che khuất bởi vật khác\n"
								+ "Không trong vùng nhìn thấy của bất kì camera nào.\n";
					}
						
					status.setText(data);

				}else {
					String data = "Please input before!";
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

}