package game;

public abstract class  Entity {
	protected double x,y;
	protected int z;
	protected boolean visible,destroyed;
	protected int radius;
	
	protected Entity(){
		visible = true;
		destroyed = false;
	}
	
	public boolean isDestroyed(){
		return destroyed;
	}
	
	public boolean isVisible(){
		return visible;
	}
	
	public int getZ(){
		return z;
	}
	protected boolean isCollideWith(Entity other){
		return Math.hypot(this.x-other.x, this.y-other.y) < this.radius+other.radius;
	}
	
	abstract public void update();
}
