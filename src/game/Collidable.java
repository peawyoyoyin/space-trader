package game;

import com.sun.javafx.geom.Shape;

public interface Collidable {
	Shape getHitBox();
	boolean isCollidingWith(Collidable other);
}
