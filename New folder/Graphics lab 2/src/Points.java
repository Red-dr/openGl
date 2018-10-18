import java.util.Random;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
/**
 * this is the class that is used as my stars and as my points for the buttens again created by me with 
 * some help from peers
 * @author callum
 *
 */
public class Points {
	
	float x;
	float y;
	double opacity;
	Random rand = new Random();
	boolean gettingBrighter;
	double opacityChange;

	

	public Points(float x, float y) {
		this.x = x;
		this.y = y;	
		this.opacity = rand.nextDouble();
		opacityChange = 0.01;
		gettingBrighter = true;

	}
	
	public void think(GL2 gl) {

		if (gettingBrighter) {
			if (opacity >= 1.0) {
				gettingBrighter = false;
			}
			else {
				opacity += opacityChange;
			}
		}
		if (!gettingBrighter) {
			if (opacity <= 0.0) {
				gettingBrighter = true;
			}
			else {
				opacity -= opacityChange;
			}
		}
		//gl.glDisable(GL2.GL_BLEND);
	}

	public void draw(GL2 gl) {
		gl.glPointSize(4);
		gl.glBegin(GL2.GL_POINT);
		gl.glColor4d(1f, .8f, .8f, opacity);
		gl.glVertex2f(x, y);
		gl.glEnd();
		
	}
	

}
