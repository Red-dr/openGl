import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;

/**
 * butten  went through some changes this is V4 the earlyer versions did not save where the points of
 * the buttens where so i culd not call them to change see if the butten was pressed
 * a peer said that the edge points where not being saved anywhere so i added the points to save them
 * @author callu
 *
 */
public class Butten {
	
	public float x_pos;
	public float y_pos;
	public float hight;
	public float length;
	public String label;
	private int font= GLUT.BITMAP_HELVETICA_18;
	public float width  ;
	public float heigth;
	public boolean toggle =false;
	
	public Points[] bPoint = new Points[4];
	private Color c;
	
	
	public Butten(float x, float y,Color c, String label){
		this.x_pos = x;
		this.y_pos = y;
		this.c = c;
		this.label = label;
		init();
		
		

	}
	
	public void draw(GL2 gl){
		
		gl.glBegin(GL2.GL_POLYGON);
		gl.glColor4f(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha());
		gl.glVertex2f(bPoint[0].x,bPoint[0].y);		
		gl.glVertex2f(bPoint[1].x,bPoint[1].y);		
		gl.glVertex2f(bPoint[2].x,bPoint[2].y);		
		gl.glVertex2f(bPoint[3].x,bPoint[3].y);
				
		
		gl.glEnd();
		text(gl);
	}

	public void toggle(){
		if(toggle){
			
			c.red=1f;
			c.blue=0.2f;
			c.green=0.2f;
			
			bPoint[0].x-=0.009f;
			bPoint[0].y+=0.009f;
			bPoint[1].x-=0.009f;
			bPoint[1].y+=0.009f;
			bPoint[2].x-=0.009f;
			bPoint[2].y+=0.009f;
			bPoint[3].x-=0.009f;
			bPoint[3].y+=0.009f;
			toggle = false;
		}
		else{
			
			c.red=0.8f;
			c.blue=0.2f;
			c.green=0.2f;
			bPoint[0].x+=0.009f;
			bPoint[0].y-=0.009f;
			bPoint[1].x+=0.009f;
			bPoint[1].y-=0.009f;
			bPoint[2].x+=0.009f;
			bPoint[2].y-=0.009f;
			bPoint[3].x+=0.009f;
			bPoint[3].y-=0.009f;
			toggle = true;
		}
	}
	public void init(){
		bPoint[0] = new Points(-.98f+x_pos, .88f+y_pos);
		bPoint[1] = new Points(-.98f+x_pos, .97f+y_pos);
		bPoint[2] = new Points(-.67f+x_pos, .97f+y_pos);
		bPoint[3] = new Points(-.67f+x_pos, .88f+y_pos);
	}
	
	public void text(GL2 gl){
		GLUT glut = new GLUT();
		//gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
		gl.glColor3f(1,1, 1);
		gl.glRasterPos2d(-.97f+x_pos, .90f+y_pos );
		
		glut.glutBitmapString(font,	label);
	}
	
}
