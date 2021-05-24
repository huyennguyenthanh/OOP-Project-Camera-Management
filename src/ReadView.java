
import java.io.FileNotFoundException;

import javax.swing.JOptionPane;

import Exception.ExistedObjectException;
import controller.*;
public class ReadView {

	public static void main(String[] args) throws FileNotFoundException, ExistedObjectException {
		// TODO Auto-generated method stub

      RoomController rc = new RoomController();
      String url;
      url = JOptionPane.showInputDialog(null, "Please input path of text: ",
      "Input path", JOptionPane.INFORMATION_MESSAGE);
      
      rc.readfile(url);
      System.out.println("obj: " + rc.getNum_of_camera());
      System.out.println("camera: " + rc.getNum_of_camera());

      rc.getManagerCamera().printInfo();
      rc.getManagerObject().printInfo();
	}

}
