package scene;

import java.io.File;
import java.io.IOException;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

public class Grid extends SceneObject {

	private float start = 0;
	private float end = 0;
	private int segments = 0;
	private Texture tex = null;
	
	public Grid(float start, float end, int segments, String texture_file_path) {
		this.start = start;
		this.end = end;
		this.segments = segments;
		
		try {
			tex = TextureIO.newTexture(new File(texture_file_path), true);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void think(double ticks) {
		// TODO Auto-generated method stub
	}

	@Override
	public void draw(GL2 gl) {
		// bind texture
		gl.glEnable(GL2.GL_TEXTURE_2D);
		gl.glMatrixMode(GL2.GL_TEXTURE);
        gl.glLoadIdentity();
        
        tex.enable(gl);
        tex.bind(gl);
		
        tex.setTexParameteri(gl, GL2.GL_TEXTURE_WRAP_S, GL2.GL_REPEAT);
		tex.setTexParameteri(gl, GL2.GL_TEXTURE_WRAP_T, GL2.GL_REPEAT);
        tex.setTexParameteri(gl, GL2.GL_TEXTURE_MIN_FILTER, GL2.GL_LINEAR);
		tex.setTexParameteri(gl, GL2.GL_TEXTURE_MAG_FILTER, GL2.GL_LINEAR);

		gl.glMatrixMode(GL2.GL_MODELVIEW);		
		
		float step = (end - start) / segments;
		gl.glPushMatrix();
		{
			gl.glTranslatef(position.x, position.y, position.z);
			// gl.glColor3f(0, 1, 0);

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
		
		tex.disable(gl);
	}

}
