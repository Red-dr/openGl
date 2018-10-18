package particalsystem;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.Animator;



public class Particalsystem implements GLEventListener {
	
static ParticalSystemTest test;
	
	public void display(GLAutoDrawable gld) {
		// TODO Auto-generated method stub
		GL2 gl= gld.getGL().getGL2();
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
		gl.glColor3d(1, 0, 0);
		gl.glBegin(GL2.GL_LINES);
		gl.glVertex2f(0.0f, 0.0f);
		gl.glVertex2f(0.5f,0.5f);
		
		test.draw(gl);
		
			
		
		gl.glEnd();
		gl.glFlush();

	}

	
	public void dispose(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub

	}

	
	public void init(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub
		test = new ParticalSystemTest();

	}


	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Frame frame = new Frame( "simple JOGL Application");
		GLProfile profile = GLProfile.get(GLProfile.GL2);
		GLCapabilities capabilities = new GLCapabilities(profile);
		GLCanvas canvas = new GLCanvas();
		canvas.addGLEventListener(new Particalsystem());
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


