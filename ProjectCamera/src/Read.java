// Tạo class Point có thuộc tính x, y, z


import point.Point;
import obj.ManagerObject;
import obj.Obj;
import room.Room;
import camera.Camera;
import camera.ManagerCamera;
import obj.ManagerObject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Read {

    int num_of_camera;
    int num_of_obj;
    Room room = new Room(); // phòng
    // Camera camera = new Camera(); // camera
    ManagerCamera managerCamera = new ManagerCamera();
    ManagerObject managerObject = new ManagerObject();


    public void readFile() throws FileNotFoundException{
        
        String [] lines = new String[100]; // các dòng đọc từ text
        int i = 0;                          // index of line

        String url;
        url = JOptionPane.showInputDialog(null, "Please input path of text: ",
        "Input path", JOptionPane.INFORMATION_MESSAGE);

        // Đọc dữ liệu từ File với Scanner
        FileInputStream fileInputStream = new FileInputStream(url);
        Scanner scanner = new Scanner(fileInputStream);

        try {
            while (scanner.hasNextLine()) {
                lines[i] = scanner.nextLine();
                System.out.println(lines[i]);
                i++;
            }
        } finally {
            try {
                scanner.close();
                fileInputStream.close();
            } catch (IOException ex) {
                System.out.println("error");
            }
        }

        num_of_obj = Integer.parseInt(lines[1]); // số vật
        num_of_camera = Integer.parseInt(lines[2+num_of_obj]); // số camera
        int idx;
        
        // Camera [] camera_list = new Camera[num_of_camera]; // list of camera
       
        Point [] points = new Point[8]; // points[ 0 -> 7] : lưu 8 đỉnh

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
            points[j] = new Point();
            points[j].setPoint(Float.parseFloat(xyz[0]), Float.parseFloat(xyz[1]), Float.parseFloat(xyz[2]));
            room.setPoints(points);
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
            Obj obj = new Obj(); // vật thể
            Point [] points_obj = new Point[8];

            for(int j=0 ; j<8 ; j++) {
                str_point[j].replace("(", "");
                // str_point[j].trim();

                xyz = str_point[j].split(", ");
                points_obj[j] = new Point();

                points_obj[j].setPoint(Float.parseFloat(xyz[0]), Float.parseFloat(xyz[1]), Float.parseFloat(xyz[2]));

                System.out.println(points_obj[j].getX() + " " + points_obj[j].getY() + " " + points_obj[j].getZ());
                
                // mangage Object add thêm obj (kiểm tra điều kiện vị trí)
            }
            
            obj.setPoints(points_obj);

            managerObject.addObject(obj);

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
            Point p = new Point();
            p.setPoint(Float.parseFloat(xyz[0]), Float.parseFloat(xyz[1]), Float.parseFloat(xyz[2]));
            // str_point[1].trim();
           
            float w_angle = Float.parseFloat(str_point[1].split("\\s")[1]); 
            float h_angle = Float.parseFloat(str_point[1].split("\\s")[2]);

            camera.setPoint(p);
            camera.setWidth_angle(w_angle);
            camera.setHeight_angle(h_angle);

            managerCamera.add(camera);


                // manage Camera add thêm camera ( kiểm tra điều kiện vị trí )
            
            count = 0;
        }
        System.out.println("=====================================");

    }

    public static void main(String[] args) throws FileNotFoundException{
        Read f = new Read();
        f.readFile();
        System.out.println("obj: " + f.num_of_obj);
        System.out.println("camera: " + f.num_of_camera);
        System.out.println("ROOM: ");
        for(int i=0 ; i<8 ; i++) {
            System.out.printf("(%.2f %.2f %.2f) %n",f.room.getPoints()[i].getX(), 
                                                    f.room.getPoints()[i].getY(), 
                                                    f.room.getPoints()[i].getZ());
        }



        for(int i=0 ; i<f.num_of_obj; i++) {
            System.out.println("Obj " + (i+1));
            for(int j=0 ; j<8; j++) {
                System.out.printf("%.2f %.2f %.2f%n", f.managerObject.getObjects().get(i).getPoints()[j].getX(), 
                                                        f.managerObject.getObjects().get(i).getPoints()[j].getY(), 
                                                        f.managerObject.getObjects().get(i).getPoints()[j].getZ());
            }
        }

        for(int i=0 ; i<f.num_of_camera ; i++) {
            System.out.println("Camera " + (i+1));
            
                System.out.printf("%.2f %.2f %.2f%n", f.managerCamera.getCameras().get(i).getPoint().getX(), 
                                                    f.managerCamera.getCameras().get(i).getPoint().getY(), 
                                                    f.managerCamera.getCameras().get(i).getPoint().getZ());
            
        }

        // System.out.println(room.getPoints()[6].getX());
    }
}
