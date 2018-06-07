package PG;

import java.awt.event.KeyEvent;

public class Hero extends FlyingObject{

	private int life;
	
	private int speed = 3;
	
	boolean left, right, up, down; // �ƶ������״̬  
	
	public Hero() {   //��ʼ��Ӣ�ۻ�
		life = 3;
		image = PlaneGame.hero1;
		width = image.getWidth();
		height = image.getHeight();
		
		x = 150; y = 400;
	}
	
	/** ���� */
	public void addLife(){  
		life++;
	}
	
	/** ���� */
	public void subtractLife(){  
		life--;
	}
	
	/** ��ȡ�� */
	public int getLife(){
		return life;
	}
	
	/** ��ǰ�����ƶ���һ�£���Ծ��룬x,y���λ��  */
	public void moveTo(int x,int y){   
		this.x = x - width / 2;
		this.y = y - height / 2;
	}
	
	
	public void KeyMoveTo(int x, int y){
		this.x = x;
		this.y = y;
	}

	/** �����ӵ� */
	public Bullet[] shoot(){   
		int xStep = width/4;      //4��
		int yStep = 20;  //��
		Bullet[] bullets = new Bullet[2];
		bullets[0] = new Bullet(x + xStep,y - yStep);  //y - yStep(�ӵ���ɻ���λ��)
		bullets[1] = new Bullet(x + 3 * xStep,y - yStep);
		return bullets;
	}
	
	/** ��ײ�㷨 */
	public boolean hit(FlyingObject other){
		
		int x1 = other.x - this.width / 2;                 //x������С����
		int x2 = other.x + this.width / 2 + other.width;   //x����������
		int y1 = other.y - this.height / 2;                //y������С����
		int y2 = other.y + this.height / 2 + other.height; //y����������
	
		int herox = this.x + this.width / 2;               //Ӣ�ۻ�x�������ĵ����
		int heroy = this.y + this.height / 2;              //Ӣ�ۻ�y�������ĵ����
		
		return herox > x1 && herox < x2 && heroy > y1 && heroy < y2;   //���䷶Χ��Ϊײ����
	}
	
	@Override
	public boolean OutofBorder() {
		// TODO Auto-generated method stub
		return false;
	}

	public void PressMove(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			up = true;
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			down = true;
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			left = true;
		}
		else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			right = true;
		}
	}
	
	public void ReleaseMove(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			up = false;
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			down = false;
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			left = false;
		}
		else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			right = false;
		}

	}
	
	@Override
	public void Move() {
		// TODO Auto-generated method stub
		if (left) {  
            x -= speed;  
            if (x < 0) {  
                x = 0;  
            }  
            
        }  
        if (right) {  
            x += speed;  
            if (x > PlaneGame.WIDTH) {  
                x = PlaneGame.WIDTH;
            }  
        }  
        if (up) {  
            y -= speed;  
            if (y < 0) {  
                y = 0;  
            }  
        }  
        if (down) {  
            y += speed;  
            if (y > PlaneGame.HEIGHT) {  
                y = PlaneGame.HEIGHT;  
            }  
        }  
	}

	
	
}
