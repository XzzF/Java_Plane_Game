package PG;

import java.awt.image.BufferedImage;


public abstract class FlyingObject {

	protected int x, y;           //����
	protected int width, height;    //��,��
	protected BufferedImage image;    //ͼƬ
	
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

	//�ж��Ƿ����
	public abstract boolean OutofBorder();
	
	//�������ƶ�
	public abstract void Move();
	
	/**
	 * ��鵱ǰ���������Ƿ��ӵ�(x,y)��(shoot)��
	 * @param Bullet �ӵ�����
	 * @return true��ʾ��������
	 */
	public boolean shootBy(Bullet bullet){
		int x = bullet.x;  //�ӵ�������
		int y = bullet.y;  //�ӵ�������
		return this.x < x && x < this.x + width && this.y < y && y < this.y + height;
	}

}
