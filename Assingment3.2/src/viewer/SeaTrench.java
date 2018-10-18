

package viewer;

import java.awt.Frame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;


import scene.*;
import scene.Submarine.*;

public class SeaTrench implements GLEventListener, KeyListener {

	// window size
	private static int WIN_HEIGHT = 800;
	private static int WIN_WIDTH = 1200;

	//scene objects
	private Submarine submarine;
	private TrackingCamera camera;
	private Grid grid;

	//lighting
	float globalAmbient[] = { 0.4f, 0.4f, 0.4f, 1 }; // global light properties
	public float[] lightPosition = { 0.0f, 7.0f, 0.0f, 1.0f };
	public float[] ambientLight = { 0.5f, 0.5f, 0.5f, 1 };
	public float[] diffuseLight = { 0.5f, 0.5f, 0.5f, 0.8f };
	
	//animation
	double prevTick = 0;
	
	//fog
	float fogColor[]= {0f,0f,.5f,1};
	
	//textures
	Texture textures ; 
	private boolean clampS = false;
	private boolean clampT = false;


	@Override
	public void init(GLAutoDrawable gld) {
		GL2 gl = gld.getGL().getGL2();
		gl.setSwapInterval(1);

		// enable depth test and set shading mode
		gl.glEnable(GL2.GL_DEPTH_TEST);
		gl.glShadeModel(GL2.GL_SMOOTH);
		gl.glEnable(GL2.GL_LIGHTING);
		gl.glEnable(GL2.GL_COLOR_MATERIAL);
		
		///fog data
		gl.glFogfv(GL2.GL_FOG_COLOR,fogColor,0);
		gl.glFogf(GL2.GL_FOG_MODE,GL2.GL_LINEAR);
		gl.glFogf(GL2.GL_FOG_START, 1.0f);
		gl.glFogf(GL2.GL_FOG_END, 20f);
		gl.glFogf(GL2.GL_FOG_DENSITY, 0.8f);
	
		try {
			textures = TextureIO.newTexture(new File("./tex/sand_tileable.jpg"), true);

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		submarine = new Submarine();
		camera = new TrackingCamera(submarine);
		grid = new Grid(-50, 50, 100);
	}
	
	@Override
	public void display(GLAutoDrawable gld) {
		GL2 gl = gld.getGL().getGL2();
		gl.glClearColor(fogColor[0], fogColor[1], fogColor[2], 0);
		// clear the depth and color buffers
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
		gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);
		gl.glEnable(GL2.GL_BLEND);
		gl.glEnable(GL2.GL_FOG);

		
		gl.glBlendFunc(GL.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_CONSTANT_ALPHA);
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();

		// update tick
		double thisTick = System.currentTimeMillis() / 1000.0;
		double delta = thisTick - prevTick;
		
			
		camera.think(delta);
		camera.draw(gl);
		
		submarine.think(delta);
		submarine.draw(gl);
		
		
//		drawTexturedPlane(gl);
		drawTexture(gl);
		
		//gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_LINE);
		
		grid.draw(gl);
		//gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_FILL);
		
		lights(gl);	
		
		gl.glDisable(GL2.GL_BLEND);
		gl.glDisable(GL2.GL_FOG);
		prevTick = thisTick;
		gl.glFlush();
	}

	@Override
	public void dispose(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub

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
		Frame frame = new Frame("Sea Trench");
		GLCanvas canvas = new GLCanvas();
		SeaTrench app = new SeaTrench();
		
		canvas.addGLEventListener(app);
		canvas.addKeyListener(app);

		final FPSAnimator animator = new FPSAnimator(canvas, 60);

		frame.add(canvas);
		frame.setSize(WIN_WIDTH, WIN_HEIGHT);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				new Thread(new Runnable() {
					@Override
					public void run() {
						animator.stop();
						System.exit(0);
					}
				}).start();
			}
		});
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
        	case KeyEvent.VK_A : { submarine.yaw_left = true; break; }
    		case KeyEvent.VK_D : { submarine.yaw_right = true; break; }
        	case KeyEvent.VK_W : { submarine.forward = true; break; }
        	case KeyEvent.VK_S : { submarine.backward = true; break; }
        	case KeyEvent.VK_UP: { submarine.up(); break;}
        	case KeyEvent.VK_DOWN:{submarine.down();break;}

        }
	}

	@Override
	public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch ( key )
        {
        	case KeyEvent.VK_A : { submarine.yaw_left = false; break; }
        	case KeyEvent.VK_D : { submarine.yaw_right = false; break; }
        	case KeyEvent.VK_W : { submarine.forward = false; break; }
        	case KeyEvent.VK_S : { submarine.backward = false; break; }

        }
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	private void drawTexture(GL2 gl) {
		gl.glEnable(GL2.GL_TEXTURE_2D);
		gl.glMatrixMode(GL2.GL_TEXTURE);
        gl.glLoadIdentity();
		Texture tex = textures;
		// bind the texture
		tex.bind(gl);
		// set clamping parameters
		tex.setTexParameteri(gl, GL2.GL_TEXTURE_WRAP_S, clampS ? GL2.GL_CLAMP : GL2.GL_REPEAT);
		tex.setTexParameteri(gl, GL2.GL_TEXTURE_WRAP_T, clampT ? GL2.GL_CLAMP : GL2.GL_REPEAT);
		tex.setTexParameteri(gl, GL2.GL_TEXTURE_MIN_FILTER, GL2.GL_LINEAR);
		tex.setTexParameteri(gl, GL2.GL_TEXTURE_MAG_FILTER, GL2.GL_LINEAR);
		
	

        gl.glMatrixMode(GL2.GL_MODELVIEW);
	}
	

}



