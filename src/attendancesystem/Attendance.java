/*Santhosh Vivekanandan
 *sxv135530
 *Purpose: Assignment 4
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package attendancesystem;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Attendance class which is domain class which does the functionalities on the Attendance
 * @author santhosh
 */
public class Attendance {
    ArrayList<CourseInfo> courses = new ArrayList<CourseInfo> (){};
    ArrayList<StudentInfo> students = new ArrayList<StudentInfo> (){};
    ArrayList<StudentInfo> stud = new ArrayList<StudentInfo> (){};
    ArrayList<EnrollInfo> enroll = new ArrayList<EnrollInfo>(){};
    ArrayList<AttendanceInfo> attend = new ArrayList<AttendanceInfo>(){};
    
    public ArrayList<StudentInfo> read(String course) throws IOException
    {
        Enrollment en = new Enrollment();
        enroll = en.read();
        Students s = new Students();
        students = s.read();
        for(int i = 0; i <enroll.size();i++ )
        {
            if(course.equals(enroll.get(i).CourseID))
            {
                
                for(int j=0;j<students.size();j++)
                {
                    String  stid = enroll.get(i).StudentID;
                    
                    if(stid.equals(students.get(j).ID))
                    {
                        StudentInfo a = students.get(j);
                        
                        stud.add(a);
                    }
                }
            }
        }
        return stud;
    }
    
    public boolean writefile(ArrayList<AttendanceInfo> att) throws IOException
    {
        try
        {
            String filepath = System.getProperty("user.dir")+"/src/attendancesystem/Attendance.txt" ;
            FileWriter fw;
            fw = new FileWriter(filepath,true);
            for(int i=0;i<att.size();i++)
            {
                AttendanceInfo a = att.get(i);
                fw.write(a.Attdate+"\t"+a.StudentID+"\t"+a.StudentName+"\t"+a.CourseID +"\t"+a.AttValue+"\n");
            }
            fw.close();
        } 
        catch(IOException e)
        {
            return false;
        }
        return true;
    }
    public boolean enterAtt(ArrayList<AttendanceInfo> att) throws IOException
    {
        return writefile(att);
    }
    public boolean modAtt(ArrayList<AttendanceInfo> att,String c,String d) throws IOException
    {
        delete(c,d);
        return writefile(att);
    }
    public void delete(String c, String d) throws IOException
    {
        attend =read();
        StringBuffer buff = new StringBuffer("");
        for (AttendanceInfo att : attend) {
            if ((att.CourseID).equals(c)&&((att.Attdate).equals(d))) {
                continue;
            } else {
                buff.append(att.Attdate+"\t"+att.StudentID+"\t"+att.StudentName+"\t"+att.CourseID +"\t"+att.AttValue+"\n");
            }
        }
        String filepath = System.getProperty("user.dir")+"/src/attendancesystem/Attendance.txt" ; 
        FileWriter fw;
        fw = new FileWriter(filepath,false);
        fw.write(buff.toString());
        fw.close();
    }
    
    
    public ArrayList<AttendanceInfo> modifyget(String CourseId , String date) throws IOException
    {
        attend = read();
        ArrayList<AttendanceInfo> ans = new ArrayList<AttendanceInfo>(){};
        for(int i =0 ; i<attend.size();i++)
        {
            AttendanceInfo a= attend.get(i);
            if(CourseId.equals(a.CourseID)&& date.equals(a.Attdate))
            {
                ans.add(a);
            }
        }
        return ans;
    }
    public ArrayList<AttendanceInfo> read() throws IOException
    {
        BufferedReader obj;
        obj = new BufferedReader(new FileReader(System.getProperty("user.dir")+"/src/attendancesystem/Attendance.txt"));
        String line;
        while((line = obj.readLine()) != null)
        {
            String data [] = line.split("\t");
            boolean val;
            if(data[4].equals("true"))
                val=true;
            else
                val=false;
            attend.add(new AttendanceInfo(data[2],data[1],data[3],val,data[0]));
        }
        obj.close();
        return  attend;
    }  
    public ArrayList<AttendanceInfo> readDate(String course) throws IOException
    {
        BufferedReader obj;
        obj = new BufferedReader(new FileReader(System.getProperty("user.dir")+"/src/attendancesystem/Attendance.txt"));
        String line;
        while((line = obj.readLine()) != null)
        {
            String data [] = line.split("\t");
            if(data[3].equals(course))
            {
            boolean val;
            if(data[4].equals("true"))
                val=true;
            else
                val=false;
            attend.add(new AttendanceInfo(data[2],data[1],data[3],val,data[0]));
            }
        }
        obj.close();
        return  attend;
    }  
}