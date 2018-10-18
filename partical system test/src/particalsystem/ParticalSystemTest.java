package particalsystem;



import java.util.ArrayList;

import com.jogamp.opengl.GL2;

public class ParticalSystemTest {
	
	private ArrayList<Partical> partical;
	

    public ParticalSystemTest() {
        partical = new ArrayList<Partical>();
    }

    public void addParticle(double x, double y)
    {
        partical.add(new Partical(x, y));
    }

    public void addParticle(double x, double y, double dx, double dy)
    {
        partical.add(new Partical(x, y, dx, dy));
    }

    public void animate(double time) {
        
    	for ( Partical p : partical )
        {
            // age particle
            p.age += time;
           
            // move particle
            p.x   += p.dx * time;
            p.y   += p.dy * time;
            
            // apply gravity
            p.dy  -= 1.0 * time;

            // let particles bounce off walls
            if ( Math.abs(p.x) > 1.0 )
            {
                p.dx = -p.dx * 0.9f;
                p.x += p.dx * time; // step back into the boundary
            }
            
            if ( Math.abs(p.y) > 1.0 )
            {
                p.dy = -p.dy * 0.9f;
                p.y += p.dy * time; // step backward into the boundary
            }
        }

        // remove the oldest particle
        // (which should be sitting at the beginning of the list)
        if ( partical.size() > 0 )
        {
            Partical p = partical.get(0);
            if ( p.age > p.ageMax )
            {
                partical.remove(p);
            }
        }
    }

    public void draw(GL2 gl) {
        gl.glEnable(GL2.GL_BLEND);
        gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);
        gl.glPointSize(4.0f);
        gl.glBegin(GL2.GL_POINTS);
            for ( Partical p : partical )
            {
                double f = p.age / p.ageMax;
                gl.glColor4d(1, 1 - f, 0, 1.0 -f);
                gl.glVertex2d(p.x, p.y);
            }
        gl.glEnd();
        gl.glDisable(GL2.GL_BLEND);
    }

}