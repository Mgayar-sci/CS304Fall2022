package Cards;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Texture.TextureReader;
import java.awt.Component;
import java.awt.event.*;
import java.io.IOException;
import javax.media.opengl.*;
import javax.media.opengl.glu.GLU;

public class BouncingCardsGLEventListener implements GLEventListener, MouseListener {

    int animationIndex = 0;
    int maxWidth = 900;
    int maxHeight = 500;

    String textureNames[] = {"Round Cards//Q.png", "Round Cards//J.png", "Round Cards//K.png", "Round Cards//Back.png"};
    TextureReader.Texture texture[] = new TextureReader.Texture[textureNames.length];
    int textures[] = new int[textureNames.length];
    private int mouseX, mouseY;
    private float ballX, ballY;
    private boolean moveLeft, moveDown;
    private GLCanvas glc;
    private double slopeX = 0.5, slopeY = 0.5, speed=5;

    /*
     5 means gun in array pos
     x and y coordinate for gun 
     */
    public void init(GLAutoDrawable gld) {
        mouseX = 5 * maxWidth;
        mouseY = 5 * maxHeight;

        GL gl = gld.getGL();
        gl.glClearColor(0.0f, 1.0f, 0.0f, 1.0f);    //This Will Clear The Background Color To Black

        gl.glEnable(GL.GL_TEXTURE_2D);  // Enable Texture Mapping
        gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);
        gl.glGenTextures(textureNames.length, textures, 0);

        for (int i = 0; i < textureNames.length; i++) {
            try {
                texture[i] = TextureReader.readTexture("Assets" + "//" + textureNames[i], true);
                gl.glBindTexture(GL.GL_TEXTURE_2D, textures[i]);

//                mipmapsFromPNG(gl, new GLU(), texture[i]);
                new GLU().gluBuild2DMipmaps(
                        GL.GL_TEXTURE_2D,
                        GL.GL_RGBA, // Internal Texel Format,
                        texture[i].getWidth(), texture[i].getHeight(),
                        GL.GL_RGBA, // External format from image,
                        GL.GL_UNSIGNED_BYTE,
                        texture[i].getPixels() // Imagedata
                );
            } catch (IOException e) {
                System.out.println(e);
                e.printStackTrace();
            }
        }
        gl.glLoadIdentity();
        gl.glOrtho(-maxWidth / 2, maxWidth / 2, -maxHeight / 2, maxHeight / 2, -1, 1);
    }

    public void display(GLAutoDrawable gld) {

        GL gl = gld.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);       //Clear The Screen And The Depth Buffer

        if (moveLeft) {
            ballX -= slopeX*speed;
        } else {
            ballX += slopeX*speed;
        }

        if (moveDown) {
            ballY-=slopeY*speed;
        } else {
            ballY+=slopeY*speed;
        }

        if (!moveLeft && ballX + 75 > maxWidth / 2) {
            changeSlope();
            moveLeft = true;
        }else if (moveLeft && ballX - 75 < -maxWidth / 2) {
            changeSlope();
            moveLeft = false;
        }

        if (!moveDown && ballY + 75 > maxHeight / 2) {
            changeSlope();
            moveDown = true;
        } if (moveDown && ballY - 75 < -maxHeight / 2) {
            changeSlope();
            moveDown = false;
        }

        if (eqDistance(mouseX, mouseY, ballX, ballY) < 75) {
            DrawSprite(gl, ballX, ballY, 1, 200, 150);
        } else {
            DrawSprite(gl, ballX, ballY, 3, 200, 150);
        }
    }

    void changeSlope() {
        double cita = Math.random()*(Math.PI/2);
        slopeX = Math.cos(cita);
        slopeY = Math.sin(cita);
        System.out.println("Slope: " + slopeX + "," + slopeY);
    }

    double eqDistance(float x1, float y1, float x2, float y2) {
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }

    public void DrawSprite(GL gl, float x, float y, int index, float width, float height) {
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[index]);	// Turn Blending On

        gl.glPushMatrix();
        gl.glTranslated(x, y, 0);
        gl.glScaled(width / 2, height / 2, 1);
        //System.out.println(x +" " + y);
        gl.glBegin(GL.GL_QUADS);
        // Front Face
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-1.0f, 1.0f, -1.0f);
        gl.glEnd();
        gl.glPopMatrix();

        gl.glDisable(GL.GL_BLEND);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

//        System.out.println("clicked");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        double x = e.getX();
        double y = e.getY();
        Component c = e.getComponent();
        double width = c.getWidth();
        double height = c.getHeight();

        mouseX = (int) ((x / width) * maxWidth) - maxWidth / 2;
        mouseY = maxHeight / 2 - ((int) ((y / height) * maxHeight));
//        glc.repaint();
//        System.out.println("pressed");

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseX = 5 * maxWidth;
        mouseY = 5 * maxHeight;
//        System.out.println("released");
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    void setCanvas(GLCanvas glcanvas) {
        this.glc = glcanvas;
    }

}
