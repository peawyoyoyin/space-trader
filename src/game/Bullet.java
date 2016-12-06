package game;

public class Bullet extends Entity implements Collidable,Render{

	private int speed;
	private int direction;
	private int damage;
	
	public Bullet(int x,int y,int speed,int direction,int damage){
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.direction = direction;
		this.damage = damage;
	}
	
	@Override
	public boolean isCollidingWith(Collidable other) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	void update() {
		// TODO Auto-generated method stub
		this.x += Math.cos(Math.toRadians(this.direction)) * speed;
		this.y += Math.sin(Math.toRadians(this.direction)) * speed;
		if (this.isCollidingWith(ship)){
			this.destroyed = true;
			ship.hit(damage);
		}
	}


}
