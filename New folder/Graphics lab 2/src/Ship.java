import com.jogamp.opengl.GL2;
/**
 * this code was also created by me also simple 
 * @author callu
 *
 */
public class Ship {
	
	public Points triangle[] = new Points[3];
	public Points hull[] = new Points[4];
	public Points stand[] = new Points[4];
	private float x_pos;
	private float y_pos;
	
	public Ship(float x, float y){
		this.x_pos = x;
		this.y_pos = y;
		
		init();
		
	}
	
	public void think(){
		x_pos -= 0.003;
		if(x_pos < -2){
			x_pos = 1;
		}
		
	}
	
	public void draw(GL2 gl){
		
		gl.glBegin(GL2.GL_POLYGON);
		gl.glColor3f(0.5f , 0.5f, 0.5f);
		gl.glVertex2f(triangle[0].x+x_pos,triangle[0].y);		
		gl.glVertex2f(triangle[1].x+x_pos,triangle[1].y);		
		gl.glVertex2f(triangle[2].x+x_pos,triangle[2].y);	
		gl.glEnd();
		gl.glBegin(GL2.GL_QUADS);
		gl.glColor3f(0.5f , 0.5f, 0.5f);
		gl.glVertex2f(stand[0].x+x_pos,stand[0].y);		
		gl.glVertex2f(stand[1].x+x_pos,stand[1].y);		
		gl.glVertex2f(stand[2].x+x_pos,stand[2].y);	
		gl.glVertex2f(stand[3].x+x_pos, stand[3].y);
		gl.glEnd();
		gl.glBegin(GL2.GL_QUADS);
		gl.glColor3f(0.5f , 0.5f, 0.5f);
		gl.glVertex2f(hull[0].x+x_pos,hull[0].y);		
		gl.glVertex2f(hull[1].x+x_pos,hull[1].y);		
		gl.glVertex2f(hull[2].x+x_pos,hull[2].y);	
		gl.glVertex2f(hull[3].x+x_pos,hull[3].y);
		gl.glEnd();
	}
	
	public void init(){
		triangle[0] = new Points(0f+x_pos,0f+y_pos);
		triangle[1] = new Points(0.25f+x_pos,0f+y_pos);
		triangle[2] = new Points(0.25f+x_pos,0.1f+y_pos);
		stand[0]= new Points(0.145f+x_pos,0f+y_pos);
		stand[1]= new Points(0.145f+x_pos,.09f+y_pos);
		stand[2]= new Points(0.165f+x_pos,.09f+y_pos);
		stand[3]= new Points(0.165f+x_pos,0f+y_pos);
		hull[0]= new Points(0.125f+x_pos,.09f+y_pos);
		hull[1]= new Points(0.125f+x_pos,.12f+y_pos);
		hull[2]= new Points(0.185f+x_pos,.12f+y_pos);
		hull[3]= new Points(0.185f+x_pos,.09f+y_pos);
		
	}
}
