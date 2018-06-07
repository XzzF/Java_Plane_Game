package PG;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import java.util.*;
import java.util.Timer;

import javax.swing.*;
import javax.imageio.ImageIO;;


public class PlaneGame extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	public static final int WIDTH = 400; // ����
	public static final int HEIGHT = 654; // ����
	
	/** ��Ϸ�ĵ�ǰ״̬: START RUNNING PAUSE GAME_OVER PASSED*/
	
	private static final int START = 0;
	private static final int RUNNING = 1;	
	private static final int PAUSE = 2;
	private static final int GAME_OVER = 3;
	private static final int BOSS = 4;
	private static final int PASSED = 5;
	private int status;

	private int score = 0; // �÷�
	private Timer timer; // ��ʱ��
	private int interval = 1000 / 100; // ʱ����(����)
	

	public static BufferedImage background;
	public static BufferedImage background3;
	public static BufferedImage start;
	public static BufferedImage airplane;
	public static BufferedImage bee;
	public static BufferedImage bullet;
	public static BufferedImage hero0;
	public static BufferedImage hero1;
	public static BufferedImage pause;
	public static BufferedImage gameover;
	public static BufferedImage hero3;
	public static BufferedImage passed;
	public static BufferedImage boss1;
	
	private Hero hero = new Hero();
	private  Boss boss = new Boss();
	
	private FlyingObject[] flyings = {}; // �л�����
	private Bullet[] bullets = {}; // �ӵ�����
	
	
	static {    //��ʼ��ͼƬ
		try {
			background = ImageIO.read(PlaneGame.class.getResource("background.png"));
			background3 = ImageIO.read(PlaneGame.class.getResource("background3.png"));
			start = ImageIO.read(PlaneGame.class.getResource("start.png"));
			airplane = ImageIO.read(PlaneGame.class.getResource("airplane.png"));
			bullet = ImageIO.read(PlaneGame.class.getResource("bullet.png"));
			hero0 = ImageIO.read(PlaneGame.class.getResource("hero0.png"));
			hero1 = ImageIO.read(PlaneGame.class.getResource("hero1.png"));
			hero3 = ImageIO.read(PlaneGame.class.getResource("hero3.png"));
			pause = ImageIO.read(PlaneGame.class.getResource("pause.png"));
			gameover = ImageIO.read(PlaneGame.class.getResource("gameover.png"));
			passed = ImageIO.read(PlaneGame.class.getResource("passed.png"));
			boss1 = ImageIO.read(PlaneGame.class.getResource("boss1.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	/** �� */
	@Override
	public void paint(Graphics g) {
		g.drawImage(background, 0, 0, null); // ������ͼ
		paintHero(g); // ��Ӣ�ۻ�
		paintBullets(g); // ���ӵ�
		paintFlyingObjects(g); // ��������
		paintScore(g); // ������
		paintStatus(g); // ����Ϸ״̬
		if(status == BOSS) paintBoss(g);
	}



	private void paintBoss(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(boss.getImage(), boss.getX(), boss.getY(), null);
	}



	private void paintHero(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(hero.getImage(), hero.getX(), hero.getY(), null);
	}
	
	private void paintBullets(Graphics g) {
		// TODO Auto-generated method stub
		for (int i = 0; i < bullets.length; i++) {
			Bullet b = bullets[i];
			g.drawImage(b.getImage(), b.getX() - b.getWidth() / 2, b.getY(), null);
		}
	}
	
	private void paintFlyingObjects(Graphics g) {
		// TODO Auto-generated method stub
		for (int i = 0; i < flyings.length; i++) {
			FlyingObject f = flyings[i];
			g.drawImage(f.getImage(), f.getX(), f.getY(), null);
		}
	}
	
	private void paintScore(Graphics g) {
		// TODO Auto-generated method stub
		int x = 10; // x����
		int y = 25; // y����
		Font font = new Font(Font.SANS_SERIF, Font.BOLD, 22); // ����
		g.setColor(new Color(0xFF0000));
		g.setFont(font); // ��������
		g.drawString("SCORE:" + score, x, y); // ������
		y = y + 20; // y������20
		g.drawString("LIFE:" + hero.getLife(), x, y); // ����
	}
	
	private void paintStatus(Graphics g) {
		// TODO Auto-generated method stub
		if(status == START) {
			g.drawImage(start, 0, 0, null);
		}
		else if(status == PAUSE) {
			g.drawImage(pause, 0, 0, null);
		}
		else if(status == GAME_OVER) {
			g.drawImage(gameover, 0, 0, null);
		}
		else if(status == PASSED) {
			g.drawImage(passed, 65, 160, null);
		}
		
	}
	
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame("Fly");
		
		frame.setSize(WIDTH, HEIGHT); // ���ô�С
		frame.setAlwaysOnTop(true); // ��������������
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ĭ�Ϲرղ���
		frame.setIconImage(new ImageIcon("images/icon.jpg").getImage()); // ���ô����ͼ��
		frame.setLocationRelativeTo(null); // ���ô����ʼλ��
		frame.setVisible(true); // �������paint
		
		
		
		PlaneGame game = new PlaneGame(); // ������
		frame.add(game); // �������ӵ�JFrame��

		game.action(frame); // ����ִ��
		
		
		
		
		
	}

	private void action(JFrame frame) {
		// TODO Auto-generated method stub
		
		//status = RUNNING;

		KeyAdapter tt = new KeyAdapter () {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				//super.keyTyped(e);
				//System.out.println("KeyTyped");
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				//super.keyPressed(e);
				//System.out.println("Pressed");
				if(status == PAUSE || status == START) { 
					if(e.getKeyCode() == KeyEvent.VK_SPACE)
						status = RUNNING;
				}
				if(status == RUNNING || status == BOSS) {
					int x = hero.getX(), y = hero.getY();
					if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			            y = Math.min(background3.getHeight(), y + 10);
			        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
			            y = Math.max(0, y - 10);
			        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					    x = Math.min(background3.getWidth(), x + 10);
			        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			            x = Math.max(0, x - 10);
			        }
					hero.KeyMoveTo(x, y);
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				//super.keyReleased(e);
                //System.out.println("Released");
			}
			
			
		};


		

		MouseAdapter l = new MouseAdapter() {
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				if(status == PAUSE) {
					status = RUNNING;
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				if(status == RUNNING || status == BOSS) {
					status = PAUSE;
				}
			}
			
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				if (status == RUNNING || status == BOSS) { // ����״̬���ƶ�Ӣ�ۻ�--�����λ��
					int x = e.getX();
					int y = e.getY();
					hero.moveTo(x, y);
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if(status == START) {
					status = RUNNING;
				}
				else if(status == GAME_OVER) {
					flyings = new FlyingObject[0]; // ��շ�����
					bullets = new Bullet[0]; // ����ӵ�
					hero = new Hero(); // ���´���Ӣ�ۻ�
					boss = new Boss();
					score = 0; // ��ճɼ�
					status = START; // ״̬����Ϊ����
				}
			}
		};
		frame.addKeyListener(tt);  //������̲���
		this.addMouseListener(l); // �������������
		this.addMouseMotionListener(l); // ������껬������
		
		
		
		timer = new Timer(); // �����̿���
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				if (status == RUNNING || status == BOSS) { // ����״̬
					enterAction(); // �������볡
					stepAction(); // ��һ��
					shootAction(); // Ӣ�ۻ����
					bangAction(); // �ӵ��������
					outOfBoundsAction(); // ɾ��Խ������Ｐ�ӵ�
					checkGameAction(); // �����Ϸ��������ͨ��
				}
				repaint(); // �ػ棬����paint()����
			}

		}, interval, interval);
		
	}
	
	
	
	
	//--------------------------------------------
	

	int enemy_counter = 0;    //�л�����

	/** �������볡 */
	protected void enterAction() {
		// TODO Auto-generated method stub
		enemy_counter++;
		if(enemy_counter % 40 == 0) {    //400ms����һ���л�
			FlyingObject obj = new EnemyPlane(); // �������һ��������
			flyings = Arrays.copyOf(flyings, flyings.length + 1);
			flyings[flyings.length - 1] = obj;
		}
	}



	//-------------------------------------------------------

	/** ��������һ�� */
	public void flyingStepAction() {
		for (int i = 0; i < flyings.length; i++) {
			FlyingObject f = flyings[i];
			f.Move();
		}
	}
	
	protected void stepAction() {
		// TODO Auto-generated method stub
		
		for (int i = 0; i < flyings.length; i++) { // ��������һ��
			FlyingObject f = flyings[i];
			f.Move();
		}

		for (int i = 0; i < bullets.length; i++) { // �ӵ���һ��
			Bullet b = bullets[i];
			b.Move();
		}
		hero.Move(); // Ӣ�ۻ���һ��
		boss.Move();
	}

	
	
	int bullet_counter = 0;    //�ӵ�����
	
	/** ��� */
	protected void shootAction() {
		// TODO Auto-generated method stub
		
		bullet_counter++;
		if(bullet_counter % 30 == 0) {     //300ms��һ���ӵ�
			Bullet[] bs = hero.shoot(); // Ӣ�۴���ӵ�
			bullets = Arrays.copyOf(bullets, bullets.length + bs.length); // ����
			System.arraycopy(bs, 0, bullets, bullets.length - bs.length, bs.length); // ׷������
		}
	}

	//-----------------------------------------------------
	
	/** �ӵ����������ײ��� */
	protected void bangAction() {
		// TODO Auto-generated method stub
	
		
		for (int i = 0; i < bullets.length; i++) { // ���������ӵ�
			Bullet b = bullets[i];
			bang(b); // �ӵ��ͷ�����֮�����ײ���
			if(status == BOSS) bangBoss(b);
		}
	}
	
	protected void bangBoss(Bullet b) {
		// TODO Auto-generated method stub
		if(boss.shootBy(b)) boss.subtractLife();
	}

	private void bang(Bullet b) {
		// TODO Auto-generated method stub
		
		int index = -1; // ���еķ���������
		for (int i = 0; i < flyings.length; i++) {
			FlyingObject obj = flyings[i];
			if (obj.shootBy(b)) { // �ж��Ƿ����
				index = i; // ��¼�����еķ����������
				break;
			}
		}
		if (index != -1) { // �л��еķ�����
			FlyingObject one = flyings[index]; // ��¼�����еķ�����

			FlyingObject temp = flyings[index]; // �����еķ����������һ�������ｻ��
			flyings[index] = flyings[flyings.length - 1];
			flyings[flyings.length - 1] = temp;

			flyings = Arrays.copyOf(flyings, flyings.length - 1); // ɾ�����һ��������(�������е�)

			Enemy e = (Enemy) one; // ǿ������ת��
			score += e.getScore(); // �ӷ�
			
		}
	}

	//----------------------------------------------

	/** ɾ��Խ������Ｐ�ӵ� */
	protected void outOfBoundsAction() {
		// TODO Auto-generated method stub
		
		int index = 0; // ����
		FlyingObject[] flyingLives = new FlyingObject[flyings.length]; // ���ŵķ�����
		for (int i = 0; i < flyings.length; i++) {
			FlyingObject f = flyings[i];
			if (!f.OutofBorder()) {
				flyingLives[index++] = f; // ��Խ�������
			}
		}
		flyings = Arrays.copyOf(flyingLives, index); // ����Խ��ķ����ﶼ����

		index = 0; // ��������Ϊ0
		Bullet[] bulletLives = new Bullet[bullets.length];
		for (int i = 0; i < bullets.length; i++) {
			Bullet b = bullets[i];
			if (!b.OutofBorder()) {
				bulletLives[index++] = b;
			}
		}
		bullets = Arrays.copyOf(bulletLives, index); // ����Խ����ӵ�����
	}

	//------------------------------------------------------

	/** �����Ϸ�Ƿ���� */
	protected void checkGameAction() {
		// TODO Auto-generated method stub
		if(isGameOver()) {
			status = GAME_OVER;
		}
		if(isGameBoss()) {
			status = BOSS;
		}
		if(isGamePass()) {
			status = PASSED;
		}
	}

	private boolean isGameBoss() {
		// TODO Auto-generated method stub
		if(score > 10) return true;
		return false;
	}



	private boolean isGamePass() {
		// TODO Auto-generated method stub
		if(boss.getLife() < 0) return true;
		return false;
	}



	private boolean isGameOver() {
		// TODO Auto-generated method stub
		for (int i = 0; i < flyings.length; i++) {
			int index = -1;
			FlyingObject obj = flyings[i];
			if (hero.hit(obj)) { // ���Ӣ�ۻ���������Ƿ���ײ
				hero.subtractLife(); // ����
				index = i; // ��¼���ϵķ���������
			}
			if (index != -1) {
				FlyingObject t = flyings[index];
				flyings[index] = flyings[flyings.length - 1];
				flyings[flyings.length - 1] = t; // ���ϵ������һ�������ｻ��

				flyings = Arrays.copyOf(flyings, flyings.length - 1); // ɾ�����ϵķ�����
			}
			
		}
		
		return hero.getLife() <= 0;
	}

	
	
	//------------------------------------------------------

	
	

}
