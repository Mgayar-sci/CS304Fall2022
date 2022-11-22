package Emojies;

import com.sun.opengl.util.FPSAnimator;
import java.awt.BorderLayout;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.swing.JFrame;

public class DrawEmojies extends JFrame {

  private GLCanvas glcanvas;
  static FPSAnimator animator = null;
  static DrawEmojiesGLEventListener wowListener = new DrawEmojiesGLEventListener();
  static DrawEmojiesGLEventListener1 starListener = new DrawEmojiesGLEventListener1();

  public static void main(String[] args) {
    int choice = 0;
    GLEventListener[] Listeners = { wowListener, starListener };
    if (args.length > 0) {
      try {
        choice = Integer.parseInt(args[0]);
        new DrawEmojies(Listeners[choice]);
        animator.start();
      } catch (ArrayIndexOutOfBoundsException e) {
        System.err.println("Only 2 listeners are available!");
      } catch (NumberFormatException e) {
        System.err.println("Please enter an integer!");
      }
    }
  }

  public DrawEmojies(GLEventListener listener) {
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
