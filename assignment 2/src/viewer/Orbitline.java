package viewer;
/**
 * made by me using stuff from assinment one
 */
import com.jogamp.opengl.GL2;

public class Orbitline {
	float radius;

	public Orbitline(GL2 gl, float radius){
		this.radius = radius;
		}
	
		public void draw(GL2 gl){
			gl.glLineWidth(4);
			gl.glBegin(2);
			gl.glColor4f(1.0f,1.0f,1.0f,0.3f);
			float scale = 50.0f;
			for ( int i = 0  ; i < 360 ; i++){
					
			
			
				float x = (float) Math.cos(Math.toRadians(i))* radius * scale;
				float z = (float) Math.sin(Math.toRadians(i))* radius * scale;
				gl.glVertex3d(x, 0.0, z);
			
		}
	
		gl.glEnd();
		
	}
	
	
	
}
