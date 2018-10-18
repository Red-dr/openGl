package utils;
/** made by me 
 * with a littel tuition on one method
 */
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;

public class Planet {
	
	float radius;
	float orbitdist;
	float orbitTime;
	public float x;
	public float y;
	public float z;
	Color color;
	float scale = 50;
	float i=0;
	
	public Planet(float radius, float orbitdist, float orbittime, Color color){
		this.radius = radius;
		this.orbitdist = orbitdist;
		this.orbitTime = orbittime;		
		this.color = color;
	}
	

	public void update(){
		
	}
	/*
	 * draws the planet
	 */
	public void draw(GL2 gl , GLUT glut){
		
	//	gl.glTranslatef(orbitdist*scale, y, z);
		gl.glColor3f(color.getRed(), color.getGreen(), color.getBlue());
		glut.glutSolidSphere(radius, 50, 50);		
	}
	/**
	 * this method takes in the time and uses it to make the planets orbit the sun 
	 * @param gl
	 * @param delta
	 */
	public void think(GL2 gl , double delta){
		
		
		if(i > 360){
			i=0;
		}
			// i needed a littel help with the calclulations orbitdist and scale
			x = (float) Math.cos(Math.toRadians(i))* (orbitdist*scale);
			z = (float) Math.sin(Math.toRadians(i))* (orbitdist*scale);
			//System.out.println(delta);
			
			//i += 100 * delta;
			// needed help with delta time got the calculation to work but it didnt work as expected 
			i+= ((360/orbitTime)*delta)*5;
			
		
	
	}

}
