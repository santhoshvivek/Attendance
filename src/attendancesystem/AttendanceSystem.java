/*Santhosh Vivekanandan
 *sxv135530
 *Purpose: Assignment 4
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package attendancesystem;

import java.io.IOException;
import java.util.ArrayList;

/**
 *This class acts as the Controller for the Attendance Maintenance System
 * @author santhosh
 */
public class AttendanceSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
      Menu a = new Menu();
      a.main();
    }
    /*
    This method Returns all the Courses in the System in the form of ArrayList of String     
    */
    public ArrayList<String> getCourse()throws IOException
    {
        Courses course = new Courses();
        return course.GetAll();
    }
    /*
    This method deletes the course given the course id and name
    */
    public boolean delcourse(String a,String b) throws IOException
    {
        Courses course= new Courses();
        CourseInfo c = new CourseInfo(a,b);
        return course.deleteCourse(c);
    }
    /*    
    This method modifies the course.
    Having the parameters course id and new name
    */
    public boolean modcourse(String CID, String newname) throws IOException
    {
        Courses course = new Courses();
        return course.modifyCourse(CID, newname);
        
    }
    /*  
    This method returns the name of the courseID given.
    */
    public String getName(String CID ) throws IOException
    {
        Courses a= new Courses();
        return a.getCourse(CID);
    }
    /*
    This Method adds the Course to the system given the ID and Name of the course
    */
    public boolean addcourse(String a,String b) throws IOException
    {
        Courses course= new Courses();
        CourseInfo c = new CourseInfo(a,b);
        return course.addCourse(c);
    }
    /*
    This Method adds the student to the system.
    */
    public boolean addstud(String a, String b, String c)throws IOException
    {
        Students s = new Students();
        StudentInfo stud = new StudentInfo(a,b,c);
        return s.addStudent(stud);
    }
    /*
    This method deletes the student from the system.
    */
    public boolean delstud(String a, String b, String c)throws IOException
    {
        Students s = new Students();
        StudentInfo stud = new StudentInfo(a,b,c);
        return s.deleteStudent(stud);
    }
    /*
    This method modifies the student details in the system
    */
    public boolean modstud(String a, String b, String c)throws IOException
    {
        Students s = new Students();
        return s.modifyStudent(a, b, c);
    }
    /*
    This Method returns the student name given the Student ID
    */
    public String[] getstud(String a)throws IOException
    {
        Students s = new Students();
        StudentInfo stud = s.getStudent(a);
        if(stud.fname.isEmpty())
        {
            String ans[] = {"",""};
            return ans;
        }
        String ans[] = {stud.fname,stud.lname};
        return ans;
    }
    /*
    This method checks if there is student with that student ID
    */
    public boolean getValidStud(String a)throws IOException
    {
        Students s = new Students();
        String stud = s.getVStudent(a);
        if(stud.equals(""))
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    /*
    This method enrolls the student into that course
    */
    public boolean enroll(String a,String b)throws IOException
    {
        Enrollment service = new Enrollment();
        boolean ans = service.add(a, b);
        return ans;
    }
    /*    
    This method returns the ArrayList to populate the attendance interface
    */
    public ArrayList<StudentInfo> EnterAtt(String c)throws IOException
    {
        Attendance a = new Attendance();
        return a.read(c);
    }
    /*    
    This method writes the attendance marked in the table. 
    */
    public boolean writeAtt(ArrayList<AttendanceInfo> att)throws IOException
    {
        Attendance a= new Attendance();
        return a.writefile(att);
    }
    public ArrayList<AttendanceInfo> getAllAtt()throws IOException
    {
        Attendance a= new Attendance();
        return a.read();
    }
    public ArrayList<AttendanceInfo> getAllAtt(String d)throws IOException
    {
        Attendance a= new Attendance();
        return a.readDate(d);
    }

    public ArrayList<AttendanceInfo> ModifyAtt(String cid, String date) throws IOException 
    {
        Attendance a= new Attendance();
        return a.modifyget(cid, date);
    }
    public boolean ModifyAttIn(ArrayList<AttendanceInfo> att,String c,String d) throws IOException
    {
        Attendance a= new Attendance();
        return a.modAtt(att,c,d);
    }
    public ArrayList<AttendanceInfo> generateReport(String Course)throws IOException
    {
        Report r = new Report();
        return r.generateReport(Course);
    }
    public ArrayList<String> getDates(String Course) throws IOException
    {
        Report r = new Report();
        return r.getDates(Course);
    }
    public ArrayList<AttendanceInfo> getValues(String Course,String name) throws IOException
    {
        Report r = new Report();
        return r.getValue(Course, name);
    }
    
    public int getValueAt(String Course,String date) throws IOException
    {
        Report r = new Report();
        return r.getValueAt(Course, date);
    }
    
}
