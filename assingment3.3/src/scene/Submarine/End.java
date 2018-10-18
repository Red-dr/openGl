package scene.Submarine;

import com.jogamp.opengl.GL2;

import scene.SceneObject;

public class End extends SceneObject {

	public End(float pos_z) {
		position.z = pos_z;
		rotation.z = 90.0f;
	}

	@Override
	public void think(double ticks) {
		// TODO Auto-generated method stub
	}

	@Override
	public void draw(GL2 gl) {
		gl.glPushMatrix();
		{
			gl.glTranslatef(0, 0, position.z);
			gl.glRotatef(rotation.z, 0, 0, 1);
			gl.glColor3f(0f, 1f, 1f);
			glut.glutWireCone(.5f, 1f, 60, 60);
		}
		gl.glPopMatrix();
	}

}
