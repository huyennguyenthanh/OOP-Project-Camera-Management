package controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import exception.CreateRoomException;
import exception.InvalidObject;

import java.io.FileNotFoundException;

import model.*;
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
    
    public void readfile (String url) throws FileNotFoundException, CreateRoomException, InvalidObject {
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
            Point[j] = new Point(Float.parseFloat(xyz[0]), Float.parseFloat(xyz[1]), Float.parseFloat(xyz[2]));
            
            
        }
        
        this.room = new Room (Point);
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
                Point_obj[j] = new Point(Float.parseFloat(xyz[0]), Float.parseFloat(xyz[1]), Float.parseFloat(xyz[2]));


                // System.out.println(Point_obj[j].getX() + " " + Point_obj[j].getY() + " " + Point_obj[j].getZ());
                
                // mangage Object add thêm obj (kiểm tra điều kiện vị trí)
            }
            Obj obj = new Obj(Point_obj);
            

            this.managerObject.addObject(obj, room);

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
            Point p = new Point(Float.parseFloat(xyz[0]), Float.parseFloat(xyz[1]), Float.parseFloat(xyz[2]));
            
            // str_point[1].trim();
           
            float w_angle = Float.parseFloat(str_point[1].split("\\s")[1]); 
            float h_angle = Float.parseFloat(str_point[1].split("\\s")[2]);

            camera.setPoint(p);
            camera.setWidth_angle(w_angle);
            camera.setHeight_angle(h_angle);

            this.managerCamera.addCamera(camera, room);


                // manage Camera add thêm camera ( kiểm tra điều kiện vị trí )
            
            count = 0;
        }
        System.out.println("=====================================");
    }
    
    
    
    
    // if a point in visible area of a camera --> return true 
    public boolean is_point_in_cam(Point p)
    {
    	Calculation c = new Calculation();
    	for (int  i = 0 ; i < this.num_of_camera; i++)
    	{
    		Camera camera = this.managerCamera.getCameras().get(i);
    		
    		
    		float v_camera = camera.volume_visible_area();
    		
    		ArrayList<Point> p1 = new ArrayList<Point>();
            ArrayList<Point> p2 = new ArrayList<Point>();
            ArrayList<Point> p3 = new ArrayList<Point>();
            ArrayList<Point> p4 = new ArrayList<Point>();
            p1.add(camera.getProjection().get(0)); p1.add(camera.getProjection().get(1)); p1.add(camera.getPoint());
            p2.add(camera.getProjection().get(1)); p2.add(camera.getProjection().get(2)); p2.add(camera.getPoint());
            p3.add(camera.getProjection().get(2)); p3.add(camera.getProjection().get(3)); p3.add(camera.getPoint());
            p4.add(camera.getProjection().get(3)); p4.add(camera.getProjection().get(0)); p4.add(camera.getPoint());
            
            // thể tích từ điểm tới các mặt bên
            float v1 = c.Volume_Pyramid(p1, p);
            float v2 = c.Volume_Pyramid(p2, p);
            float v3 = c.Volume_Pyramid(p3, p);
            float v4 = c.Volume_Pyramid(p4, p);
            
            // thể tích từ điểm tới đáy
            float v5 = c.Volume_Pyramid(camera.getProjection(), p);
            
            // tổng thể tích
            float v_point = v1 + v2 + v3 + v4 + v5;
            // System.out.println(v1 + " " + v2 + " " + v3 + " " + v4 + " "+v5+" "+ vS);
            if((float) Math.round(v_camera) == (float) Math.round(v_point)){
            	return true;
            }
    		
    	}
    	return false;
    }
    
    // true means the point is inside the object
    public boolean is_point_in_obj(Point point) {
    	
    	Calculation c = new Calculation();
    	for (int i = 0; i < this.num_of_obj; i++)
    	{
    		Obj o = this.managerObject.getObjects().get(i);
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
    		
    		// điểm nằm trong vật
    		if (check == 3)
    			return true;	
    	}
    	
    	return false;
    	
    }
    
    // kiểm tra xem 1 điểm có bị che khuất không , nếu bị che -> true
    public boolean is_overcast_by_obj(Point point) {
    	
    	
    	return true;
    	
    }
    
    
    
    
    // tính toán vùng nhìn thấy
    public int visible_area() {
    	int width = Math.round(this.room.getWidth());
    	int length = Math.round(this.room.getLength());
    	int height = Math.round(this.room.getHeight());
    	
    	int V = 0;
    	for (int i = 0; i < width; i ++)
    	{
    		for (int j = 0; j < length; j++)
    		{
    			for (int k = 0; k < height; k++)
    			{
    				
    			}
    		}
    	}
    	
    	return V;
    	
    	
    	
    }



}
