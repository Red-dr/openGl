package utils;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;

public class Blade {
	
	public void draw(GL2 gl, GLUT glut){
		
		gl.glPushMatrix();
		{
			gl.glScalef(0.6f,0.1f,0.1f);
			gl.glColor3f(1, 1, 1);
			glut.glutSolidCube(1f);
		}
		gl.glPopMatrix();		
	}
}	


