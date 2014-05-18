/*Santhosh Vivekanandan
 *sxv135530
 *Purpose: Assignment 4
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package attendancesystem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *Domain object for Enrollment
 * @author santhosh
 */
public class Enrollment {
    ArrayList<EnrollInfo> EnList= new ArrayList<EnrollInfo>() {}; 
    
    public ArrayList<EnrollInfo> read()throws IOException 
    {
        BufferedReader obj;
        obj = new BufferedReader(new FileReader(System.getProperty("user.dir")+"/src/attendancesystem/Enroll.txt"));
	String line;
        while((line = obj.readLine()) != null)
        {
            String data [] = line.split("\t");
            EnList.add(new EnrollInfo(data[0],data[1]));
        }
        obj.close();
        return  EnList;
    }
    
    public ArrayList<EnrollInfo> read(String Course)throws IOException 
    {
        BufferedReader obj;
        obj = new BufferedReader(new FileReader(System.getProperty("user.dir")+"/src/attendancesystem/Enroll.txt"));
	String line;
        while((line = obj.readLine()) != null)
        {
            String data [] = line.split("\t");
            if(Course.equals(data[0]))
            {
            EnList.add(new EnrollInfo(data[0],data[1]));
            }
        }
        obj.close();
        return  EnList;
    }
    public boolean add(String a, String b)throws IOException
    {
        EnList = read();
        int found =0;
        a = a.substring(0,a.indexOf(","));
        for(int i=0 ;i<EnList.size();i++)
        {
            if(EnList.get(i).CourseID.equals(a) && EnList.get(i).StudentID.equals(b))
            {
                found=1;
            }
        }
        if(found == 0)
        {
            
            EnrollInfo add = new EnrollInfo(a,b);
            writefile(add);
            return true;
            
        }
        else
            return false;
    }
    public boolean delete(String a, String b)throws IOException
    {
        EnList = read();
        remove(a,b);
        return true;
    }
    public void remove(String a,String b) throws IOException
    {
        EnList =read();
        StringBuffer buff = new StringBuffer("");
            
        for (EnrollInfo EnList1 : EnList) {
            if ((EnList1.CourseID).equals(a)&&(EnList1.StudentID).equals(b)) {
                continue;
            } else {
                buff.append(EnList1.CourseID + "\t" + EnList1.StudentID + "\n");
            }
        }
        String filepath = System.getProperty("user.dir")+"/src/attendancesystem/Enroll.txt" ; 
        FileWriter fw;
        fw = new FileWriter(filepath,false);
        fw.write(buff.toString());
        fw.close();
        System.out.println("here");
    }
    
    public void removeCourse(String a) throws IOException
    {
        EnList =read();
        StringBuffer buff = new StringBuffer("");
            
        for (EnrollInfo EnList1 : EnList) {
            if ((EnList1.CourseID).equals(a)) {
                continue;
            } else {
                buff.append(EnList1.CourseID + "\t" + EnList1.StudentID + "\n");
            }
        }
        String filepath = System.getProperty("user.dir")+"/src/attendancesystem/Enroll.txt" ; 
        FileWriter fw;
        fw = new FileWriter(filepath,false);
        fw.write(buff.toString());
        fw.close();
    }
    
    public void removeStud(String a) throws IOException
    {
        EnList =read();
        StringBuffer buff = new StringBuffer("");
            
        for (EnrollInfo EnList1 : EnList) {
            if ((EnList1.StudentID).equals(a)) {
                continue;
            } else {
                buff.append(EnList1.CourseID + "\t" + EnList1.StudentID + "\n");
            }
        }
        String filepath = System.getProperty("user.dir")+"/src/attendancesystem/Enroll.txt" ; 
        FileWriter fw;
        fw = new FileWriter(filepath,false);
        fw.write(buff.toString());
        fw.close();
    }
    
    public void writefile(EnrollInfo a)throws IOException
    {
        String filepath = System.getProperty("user.dir")+"/src/attendancesystem/Enroll.txt" ; 
        FileWriter fw;
        fw = new FileWriter(filepath,true);
        fw.write(a.CourseID+"\t"+a.StudentID+"\n");
        fw.close();
        
    }
    
    
    
}
