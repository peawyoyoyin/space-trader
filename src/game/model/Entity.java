package game.model;

public abstract class  Entity implements Comparable<Entity> {
	public double x,y;
	public int z;
	protected boolean visible,destroyed;
	protected double radius;
	
	protected Entity(){
		visible = true;
		destroyed = false;
	}
	
	@Override
	public int compareTo(Entity other) {
		// TODO Auto-generated method stub
		return this.z-other.z;
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
	public boolean isCollideWith(Entity other){
		return Math.hypot(this.x-other.x, this.y-other.y) < this.radius+other.radius;
	}
	
	abstract public void update();
}
