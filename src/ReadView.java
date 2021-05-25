
import java.io.FileNotFoundException;

import javax.swing.JOptionPane;

import Exception.ExistedObjectException;
import controller.*;
import view.*;
import model.*;
public class ReadView {

	public static void main(String[] args) throws FileNotFoundException, ExistedObjectException {
		// TODO Auto-generated method stub

      RoomController rc = new RoomController();
      rc.run();
	}

}
