package view;

import javax.swing.*;
import java.awt.*;


public class projection extends JFrame  {

//	private static final long serialVersionUID = 1L;
//	Color background_cl1 = Color.white;
//	Color background_cl2 = Color.black;
//	Color x_cl = Color.red;
//	Color y_cl = Color.blue;
//	int column = 20, row = 30, count = 0;			
//	int Size = 0;
//	Container cn;
//	JPanel pn, pn2;
//	JLabel lb;
//	JButton newGame_bt, undo_bt, exit_bt;
//	// mang gia tri hinh chieu
//	int hc[][] = new int[column][row];
//	
//	private JButton b[][] = new JButton[column + 2][row + 2];
//	public projection() {
//	//	super(s);
//		cn =this.getContentPane();
//		pn = new JPanel();
//		pn.setLayout(new GridLayout(column,row));
//		
//		
//		// kiem tra dkien de to den trang
//		
//		
//		for (int i = 0; i < column; i++)
//			for (int j = 0; j < row; j++) {
//				if (hc[i][j]==1) {
//				b[i][j] = new JButton("1");
//				//b[i][j].setActionCommand(i + " " + j);
//				}
//				else
//				{
//					b[i][j] = new JButton("");
//				//	b[i][j+1].setActionCommand(i + " " + j);
//				}
//				if (b[i][j].getText()=="1") {
//				b[i][j].setBackground(background_cl2);
//				} else
//				{
//					b[i][j].setBackground(background_cl1);
//				}
//			
//			}
//		
//		for (int i = 0; i <column; i++)
//			for (int j = 0; j <row; j++)
//				pn.add(b[i][j]);
//		
//		
//	//	exit_bt.setForeground(x_cl);
//		cn.add(pn);
//		pn2 = new JPanel();
//		pn2.setLayout(new FlowLayout());
//	
//		cn.add(pn2,"North");
//		this.setVisible(true);
//		this.setSize(1400, 1000);
//		this.setLocationRelativeTo(null);
//		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
//		undo_bt.setEnabled(false);
//	}

	private static final long serialVersionUID = 1L;

	public projection() {
		 SmileyApp smiley = new SmileyApp();
	
		this.setVisible(true);
		this.setSize(1400, 800);
	
		this.setLocationRelativeTo(null);
		
		this.add(smiley);
	}
	
}

class SmileyApp extends JPanel {

	private static final long serialVersionUID = 1L;
	Color background_cl1 = Color.white;
	Color background_cl2 = Color.black;
	
	int column = 1400, row = 800;	
	int hc[][] = new int[column][row];

	 @Override
	 public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	   
	    	g.setColor(Color.white);
	    	g.fillRect(0, 0,column, row);	
	    	
			for (int i = 0; i < column; i++)
				for (int j = 0; j < row; j++) 
				{
					if (hc[j][i]==1)
					{
					g.setColor(background_cl2);
					
					g.fillRect(i, j, 1, 1);

					}
					
				}
		
	 }
}
