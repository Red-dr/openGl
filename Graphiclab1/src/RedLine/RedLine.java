package RedLine;

import java.awt.Frame;
import java.awt.event.WindowEvent;
import java.util.Random;
import java.awt.event.WindowAdapter;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.Animator;

public class RedLine implements GLEventListener {

	Random rand = new Random();
	@Override
	public void display(GLAutoDrawable gld) {
		// TODO Auto-generated method stub
		GL2 gl= gld.getGL().getGL2();
		gl.glEnable(GL2.GL_BLEND);
		gl.glBlendFunc(GL.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_CONSTANT_ALPHA);
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
		
		
		for( int i=0 ;i < 50 ;++i){
			gl.glPointSize(4);
			gl.glBegin(GL2.GL_POINTS);
			gl.glColor4d(1, 1, 1, 1);
			gl.glVertex2f((rand.nextFloat()*2-1), (rand.nextFloat()*2-1));
		}
//		gl.glColor3d(1, 0, 0);
//		gl.glBegin(GL2.GL_LINES);
//		gl.glVertex2f(0.0f, 0.0f);
//		gl.glVertex2f(0.5f,0.5f);
		gl.glEnd();
		gl.glFlush();
		
		
		for (Points p : star) {
			p.think(gl);		
			gl.glPointSize(4);
			gl.glBegin(GL2.GL_POINTS);
			gl.glColor4d(1, .8, .8, p.opacity);
			gl.glVertex2f(p.x, p.y);
		}
			
		
		

	}

	@Override
	public void dispose(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Frame frame = new Frame( "simple JOGL Application");
		GLProfile profile = GLProfile.get(GLProfile.GL2);
		GLCapabilities capabilities = new GLCapabilities(profile);
		GLCanvas canvas = new GLCanvas();
		canvas.addGLEventListener(new RedLine());
		frame.add(canvas);
		frame.setSize(640, 480);
		final Animator animator = new Animator(canvas);
		  frame.addWindowListener(new WindowAdapter() {
	        	public void windowClosing(WindowEvent e) {
	                // Run this on another thread than the AWT event queue to
	                // make sure the call to Animator.stop() completes before
	                // exiting
	                new Thread(new Runnable() {

	                    public void run() {
	                        animator.stop();
	                        System.exit(0);
	                    }
	                }).start();
	            }
	        });
		
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		animator.start();
		

	}

}
