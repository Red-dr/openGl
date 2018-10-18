package utils;
/**
 * made by me again quite simple but it didnt work in the end
 */
import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;

public class Positions {
	
	
	public void draw(GL2 gl){
		
		gl.glColor3d(1, 0, 0);
		
		gl.glBegin(GL2.GL_LINES);
		gl.glVertex3f(0.0f, 0.0f, 0.0f);
		gl.glVertex3f(100.0f,0.0f,0.0f);
		
		
		gl.glColor3d(0, 1, 0);
		gl.glVertex3f(0.0f, 0.0f, 0.0f);
		gl.glVertex3f(0.0f,100.0f,0.0f);
		
		
		
		gl.glColor3d(0, 0, 1);
		gl.glVertex3f(0.0f, 0.0f, 0.0f);
		gl.glVertex3f(0.0f,0.0f,100.0f);
		
		gl.glEnd();
	}

}
