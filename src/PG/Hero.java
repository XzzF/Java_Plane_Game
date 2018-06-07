package PG;

import java.awt.event.KeyEvent;

public class Hero extends FlyingObject{

	private int life;
	
	private int speed = 3;
	
	boolean left, right, up, down; // 移动方向的状态  
	
	public Hero() {   //初始化英雄机
		life = 3;
		image = PlaneGame.hero1;
		width = image.getWidth();
		height = image.getHeight();
		
		x = 150; y = 400;
	}
	
	/** 增命 */
	public void addLife(){  
		life++;
	}
	
	/** 减命 */
	public void subtractLife(){  
		life--;
	}
	
	/** 获取命 */
	public int getLife(){
		return life;
	}
	
	/** 当前物体移动了一下，相对距离，x,y鼠标位置  */
	public void moveTo(int x,int y){   
		this.x = x - width / 2;
		this.y = y - height / 2;
	}
	
	
	public void KeyMoveTo(int x, int y){
		this.x = x;
		this.y = y;
	}

	/** 发射子弹 */
	public Bullet[] shoot(){   
		int xStep = width/4;      //4半
		int yStep = 20;  //步
		Bullet[] bullets = new Bullet[2];
		bullets[0] = new Bullet(x + xStep,y - yStep);  //y - yStep(子弹距飞机的位置)
		bullets[1] = new Bullet(x + 3 * xStep,y - yStep);
		return bullets;
	}
	
	/** 碰撞算法 */
	public boolean hit(FlyingObject other){
		
		int x1 = other.x - this.width / 2;                 //x坐标最小距离
		int x2 = other.x + this.width / 2 + other.width;   //x坐标最大距离
		int y1 = other.y - this.height / 2;                //y坐标最小距离
		int y2 = other.y + this.height / 2 + other.height; //y坐标最大距离
	
		int herox = this.x + this.width / 2;               //英雄机x坐标中心点距离
		int heroy = this.y + this.height / 2;              //英雄机y坐标中心点距离
		
		return herox > x1 && herox < x2 && heroy > y1 && heroy < y2;   //区间范围内为撞上了
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
