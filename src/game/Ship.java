package game;

import javafx.scene.input.KeyCode;

public abstract class Ship extends Entity implements Collidable, Renderable {

	private int hp;
	private int maxHp;
	private int speed;
	private int maxSpeed;
	private int accelerate;
	private int turnRate;
	private int direction;

	public Ship(int x, int y, int hp, int maxHp, int speed, int maxSpeed, int accelerate, int turnRate, int direction) {
		this.x = x;
		this.y = y;
		this.hp = hp;
		this.maxHp = maxHp;
		this.speed = speed;
		this.maxSpeed = maxSpeed;
		this.accelerate = accelerate;
		this.turnRate = turnRate;
		this.direction = direction;
	}

	public int getHp() {
		return hp;
	}

	public int getMaxHp() {
		return maxHp;
	}

	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
	}

	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public void forward() {
		this.speed = this.speed + this.accelerate;
		if (this.speed > this.maxSpeed) {
			this.speed = this.maxSpeed;
		}
	}
	public void back(){
		this.speed = this.speed - this.accelerate;
		if (this.speed < -this.maxSpeed) {
			this.speed = -this.maxSpeed;
		}
	}
	
	public void turn(boolean left) {
		if (left) {
			this.direction -= this.turnRate;
			if (this.direction < 0)
				this.direction += 360;
		} else {
			this.direction += this.turnRate;
			if (this.direction >= 360)
				this.direction -= 360;
		}
	}

	public void hit(int damage) {
		this.hp = this.hp - damage;
		if (this.hp < 0) {
			this.destroyed = true;
		}
	}
	
	public void shoot(){
		
	}

	public void healMaxHp() {
		this.hp = this.maxHp;
	}

	@Override
	void update() {
		// TODO Auto-generated method stub
		this.x += Math.cos(Math.toRadians(this.direction)) * speed;
		this.y += Math.sin(Math.toRadians(this.direction)) * speed;
		if (InputUtility.getKeyPressed(KeyCode.W)) {
			forward();
		}else if (InputUtility.getKeyPressed(KeyCode.S)){
			back();
		}
		if (InputUtility.getKeyPressed(KeyCode.A)) {
			turn(true);
		} else if (InputUtility.getKeyPressed(KeyCode.D)) {
			turn(false);
		}
	}
	

}
