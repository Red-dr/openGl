import com.jogamp.opengl.GL2;
/**
 * this code was made by me no help needed as its simple
 * @author callu
 *
 */
public class Sky {
	
	
	
	public void draw(GL2 gl){
		
		gl.glBegin(GL2.GL_POLYGON);
		gl.glColor4d(1f, 0f, 0f, .7f);
		gl.glVertex2d(-1, -1);
		gl.glColor4d(0f, 0f, 0f, .3f);
		gl.glVertex2d(-1, 1);
		gl.glColor4d(0f, 0f, 0f, .3f);
		gl.glVertex2d(1,1);
		gl.glColor4d(1f, 0, 0f,.7f);
		gl.glVertex2d(1, -1);
		gl.glEnd();
	}

}
