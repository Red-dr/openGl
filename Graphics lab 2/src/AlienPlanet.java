import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.Animator;
import com.jogamp.opengl.util.gl2.GLUT;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.util.Random;

public class AlienPlanet implements GLEventListener, MouseListener {

	static Random rand = new Random();
	static Points[] star = new Points[50];
	static Land[] l;
	static Sky sky;
	static Asteroid ast;
	static PartacalSystem psystem;
	static ButtenPanel bPan;
	static Ship ship;
	static Time t;
	// static Star star;
	boolean asteroids = false;
	boolean time = false;
	boolean twinkle = false;
	boolean spaceship = false;
	boolean atmospheare = false;
	int listIndex = 1;
	int landList;
	int starlist;

	private int windowWidth = 1280;
	private int windowHeight = 920;

	@Override
	public void display(GLAutoDrawable gld) {
		// TODO Auto-generated method stub
		GL2 gl = gld.getGL().getGL2();
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
		// gl.glEnable(GL2.GL_DEPTH_TEST);
		gl.glEnable(GL2.GL_BLEND);
		gl.glBlendFunc(GL.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_CONSTANT_ALPHA);
//this bit of code was made by a friend who helped me with the first lecture
		if (twinkle) {
			for (Points p : star) {
				p.think(gl);
				gl.glColor4d(1, .8, .8, p.opacity);
				gl.glPointSize(4);
				gl.glVertex2f(p.x, p.y);
			}
		}

		t.draw(gl);
		if (atmospheare) {
			sky.draw(gl);
		}
		if(time){
			t.think();
		}
		 t.think();
		gl.glCallList(starlist);
		for (Points p : star) {
			// p.think(gl);
			if (twinkle) {
				p.think(gl);
			}
			gl.glPointSize(4);
			gl.glBegin(GL2.GL_POINTS);
			gl.glColor4d(1, .8, .8, p.opacity);
			gl.glVertex2f(p.x, p.y);
		}
		
		gl.glEnd();
		gl.glDisable(GL2.GL_BLEND);

		if (asteroids) {
			psystem.draw(gl);
			psystem.think();
		}
		gl.glCallList(landList);
		bPan.draw(gl);
		if (spaceship) {
			ship.draw(gl);
			ship.think();
		}

		gl.glFlush();

	}

	@Override
	public void dispose(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub

	}
//this bit was made my be 
	private void createStarList(GL2 gl, int DLindex) {
		starlist = DLindex;
		gl.glNewList(starlist, GL2.GL_COMPILE);
		// star.draw(gl);
		for (Points p : star) {
			// p.think(gl);
			gl.glPointSize(4);
			gl.glBegin(GL2.GL_POINTS);
			gl.glColor4d(1, .8, .8, p.opacity);
			gl.glVertex2f(p.x, p.y);
			// gl.glEnd();
		}
		gl.glEndList();

	}
//this was also made by me although it apprently doesnt work
	private void createLandList(GL2 gl, int DLindex) {
		landList = DLindex;
		gl.glNewList(landList, GL2.GL_COMPILE);
		l[0].draw(gl);
		l[1].draw(gl);
		l[2].draw(gl);
		gl.glEndList();
	}

	@Override
	public void init(GLAutoDrawable arg0) {
		GL2 gl = arg0.getGL().getGL2();
		listIndex = gl.glGenLists(2);

		for (int i = 0; i < star.length; i++) {
			star[i] = new Points((rand.nextFloat() * 2 - 1), (rand.nextFloat() * 2 - 1));
			//System.out.println("star " + i + " " + star[i].x + " " + star[i].y);
		}

		l = new Land[3]; //

		l[0] = new Land(0f, -0.5f, new Color(249f / 255f, 66f / 255f, .0f, 1f));
		l[1] = new Land(-0.2f, -0.7f, new Color(1f, 128f / 255f, .0f, 1f));
		l[2] = new Land(-.8f, -0.9f, new Color(250f / 255f, 201f / 255f, 0f, 1f));
		createLandList(gl, listIndex);
		// createStarList(gl, listIndex+1);

		sky = new Sky();

		psystem = new PartacalSystem();
		psystem.init();
		bPan = new ButtenPanel();
		ship = new Ship(.5f, .5f);
		t = new Time();

	}

	@Override
	public void reshape(GLAutoDrawable canvas, int x, int y, int width, int height) {
		height = (height == 0) ? 1 : height;
		windowWidth = width;
		windowHeight = height;
	}

	public static void main(String[] args) {

		// TODO Auto-generated method stub
		Frame frame = new Frame("simple JOGL Application");
		GLProfile profile = GLProfile.get(GLProfile.GL2);
		GLCapabilities capabilities = new GLCapabilities(profile);
		GLCanvas canvas = new GLCanvas();
		AlienPlanet lab = new AlienPlanet();
		canvas.addGLEventListener(lab);
		canvas.addMouseListener(lab);
		frame.add(canvas);
		frame.setSize(1280, 920);
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

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}
//this bit of code was created by me once my firend helped me with the saving on the butten positions
	@Override
	public void mouseReleased(MouseEvent e) {
		float mouseX = e.getX();
		float mouseY = windowHeight - e.getY();

		float openglX = 2.0f * (mouseX / windowWidth) - 1.0f;
		float openglY = 2.0f * (mouseY / windowHeight) - 1.0f;

		// System.out.println("CLICK: " + openglX + "," + openglY);

		for (int i = 0; i < 6; ++i) {
			if (openglX >= bPan.bTop[i].bPoint[0].x && openglX <= bPan.bTop[i].bPoint[3].x
					&& openglY >= bPan.bTop[i].bPoint[0].y && openglY <= bPan.bTop[i].bPoint[1].y) {

				String press = bPan.bTop[i].label;

				switch (press) {
				case "Asteroids":

					if (asteroids) {
						asteroids = false;
						bPan.bTop[0].toggle();
					} else {
						asteroids = true;
						bPan.bTop[0].toggle();
					}
					System.out.println(asteroids);
					break;

				case "Twinkle":
					if (twinkle) {
						twinkle = false;
						bPan.bTop[4].toggle();
					} else {
						twinkle = true;
						bPan.bTop[4].toggle();
					}
					break;

				case "Atmosphere":
					if (atmospheare) {
						atmospheare = false;
						bPan.bTop[1].toggle();
					} else {
						atmospheare = true;
						bPan.bTop[1].toggle();
					}
					break;

				case "Spaceship":
					if (spaceship) {
						spaceship = false;
						bPan.bTop[3].toggle();
					} else {
						spaceship = true;
						bPan.bTop[3].toggle();
					}
					break;
					
				case "Time":
					if (time) {
						time = false;
						bPan.bTop[2].toggle();
					} else {
						time = true;
						bPan.bTop[2].toggle();
					}
					break;
				case "Earth Quake":
					if (time) {
						
						bPan.bTop[5].toggle();
					} else {
						
						bPan.bTop[5].toggle();
					}
					break;
				}

			}
		}

	}

}
