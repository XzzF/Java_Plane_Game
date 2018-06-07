package PG;



public class Boss extends FlyingObject implements Award, Enemy{

	private int life;
	
	private int xSpeed = 3;   //x�����ƶ��ٶ�
	private int ySpeed = 0;   //y�����ƶ��ٶ�
	private int awardScore;    //��������
	
	public Boss() {
		life = 5000;
		image = PlaneGame.boss1;
		width = image.getWidth();
		height = image.getHeight();
		x = 65;  y = 10;
		awardScore = 100;   //��ʼ��ʱ������
	}
	
	/** ���� */
	public void subtractLife(){  
		life--;
	}
	
	/** ��ȡ�� */
	public int getLife(){
		return life;
	}
	
	@Override
	public int getScore() {
		// TODO Auto-generated method stub
		return awardScore;
	}

	@Override
	public boolean OutofBorder() {
		// TODO Auto-generated method stub
		return false;
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
