import com.jogamp.opengl.GL2;
/**
 * this whole class was created by me useing the think that i go help with from a class mate 
 * @author callu
 *
 */
public class Time {

	private boolean gettingBrighter;
	private float opacityChange;
	private float opacity2;
	
	
	public Time() {	
		opacityChange = 0.001f;
		gettingBrighter = true;
		opacity2 = 0;
	}
	
	public void think(){

		if (gettingBrighter) {
			if (opacity2 >= 1.0) {
				gettingBrighter = false;
			}
			else {
				opacity2 += opacityChange;
			}
		}
		if (!gettingBrighter) {
			if (opacity2 <= 0.0) {
				gettingBrighter = true;
			}
			else {
				opacity2 -= opacityChange;
			}
		}
	}
	
	public void draw(GL2 gl){
		
		gl.glBegin(GL2.GL_POLYGON);
		gl.glColor4d(0f, 128f/255f, 192f/255f, opacity2);
		gl.glVertex2d(-1, -1);
		gl.glColor4d(0f, 128f/255f, 192f/255f, opacity2);
		gl.glVertex2d(-1, 1);
		gl.glColor4d(0f, 128f/255f, 192f/255f, opacity2);
		gl.glVertex2d(1,1);
		gl.glColor4d(0f, 128f/255f, 192f/255f, opacity2);
		gl.glVertex2d(1, -1);
		gl.glEnd();
	}
}
