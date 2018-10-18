import java.util.ArrayList;
import java.util.Random;

import com.jogamp.opengl.GL2;
/**
 * this class i had alot of trouble with i created it my self wiht lots of hints and nudges from peers
 * 
 * @author callu
 *
 */
public class PartacalSystem {


	private float min_R = 0.008f;
	private float max_R = 0.1f;
	private int max_popluation = 8;
	ArrayList<Asteroid> asteroids = new ArrayList<Asteroid>(); 
	public static Asteroid ast;// = new Asteroid(0, 0, 0, 0);
	Random rand = new Random();
	
	
	public void think(){
		
		/**
		 * this method calls the method in the asteroids class to make the asteriods  move 
		 */
		for( int i = 0; i < asteroids.size(); ++i){
			asteroids.get(i).think();
		}
		
		
	}
	
	/**
	 * this method draws the asteroids 
	 * @param gl
	 */
	public void draw(GL2 gl){
		for( int i = 0 ; i < asteroids.size(); ++i ){
			asteroids.get(i).draw(gl);
		}
		
	}

	/**
	 * this method makes new asteriods with random values between the maz and the min radius values 
	 * and adds it to the list 
	 */
	public void init(){
		
		for ( int i =0 ; i < max_popluation; ++i){		
			Asteroid ast = new Asteroid(rand.nextFloat() * (max_R - min_R) + min_R,
					rand.nextFloat() * (max_R - min_R) + min_R,
					(rand.nextFloat()*2-1) ,(rand.nextFloat()*2-1) );
						
			asteroids.add(ast);
			
		}
	}

	
	
}
