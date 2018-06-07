package PG;

public class Bullet extends FlyingObject {
	
	private int speed = 3;

	public Bullet(int x, int y) {
		this.x = x;
		this.y = y;
		this.image = PlaneGame.bullet;
	}
	
	@Override
	public boolean OutofBorder() {
		// TODO Auto-generated method stub
		return y < -height;
	}

	@Override
	public void Move() {
		// TODO Auto-generated method stub
		y -= speed;
	}

}
