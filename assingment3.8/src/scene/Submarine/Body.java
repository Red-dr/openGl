package scene.Submarine;

import java.io.File;
import java.io.IOException;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLUquadric;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

import scene.SceneObject;

public class Body extends SceneObject {

	private Texture tex = null;
	
	public Body(float scale_x, float scale_y, float scale_z, String texture_file_path) {
		scale.x = scale_x;
		scale.y = scale_y;
		scale.z = scale_z;
		
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
		gl.glPushMatrix();
		{
			gl.glScalef(scale.x, scale.y, scale.z);
			//gl.glColor3f(0f, 0f, 1f);
			//glut.glutSolidSphere(2, 60, 60);
			
			GLUquadric quadric = glu.gluNewQuadric();
			glu.gluQuadricDrawStyle(quadric, glu.GLU_FILL);
			glu.gluQuadricTexture(quadric, true);
			glu.gluSphere(quadric, 2, 60, 60);
		}
		gl.glPopMatrix();
		
		tex.disable(gl);
	}

}
