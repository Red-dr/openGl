package viewer;
/** 
 * this was given to us in startcode but was adapted by me the matix was made but the push pop was me 
 */

import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.gl2.GLUT;

import utils.Camera;

import utils.*;



public class SeaTrench implements GLEventListener, KeyListener {

	private static int WIN_HEIGHT = 800;
	private static int WIN_WIDTH = 1200;
	private Camera camera;
	private GLUT glut;
	private Submarine submarine;
	
	
	//submarine body fields;


		
	//lighting
	float globalAmbient[] = { 0.4f, 0.4f, 0.4f, 1 }; 	// global light properties
	public float[] lightPosition = { 0.0f, 0.0f, 0.0f, 1.0f };
	public float[] ambientLight = { 0.5f, 0.5f, 0.5f, 1 };
	public float[] diffuseLight = { 0.5f, 0.5f, 0.5f, 0.8f };
	
	//animation
	private float theta = 0;
	double thisTick ;
	double prevTick;
	
	
	
	

	@Override
	public void display(GLAutoDrawable gld) {
		GL2 gl = gld.getGL().getGL2();
		
		// clear the depth and color buffers
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
		gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);
		gl.glEnable(GL2.GL_BLEND);
		gl.glBlendFunc(GL.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_CONSTANT_ALPHA);
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();
		thisTick = System.currentTimeMillis() / 1000.0;
		double delta = thisTick - prevTick;		
			
		camera.think();
		camera.draw(gl);
		
		
		gl.glPushMatrix();
		{
			submarine.think();
			submarine.draw(gl, glut);
		}		
		gl.glPopMatrix();
		//gl.glScalef(0.2f,0.1f,0.6f);
		gl.glColor3f(1, 1, 1);
		glut.glutSolidCube(1f);
		
		gl.glColor3f(0f, 1f, 1f);
		glut.glutWireCone(.5f, 1f, 60, 60);
				
		lights(gl);	
		prevTick = thisTick;
		gl.glDisable(GL2.GL_BLEND);
		gl.glFlush();
	}

	@Override
	public void dispose(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(GLAutoDrawable gld) {
		GL2 gl = gld.getGL().getGL2();
		gl.setSwapInterval(1);

		// enable depth test and set shading mode
		gl.glEnable(GL2.GL_DEPTH_TEST);
		gl.glShadeModel(GL2.GL_SMOOTH);
		gl.glEnable(GL2.GL_LIGHTING);
		gl.glEnable(GL2.GL_COLOR_MATERIAL);

		camera = new Camera();
		camera.draw(gl);
		
		submarine = new Submarine();
		glut = new GLUT();		

	}
	
	public void lights(GL2 gl){
		// set the global ambient light level
	    gl.glLightModelfv(GL2.GL_LIGHT_MODEL_AMBIENT, globalAmbient, 0); 
	    //set light 0 properties
	    gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_AMBIENT, ambientLight, 0);
		gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, diffuseLight, 0);
		//normalise the normal surface vectors
		gl.glEnable(GL2.GL_NORMALIZE);
		//position light 0 at the origin
		gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, lightPosition, 0);
		//enable light 0
		gl.glEnable(GL2.GL_LIGHT0);
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		camera.newWindowSize(width, height);
	}

	public static void main(String[] args) {
		Frame frame = new Frame("Inner Solar System Viewer");
		GLCanvas canvas = new GLCanvas();
		SeaTrench app = new SeaTrench();
		
		canvas.addGLEventListener(app);
		canvas.addKeyListener(app);
		frame.add(canvas);
		frame.setSize(WIN_WIDTH, WIN_HEIGHT);
		final FPSAnimator animator = new FPSAnimator(canvas, 60);
		frame.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				// Run this on another thread than the AWT event queue to
				// make sure the call to Animator.stop() completes before
				// exiting
				new Thread(new Runnable() {

					@Override
					public void run() {
						animator.stop();
						System.exit(0);
					}
				}).start();
			}
		});
		// Center frame
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		canvas.setFocusable(true);
		canvas.requestFocus();
		animator.start();


	}

	@Override
	public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch ( key )
        {
        	case KeyEvent.VK_A : { submarine.sleft = true; break; }
        	case KeyEvent.VK_D : { submarine.sright = true; break; }
        	case KeyEvent.VK_W : { submarine.forward = true; break; }
        	case KeyEvent.VK_S : { submarine.back= true; break; }
//            case KeyEvent.VK_Q : { anglesDelta[2] = 0.0; break; }
//            case KeyEvent.VK_E : { anglesDelta[2] = 0.0; break; }
            case KeyEvent.VK_C : {submarine.up();  break;}
            case KeyEvent.VK_E : {submarine.down(); break;}
            case KeyEvent.VK_UP: {submarine.pitchup = true; break;}
            case KeyEvent.VK_DOWN: {submarine.pitchdown = true; break;}
            case KeyEvent.VK_LEFT: {submarine.yawleft = true; break;}
            case KeyEvent.VK_RIGHT:{submarine.yawright = true; break;}
            
        }
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch ( key )
        {
            case KeyEvent.VK_A : { submarine.sleft = false; break; }
            case KeyEvent.VK_D : { submarine.sright = false; break; }
            case KeyEvent.VK_W : { submarine.forward = false; break; }
            case KeyEvent.VK_S : { submarine.back= false; break; }
//            case KeyEvent.VK_Q : { anglesDelta[2] = 0.0; break; }
//            case KeyEvent.VK_E : { anglesDelta[2] = 0.0; break; }

            case KeyEvent.VK_UP: {submarine.pitchup = false; break;}
            case KeyEvent.VK_DOWN: {submarine.pitchdown = false; break;}
            case KeyEvent.VK_LEFT: {submarine.yawleft = false; break;}
            case KeyEvent.VK_RIGHT:{submarine.yawright = false; break;}
        }
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}


}
