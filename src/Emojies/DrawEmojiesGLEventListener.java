package Emojies;

import java.awt.Color;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;

public class DrawEmojiesGLEventListener implements GLEventListener {

    int angle = 180;

    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        GL gl = glAutoDrawable.getGL();

        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();

        gl.glOrtho(-400, 400.0, -400, 400.0, -1.0, 1.0);
    }

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        GL gl = glAutoDrawable.getGL();

        gl.glClear(GL.GL_COLOR_BUFFER_BIT);

        drawCircle(gl, 300, Color.YELLOW, true, 0, 0);
        drawCircle(gl, 100, Color.WHITE, true, 150, 65);
        drawCircle(gl, 100, Color.WHITE, true, -150, 65);

//        double angleX = 50 * Math.cos(Math.toRadians(angle));
//        double angleY = 50 * Math.sin(Math.toRadians(angle));
//
//        drawCircle(gl, 50, Color.BLACK, true, 150 + angleX, 65 + angleY);
//        drawCircle(gl, 50, Color.BLACK, true, -150 - angleX, 65 + angleY);

        drawCircle(gl, 50, Color.BLACK, true, 150, 65, angle, 50);
        drawCircle(gl, 50, Color.BLACK, true, -150, 65, -angle, -50);

        drawCircle(gl, 100, Color.BLACK, true, 0, -150);

        angle++;
    }

    void drawCircle(GL gl, int r, Color c, boolean filled, double x, double y) {
        gl.glPushMatrix();
        gl.glTranslated(x, y, 0);
        drawPolyLines(gl, r, c, 360, 0, 1, filled);
        gl.glPopMatrix();
    }

    void drawCircle(GL gl, int r, Color c, boolean filled, double x, int y, int angle, int r1) {
        gl.glPushMatrix();
        gl.glTranslated(x, y, 0);
        gl.glRotated(angle, 0, 0, 1);
        gl.glTranslated(r1, 0, 0);
        drawPolyLines(gl, r, c, 360, 0, 1, filled);
        gl.glPopMatrix();
    }

    void drawPolyLines(GL gl, int r, Color c, double sides, double startAngle, int step, boolean filled) {
        gl.glColor3fv(c.getColorComponents(null), 0);
        if (filled) {
            gl.glBegin(GL.GL_POLYGON);
        } else {
            gl.glBegin(GL.GL_LINE_LOOP);
        }

        for (double i = startAngle; i < 360 * step + startAngle; i += step * 360.0 / sides) {
            gl.glVertex2d(r * Math.cos(Math.toRadians(i)), r * Math.sin(Math.toRadians(i)));
        }
        gl.glEnd();
    }

    void drawEllipse(GL gl, double a, double b, Color c, double sides, double startAngle, int step) {
        gl.glColor3fv(c.getColorComponents(null), 0);
        gl.glBegin(GL.GL_LINE_LOOP);
        for (double i = startAngle; i < 360 * step + startAngle; i += step * 360.0 / sides) {
            double r = a * b / (Math.sqrt(Math.pow(b * Math.cos(Math.toRadians(i)), 2) + Math.pow(a * Math.sin(Math.toRadians(i)), 2)));
            gl.glVertex2d(r * Math.cos(Math.toRadians(i)), r * Math.sin(Math.toRadians(i)));
        }
        gl.glEnd();
    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {

    }

    @Override
    public void displayChanged(GLAutoDrawable glAutoDrawable, boolean b, boolean b1) {

    }
}
