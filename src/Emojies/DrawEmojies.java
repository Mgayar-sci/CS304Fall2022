package Emojies;

import com.sun.opengl.util.FPSAnimator;
import java.awt.BorderLayout;
import javax.media.opengl.GLCanvas;
import javax.swing.JFrame;

public class DrawEmojies extends JFrame {

  private GLCanvas glcanvas;
    static FPSAnimator animator = null;
//  private DrawEmojiesGLEventListener1 listener = new DrawEmojiesGLEventListener1();
  private DrawEmojiesGLEventListener1 listener = new DrawEmojiesGLEventListener1();

  public static void main(String[] args) {
    new DrawEmojies();
    animator.start();
  }

  public DrawEmojies() {
    super("Simple Application");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    glcanvas = new GLCanvas();
    glcanvas.addGLEventListener(listener);
    animator = new FPSAnimator(glcanvas, 60);
    
    add(glcanvas, BorderLayout.CENTER);
    setSize(800, 800);
    setLocationRelativeTo(this);
    setVisible(true);
  }

}
