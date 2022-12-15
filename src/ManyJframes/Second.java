/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManyJframes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author moham
 */
public class Second extends JFrame implements ActionListener {

    public Second() {
        setTitle("Second");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
//        JButton jb = new JButton("Open new frame");
//        jb.addActionListener(this);
//        add(jb);
        setSize(900, 500);
        setLocationRelativeTo(null);
//        setVisible(true);
        setFocusable(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
}
