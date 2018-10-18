package utils;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;

public class Body {
	


	public void draw(GL2 gl, GLUT glut){	
		
		gl.glColor3f(0f, 0f, 1f);
		
		glut.glutWireSphere(2, 60, 60);				
	}

}
