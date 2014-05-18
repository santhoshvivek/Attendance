/*Santhosh Vivekanandan
 *sxv135530
 *Purpose: Assignment 4
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package attendancesystem;

import java.util.Date;

/**
 * Information Expert for Attendance
 * @author santhosh
 */
public class AttendanceInfo {
    String StudentID;
    String StudentName;
    String CourseID;
    boolean AttValue;
    String Attdate;
    
    AttendanceInfo()
    {
        
    }
    
    AttendanceInfo(String a, String b, String c, boolean d, String e)
    {
        this.StudentID = a;
        this.StudentName=b;
        this.CourseID = c;
        this.AttValue=d;
        this.Attdate=e;
        
    }
    
}
