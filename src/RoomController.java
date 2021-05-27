
import java.io.FileNotFoundException;

import controller.RoomModel;
import view.*;
public class RoomController {
	
	private RoomModel rm = new RoomModel();
	private RoomView rv = new RoomView();
	
	public RoomController () {
		
	}
	
	public void run() throws FileNotFoundException {

	      this.rv.run(rm);
	}

}
