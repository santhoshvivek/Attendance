/*Santhosh Vivekanandan
 *sxv135530
 *Purpose: Assignment 4
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package attendancesystem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author santhosh
 */
public class Students {
    
    ArrayList<StudentInfo> StudentList = new ArrayList<StudentInfo>() {};
    
    public ArrayList<StudentInfo> read()throws IOException 
    {
        BufferedReader obj;
        obj = new BufferedReader(new FileReader(System.getProperty("user.dir")+"/src/attendancesystem/Student.txt"));
	String line;
        while((line = obj.readLine()) != null)
        {
            String data [] = line.split("\t");
            StudentList.add(new StudentInfo(data[0],data[1],data[2]));
        }
        obj.close();
        return  StudentList;
    }
    public boolean addStudent ( StudentInfo a)throws IOException
    {
        StudentList =read();
        int found=0;
        if(StudentList.isEmpty())
        {
            writefile(a);
            StudentList = read();
            return true;
        }
        else
        {
        for(int i= 0 ; i<StudentList.size();i++)
        {
            if(StudentList.get(i).ID.equals(a.ID))
                found=1;
        }
        /*for (StudentInfo StudentList1 : StudentList) {
                   System.out.println("1");
            if (StudentList1.Student_ID.equals(a.Student_ID)) {
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
            StudentList = read();
            return true;
        }
        }
    }
    public boolean modifyStudent (String a,String b,String c) throws IOException
    {
        StudentList = read();
        int found=0;
        BufferedWriter obj;
        obj = new BufferedWriter(new FileWriter(System.getProperty("user.dir")+"/src/attendancesystem/Student.txt"));
	int i;
        for (i = 0; i<=StudentList.size();i++) {
            if ((StudentList.get(i).ID).equals(a)) {
                found = 1;
                break;
            }
        }
        if (found !=0)
        {
            modify(a,b,c);
            return true;
        }
        else
        {
            return false;
        }
        
    }
    public boolean deleteStudent ( StudentInfo a)throws IOException
    {
        StudentList = read();
        int found=0;
        BufferedWriter obj;
        obj = new BufferedWriter(new FileWriter(System.getProperty("user.dir")+"/src/attendancesystem/Student.txt"));
	int i;
        for (i = 0; i<=StudentList.size();i++) {
            if ((StudentList.get(i).ID).equals(a.ID)) {
                found = 1;
                break;
            }
        }
        if (found !=0)
        {
            remove(a.ID);
            return true;
        }
        else
        {
            return false;
        }
        
    }
    public void modify(String a, String b, String c) throws IOException
    {
        StudentList =read();
        StringBuffer buff = new StringBuffer("");
        for (StudentInfo StudentList1 : StudentList) {
            if ((StudentList1.ID).equals(a)) {
                buff.append(a + "\t" + b + "\t" +c +"\n");
            } else {
                buff.append(StudentList1.ID + "\t" + StudentList1.fname + "\t" + StudentList1.lname +"\n");
            }
        }
        String filepath = System.getProperty("user.dir")+"/src/attendancesystem/Student.txt" ; 
        FileWriter fw;
        fw = new FileWriter(filepath,true);
        fw.write(buff.toString());
        fw.close();
        
	
    }
    public void remove(String a) throws IOException
    {
        StudentList =read();
        StringBuffer buff = new StringBuffer("");
        for (StudentInfo StudentList1 : StudentList) {
            if ((StudentList1.ID).equals(a)) {
                continue;
            } else {
                buff.append(StudentList1.ID + "\t" + StudentList1.fname +"\t"+StudentList1.lname+ "\n");
                
            }
        }
        String filepath = System.getProperty("user.dir")+"/src/attendancesystem/Student.txt" ; 
        FileWriter fw;
        fw = new FileWriter(filepath,true);
        fw.write(buff.toString());
        fw.close();
        
	
    }
    public void writefile(StudentInfo a)throws IOException
    {
        System.out.println("writefile");
        String filepath = System.getProperty("user.dir")+"/src/attendancesystem/Student.txt" ; 
        FileWriter fw;
        fw = new FileWriter(filepath,true);
        fw.write(a.ID+"\t"+a.fname+"\t"+a.lname+"\n");
        fw.close();
        
    }
    public StudentInfo getStudent(String a) throws IOException
    {
        StudentList = read();
        int i;
        for (i = 0; i<StudentList.size();i++) {
            if ((StudentList.get(i).ID).equals(a)) {
                return StudentList.get(i);
            }
        }
        return null;
        
    }
    public String getVStudent(String a) throws IOException
    {
        StudentList = read();
        int i;
        for (i = 0; i<StudentList.size();i++) {
            if ((StudentList.get(i).ID).equals(a)) {
                return StudentList.get(i).toString();
            }
        }
        return "";
        
    }
    
}
