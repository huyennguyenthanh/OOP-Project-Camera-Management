
import java.io.FileNotFoundException;

import javax.swing.JOptionPane;

import Exception.ExistedObjectException;
import controller.*;
import view.*;
public class RoomController {
	
	private RoomModel rm = new RoomModel();
	private RoomView rv = new RoomView();
	
	public RoomController () {
		
	}
	
	public void run() throws FileNotFoundException, ExistedObjectException {
//		  String url;
//	      url = JOptionPane.showInputDialog(null, "Please input path of text: ",
//	      "Input path", JOptionPane.INFORMATION_MESSAGE);
//	      
//	      this.rm.readfile(url);
//	      System.out.println("obj: " + this.rm.getNum_of_camera());
//	      System.out.println("camera: " + this.rm.getNum_of_camera());
//
//	      this.rm.getManagerCamera().printInfo();
//	      this.rm.getManagerObject().printInfo();

	      this.rv.run(rm);
	}

}
