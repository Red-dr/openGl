package scene.Submarine;

import com.jogamp.opengl.GL2;
import scene.SceneObject;

public class Submarine extends SceneObject {

	// submarine parts
	private Body body = null;
	private Propeller propeller = null;
	private End bum = null;

	public final double SPEED = 10;
	public final double YAW_RATE = 32;
	public boolean forward = false;
	public boolean backward = false;
	public boolean dive_up = false;
	public boolean dive_down = false;
	public boolean yaw_left = false;
	public boolean yaw_right = false;

	public Submarine(float altitude) {
		body = new Body(0.3f, 0.3f, 1.2f, "./tex/metal.jpg");
		bum = new End(-2.4f);
		propeller = new Propeller(bum.getPosition().z);

		this.position.y = altitude;
	}

	@Override
	public void think(double ticks) {
		// update my orientation
		if (yaw_left || yaw_right) {
			double delta = (yaw_left ? YAW_RATE : -YAW_RATE) * ticks; 
			yaw((float) delta);
		}

		// update my position
		if (forward || backward) {
			double speed = forward ? SPEED : -SPEED;
			double pitch = Math.toRadians(rotation.x);
			double yaw = Math.toRadians(rotation.y);
			double y_dist = ticks * speed * Math.sin(pitch);
		    double xz_dist = ticks * speed * Math.cos(pitch);

		    position.x += (float)(xz_dist * Math.sin(yaw));
		    position.y += (float) y_dist;
		    position.z += (float)(xz_dist * Math.cos(yaw));
		    
		    // change rotating speed of the propeller
		    propeller.setRotationSpeed(forward ? 900.0f : -900.0f);
		} else {
			propeller.setRotationSpeed(250.0f);
		}
		
		if (dive_up || dive_down) {
			position.y += ticks * (dive_up ? SPEED : -SPEED);
			
			float min_level = body.getScale().y * 2.0f;
			
			if (position.y < min_level) { // touching sea bed?
				position.y = min_level;
			}
		}
		
		body.think(ticks);
		propeller.think(ticks);
	}

	@Override
	public void draw(GL2 gl) {
		gl.glPushMatrix();
		{
			gl.glTranslatef(position.x, position.y, position.z);
			gl.glRotatef(rotation.z, 1, 0, 0);
			gl.glRotatef(rotation.y, 0, 1, 0);
			gl.glRotatef(rotation.x, 0, 0, 1);
			
			body.draw(gl);
			bum.draw(gl);
			propeller.draw(gl);
		}
		gl.glPopMatrix();
	}

}
