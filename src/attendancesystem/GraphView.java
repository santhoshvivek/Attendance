/*Santhosh Vivekanandan
 *sxv135530
 *Purpose: Assignment 4
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package attendancesystem;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * LIne Graph for the course selected
 * @author santhosh
 */
public class GraphView extends JFrame{
    static String Course;
    public static void main(String args)
    {
        Course = args;
        
        GraphView a = new GraphView();
        a.setVisible(true);
    }
    GraphView()
    {
        
        JPanel panel=new JPanel();
        getContentPane().add(panel);
        setSize(450,450);

    }
    @SuppressWarnings("empty-statement")
    public void paint(Graphics g) {
        try {
            Map<String,Integer> hm = new HashMap<String,Integer>();
            
            super.paint(g);
            int a =10;
            Graphics2D g1 = (Graphics2D) g;
            AttendanceSystem as =new AttendanceSystem();
            ArrayList<String> S = new ArrayList<>();
            S = as.getDates(Course);
            for(int i = 0 ; i<S.size();i++)
            {
                int val = as.getValueAt(Course, S.get(i));
                String date = S.get(i);
                hm.put(date, val);
            }
            int size =hm.size();
            g1.drawLine(30, 0, 30, 420);
            g1.drawLine(30,420, 1000, 420);
            int x =30;
            for(int k=0;k<10;k++)
            {
                g1.drawString(k+"", 20,420-(50*k) );
            }
            for(int i=1; i<size;i++)
            {
                g1.drawString(S.get(i-1), 80+(80*(i-1)), 430);
                int y1 = 420-(hm.get(S.get(i-1)) * 50);
                int y2 = 420-(hm.get(S.get(i))*50);
                int x1 = x+80;
                int x2 = x1+80;
                x+=80;
                g1.drawLine(x1, y1, x2, y2);
            }
            g1.drawString(S.get(size-1),80+(80*(size-1)),430);
        } catch (IOException ex) {
            Logger.getLogger(GraphView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}
