/*Santhosh Vivekanandan
 *sxv135530
 *Purpose: Assignment 4
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package attendancesystem;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Enter Attendance UI
 * @author santhosh
 */
public class EnterAttendUI {
    static String cid, date;
    
    public static void main(String args, String d) throws IOException
    {
        cid = args;
        date = d;
        EnterAttendUI add = new EnterAttendUI();
        
    }
    public EnterAttendUI() throws IOException
    {
        final JFrame frame1 = new JFrame();
        frame1.setTitle("Add Attendance: "+cid+","+date);
        Object columnHeaders[] = { "Student ID", "Student Name", "Attendance" };
        ArrayList<StudentInfo> stud = new ArrayList<>();
        final AttendanceSystem att = new AttendanceSystem();
        stud = att.EnterAtt(cid);
        Object[][] data = new Object[stud.size()][3];
        for(int i =0;i<stud.size();i++)
        {
            StudentInfo s= new StudentInfo();
            s = stud.get(i);
            data[i][0] = (Object)s.ID;
            data[i][1] = (Object)(s.fname+" "+s.lname);
            data[i][2] = false;
        }
        
        DefaultTableModel model = new DefaultTableModel(data, columnHeaders);
        final JTable jTable1 = new JTable(model){
            private static final long serialVersionUID = 1L;
             @Override
            public Class getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return Boolean.class;
                    default:
                        return String.class;
                }
        }
        };
        JPanel p1 = new JPanel(new GridLayout(1,3));
        JPanel p2 = new JPanel(new GridLayout(1,3));
        JButton dummy = new JButton(" ");
        JButton dummy1 = new JButton(" ");
        JButton dummy2 = new JButton(" ");
        JButton dummy3 = new JButton(" ");
        final JButton button = new JButton("Select All");
        JButton add = new JButton("Finish");
        p1.add(dummy);
        p1.add(dummy1);
        p1.add(button);
        p2.add(dummy2);
        p2.add(add);
        p2.add(dummy3);
        JScrollPane scrollPane = new JScrollPane(jTable1);
        frame1.add(scrollPane,BorderLayout.CENTER);
        frame1.add(p1,BorderLayout.NORTH);
        frame1.add(p2,BorderLayout.SOUTH);
        dummy.setVisible(false);
        dummy2.setVisible(false);
        dummy3.setVisible(false);
        dummy1.setVisible(false);
        frame1.setSize(400,400);
        frame1.setVisible(true);
        frame1.setDefaultCloseOperation(EXIT_ON_CLOSE);
        add.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae) {
                ArrayList<AttendanceInfo> enter = new ArrayList();
                for(int i=0; i<jTable1.getRowCount();i++)
                {
                    AttendanceInfo attinfo = new AttendanceInfo();
                    attinfo.StudentName = (String) jTable1.getValueAt(i,0);
                    attinfo.StudentID = (String) jTable1.getValueAt(i, 1);
                    attinfo.AttValue = (boolean) jTable1.getValueAt(i, 2);
                    attinfo.CourseID = cid;
                    attinfo.Attdate = date;
                    enter.add(attinfo);
                    
                }
                try {
                    boolean c = att.writeAtt(enter);
                } catch (IOException ex) {
                    Logger.getLogger(EnterAttendUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                frame1.setVisible(false);
        }});
            

        button.addActionListener(new ActionListener()   {

            @Override
            public void actionPerformed(ActionEvent ae) {
                if(button.getText().equals("Select All"))
                {
                    button.setText("Deselect All");
                for(int i=0; i<jTable1.getRowCount();i++)
                    {
                            jTable1.setValueAt(true, i, 2);                        
                    }
                }
                else{
                    button.setText("Select All");
                     for(int i=0; i<jTable1.getRowCount();i++)
                    {
                            jTable1.setValueAt(false, i, 2);                        
                    }
                }
            }
            
        });
    }
    
    
}
