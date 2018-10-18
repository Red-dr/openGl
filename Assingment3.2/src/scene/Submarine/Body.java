package scene.Submarine;

import com.jogamp.opengl.GL2;
import scene.SceneObject;

public class Body extends SceneObject {

	public Body(float scale_x, float scale_y, float scale_z) {
		scale.x = scale_x;
		scale.y = scale_y;
		scale.z = scale_z;
	}
	
	@Override
	public void think(double ticks) {
		// TODO Auto-generated method stub
	}

	@Override
	public void draw(GL2 gl) {
		gl.glPushMatrix();
		{
			gl.glScalef(scale.x, scale.y, scale.z);
			gl.glColor3f(.5f, .5f, .5f);
			glut.glutSolidSphere(2, 60, 60);
		}
	}

}
