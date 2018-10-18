import java.util.Random;

import com.jogamp.opengl.GL2;
/**
 * this class was a team effort with a peer and i could not understand how to make triagle fan work/ quad strip
 * the teacher also helped
 * @author callu
 *
 */
public class Land {
	Random rand = new Random();
	
	private float[] x;
	private float[] y;
	private int NO_POINTS = 15;
	private float [] color4f ={ 1,0,0,0};
	private float MAX_HEIGHT = 0;
	private float MIN_HEIGHT = -0.5f;
	private Color c;
	
	// need to have 3 different array lists for the different mountain ranged
	
	
	
	
	

	public Land(float MAX_H, float MIN_H, Color c ){
		this.c = c;
		x = new float[ (NO_POINTS +1)];
		y = new float[ (NO_POINTS +1)];
		float xpos = -1.0f;
		for(int i = 0; i <= NO_POINTS; i++){
			x[i] = xpos;
			y[i] = rand.nextFloat() * (MAX_H - MIN_H) + MIN_H;
			xpos += 2.0f/NO_POINTS;
		}
		
		}

	public void draw(GL2 gl){
		gl.glBegin(GL2.GL_QUAD_STRIP);		
		gl.glColor4f(c.getRed(), c.getGreen(), c.getBlue(),c.getAlpha());
		for( int i = 0 ; i <= NO_POINTS;i++){
			gl.glVertex2f(x[i], -1.0f);
			gl.glVertex2f(x[i], y[i] );
		}
		
		gl.glEnd();
		
		
	}
	

	
}
