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
 * Model View Controller
 * @author santhosh
 */
public class Report {
    
    public ArrayList<AttendanceInfo> generateReport(String Course) throws IOException
    {
        Attendance att = new Attendance();
        ArrayList<AttendanceInfo> report = new ArrayList<AttendanceInfo>(){};
        report = att.readDate(Course);
        return report;
    }
    
    public ArrayList<String> getDates(String Course)throws IOException
    {
        Attendance att = new Attendance();
        ArrayList<String> dates = new ArrayList<String>(){};
        ArrayList<AttendanceInfo> report = new ArrayList<AttendanceInfo>(){};
        report = att.readDate(Course);
        for (AttendanceInfo report1 : report) 
        {
            int found=0;
            String date = report1.Attdate;
            for (String date1 : dates) 
            {
                if(date.equals(date1))
                {
                    found=1;
                }
            }
            if(found==0)
            {
                dates.add(date);
            }
        }
        return dates;
    }
    public ArrayList<AttendanceInfo> getValue(String Course, String name)throws IOException
    {
        Attendance att = new Attendance();
        ArrayList<AttendanceInfo> report = new ArrayList<AttendanceInfo>(){};
        report = att.readDate(Course);
        ArrayList<AttendanceInfo> ans = new ArrayList<AttendanceInfo>(){};
        for (AttendanceInfo report1 : report) 
        {
            if((report1.StudentName).equals(name)&&(report1.CourseID).equals(Course))
            {
                ans.add(report1);
            }
        }
        return ans;
    }
    public int getValueAt(String Course,String date)throws IOException
    {
        int count=0;
        Attendance att = new Attendance();
        ArrayList<AttendanceInfo> report = new ArrayList<AttendanceInfo>(){};
        report = att.readDate(Course);
        for (AttendanceInfo report1 : report) 
        {
            if(report1.AttValue && (report1.Attdate).equals(date))
                count++;
        }
        return count;
    }
    
}
