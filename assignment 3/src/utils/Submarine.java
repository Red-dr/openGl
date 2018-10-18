package utils;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;

public class Submarine {

	float sub_body_scale_z =  1f;
	float sub_body_scale_x = .3f;
	float sub_body_scale_y = .3f;
	float sub_end_angle =180f;
	float sub_end_pos_z = -1.2f;
	float sub_y_rot = 0f;
	float rot_angle = .5f;
	
	//movement

	public float movement = .2f;
	public boolean pitchup = false;
	public boolean pitchdown = false;
	public boolean yawleft = false;
	public boolean yawright = false;
	public boolean forward = false;
	public boolean back = false;
	public boolean sleft = false;
	public boolean sright = false;
	
	
	
	private Body body;
	private Propeller prop;
	private SubEnd bum;
	private Vector pos;
	
	public double sub_pos[];
	
	
	public Submarine(){
		pos = new Vector(0, 0, 0);
		body = new Body();
		prop = new Propeller();
		bum = new SubEnd();	
		sub_pos = new double[] {pos.x,pos.y,pos.z};
	}

	public void draw(GL2 gl, GLUT glut){
		
//	//submarine
		gl.glPushMatrix();
		{
			gl.glScaled(2f, 2f, 2f);
			gl.glTranslatef(pos.x, pos.y, pos.z);
			gl.glRotatef(sub_y_rot, 0, 1, 0);
			// body matrix
			gl.glPushMatrix();
			{
				gl.glScalef(sub_body_scale_x, sub_body_scale_y, sub_body_scale_z);
				body.draw(gl, glut);
			}
			gl.glPopMatrix();
			//subend matrix
			gl.glPushMatrix();
			{
				
				gl.glTranslatef(0, 0, sub_end_pos_z);
				gl.glRotatef(sub_end_angle, 0, 1, 0);
				bum.draw(gl, glut);
			}
			gl.glPopMatrix();
			// propeller matrix
			gl.glPushMatrix();
			{
				gl.glTranslatef(0, 0, sub_end_pos_z-1f);
				prop.draw(gl, glut);
			}
			gl.glPopMatrix();
			
		}
		gl.glPopMatrix();
	}
	
	public void think(){
		

		 if(yawleft)	{rotLeft();}
		 if(yawright)	{rotRight();}
		 if(forward)	{forward();}
		 if(back)    	{backward();}
		 if(sleft)  	{left();}
		 if(sright) 	{right();}
//		 update_look_at();
	}
//	 public void update_look_at()
//	 {
//	    movement(sub_pos, yaw_angle, pitch_angle, lookatdis);
//	 }
	
	public void up(){
		pos.y = pos.y-.1f;
		
	}
	public void down(){
		pos.y = pos.y+.1f;	
	}
	public void forward(){
		pos.z = pos.z+.1f;
	}
	public void backward(){
		pos.z = pos.z-.1f;
	}
	public void left(){
		pos.x = pos.x+.1f;
	}
	public void right(){
		pos.x = pos.x-.1f;
	}

	public void rotLeft() {
		sub_y_rot += rot_angle;
		if (sub_y_rot > 360) {
			sub_y_rot = 0;
		}
	}

	public void rotRight() {
		sub_y_rot -= rot_angle % 360f;

	}

	/**
	 * these next 2 fucntions  change the yaw making sure that if it goes over 360 it will ajust the value to 
	 * the next place in the 360 angle to stop memory leaks
	 */

		  
		  /**
		   * the nest 2 funtions change the pitch making sure that it does not do to world fliping states 
		   */

	


	
	
	
	
}
