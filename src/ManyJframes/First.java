/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManyJframes;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author moham
 */
public class First extends JFrame implements ActionListener {

//    public static void main(String[] args) {
//        new First();
//    }


    public First() {
        setTitle("First");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JButton jb = new JButton();
        ImageIcon icon = new ImageIcon(getClass().getResource("/ManyJframes/b8.png"));
        Image img = icon.getImage() ;
        Image newimg = img.getScaledInstance(100,36,Image.SCALE_SMOOTH);
        jb.setIcon(new ImageIcon(newimg));
//        jb.setIcon(icon);
//        jb.setPreferredSize(new java.awt.Dimension(100, 50));
        jb.addActionListener(this);
        add(jb);
        setSize(900, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        setFocusable(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Second window = new Second();
        window.setVisible(true);
    }
}
