package scene.Submarine;

import com.jogamp.opengl.GL2;
import scene.SceneObject;
import scene.Submarine.Blade;

public class Propeller extends SceneObject {


	private Blade[] blade = new Blade[4];

	private float rotation_speed = 1000f;

	public Propeller(float pos_z) {
		position.z = pos_z;
		blade[0] = new Blade(0);// defer the rotation transformation to the Blade class
		blade[1] = new Blade(90);
		blade[2] = new Blade(180);
		blade[3] = new Blade(270);
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

			// TODO: refactor the code using for-loop (and array of Blade)
			
			for(int i = 0; i<3; ++i)
			{
				
				blade[0].draw(gl); // first blade
				blade[1].draw(gl); // second blade
				blade[2].draw(gl); // third blade
				blade[3].draw(gl); // fourth blade
			}
			
		}
		gl.glPopMatrix();
	}

}
