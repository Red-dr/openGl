package scene;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;

import utils.Vector;

public abstract class SceneObject {

	// shared static attribute(s) among subclasses
	protected static GLU glu = new GLU();
	protected static GLUT glut = new GLUT();
	
	protected Vector position = new Vector(0, 0, 0);
	protected Vector rotation = new Vector(0, 0, 0);
	protected Vector scale = new Vector(0, 0, 0);
	
	public SceneObject() {
		// TODO Auto-generated constructor stub
	}
	
	public Vector getPosition() {
		return position;
	}
	
	public Vector getScale() {
		return scale;
	}
	
	public float getPitch() {
		return rotation.x;
	}
	
	public float getYaw() {
		return rotation.y;
	}
	
	public float getRoll() {
		return rotation.z;
	}

	public void pitch(float delta) {
		rotation.x = (rotation.x + delta) % 360; 
	}
	
	public void yaw(float delta) {
		rotation.y = (rotation.y + delta) % 360;
	}
	
	// leave details to the subclass
	public abstract void think(double ticks);
	public abstract void draw(GL2 gl);
}
