package scene;

import com.jogamp.opengl.GL2;

public class Grid extends SceneObject {

	private float start = 0;
	private float end = 0;
	private int segments = 0;
	
	public Grid(float start, float end, int segments) {
		this.start = start;
		this.end = end;
		this.segments = segments;
	}

	@Override
	public void think(double ticks) {
		// TODO Auto-generated method stub
	}

	@Override
	public void draw(GL2 gl) {
		// TODO Auto-generated method stub
		float step = (end - start) / segments;
		gl.glPushMatrix();
		{
			gl.glTranslatef(position.x, position.y, position.z);
			gl.glColor3f(1, 1, 1);

			for (float x = start; x < end; x += step) {
				for (float z = start; z < end; z += step) {
					gl.glBegin(GL2.GL_QUADS);
					gl.glTexCoord2d(0, 0);
					gl.glVertex3f(x, 0, z);
					gl.glTexCoord2d(1, 0);
					gl.glVertex3f(x+step, 0, z);
					gl.glTexCoord2d(1, 1);
					gl.glVertex3f(x+step, 0, z+step);
					gl.glTexCoord2d(0, 1);
					gl.glVertex3f(x, 0, z+step);
					gl.glEnd();
				}
			}
		}
		gl.glPopMatrix();
	}

}
