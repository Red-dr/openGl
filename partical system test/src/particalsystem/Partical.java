package particalsystem;

public class Partical {
	 
	public double x,  y;
    public double dx, dy;
    public double age;
    public double ageMax;

    public Partical(double x, double y)
    {
        // only a point given: steer away from center
        this(x, y, x*3, y*3);
    }

    public Partical(double x, double y, double dx, double dy)
    {
        this.x   = x;
        this.y   = y;
        this.dx  = dx;
        this.dy  = dy;
        this.age = 0.0f;
        this.ageMax = 1.0f + (double)Math.random() * 10;
    }

}
