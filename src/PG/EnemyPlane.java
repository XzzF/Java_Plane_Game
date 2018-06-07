package PG;

import java.util.Random;

public class EnemyPlane extends FlyingObject implements Award, Enemy {

	private int xSpeed = 1;   //x�����ƶ��ٶ�
	private int ySpeed = 2;   //y�����ƶ��ٶ�
	private int awardScore;    //��������
	
	public EnemyPlane() {
		this.image = PlaneGame.airplane;
		width = image.getWidth();
		height = image.getHeight();
		y = -height;
		Random rand = new Random();
		x = rand.nextInt(PlaneGame.WIDTH - width);
		awardScore = rand.nextInt(2);   //��ʼ��ʱ������
	}
	
	@Override
	public int getScore() {
		// TODO Auto-generated method stub
		return awardScore;
	}

	@Override
	public boolean OutofBorder() {
		// TODO Auto-generated method stub
		return y > PlaneGame.HEIGHT;
	}

	@Override
	public void Move() {
		// TODO Auto-generated method stub
		x += xSpeed;
		y += ySpeed;
		if(x > PlaneGame.WIDTH-width){  
			xSpeed = -1;
		}
		if(x < 0){
			xSpeed = 1;
		}
	}

}
