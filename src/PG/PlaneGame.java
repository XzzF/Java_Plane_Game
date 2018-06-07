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
	public static final int WIDTH = 400; // 面板宽
	public static final int HEIGHT = 654; // 面板高
	
	/** 游戏的当前状态: START RUNNING PAUSE GAME_OVER PASSED*/
	
	private static final int START = 0;
	private static final int RUNNING = 1;	
	private static final int PAUSE = 2;
	private static final int GAME_OVER = 3;
	private static final int BOSS = 4;
	private static final int PASSED = 5;
	private int status;

	private int score = 0; // 得分
	private Timer timer; // 定时器
	private int interval = 1000 / 100; // 时间间隔(毫秒)
	

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
	
	private FlyingObject[] flyings = {}; // 敌机数组
	private Bullet[] bullets = {}; // 子弹数组
	
	
	static {    //初始化图片
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
	
	
	
	/** 画 */
	@Override
	public void paint(Graphics g) {
		g.drawImage(background, 0, 0, null); // 画背景图
		paintHero(g); // 画英雄机
		paintBullets(g); // 画子弹
		paintFlyingObjects(g); // 画飞行物
		paintScore(g); // 画分数
		paintStatus(g); // 画游戏状态
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
		int x = 10; // x坐标
		int y = 25; // y坐标
		Font font = new Font(Font.SANS_SERIF, Font.BOLD, 22); // 字体
		g.setColor(new Color(0xFF0000));
		g.setFont(font); // 设置字体
		g.drawString("SCORE:" + score, x, y); // 画分数
		y = y + 20; // y坐标增20
		g.drawString("LIFE:" + hero.getLife(), x, y); // 画命
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
		
		frame.setSize(WIDTH, HEIGHT); // 设置大小
		frame.setAlwaysOnTop(true); // 设置其总在最上
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 默认关闭操作
		frame.setIconImage(new ImageIcon("images/icon.jpg").getImage()); // 设置窗体的图标
		frame.setLocationRelativeTo(null); // 设置窗体初始位置
		frame.setVisible(true); // 尽快调用paint
		
		
		
		PlaneGame game = new PlaneGame(); // 面板对象
		frame.add(game); // 将面板添加到JFrame中

		game.action(frame); // 启动执行
		
		
		
		
		
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
				if (status == RUNNING || status == BOSS) { // 运行状态下移动英雄机--随鼠标位置
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
					flyings = new FlyingObject[0]; // 清空飞行物
					bullets = new Bullet[0]; // 清空子弹
					hero = new Hero(); // 重新创建英雄机
					boss = new Boss();
					score = 0; // 清空成绩
					status = START; // 状态设置为启动
				}
			}
		};
		frame.addKeyListener(tt);  //处理键盘操作
		this.addMouseListener(l); // 处理鼠标点击操作
		this.addMouseMotionListener(l); // 处理鼠标滑动操作
		
		
		
		timer = new Timer(); // 主流程控制
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				if (status == RUNNING || status == BOSS) { // 运行状态
					enterAction(); // 飞行物入场
					stepAction(); // 走一步
					shootAction(); // 英雄机射击
					bangAction(); // 子弹打飞行物
					outOfBoundsAction(); // 删除越界飞行物及子弹
					checkGameAction(); // 检查游戏结束或者通关
				}
				repaint(); // 重绘，调用paint()方法
			}

		}, interval, interval);
		
	}
	
	
	
	
	//--------------------------------------------
	

	int enemy_counter = 0;    //敌机计数

	/** 飞行物入场 */
	protected void enterAction() {
		// TODO Auto-generated method stub
		enemy_counter++;
		if(enemy_counter % 40 == 0) {    //400ms生成一个敌机
			FlyingObject obj = new EnemyPlane(); // 随机生成一个飞行物
			flyings = Arrays.copyOf(flyings, flyings.length + 1);
			flyings[flyings.length - 1] = obj;
		}
	}



	//-------------------------------------------------------

	/** 飞行物走一步 */
	public void flyingStepAction() {
		for (int i = 0; i < flyings.length; i++) {
			FlyingObject f = flyings[i];
			f.Move();
		}
	}
	
	protected void stepAction() {
		// TODO Auto-generated method stub
		
		for (int i = 0; i < flyings.length; i++) { // 飞行物走一步
			FlyingObject f = flyings[i];
			f.Move();
		}

		for (int i = 0; i < bullets.length; i++) { // 子弹走一步
			Bullet b = bullets[i];
			b.Move();
		}
		hero.Move(); // 英雄机走一步
		boss.Move();
	}

	
	
	int bullet_counter = 0;    //子弹计数
	
	/** 射击 */
	protected void shootAction() {
		// TODO Auto-generated method stub
		
		bullet_counter++;
		if(bullet_counter % 30 == 0) {     //300ms打一颗子弹
			Bullet[] bs = hero.shoot(); // 英雄打出子弹
			bullets = Arrays.copyOf(bullets, bullets.length + bs.length); // 扩容
			System.arraycopy(bs, 0, bullets, bullets.length - bs.length, bs.length); // 追加数组
		}
	}

	//-----------------------------------------------------
	
	/** 子弹与飞行物碰撞检测 */
	protected void bangAction() {
		// TODO Auto-generated method stub
	
		
		for (int i = 0; i < bullets.length; i++) { // 遍历所有子弹
			Bullet b = bullets[i];
			bang(b); // 子弹和飞行物之间的碰撞检查
			if(status == BOSS) bangBoss(b);
		}
	}
	
	protected void bangBoss(Bullet b) {
		// TODO Auto-generated method stub
		if(boss.shootBy(b)) boss.subtractLife();
	}

	private void bang(Bullet b) {
		// TODO Auto-generated method stub
		
		int index = -1; // 击中的飞行物索引
		for (int i = 0; i < flyings.length; i++) {
			FlyingObject obj = flyings[i];
			if (obj.shootBy(b)) { // 判断是否击中
				index = i; // 记录被击中的飞行物的索引
				break;
			}
		}
		if (index != -1) { // 有击中的飞行物
			FlyingObject one = flyings[index]; // 记录被击中的飞行物

			FlyingObject temp = flyings[index]; // 被击中的飞行物与最后一个飞行物交换
			flyings[index] = flyings[flyings.length - 1];
			flyings[flyings.length - 1] = temp;

			flyings = Arrays.copyOf(flyings, flyings.length - 1); // 删除最后一个飞行物(即被击中的)

			Enemy e = (Enemy) one; // 强制类型转换
			score += e.getScore(); // 加分
			
		}
	}

	//----------------------------------------------

	/** 删除越界飞行物及子弹 */
	protected void outOfBoundsAction() {
		// TODO Auto-generated method stub
		
		int index = 0; // 索引
		FlyingObject[] flyingLives = new FlyingObject[flyings.length]; // 活着的飞行物
		for (int i = 0; i < flyings.length; i++) {
			FlyingObject f = flyings[i];
			if (!f.OutofBorder()) {
				flyingLives[index++] = f; // 不越界的留着
			}
		}
		flyings = Arrays.copyOf(flyingLives, index); // 将不越界的飞行物都留着

		index = 0; // 索引重置为0
		Bullet[] bulletLives = new Bullet[bullets.length];
		for (int i = 0; i < bullets.length; i++) {
			Bullet b = bullets[i];
			if (!b.OutofBorder()) {
				bulletLives[index++] = b;
			}
		}
		bullets = Arrays.copyOf(bulletLives, index); // 将不越界的子弹留着
	}

	//------------------------------------------------------

	/** 检查游戏是否结束 */
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
			if (hero.hit(obj)) { // 检查英雄机与飞行物是否碰撞
				hero.subtractLife(); // 减命
				index = i; // 记录碰上的飞行物索引
			}
			if (index != -1) {
				FlyingObject t = flyings[index];
				flyings[index] = flyings[flyings.length - 1];
				flyings[flyings.length - 1] = t; // 碰上的与最后一个飞行物交换

				flyings = Arrays.copyOf(flyings, flyings.length - 1); // 删除碰上的飞行物
			}
			
		}
		
		return hero.getLife() <= 0;
	}

	
	
	//------------------------------------------------------

	
	

}
