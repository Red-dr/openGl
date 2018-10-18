package utils;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;

public class SubEnd {
	
	
	public void draw(GL2 gl, GLUT glut){
		gl.glColor3f(0f, 1f, 1f);
		glut.glutWireCone(.5f, 1f, 60, 60);
	}

}
