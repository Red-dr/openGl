import java.util.Random;

import com.jogamp.opengl.GL2;

/**
 * asterroid class was crated with help form a peer changed where made by me only help was with the drawing of
 * a circle 
 * @author callu
 *
 */
public class Asteroid {

	public  float min_radius ;
	public float max_radius;
	
	Random rand = new Random();
	private float x[];
	private float y[];
	private float pos_x = 0;
	private float pos_y = 0;
	private int smooth = rand.nextInt(14-7)+7;

	
	public Asteroid(float min_radius, float max_radius, float pos_x, float pos_y){
		this.min_radius = min_radius;
		this.max_radius = max_radius;
		this.pos_x = pos_x;
		this.pos_y = pos_y;
		x = new float[smooth +1];
		y = new float[smooth +1];
		init();		
	}
	
	public void think(){
		pos_y -= 0.015;	
		pos_x -= 0.003;
		if (pos_y <=-1 ){
			pos_y =((rand.nextFloat()*2-1))+2;
			pos_x = ((rand.nextFloat()*2-1));
		}
		
	}
	
	public void init(){
		float current_angle = 0;
		float angle_dif = (float) 360/smooth;
		x[0]=0;
		y[0]=0;
		for ( int i = 0  ; i < smooth ; i++){
			float radians = (float) Math.toRadians(current_angle);	
			float radius = rand.nextFloat() *( max_radius - min_radius)+ min_radius;
			
			x[i] = (float) Math.cos(radians)* radius;
			y[i] = (float) Math.sin(radians)* radius;
			
			current_angle += angle_dif;	
		}
		x[smooth]= x[1];
		y[smooth]= y[1];
	}
		
	
	public void draw(GL2 gl){
		gl.glBegin(GL2.GL_TRIANGLE_FAN); 
		gl.glColor4f(159f/255f,150f/255f,150f/255f, 1);		
		for (int i = 0; i <x.length; ++i ){
			//gl.glVertex2f(x[i], y[i]);
			gl.glVertex2f(x[i]+pos_x, y[i]+pos_y);
		}
		
		gl.glEnd();
	}
	
}