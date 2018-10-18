import java.util.ArrayList;
import java.util.Random;

import com.jogamp.opengl.GL2;
/**
 * this class was also created by me but i did not work in the end and i could not find out why
 * this code broke my planet even though it is very simalir to the points class
 * @author callu
 *
 */
public class Star {

	double opacity;
	Random rand = new Random();
	private boolean gettingBrighter;
	private double opacityChange;
	private int max_pop = 50;
	ArrayList<Points> star = new ArrayList<Points>();
	//static Points star[] = new Points[50];
	
	public Star(){
		opacity = rand.nextDouble();
		opacityChange = 0.01;
		gettingBrighter = true;
		
	}
	
	public void init(){
		
		for ( int i = 0 ; i <max_pop; i++ ){
			Points s =  new Points((rand.nextFloat()*2-1),(rand.nextFloat()*2-1));
		star.add(s);
		}		
	}
	
	public void think(GL2 gl) {

		if (gettingBrighter) {
			if (opacity >= 1.0) {
				gettingBrighter = false;
			}
			else {
				opacity += opacityChange;
			}
		}
		if (!gettingBrighter) {
			if (opacity <= 0.0) {
				gettingBrighter = true;
			}
			else {
				opacity -= opacityChange;
			}
		}
		//gl.glDisable(GL2.GL_BLEND);
	}

	public void draw(GL2 gl) {
		for(int i = 0; i< star.size();++i){
			star.get(i).draw(gl);
		}
		
	}
}
