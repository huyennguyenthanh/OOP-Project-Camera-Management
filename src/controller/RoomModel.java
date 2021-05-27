package controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import exception.CreateRoomException;
import exception.InvalidCamera;
import exception.InvalidObject;

import java.io.FileNotFoundException;

import model.Camera;
import model.Obj;
import model.Room;
import model.space.*;


public class RoomModel {
    
	int num_of_camera;
    int num_of_obj;
	public Room room;
    
    // Camera camera = new Camera(); // camera
    private ManagerCamera managerCamera = new ManagerCamera();
    private ManagerObject managerObject = new ManagerObject();
    
	public int getNum_of_camera() {
		return num_of_camera;
	}

	public void setNum_of_camera(int num_of_camera) {
		this.num_of_camera = num_of_camera;
	}

	public int getNum_of_obj() {
		return num_of_obj;
	}

	public void setNum_of_obj(int num_of_obj) {
		this.num_of_obj = num_of_obj;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public ManagerCamera getManagerCamera() {
		return managerCamera;
	}

	public void setManagerCamera(ManagerCamera managerCamera) {
		this.managerCamera = managerCamera;
	}

	public ManagerObject getManagerObject() {
		return managerObject;
	}

	public void setManagerObject(ManagerObject managerObject) {
		this.managerObject = managerObject;
	}
    
    public void readfile (String url) throws FileNotFoundException, CreateRoomException, InvalidObject, InvalidCamera {
    	String [] lines = new String[100]; // các dòng đọc từ text
        int i = 0; 
        
    	FileInputStream fileInputStream = new FileInputStream(url);
        Scanner scanner = new Scanner(fileInputStream);

        try {
            while (scanner.hasNextLine()) {
                lines[i] = scanner.nextLine();
                // System.out.println(lines[i]);
                i++;
            }
        } finally {
            try {
                scanner.close();
                fileInputStream.close();
            } catch (IOException ex) {
                // System.out.println("error");
            }
        }

        num_of_obj = Integer.parseInt(lines[1]); // số vật
        num_of_camera = Integer.parseInt(lines[2+num_of_obj]); // số camera
        int idx;
        
        // Camera [] camera_list = new Camera[num_of_camera]; // list of camera
       
        Point [] Point = new Point[8]; // Point[ 0 -> 7] : lưu 8 đỉnh

        String str_point[] = new String[8]; // toạ độ đỉnh theo string
        int count = 0;                      // đếm đỉnh

        // -------------------------------------------------------------------------
        // tách lấy 8 đỉnh của phòng       
        str_point[count] = "";
        for(int j=0; j < lines[0].length() ; j++) {

            if (lines[0].charAt(j) == '(') {
               continue;
            }
            else if(lines[0].charAt(j) == ')') {
                str_point[count] = str_point[count] + "\0";
                count++;
                if(count < 8) {
                    str_point[count] = "";
                }
            }
            else {
                str_point[count] += lines[0].charAt(j);
            }
        }

        
        String [] xyz;
        for(int j=0 ; j<8 ; j++) {
            str_point[j].replace("(", "");
            // str_point[j].trim();
            xyz = str_point[j].split(", ");
            Point[j] = new Point(Double.parseDouble(xyz[0]) * 100, Double.parseDouble(xyz[1]) * 100, Double.parseDouble(xyz[2]) * 100);
            System.out.print(Point[j].printInfo());
            
        }
        try {
        this.room = new Room (Point);
        }catch (CreateRoomException e)
        {
        	
        	JOptionPane.showMessageDialog(null, e.getMessage(),"Warning",JOptionPane.ERROR_MESSAGE);
        }
        // --------------------------------------------------------------------------------------
        // tách lấy 8 đỉnh của vật thể
        


        for(i = 2; i < 2+num_of_obj ; i++ ) {
            count = 0;
            if(count < 8) {
                str_point[count] = "";
                for(int j=0; j < lines[i].length() ; j++) {

                    if (lines[i].charAt(j) == '(') {
                        continue;
                    }
                    else if(lines[i].charAt(j) == ')') {
                        str_point[count] = str_point[count] + "\0";
                        count++;
                        if(count < 8) {
                            str_point[count] = "";
                        }
                    }
                    else {
                        str_point[count] += lines[i].charAt(j);
                    }
                }
            }
             // vật thể
            Point [] Point_obj = new Point[8];

            for(int j=0 ; j<8 ; j++) {
                str_point[j].replace("(", "");
                // str_point[j].trim();

                xyz = str_point[j].split(", ");
                Point_obj[j] = new Point(Double.parseDouble(xyz[0]) * 100, Double.parseDouble(xyz[1]) * 100, Double.parseDouble(xyz[2]) * 100);


                // System.out.println(Point_obj[j].getX() + " " + Point_obj[j].getY() + " " + Point_obj[j].getZ());
                
                // mangage Object add thêm obj (kiểm tra điều kiện vị trí)
            }
            Obj obj = new Obj(Point_obj);
            

            try {
            this.managerObject.addObject(obj, room);
            }catch (InvalidObject e)
            {
            	JOptionPane.showMessageDialog(null, e.getMessage(),"Warning",JOptionPane.ERROR_MESSAGE);
            }

        }
                
        // ===========================================================

        // --------------------------------------------------------------------
        // tách lấy vị trí camera
        // 1 dòng room + 1 dòng num of obj + num of obj + 1 dòng num of camera
        // i = 6 -> 8 // 3 camera
         // bắt đầu duyệt camera
        count = 0;
        System.out.println("===============================");
        for(idx = 3+num_of_obj; idx < (3+num_of_obj+num_of_camera); idx++) {
            // System.out.println(lines[idx]);
            Camera camera = new Camera();
            str_point[count] = "";
            for(int j=0; j < lines[idx].length() ; j++) {
                if (lines[idx].charAt(j) == '(') {
                    continue;
                }
                else if(lines[idx].charAt(j) == ')') {
                    str_point[count] = str_point[count] + "\0";
                    count++;
                    if(count < 8) {
                        str_point[count] = "";
                    }
                }
                else {
                    str_point[count] += lines[idx].charAt(j);
                }
            }
            
            str_point[0].replace("(", "");
           
            // str_point[j].trim();
            xyz = str_point[0].split(", ");
            Point p = new Point(Double.parseDouble(xyz[0]) * 100, Double.parseDouble(xyz[1]) * 100, Double.parseDouble(xyz[2]) * 100);
            
            // str_point[1].trim();
           
            double w_angle = Double.parseDouble(str_point[1].split("\\s")[1]); 
            double h_angle = Double.parseDouble(str_point[1].split("\\s")[2]);

            camera.setPoint(p);
            camera.setWidth_angle(w_angle);
            camera.setHeight_angle(h_angle);

            try {
            	this.managerCamera.addCamera(camera, room);
            } catch (InvalidCamera e){
            	JOptionPane.showMessageDialog(null, e.getMessage(),"Warning",JOptionPane.ERROR_MESSAGE);
            	
            }
            


                // manage Camera add thêm camera ( kiểm tra điều kiện vị trí )
            
            count = 0;
        }
        System.out.println("=====================================");
    }
    
    
    
    
    // if a point in visible area of a camera --> return true 
    public boolean is_point_in_cam_all(Point p)
    {
    	
    	for (int  i = 0 ; i < this.num_of_camera; i++)
    	{
    		Camera camera = this.managerCamera.getCameras().get(i);
    		if (is_point_in_cam(p, camera))
    		{
    			return true;
    		}
    	}
    	return false;
    }
    public boolean is_point_in_cam(Point p, Camera camera)
    {
    	Calculation c = new Calculation();
    	double v_camera = camera.volume_visible_area();
		
		ArrayList<Point> p1 = new ArrayList<Point>();
        ArrayList<Point> p2 = new ArrayList<Point>();
        ArrayList<Point> p3 = new ArrayList<Point>();
        ArrayList<Point> p4 = new ArrayList<Point>();
        p1.add(camera.getProjection().get(0)); p1.add(camera.getProjection().get(1)); p1.add(camera.getPoint());
        p2.add(camera.getProjection().get(1)); p2.add(camera.getProjection().get(2)); p2.add(camera.getPoint());
        p3.add(camera.getProjection().get(2)); p3.add(camera.getProjection().get(3)); p3.add(camera.getPoint());
        p4.add(camera.getProjection().get(3)); p4.add(camera.getProjection().get(0)); p4.add(camera.getPoint());
        
        // thể tích từ điểm tới các mặt bên
        double v1 = c.Volume_Pyramid(p1, p);
        double v2 = c.Volume_Pyramid(p2, p);
        double v3 = c.Volume_Pyramid(p3, p);
        double v4 = c.Volume_Pyramid(p4, p);
        
        // thể tích từ điểm tới đáy
        double v5 = c.Volume_Pyramid(camera.getProjection(), p);
//        System.out.print(camera.printInfo());
//        System.out.print("\nThể tích khối cam: " + v_camera);
//        System.out.print(camera.printInfoProjection());
//        System.out.print("\nThể tích v1 " + v1);
//        System.out.print("\nThể tích v1 " + v2);
//        System.out.print("\nThể tích v1 " + v3);
//        System.out.print("\nThể tích v1 " + v4);
//        System.out.print("\nThể tích v1 " + v5);
        // tổng thể tích
        double v_point = v1 + v2 + v3 + v4 + v5;
        
        //System.out.print("Thể tích vpoint" + v_point);
        // System.out.println(v1 + " " + v2 + " " + v3 + " " + v4 + " "+v5+" "+ vS);
        if(Math.abs(v_point - v_camera) < 1.0){
        	return true;
        }
        return false;
    }
    
    
    // true means the point is inside the object
    public boolean is_point_in_obj_all(Point point) {

    	for (int i = 0; i < this.num_of_obj; i++)
    	{
    		Obj o = this.managerObject.getObjects().get(i);
    		if (is_point_in_obj(point, o))	
    			return true;
    	}
    	
    	return false;
    	
    }
    private boolean is_point_in_obj(Point point, Obj o)
    {
    	Calculation c = new Calculation();
    	int check = 0;
    	
		Vector3D m = new Vector3D(o.getPoints()[1], o.getPoints()[0]);
		Vector3D n = new Vector3D(o.getPoints()[3], o.getPoints()[0]);
		Vector3D p = new Vector3D(o.getPoints()[4], o.getPoints()[0]);
		Vector3D q = new Vector3D(point, o.getPoints()[0]);
		
		
		if ((0 <= c.ScalarVec(q, m)) && (c.ScalarVec(q, m) <= c.ScalarVec(m, m)))
			check += 1;
		if ((0 <= c.ScalarVec(q, n)) && (c.ScalarVec(q, n) <= c.ScalarVec(n, n)))
			check += 1;
		if ((0 <= c.ScalarVec(q, p)) && (c.ScalarVec(q, p) <= c.ScalarVec(p, p)))
			check += 1;
		if (check == 3)
			return true;
		return false;
    	
    }


    // true là bị che
	public boolean is_overcast_by_obj(Point point) {
		Calculation c = new Calculation();
		Line AB; // đường thẳng nối point và camera
		double h;
		int check = 0;
		for (int i = 0; i < managerCamera.getNum_cams(); i++) 
		{
			// System.out.print(managerCamera.getCameras().get(i).printInfo());
			if (is_point_in_cam(point, managerCamera.getCameras().get(i))) 
			{
				
				boolean check_a = false; // nhìn thấy
				for (int j = 0; j < managerObject.getNum_objs(); j++) {
					AB = new Line(point, managerCamera.getCameras().get(i).getPoint());
					Plane[] planeListOfObj = getPlaneListOfObj(managerObject.getObjects().get(j));

					for (int k = 0; k < planeListOfObj.length; k++) {
						Point intersectionPoint = c.GetIntPoint(AB, planeListOfObj[k]);
						if (intersectionPoint == null) {
							
							continue;
						}

						if (!is_point_in_obj(intersectionPoint, managerObject.getObjects().get(j))) {

							continue;
						}
						Vector3D MN = new Vector3D(point, intersectionPoint); // vector nối point và giao
						Vector3D MP = new Vector3D(point, managerCamera.getCameras().get(i).getPoint()); // vector noi
																											// point vs
																											// cam
						if (!MN.checkVectorInTheSameDirection(MP)) {
							continue;
						}
						h = MN.getRatioOfTwoVectors(MP);
						if (h > 0 && h < 1)
							check_a = true; // bị che
					}
				}
				// System.out.print("Nằm trg tầm nhìn \n");
				// System.out.print(check_a);
				if (check_a != true)
					check +=1; // thuộc cam và nhìn thấy

			}
		}
		// System.out.print("\nCheck "+ check);
		if (check == 0)
			return true;
		return false;
	}
    private Plane[] getPlaneListOfObj (Obj obj){
        Plane[] planeListOfObj = new Plane[6];
        // mặt ABCD
        planeListOfObj[0] = new Plane(obj.getPoint(0),obj.getPoint(1),obj.getPoint(2));
        // mặt A1B1C1D1
        planeListOfObj[1] = new Plane(obj.getPoint(4),obj.getPoint(5),obj.getPoint(6));
        // mặt A1B1BA
        planeListOfObj[2] = new Plane(obj.getPoint(4),obj.getPoint(5),obj.getPoint(0));
        // mặt B1C1CB1
        planeListOfObj[3] = new Plane(obj.getPoint(1),obj.getPoint(2),obj.getPoint(5));
        // mặt C1D1DC
        planeListOfObj[4] = new Plane(obj.getPoint(2),obj.getPoint(3),obj.getPoint(6));
        // mặt A1D1DA
        planeListOfObj[5] = new Plane(obj.getPoint(4),obj.getPoint(7),obj.getPoint(3));
        return planeListOfObj;
    }
    
    
    
    
    
    // nếu một điểm là nhìn thấy return true
    private boolean is_point_visible(Point point)
    {
    	if (is_point_in_obj_all(point))
    	{
    		return false;
    	}
    		
    	else if (is_overcast_by_obj(point))
    	{
    		return false;
    	}
    	return true;
    }
    
    
    // tính toán vùng nhìn thấy
    public int visible_area() {
    	int width = (int) Math.round(this.room.getWidth());
    	int length = (int) Math.round(this.room.getLength());
    	int height = (int) Math.round(this.room.getHeight());
    	
    	int V = 0;
    	for (int i = 0; i < width; i ++)
    	{
    		for (int j = 0; j < length; j++)
    		{
    			for (int k = 0; k < height; k++)
    			{
    				Point point = new Point(i, j, k);
        			if (is_point_visible(point))
        				V += 1;
    			}
    		
    		}
    		if (i%10 == 0)
    			System.out.print("\nLoading:  " + i + "/" + width + "");
    	}
    	
    	System.out.print("\nLoading: 100% \n");
    	
    	return V;

    }
 // tính hình chiếu từ trái sang phải ADD1A1
    // 1 là nhìn thấy, 0 là không nhìn thấy
    public int [][] projection_left_to_right(){
    	int m = (int) Math.round(this.room.getLength());
    	int n = (int) Math.round(this.room.getHeight());

		int [][] projection =  new int[m+1][n+1];
		
		for (int i = 0; i < m; i ++)
    	{
    		for (int j = 0; j < n; j++)
    		{
    			Point point = new Point(this.room.getWidth(), (double) i, (double) j);
    			if (is_point_visible(point))
    				projection[i][j] = 1;
    			else 
    				projection[i][j] = 0;
    				
    		}
    	}

		
		return projection;

    }
    
 
    // tính hình chiếu từ phải sang trái BCC1B1
    // 1 là nhìn thấy, 0 là không nhìn thấy
    public int [][] projection_right_to_left(){
    	// Code here
    	int m = (int) Math.round(this.room.getLength());
    	int n = (int) Math.round(this.room.getHeight());

		int [][] projection =  new int[m + 1][n + 1];
		
		for (int i = 0; i < m; i ++)
    	{
    		for (int j = 0; j < n; j++)
    		{
    			Point point = new Point(0, (double) i, (double) j); // <--
    			if (is_point_visible(point))
    				projection[i][j] = 1;
    			else 
    				projection[i][j] = 0;
    				
    		}
    	}
		return projection;
    }
    
 
    // tính hình chiếu từ trên xuống dưới A1B1C1D1
    // 1 là nhìn thấy, 0 là không nhìn thấy
    public int [][] projection_top_to_bottom(){
    	// Code here
    	int m = (int) Math.round(this.room.getLength());
    	int n = (int) Math.round(this.room.getWidth());

		int [][] projection =  new int[m + 1][n + 1];
		
		for (int i = 0; i < m; i ++)
    	{
    		for (int j = 0; j < n; j++)
    		{
    			Point point = new Point((double) j, (double) i, 0);
    			if (is_point_visible(point))
    				projection[i][j] = 1;
    			else 
    				projection[i][j] = 0;
    				
    		}
    	}
		return projection;

    }
 
    // tính hình chiếu từ dưới lên trên ABCD
    // 1 là nhìn thấy, 0 là không nhìn thấy
    public int [][] projection_bottom_to_top(){
    	// Code here
    	int m = (int) Math.round(this.room.getLength());
    	int n = (int) Math.round(this.room.getWidth());

		int [][] projection =  new int[m + 1][n + 1];
		
		for (int i = 0; i < m; i ++)
    	{
    		for (int j = 0; j < n; j++)
    		{
    			Point point = new Point((double) j, (double) i, 0);
    			if (is_point_visible(point))
    				projection[i][j] = 1;
    			else 
    				projection[i][j] = 0;
    				
    		}
    	}
		return projection;
    }
 
    // tính hình chiếu từ trước về sau CDD1C1
    // 1 là nhìn thấy, 0 là không nhìn thấy
    public int [][] projection_front_to_back(){
    	// Code here
    	int m = (int) Math.round(this.room.getWidth());
    	int n = (int) Math.round(this.room.getHeight());

		int [][] projection =  new int[m + 1][n + 1];
		
		for (int i = 0; i < m; i ++)
    	{
    		for (int j = 0; j < n; j++)
    		{
    			Point point = new Point((double) i, 0, (double) j);
    			if (is_point_visible(point))
    				projection[i][j] = 1;
    			else 
    				projection[i][j] = 0;
    				
    		}
    	}
		return projection;
    }
    // tính hình chiếu từ sau ra trưóc ABB1A1
    // 1 là nhìn thấy, 0 là không nhìn thấy
    public int [][] projection_back_to_front(){
    	// Code here
    	
    	int m = (int) Math.round(this.room.getWidth());
    	int n = (int) Math.round(this.room.getHeight());

		int [][] projection =  new int[m + 1][n + 1];
		
		for (int i = 0; i < m; i ++)
    	{
    		for (int j = 0; j < n; j++)
    		{
    			Point point = new Point((double) i, this.room.getLength(), (double)j);
    			if (is_point_visible(point))
    				projection[i][j] = 1;
    			else 
    				projection[i][j] = 0;
    				
    		}
    	}
		return projection;
		
    }
    
//    // tính hình chiếu từ trái sang phải ADD1A1
//    // 1 là nhìn thấy, 0 là không nhìn thấy
//    public int [][] projection_left_to_right(){
//    	int m = (int) Math.round(this.room.getLength());
//    	int n = (int) Math.round(this.room.getHeight());
//
//		int [][] projection =  new int[m][n];
//		
//		for (int i = 0; i < m; i ++)
//    	{
//    		for (int j = 0; j < n; j++)
//    		{
//    			Point point = new Point(0.0, (double) i, (double) j);
//    			if (is_point_visible(point))
//    				projection[i][j] = 1;
//    			else 
//    				projection[i][j] = 0;
//    				
//    		}
//    	}
//		
//		
//		return projection;
//
//    }
//    
// 
//    // tính hình chiếu từ phải sang trái BCC1B1
//    // 1 là nhìn thấy, 0 là không nhìn thấy
//    public int [][] projection_right_to_left(){
//    	// Code here
//    	int m = (int) Math.round(this.room.getLength());
//    	int n = (int) Math.round(this.room.getHeight());
//
//		int [][] projection =  new int[m + 1][n + 1];
//		
//		for (int i = 0; i < m; i ++)
//    	{
//    		for (int j = 0; j < n; j++)
//    		{
//    			Point point = new Point((double)(this.room.getWidth()),(double) i,(double) j); // <--
//    			if (is_point_visible(point))
//    				projection[i][j] = 1;
//    			else 
//    				projection[i][j] = 0;
//    				
//    		}
//    	}
//		
//		return projection;
//    }
//    
// 
//    // tính hình chiếu từ trên xuống dưới A1B1C1D1
//    // 1 là nhìn thấy, 0 là không nhìn thấy
//    public int [][] projection_top_to_bottom(){
//    	// Code here
//    	int m = (int) Math.round(this.room.getLength());
//    	int n = (int) Math.round(this.room.getWidth());
//
//		int [][] projection =  new int[m][n];
//		
//		for (int i = 0; i < m; i ++)
//    	{
//    		for (int j = 0; j < n; j++)
//    		{
//    			Point point = new Point((double) i, (double) j, this.room.getHeight());
//    			if (is_point_visible(point))
//    				projection[i][j] = 1;
//    			else 
//    				projection[i][j] = 0;
//    				
//    		}
//    	}
//		
//
//		return projection;
//
//    }
// 
//    // tính hình chiếu từ dưới lên trên ABCD
//    // 1 là nhìn thấy, 0 là không nhìn thấy
//    public int [][] projection_bottom_to_top(){
//    	// Code here
//    	int m = (int) Math.round(this.room.getLength());
//    	int n = (int) Math.round(this.room.getWidth());
//
//		int [][] projection =  new int[m][n];
//		
//		for (int i = 0; i < m; i ++)
//    	{
//    		for (int j = 0; j < n; j++)
//    		{
//    			Point point = new Point((double) i, (double) j, 0.0);
//    			if (is_point_visible(point))
//    				projection[i][j] = 1;
//    			else 
//    				projection[i][j] = 0;
//    				
//    		}
//    	}
//		
//
//		return projection;
//    }
// 
//    // tính hình chiếu từ trước về sau CDD1C1
//    // 1 là nhìn thấy, 0 là không nhìn thấy
//    public int [][] projection_front_to_back(){
//    	// Code here
//    	int m = (int) Math.round(this.room.getWidth());
//    	int n = (int) Math.round(this.room.getHeight());
//
//		int [][] projection =  new int[m][n];
//		
//		for (int i = 0; i < m; i ++)
//    	{
//    		for (int j = 0; j < n; j++)
//    		{
//    			Point point = new Point((double)i, 0.0, (double) j);
//    			if (is_point_visible(point))
//    				projection[i][j] = 1;
//    			else 
//    				projection[i][j] = 0;
//    				
//    		}
//    	}
//		
//
//		return projection;
//    }
//    // tính hình chiếu từ sau ra trưóc ABB1A1
//    // 1 là nhìn thấy, 0 là không nhìn thấy
//    public int [][] projection_back_to_front(){
//    	// Code here
//    	
//    	int m = (int) Math.round(this.room.getWidth());
//    	int n = (int) Math.round(this.room.getHeight());
//
//		int [][] projection =  new int[m][n];
//		
//		for (int i = 0; i < m; i ++)
//    	{
//    		for (int j = 0; j < n; j++)
//    		{
//    			Point point = new Point((double) i, this.room.getLength(), (double) j);
//    			if (is_point_visible(point))
//    				projection[i][j] = 1;
//    			else 
//    				projection[i][j] = 0;
//    				
//    		}
//    	}
//		
//
//		return projection;
		
    
    
    
    



}
