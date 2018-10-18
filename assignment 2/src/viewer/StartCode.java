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
import utils.Positions;
import utils.*;



public class StartCode implements GLEventListener, KeyListener {

	private static int WIN_HEIGHT = 800;
	private static int WIN_WIDTH = 1200;
	private Camera camera;
	private GLUT glut;
	private Positions pos;
	private Sun sun;
	private Planet murcury;
	private Planet venus;
	private Planet earth;
	private Planet moon;
	private Planet mars;
	private Planet phobos;
	private Planet deimos;
	private Color color;
	private Orbitline m_orbit;
	private Orbitline v_orbit;
	private Orbitline e_orbit;
	private Orbitline mars_orbit;
		
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
		pos.draw(gl);	
		camera.think();
		camera.draw(gl);
		
		gl.glPushMatrix();
		sun.draw(gl, glut);
		
		m_orbit.draw(gl);
		v_orbit.draw(gl);
		e_orbit.draw(gl);
		mars_orbit.draw(gl);
		//gl.glPopMatrix();
		
		//murcurry pushpop
		gl.glPushMatrix();
		murcury.think(gl,delta);
		gl.glTranslatef(murcury.x, 0, murcury.z);
		murcury.draw(gl, glut);
		gl.glPopMatrix();
		
		//venus push pop
		gl.glPushMatrix();		
		venus.think(gl, delta);
		gl.glTranslatef(venus.x, 0, venus.z);
		venus.draw(gl, glut);		
		gl.glPopMatrix();
		
		//earth pushpop
		gl.glPushMatrix();		
		earth.think(gl, delta);
		gl.glTranslatef(earth.x, 0, earth.z);
			
		//moon pushpop
		gl.glPushMatrix();		
		moon.think(gl, delta);
		gl.glTranslatef(moon.x, 0, moon.z);
		moon.draw(gl, glut);	
		gl.glPopMatrix();
		earth.draw(gl, glut);
		gl.glPopMatrix();
		
		//mars pushpop
		gl.glPushMatrix();		
		mars.think(gl, delta);
		gl.glTranslatef(mars.x, 0, mars.z);
		
		//phobos pushpop		
		gl.glPushMatrix();		
		phobos.think(gl, delta);
		gl.glTranslatef(phobos.x, 0, phobos.z);
		phobos.draw(gl, glut);
		gl.glPopMatrix();
		
		//deimos pushpop	
		gl.glPushMatrix();		
		deimos.think(gl, delta);		
		gl.glTranslatef(deimos.x, 0, deimos.z);					
		deimos.draw(gl, glut);
		
		gl.glPopMatrix();
		
		mars.draw(gl, glut);		
		gl.glPopMatrix();
		
		gl.glPopMatrix();
		
		
		//orbit.draw(gl);		
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
		
		sun = new Sun();
		pos = new Positions();
		camera = new Camera();
		camera.draw(gl);
		
		murcury = new Planet(1f,    0.39f,  88f,           new Color(1,0,0,0));
		venus = new Planet(2.449f,  0.72f,      224.7f,    new Color(184/255f,134/255f,11/255f,0f));
		earth = new Planet (1.083f, 1f,      365.2f,       new Color(30/255f,144/255f,1f, 0));
		moon = new Planet(0.289f,   0.013f,   27.3f,        new Color(190/255f,190/255f,190/255f,0f));
		
		mars = new Planet(0.523f,   1.52f,     687f,       new Color(1f,140/255,0f,0f));
		phobos = new Planet(0.0017f, 0.0000313f, 0.32f,    new Color(1f,218/255f,185/255f,0f));
		deimos = new Planet(0.002f,  0.007f,     1.3f,     new Color(244/255f,238/255f,224/25f,0f));
		
		m_orbit = new Orbitline(gl, 0.39f);
		v_orbit = new Orbitline(gl, 0.72f);
		e_orbit = new Orbitline(gl, 1f);
		mars_orbit = new Orbitline(gl, 1.52f);
		
		
		
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
		StartCode app = new StartCode();
		
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
        case KeyEvent.VK_A : { camera.sleft = true; break; }
        case KeyEvent.VK_D : { camera.sright = true; break; }
        case KeyEvent.VK_W : { camera.forward = true; break; }
        case KeyEvent.VK_S : { camera.back= true; break; }
//            case KeyEvent.VK_Q : { anglesDelta[2] = 0.0; break; }
//            case KeyEvent.VK_E : { anglesDelta[2] = 0.0; break; }
            case KeyEvent.VK_C : {camera.up();  break;}
            case KeyEvent.VK_E : {camera.down(); break;}
            case KeyEvent.VK_UP: {camera.pitchup = true; break;}
            case KeyEvent.VK_DOWN: {camera.pitchdown = true; break;}
            case KeyEvent.VK_LEFT: {camera.yawleft = true; break;}
            case KeyEvent.VK_RIGHT:{camera.yawright = true; break;}
            
        }
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch ( key )
        {
            case KeyEvent.VK_A : { camera.sleft = false; break; }
            case KeyEvent.VK_D : { camera.sright = false; break; }
            case KeyEvent.VK_W : { camera.forward = false; break; }
            case KeyEvent.VK_S : { camera.back= false; break; }
//            case KeyEvent.VK_Q : { anglesDelta[2] = 0.0; break; }
//            case KeyEvent.VK_E : { anglesDelta[2] = 0.0; break; }

        case KeyEvent.VK_UP: {camera.pitchup = false; break;}
        case KeyEvent.VK_DOWN: {camera.pitchdown = false; break;}
        case KeyEvent.VK_LEFT: {camera.yawleft = false; break;}
        case KeyEvent.VK_RIGHT:{camera.yawright = false; break;}
        }
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}


}
