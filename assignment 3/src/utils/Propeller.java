package utils;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;

public class Propeller {
	
	public Blade blade;
	public Blade blade2;
	
	float current_rotation = 0f;
	float rotation_speed = 10f;
	
	public Propeller(){
		blade = new Blade();
		blade2 = new Blade();
	}
	
	
	public void draw(GL2 gl, GLUT glut){
		
		gl.glPushMatrix();
		{
			current_rotation += rotation_speed;
			if(current_rotation >360f){
				current_rotation = 0f;
			}
			
			gl.glRotatef(current_rotation, 0f, 0f, 1f);
			//first blade
			gl.glPushMatrix();
			{
				gl.glRotatef(90f, 0f, 0f, 1.0f);
				blade.draw(gl, glut);
			}
			gl.glPopMatrix();
			//second blade
			gl.glPushMatrix();
			{
				gl.glRotatef(180f, 0f, 0f, 1.0f);
				blade2.draw(gl, glut);
			}
			gl.glPopMatrix();
		}
		gl.glPopMatrix();		
	}
}
