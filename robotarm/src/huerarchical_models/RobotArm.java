package huerarchical_models;

import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;
import com.jogamp.opengl.util.FPSAnimator;


public class RobotArm implements GLEventListener, KeyListener {

    // dimensions of the base of the robot
    private static final double BASE_HEIGHT      = 1.0;
    private static final double BASE_RADIUS      = 0.5;
    // dimensions of the lower arm of the robot
    private static final double LOWER_ARM_HEIGHT = 1.5;
    private static final double LOWER_ARM_RADIUS = 0.3;
    // dimensions of the upper arm of the robot
    private static final double UPPER_ARM_HEIGHT = 1.5;
    private static final double UPPER_ARM_RADIUS = 0.2;
    

    private double lastTick = -1;

    private double angles[];
    private double anglesDelta[];

    private GLU glu;
    private GLUquadric quadric;
    private static GLCanvas canvas;

    private TrackballCamera camera;


    public static void main(String[] args) {
        Frame frame = new Frame("Robot Arm");
        canvas = new GLCanvas();

        System.out.println("Key mapping:");
        System.out.println("A/D: Rotate base");
        System.out.println("W/S: Rotate lower arm");
        System.out.println("Q/E: Rotate upper arm");
        System.out.println("R  : Reset");
        System.out.println("Left  mouse button: Rotate scene");
        System.out.println("Right mouse button: Change camera distance");
        System.out.println("Mouse wheel       : Zoom");

        RobotArm app = new RobotArm();
        canvas.addGLEventListener(app);
        canvas.addKeyListener(app);
        
        frame.add(canvas);
        frame.setSize(500, 500);
        final FPSAnimator animator = new FPSAnimator(canvas,60);
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
        animator.start();
    }

    @Override
    public void init(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        // Enable VSync
        gl.setSwapInterval(1);
        // Setup the drawing area and shading mode
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glShadeModel(GL2.GL_SMOOTH); // try setting this to GL_FLAT and see what happens.
        gl.glEnable(GL2.GL_DEPTH_TEST);

        glu = new GLU();

        camera = new TrackballCamera(canvas);
        camera.setLookAt(0, 1, 0);
        camera.setDistance(10);
        camera.setFieldOfView(40);

        // initialise the angles
        angles      = new double[]{0.0, 0.0, 0.0};
        anglesDelta = new double[]{0.0, 0.0, 0.0};

        // create the quadric for the cylinder
        quadric = glu.gluNewQuadric();
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        camera.newWindowSize(width, height);
    }

    @Override
    public void display(GLAutoDrawable drawable) {

        animate();

        GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);

        // the camera does all the projection and positioning
        camera.draw(gl);

        // draw the coordinate axes
        drawCoordinateAxes(gl);

        // rotate the base and draw it
        gl.glRotated(angles[0], 0, 1, 0);
        // we don't want the scaling to be inherited, put in push/pop
        gl.glPushMatrix();
            gl.glScaled(BASE_RADIUS, BASE_HEIGHT, BASE_RADIUS);
            drawCylinder(gl);
        gl.glPopMatrix();

        // move lower arm to the end of the base
        gl.glTranslated(0, BASE_HEIGHT, 0);
        // rotate the lower arm
        gl.glRotated(angles[1], 0, 0, 1);
        // we don't want the scaling to be inherited, put in push/pop
        gl.glPushMatrix();
            gl.glScaled(LOWER_ARM_RADIUS, LOWER_ARM_HEIGHT, LOWER_ARM_RADIUS);
            drawCylinder(gl);
        gl.glPopMatrix();

        // move upper arm to the end of the lower arm
        gl.glTranslated(0, LOWER_ARM_HEIGHT, 0);
        // rotate the upper arm
        gl.glRotated(angles[2], 0, 0, 1);
        // we don't want the scaling to be inherited, put in push/pop
        gl.glPushMatrix();
            gl.glScaled(UPPER_ARM_RADIUS, UPPER_ARM_HEIGHT, UPPER_ARM_RADIUS);
            drawCylinder(gl);
        gl.glPopMatrix();

        // Flush all drawing operations to the graphics card
        gl.glFlush();
    }

    private void animate()
    {
        // animate the rotations of the robot arm
        double tick = System.currentTimeMillis() / 1000.0;
        if ( lastTick > 0 )
        {
            double delta = tick - lastTick;
            for ( int i = 0 ; i < angles.length ; i++ )
            {
                angles[i] += anglesDelta[i] * 20.0 * delta;
            }
        }
        lastTick = tick;
    }

    private void drawCoordinateAxes(GL2 gl)
    {
        gl.glLineWidth(2.0f);
        gl.glBegin(GL2.GL_LINES);
            gl.glColor3d(1, 0, 0);
            gl.glVertex3d(0, 0, 0);
            gl.glVertex3d(2, 0, 0);
            gl.glColor3d(0, 1, 0);
            gl.glVertex3d(0, 0, 0);
            gl.glVertex3d(0, 2, 0);
            gl.glColor3d(0, 0, 1);
            gl.glVertex3d(0, 0, 0);
            gl.glVertex3d(0, 0, 2);
        gl.glEnd();
    }

    private void drawCylinder(GL2 gl)
    {
        gl.glLineWidth(1.0f);
        gl.glColor4d(1, 1, 1, 1);
        // render the cylinder as wireframe object
        glu.gluQuadricDrawStyle(quadric, GLU.GLU_LINE);
        // rotate cylinder so that it points upwards
        gl.glRotated(-90, 1, 0, 0);
        // draw the arm
        glu.gluCylinder(quadric, 1, 1, 1, 10, 10);
    }

       @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch ( key ) 
        {
            case KeyEvent.VK_A : { anglesDelta[0] =  1.0; break; }
            case KeyEvent.VK_D : { anglesDelta[0] = -1.0; break; }
            case KeyEvent.VK_W : { anglesDelta[1] =  1.0; break; }
            case KeyEvent.VK_S : { anglesDelta[1] = -1.0; break; }
            case KeyEvent.VK_Q : { anglesDelta[2] =  1.0; break; }
            case KeyEvent.VK_E : { anglesDelta[2] = -1.0; break; }
            case KeyEvent.VK_R : { angles = new double[]{0.0, 0.0, 0.0}; break; }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch ( key )
        {
            case KeyEvent.VK_A : { anglesDelta[0] = 0.0; break; }
            case KeyEvent.VK_D : { anglesDelta[0] = 0.0; break; }
            case KeyEvent.VK_W : { anglesDelta[1] = 0.0; break; }
            case KeyEvent.VK_S : { anglesDelta[1] = 0.0; break; }
            case KeyEvent.VK_Q : { anglesDelta[2] = 0.0; break; }
            case KeyEvent.VK_E : { anglesDelta[2] = 0.0; break; }
        }
    }

	@Override
	public void dispose(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub
		
	}


}

