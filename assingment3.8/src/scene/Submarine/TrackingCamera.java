package scene.Submarine;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;

import scene.SceneObject;
import utils.Vector;

public class TrackingCamera extends SceneObject {

	private static final double FOV = 45;
	private static GLU glu = new GLU(); 
	
	private double windowWidth  = 0;
	private double windowHeight = 0;
	private SceneObject target = null;
	private float dist = 10.0f; // distance to the target's position
	private Vector at = new Vector(0, 0, 0);
	private Vector up = new Vector(0, 1, 0);
	
	public TrackingCamera(SceneObject target) {
		this.target = target; 
	}

	/**
     * Passes a new window size to the camera.
     * This method should be called from the <code>reshape()</code> method
     * of the main program.
     *
     * @param width the new window width in pixels
     * @param height the new window height in pixels
     */
    public void newWindowSize(int width, int height) {
        windowWidth = Math.max(1.0, width);
        windowHeight = Math.max(1.0, height);
    }
	
    public void setDistance(float dist) {
    	this.dist = dist;
    }
    
	@Override
	public void think(double ticks) {
		// re-calculate camera position, look-at position and up-vector
		at.x = target.getPosition().x;
		at.y = target.getPosition().y;
		at.z = target.getPosition().z;

		double pitch = Math.toRadians(target.getPitch() + getPitch());
		double yaw = Math.toRadians(target.getYaw() + getYaw());
		double y_dist = -dist * Math.sin(pitch);
		double xz_dist = -dist * Math.cos(pitch);

		position.y = at.y + (float) y_dist;
		position.x = at.x + (float) (xz_dist * Math.sin(yaw));
		position.z = at.z + (float) (xz_dist * Math.cos(yaw));
	}

	@Override
	public void draw(GL2 gl) {
		// set up projection first
		gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(FOV, (float) windowWidth / (float) windowHeight, 0.1, 200);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
        glu.gluLookAt(
    		position.x, position.y, position.z, //eye
          	at.x, at.y, at.z, // looking at 
            up.x, up.y, up.z  // up vector
        );
	}

}
