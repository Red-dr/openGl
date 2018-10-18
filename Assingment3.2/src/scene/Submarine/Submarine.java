package scene.Submarine;

import com.jogamp.opengl.GL2;
import scene.SceneObject;

public class Submarine extends SceneObject {

	// submarine parts
	private Body body = null;
	private Propeller propeller = null;
	private End bum = null;

	public double speed = 10;
	public double yawRate = 32;
	public boolean forward = false;
	public boolean backward = false;
	public boolean yaw_left = false;
	public boolean yaw_right = false;
	public boolean up = false;
	public boolean down = false;

	public Submarine() {
		body = new Body(0.3f, 0.3f, 1f);
		bum = new End(-1.2f);
		propeller = new Propeller(bum.getPosition().z - 1.0f);
	}

	@Override
	public void think(double ticks) {
		// update my orientation
		if (yaw_left || yaw_right) {
			rotation.y = rotation.y + (float)((yaw_left ? yawRate : -yawRate) * ticks);
			rotation.y = rotation.y % 360;
		}

		// update my position
		if (forward || backward) {
			double speed = forward ? 10 : -10;
			double pitch = Math.toRadians(rotation.x);
			double yaw = Math.toRadians(rotation.y);
			double y_dist = ticks * speed * Math.sin(pitch);
		    double xz_dist = ticks * speed * Math.cos(pitch);

		    position.x += (float)(xz_dist * Math.sin(yaw));
		    position.y += (float) y_dist;
		    position.z += (float)(xz_dist * Math.cos(yaw));
		}
		
		body.think(ticks);
		propeller.think(ticks);
	}

	@Override
	public void draw(GL2 gl) {
		gl.glPushMatrix();
		{
			gl.glTranslatef(position.x, position.y+1f, position.z);
			gl.glRotatef(rotation.z, 1, 0, 0);
			gl.glRotatef(rotation.y, 0, 1, 0);
			gl.glRotatef(rotation.x, 0, 0, 1);
			
			body.draw(gl);
			bum.draw(gl);
			
			propeller.draw(gl);
		}
		gl.glPopMatrix();
	}
	
	public void up(){
		position.y+=.2f;
		if(position.y >3){
			position.y=3;
		}
	}
	public void down(){
		position.y-=.2f;
		if(position.y<0){
			position.y=0;
		}
	}
		

}
