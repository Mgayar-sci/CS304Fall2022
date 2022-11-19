package Events_GLCanvas;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.*;
import java.awt.event.*;
import javax.media.opengl.*;
import javax.swing.*;

/**
 *
 * @author Mohamed
 */
/**
 * This is a basic JOGL app. Feel free to reuse this code or modify it.
 */
public class KeyExample extends JFrame {

    static GLCanvas glcanvas = null;

    public static void main(String[] args) {
        final KeyExample app = new KeyExample();
// show what we've done
        SwingUtilities.invokeLater(
                new Runnable() {
                    public void run() {
                        app.setVisible(true);
                        glcanvas.requestFocusInWindow();
                    }
                }
        );
    }

    public KeyExample() {
//set the JFrame title
        super("KeyListener Example");
//kill the process when the JFrame is closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//create our KeyDisplay which serves two purposes
// 1) it is our GLEventListener, and
// 2) it is our KeyListener
        KeyDisplay kd = new KeyDisplay();
//only three JOGL lines of code ... and here they are
        glcanvas = new GLCanvas();
        glcanvas.addGLEventListener(kd);
        glcanvas.addKeyListener(kd);
//we'll want this for our repaint requests
        kd.setGLCanvas(glcanvas);
//add the GLCanvas just like we would any Component
        getContentPane().add(glcanvas, BorderLayout.CENTER);
        setSize(500, 300);
//center the JFrame on the screen
        centerWindow(this);
    }

    public void centerWindow(Component frame) {
        Dimension screenSize
                = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = frame.getSize();

        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }
        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }
        frame.setLocation(
                (screenSize.width - frameSize.width) >> 1,
                (screenSize.height - frameSize.height) >> 1
        );
    }
}

class KeyDisplay implements GLEventListener,
        KeyListener {

    int xPosition = 25;
    int yPosition = 25;
    float red = 0.0f;
    float green = 0.0f;
    float blue = 1.0f;
    GLCanvas glc;

    public void setGLCanvas(GLCanvas glc) {
        this.glc = glc;
    }

    /**
     * Take care of initialization here.
     */
    public void init(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        red = 0.0f;
        green = 0.0f;
        blue = 1.0f;
        gl.glClearColor(red, green, blue, 0.0f);
        gl.glViewport(0, 0, 50, 50);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(0.0, 50, 0, 50, -1, 1);
    }

    /**
     * Take care of drawing here.
     */
    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
//Remember point size refers
//to pixels, not the coordinate
//system we've set up in the
//GLCanvas
        gl.glPointSize(6.0f);
        red = 0.0f;
        green = 1.0f;
        blue = 0.0f;
        gl.glColor3f(red, green, blue);
        gl.glBegin(GL.GL_POINTS);
            gl.glVertex2i(xPosition, yPosition);
        gl.glEnd();
    }

    /**
     * Called when the GLDrawable (GLCanvas or GLJPanel) has changed in size. We
     * won't need this, but you may eventually need it -- just not yet.
     */
    public void reshape(
            GLAutoDrawable drawable,
            int x,
            int y,
            int width,
            int height
    ) {
    }

    /**
     * If the display depth is changed while the program is running this method
     * is called. Nowadays this doesn't happen much, unless a programmer has his
     * program do it.
     */
    public void displayChanged(
            GLAutoDrawable drawable,
            boolean modeChanged,
            boolean deviceChanged
    ) {
    }
////////////////////////////////////
// KeyListener implementation below

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyChar()+"\t"+e.getKeyCode());
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            yPosition += 1;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            yPosition -= 1;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            xPosition -= 1;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            xPosition += 1;
        }
        glc.repaint();
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_8) {
            yPosition += 1;
        } else if (e.getKeyChar() == KeyEvent.VK_2) {
            yPosition -= 1;
        } else if (e.getKeyChar() == KeyEvent.VK_4) {
            xPosition -= 1;
        } else if (e.getKeyChar() == KeyEvent.VK_6) {
            xPosition += 1;
        }
        glc.repaint();
    }
}
