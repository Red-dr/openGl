package utils;
/**
 * made by me quite simple
 */
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;

public class Sun {
	
	//private GLUT glut;

	public void draw(GL2 gl, GLUT glut){
		
		
		gl.glColor3f(1f, 1f, 0f);
		glut.glutSolidSphere(6, 60, 60);
		gl.glColor4f(.8f, .8f, 0, .2f);
		glut.glutSolidSphere(6.5f, 60, 60);
		gl.glColor4f(1f, 1f, 1f,.1f);
		glut.glutSolidSphere(7f, 60, 60);
			
		
	}
	
		
	
	
}
