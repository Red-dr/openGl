import com.jogamp.opengl.GL2;
/**
 * butten panel was crated fully by me 
 * @author callu
 *
 */
public class ButtenPanel {
	
	private float length = .29f;
	private float gap = .05f;
	private float x = 0;
	private float y = -.009f;
	private float x_second_butten = .009f;
	
	public  Butten[] bTop;// = new Butten(0,0,0,0);
	public  Butten[] bBottem;
	private String[] label = new String[]{"Asteroids","Atmosphere","Time","Spaceship","Twinkle","Earth Quake" };
	
	
	public ButtenPanel(){
		init();
	}
	
	public void draw(GL2 gl){
		
		gl.glBegin(GL2.GL_POLYGON);
		gl.glColor3f(.5f, .5f, .5f);
		gl.glVertex2d(-1, .85);		
		gl.glVertex2d(-1, 1);		
		gl.glVertex2d(1,1);		
		gl.glVertex2d(1, .85);
		gl.glEnd();
		
		for (int i=0; i<6; ++i){
			bBottem[i].draw(gl);
			bTop[i].draw(gl);
		//	bBottem[i].draw(gl);
		}
		
	
		
	}
	
	private void init(){
		
		
		bTop= new Butten[6];	
		bBottem = new Butten[6];
		for( int i = 0; i < 6; ++i ){		
			 bTop[i] = new Butten(x, 0,new Color(1f,.2f,.2f,0f),label[i]);
			 bBottem[i]= new Butten(x+x_second_butten, y,new Color(8f, .5f, .5f, .5f),label[i]);
			 x=x+length+gap;
			
			}	
		
	}
}
