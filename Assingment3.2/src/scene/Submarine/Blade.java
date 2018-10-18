package scene.Submarine;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;

import scene.SceneObject;

public class Blade extends SceneObject {

	public Blade(float rot_z) {
		this.rotation.z = rot_z;
		this.scale.x = 2f;
		this.scale.y = 0.1f;
		this.scale.z = 0.1f;
	}
	
	@Override
	public void think(double ticks) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void draw(GL2 gl) {
		gl.glPushMatrix();
		{
			gl.glRotatef(rotation.z, 0f, 0f, 1.0f);
			gl.glScalef(scale.x, scale.y, scale.z);
			gl.glColor3f(0, 0, 1);
			glut.glutSolidCube(1f);
		}
		gl.glPopMatrix();
	}

}
