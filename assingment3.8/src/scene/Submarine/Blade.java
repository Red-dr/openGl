package scene.Submarine;

import com.jogamp.opengl.GL2;
import scene.SceneObject;

public class Blade extends SceneObject {

	public Blade(float rot_z) {
		this.rotation.z = rot_z;
		this.scale.x = 0.05f;
		this.scale.y = 0.60f;
		this.scale.z = 0.15f;
	}
	
	@Override
	public void think(double ticks) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void draw(GL2 gl) {
		gl.glPushMatrix();
		{
			gl.glRotatef(rotation.z, 0.0f, 0f, 1.0f);
			gl.glScalef(scale.x, scale.y, scale.z);
			gl.glColor3f(1, 1, 1);
			glut.glutSolidCube(1f);
		}
		gl.glPopMatrix();
	}

}
