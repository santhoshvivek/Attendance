package attendancesystem;

/*Santhosh Vivekanandan
 *sxv135530
 *Purpose: Assignment 4
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Domain object for COurses
 * @author santhosh
 */
public class Courses {
    ArrayList<CourseInfo> courselist = new ArrayList<CourseInfo>() {};
    
        
       
    public ArrayList<CourseInfo> read()throws IOException 
    {
        BufferedReader obj;
        obj = new BufferedReader(new FileReader(System.getProperty("user.dir")+"/src/attendancesystem/Course.txt"));
	String line;
        while((line = obj.readLine()) != null)
        {
            String data [] = line.split("\t");
            courselist.add(new CourseInfo(data[0],data[1]));
        }
        obj.close();
        return  courselist;
    }
    public ArrayList<String> GetAll()throws IOException
    {
        ArrayList<String> ans = new ArrayList<String>() {};
        courselist = read();
        for(int i=0;i<courselist.size();i++)
            ans.add(i,courselist.get(i).Course_ID+","+courselist.get(i).Course_Name);
        return ans;
    }
    public boolean addCourse ( CourseInfo a)throws IOException
    {
        courselist =read();
        int found=0;
        if(courselist.isEmpty())
        {
            writefile(a);
            courselist = read();
            return true;
        }
        else
        {
        for(int i= 0 ; i<courselist.size();i++)
        {
            if(courselist.get(i).Course_ID.equals(a.Course_ID))
                found=1;
        }
        /*for (CourseInfo courselist1 : courselist) {
                   System.out.println("1");
            if (courselist1.Course_ID.equals(a.Course_ID)) {
                found = 1;
                break;
            }
        }
                */
        if (found !=0)
        {
            return false;         
        }
        else
        {
            writefile(a);
            courselist = read();
            return true;
        }
        }
    }
    public boolean modifyCourse (String a,String b) throws IOException
    {
        courselist = read();
        int found=0;
        BufferedWriter obj;
        obj = new BufferedWriter(new FileWriter(System.getProperty("user.dir")+"/src/attendancesystem/Course.txt"));
	int i;
        for (i = 0; i<=courselist.size();i++) {
            if ((courselist.get(i).Course_ID).equals(a)) {
                found = 1;
                break;
            }
        }
        if (found !=0)
        {
            modify(a,b);
            return true;
        }
        else
        {
            return false;
        }
        
    }
    public boolean deleteCourse ( CourseInfo a)throws IOException
    {
        courselist = read();
        int found=0;
        BufferedWriter obj;
        obj = new BufferedWriter(new FileWriter(System.getProperty("user.dir")+"/src/attendancesystem/Course.txt"));
	int i;
        for (i = 0; i<=courselist.size();i++) {
            if ((courselist.get(i).Course_ID).equals(a.Course_ID)) {
                found = 1;
                break;
            }
        }
        if (found !=0)
        {
            remove(a.Course_ID);
            return true;
        }
        else
        {
            return false;
        }
        
    }
    public void modify(String a, String b) throws IOException
    {
        courselist =read();
        StringBuffer buff = new StringBuffer("");
        for (CourseInfo courselist1 : courselist) {
            if ((courselist1.Course_ID).equals(a)) {
                buff.append(a + "\t" + b + "\n");
            } else {
                buff.append(courselist1.Course_ID + "\t" + courselist1.Course_Name + "\n");
            }
        }
        String filepath = System.getProperty("user.dir")+"/src/attendancesystem/Course.txt" ; 
        FileWriter fw;
        fw = new FileWriter(filepath,true);
        fw.write(buff.toString());
        fw.close();
        
	
    }
    public void remove(String a) throws IOException
    {
        courselist =read();
        StringBuffer buff = new StringBuffer("");
        for (CourseInfo courselist1 : courselist) {
            if ((courselist1.Course_ID).equals(a)) {
                continue;
            } else {
                buff.append(courselist1.Course_ID + "\t" + courselist1.Course_Name + "\n");
            }
        }
        String filepath = System.getProperty("user.dir")+"/src/attendancesystem/Course.txt" ; 
        FileWriter fw;
        fw = new FileWriter(filepath,true);
        fw.write(buff.toString());
        fw.close();
        
	
    }
    public void writefile(CourseInfo a)throws IOException
    {
        System.out.println("writefile");
        String filepath = System.getProperty("user.dir")+"/src/attendancesystem/Course.txt" ; 
        FileWriter fw;
        fw = new FileWriter(filepath,true);
        fw.write(a.Course_ID+"\t"+a.Course_Name+"\n");
        fw.close();
        
    }
    public String getCourse(String a) throws IOException
    {
        courselist = read();
        int i;
        for (i = 0; i<courselist.size();i++) {
            if ((courselist.get(i).Course_ID).equals(a)) {
                return courselist.get(i).Course_Name;
            }
        }
        return null;
        
    }
    
}
