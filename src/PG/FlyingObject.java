package PG;

import java.awt.image.BufferedImage;


public abstract class FlyingObject {

	protected int x, y;           //坐标
	protected int width, height;    //宽,高
	protected BufferedImage image;    //图片
	
	//setter
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
	//getter
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public BufferedImage getImage() {
		return image;
	}

	//判断是否出界
	public abstract boolean OutofBorder();
	
	//飞行物移动
	public abstract void Move();
	
	/**
	 * 检查当前飞行物体是否被子弹(x,y)击(shoot)中
	 * @param Bullet 子弹对象
	 * @return true表示被击中了
	 */
	public boolean shootBy(Bullet bullet){
		int x = bullet.x;  //子弹横坐标
		int y = bullet.y;  //子弹纵坐标
		return this.x < x && x < this.x + width && this.y < y && y < this.y + height;
	}

}
