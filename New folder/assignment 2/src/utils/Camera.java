package utils;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import java.math.*;
/**
 * this class was started by teacher but modified by me
 * @author callu
 *
 */
public class Camera {
	//changes the distance of the opject you are viewing
	private static final double FOV = 60;
	
	public float pitch_angle = 0f;
	public float yaw_angle = 0f;
	float lookatdis = 20f;
	float yaw_adjust = .5f;
	float pitch_adjust = .5f;
	public float movement = 5f;
	public boolean pitchup = false;
	public boolean pitchdown = false;
	public boolean yawleft = false;
	public boolean yawright = false;
	public boolean forward = false;
	public boolean back = false;
	public boolean sleft = false;
	public boolean sright = false;
	
	 double windowWidth      = 1;
	 double windowHeight     = 1;
	 private double cameye[] = { 0, 0, -20d};
	
    
	// the point to look at
	private double lookAt[] = {0, 0, 0};

	
	public void draw(GL2 gl){
		// set up projection first
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        GLU glu = new GLU();
        glu.gluPerspective(FOV, (float) windowWidth / (float) windowHeight, 0.1, 200);
        // set up the camera position and orientation
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
      // update_look_at();
        glu.gluLookAt(cameye[0], cameye[1], cameye[2], //eye
                	  lookAt[0], lookAt[1], lookAt[2], // looking at 
                      0.0, 1.0, 0.0); // up   
	}
	
	/**
     * Sets up the lookAt point - could be a specified object's location
     * @param x X coordinate of the lookAt point
     * @param y Y coordinate of the lookAt point
     * @param z Z coordinate of the lookAt point
     */
    public void setLookAt(double x, double y, double z) {
        lookAt = new double[]{x, y, z};
    }
    
    public void setEye(double x, double y, double z){
    	cameye[0] = x;
    	cameye[1] = y;
    	cameye[2] = z;
    }
    
    
	public void up(){
		cameye[1] = cameye[1]-1;
		
	}
	public void down(){
		cameye[1] = cameye[1]+1;
		
	}
		
/**
 * these next 2 fucntions  change the yaw makeing sure that if it goes over 360 it will ajust the value to 
 * the next place in the 360 angle to stop memory leaks
 */
	  public void yaw_right()
	  {
	    yaw_angle -= yaw_adjust;
	    if (yaw_angle < 0.0F) {
	      yaw_angle += 360.0F;
	    }
	  }
	  
	  public void yaw_left()
	  {
	    yaw_angle += yaw_adjust % 360.0F;
	  }
	  
	  /**
	   * the nest 2 funtions change the pitch making sure that it does not do to world fliping states 
	   */
	  public void pitch_up()
	  {
	    pitch_angle += pitch_adjust;
	    if (pitch_angle > 89.0F) {
	    	pitch_angle = 89.0F;
	    }
	  }
	  
	  public void pitch_down()
	  {
	    pitch_angle -= pitch_adjust;
	    if (pitch_angle < -89.0F) {
	      pitch_angle = -89.0F;
	    }
	  }
	
/**
 * this method sees which items have been pressed and calls the nessasary fucntion
 */
	  
	  public void think(){
		  
		 if(pitchup){pitch_up(); };
		 if(pitchdown){pitch_down(); }
		 if(yawleft){yaw_left(); }
		 if (yawright){yaw_right();}

		 

		  update_look_at();
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

    
    
	  public void update_look_at()
	  {
	    movement(lookAt, yaw_angle, pitch_angle, lookatdis);
	  }
	  /**
	   * this method takes in the camera lookAt posistion the current yaw, pitch and the distance 
	   * and calculates the new positions of the lookAt in the camera;
	   * @param camera
	   * @param yaw
	   * @param pitch
	   * @param dist
	   */
	  private void movement(double[] camera, float yaw, float pitch, float dist)
	  {
	    
	    //needed help with this one as it just didnt work right untill some one told me to add the cameye to this
	    double y_dist = dist * Math.sin(Math.toRadians(pitch));
	    camera[1] = y_dist + cameye[1];
	    
	    double xz_dist = dist * Math.cos(Math.toRadians(pitch));
	    
	    camera[0] = (xz_dist * Math.sin(Math.toRadians(yaw))+cameye[0]);
	    camera[2] = (xz_dist * Math.cos(Math.toRadians(yaw))+cameye[2]);
	    
	   
	  }
}
