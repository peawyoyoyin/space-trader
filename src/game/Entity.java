package game;

public abstract class  Entity implements Comparable<Entity> {
	protected double x,y;
	protected int z;
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
	protected boolean isCollideWith(Entity other){
		return Math.hypot(this.x-other.x, this.y-other.y) < this.radius+other.radius;
	}
	
	abstract public void update();
}
