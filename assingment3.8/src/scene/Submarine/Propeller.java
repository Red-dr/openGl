package scene.Submarine;

import com.jogamp.opengl.GL2;
import scene.SceneObject;
import scene.Submarine.Blade;

public class Propeller extends SceneObject {

	private Blade[] blades = null;
	private float rotation_speed = 100f;

	public Propeller(float pos_z) {
		position.z = pos_z;
		blades = new Blade[4];

		// defer the rotation transformation to the Blade class;
		// refactored using for-loop
		for (int i = 0; i < blades.length; i++) {
			blades[i] = new Blade(i * 180 / blades.length);
		}
	}

	public void setRotationSpeed(float speed) {
		rotation_speed = speed;
	}
	
	@Override
	public void think(double ticks) {
		// apply rotation speed
		rotation.z = (rotation.z + rotation_speed * (float)ticks);
		
		// bound the range of the result by 360 degs
		if (rotation.z > 360) {
			rotation.z = rotation.z % 360; // using modulo: 361.234 becomes 1.234
		}
	}
	
	@Override
	public void draw(GL2 gl) {
		gl.glPushMatrix();
		{
			gl.glTranslatef(0, 0, position.z);
			gl.glRotatef(rotation.z, 0f, 0f, 1f);


			for (int i = 0; i < blades.length; i++) {
				blades[i].draw(gl);
			}
		}
		gl.glPopMatrix();
	}

}
