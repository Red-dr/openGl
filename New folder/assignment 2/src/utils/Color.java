package utils;
/**
 * pulled this class from the other assingmet
 * @author callu
 *
 */
public class Color {

	float blue;
	public float getBlue() {
		return blue;
	}


	public void setBlue(float blue) {
		this.blue = blue;
	}


	public float getRed() {
		return red;
	}


	public void setRed(float red) {
		this.red = red;
	}


	public float getGreen() {
		return green;
	}


	public void setGreen(float green) {
		this.green = green;
	}


	public float getAlpha() {
		return alpha;
	}


	public void setAlpha(float alpha) {
		this.alpha = alpha;
	}


	float red;
	float green;
	float alpha;
	
	
	public Color(float r, float g, float b, float a){
		this.red = r;
		this.green = g;
		this.blue = b;
		this.alpha = a;		
	}

}

