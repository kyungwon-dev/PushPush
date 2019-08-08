package PushPush;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
@SuppressWarnings("serial")
public class View extends JPanel{
	
	private JFrame[] frame;
	@SuppressWarnings("unused")
	private MenuBar Menubar;
	@SuppressWarnings("unused")
	private Menu[] menu;
	private Button[] button;
	private JTextField[] text;
	private JLabel[] label;
	private JPanel panel; 
	private Image[] images;
	private Toolkit toolkit;
	private int StageMap[][];
	private int Stage,Move,Score,adogen,Jump,Time,saveTime;
	private boolean Set;
	private FileReader reader;
	private FileWriter writer;
	private char[][] Rank;
	View() {
		Rank = new char[5][4];
		panel= new JPanel();
		text = new JTextField[3];
		frame = new JFrame[4];
		menu = new Menu[2];
		label = new JLabel[3];
		Menubar = new MenuBar();
		button = new Button[11];
		images = new Image[11];
		toolkit = this.getToolkit();
		StageMap = new int[10][10];
		Move = Stage = Score = adogen = Jump = Time =saveTime= 0;
		Set=false;
		writer = null;
		reader=null;
	}
	/***********************�ؽ�Ʈ�ʵ�*********************/
	
	public JTextField[] GetJTextField() {return text;}
	public JLabel[] GetJLabel() {return label;}
	public void GetString(String str) {label[0].setText(str);}
	
	public void InsertNum() {
		button[9] = new Button("�Է�");
	}
	/***********************������*********************/
	public void Mainframe() {
		frame[0] = new JFrame("PUSH PUSH");
		frame[0].setPreferredSize(new Dimension(800, 529));
		frame[0].setLocation(
				(Toolkit.getDefaultToolkit().getScreenSize().width - 800) / 2,
				(Toolkit.getDefaultToolkit().getScreenSize().height - 529) / 2);
		frame[0].setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame[0].pack();
		frame[0].setVisible(true);
	}
	/***********************�̴ϰ���*********************/
	public void miniGameframe()
	{
		frame[1]= new JFrame("Number Baseball");
		frame[1].setPreferredSize(new Dimension(344, 80));
		frame[1].setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - 344) / 2 + 220,
				(Toolkit.getDefaultToolkit().getScreenSize().height - 400) / 2 - 40 );
		frame[1].pack();
		frame[1].setVisible(true);
	}
	public void minigameSet()
	{
		Container contentPane = frame[1].getContentPane();
		panel.setLayout(new GridLayout(1,4));
		for(int i=0;i<3;i++)
		{
			text[i]=new JTextField();
			panel.add(text[i]);	
		}
		InsertNum();
		panel.add(button[9]);
		label[0] = new JLabel("3���� ���ڸ� �Է��ϼ���.");
		contentPane.add(panel,BorderLayout.CENTER);
		contentPane.add(label[0],BorderLayout.SOUTH);
	}
	
	/***********************��ŷ*********************/
	public void Ranking() {
		frame[2] = new JFrame("Rank");
		frame[2].setPreferredSize(new Dimension(200, 600));
		frame[2].setLocation(
				(Toolkit.getDefaultToolkit().getScreenSize().width - 200) / 2,
				(Toolkit.getDefaultToolkit().getScreenSize().height - 600) / 2);
		frame[2].pack();
		frame[2].setVisible(true);
	}
	public void Helping() {
		frame[3] = new JFrame("Help");
		frame[3].setPreferredSize(new Dimension(400, 300));
		frame[3].setLocation(
				(Toolkit.getDefaultToolkit().getScreenSize().width - 200) / 2,
				(Toolkit.getDefaultToolkit().getScreenSize().height - 600) / 2);
		frame[3].pack();
		frame[3].setVisible(true);
	}
	public void Help_Ann()
	{
		Container contentPane = frame[3].getContentPane();
		contentPane.setLayout(new BoxLayout(contentPane,BoxLayout.Y_AXIS));
		contentPane.add(new JLabel("���۹�:����Ű�� ������ ���."));
		contentPane.add(new JLabel("�̴ϰ���:���� �߱�����.6�� �̳��� �����ؾ� ��"));
		contentPane.add(new JLabel("�������� �߸� ���� ��� ������Ƿ� ����."));
		contentPane.add(new JLabel("�������� �ɸ��� �������� ����"));
		contentPane.add(new JLabel("�ð��� 60�ʰ� �Ǹ� �����"));
		contentPane.add(new JLabel("����:(60-time)*50 + Move*-5"));
	}
	
	public void RankSet()
	{
		FileLoad();
		Container contentPane = frame[2].getContentPane();
		GridLayout layout = new GridLayout(6,2);
		contentPane.setLayout(layout);
		contentPane.add(new JLabel("Rank"));
		contentPane.add(new JLabel("Score"));
		
		for(int i=0;i<5;i++)
		{
			contentPane.add(new JLabel(" "+(i+1)+"  "));
			contentPane.add(new JLabel(String.copyValueOf(Rank[i])));
		}
			
	}
	
	/***********************�����ӹ�ȯ*********************/
	
	/***********************��ư********************/
	@SuppressWarnings("deprecation")
	public void Buttoninit() {
		Rank();StartButton();EndButton();miniGame();
		Useadogen();UseJump();UseTime();Stop();Help(); 
		for (int i = 0; i < 9; i++)
			frame[0].add(button[i]);
		for(int i=2;i<9;i++)
			button[i].hide();
	}
	public void StartButton() {
		button[0] = new Button("�������ϱ⢸");
		button[0].setForeground(Color.BLACK);
		button[0].setBackground(Color.white);
		button[0].setBounds(450,40,150,40); 
	}
	public void EndButton() {
		button[1] = new Button("�������ϱ⢸");
		button[1].setForeground(Color.WHITE);
		button[1].setBackground(Color.BLACK);
		button[1].setBounds(620, 400, 150, 40); 
	}
	public void miniGame() {
		button[2] = new Button("�ڹ̴� ���ӡ�");
		button[2].setForeground(Color.yellow);
		button[2].setBackground(Color.black);
		button[2].setBounds(450, 220,150, 40); 
	}
	public void Rank() {
		button[3] = new Button("���� ����");
		button[3].setVisible(true);
		button[3].setForeground(Color.black);
		button[3].setBackground(Color.CYAN);
		button[3].setBounds(620, 100, 150, 40); 
	}
	public void Help() {
		button[4] = new Button("����");
		button[4].setForeground(Color.YELLOW);
		button[4].setBackground(Color.BLUE);
		button[4].setBounds(450, 400, 150, 35);
	}
	public void Stop() {
		button[5] = new Button("�Ͻ�����");
		button[5].setForeground(Color.RED);
		button[5].setBackground(Color.GRAY);
		button[5].setBounds(620, 40, 150, 40); 
	}
	public void Useadogen() {
		button[6] = new Button("����ϱ�");
		button[6].setForeground(Color.YELLOW);
		button[6].setBackground(Color.BLACK);
		button[6].setBounds(620, 260, 150, 35); 
	}
	public void UseJump() {
		button[7] = new Button("����ϱ�");
		button[7].setForeground(Color.YELLOW);
		button[7].setBackground(Color.BLACK);
		button[7].setBounds(620, 300, 150, 35); 
	}
	public void UseTime() {
		button[8] = new Button("����ϱ�");
		button[8].setForeground(Color.YELLOW);
		button[8].setBackground(Color.BLACK);
		button[8].setBounds(620, 340, 150, 35); 
	}
	/***********************�� �޾ƿ���*********************/
	public void SetImg(int i,String as) {images[i]= toolkit.getImage(as);}
	public Button[] getButton() {return button;}
	public JPanel getPanel(){return panel;}
	public Frame[] GetFrame() {	return frame;}
	public void SetMove(int move){Move = move;}
	public void SetStage(int stage) {Stage = stage;}
	public void SetScore(int score) {Score = score;}
	public void SetTime(int time) {Time = time;}
	public void SetMap(int map[][]) {StageMap = map;}
	public void setPaint(boolean set){Set=set;}
	public void Setadogen(int num){adogen=num;}
	public void SetJump(int num){Jump=num;}
	public void SetSaveTime(int num){saveTime=num;}
	/***********************�̹���*********************/
	public void MapImage() {
		images[0] = toolkit.getImage("../images/�ɸ���(0).jpg");
		images[1] = toolkit.getImage("../images/����(1).jpg");
		images[2] = toolkit.getImage("../images/��ź(2).jpg");
		images[3] = toolkit.getImage("../images/Ű(3).jpg");
		images[4] = toolkit.getImage("../images/��(4).jpg");
		images[5] = toolkit.getImage("../images/Ȯ��(5).gif");
		images[6] = toolkit.getImage("../images/���(6).jpg");
		images[7] = toolkit.getImage("../images/���ȭ��.jpg");
		images[8] = toolkit.getImage("../images/Boom.jpg");
		images[9] = toolkit.getImage("../images/�ʱ�ȭ��.jpg");
		}
	public void paintMap() {
		this.MapImage();
		Container contentPane = frame[0].getContentPane();
		contentPane.add(this);
	}
	/***********************�׸��� �� �׸� ���*********************/
	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawImage(images[9], 0, 0, this);
		if (Set==true) {
			g.clearRect(0, 0, getWidth(), getHeight());
			g.drawImage(images[7], 0, 0, this);
			g.setColor(Color.ORANGE);
			g.fillRect(0, 0, 520, 520);
			g.setColor(Color.RED);
			g.fillRect(450, 160, 320, 40);
			g.drawImage(images[8], 520 + (int)(3.5 * Time), 160, this);
			g.setColor(Color.WHITE);
			g.drawRect(450, 160, 320, 40);
			g.setFont(new Font("����", 0, 40));
			g.drawString("STAGE" + (Stage + 1), 180, 32);
			g.setFont(new Font("�߰��", 0, 25));
			g.drawString("MOVE:\n" + Move, 450, 110);
			g.drawString("SCORE : " + Score, 450, 150);
			g.drawString("TIME", 448, 190);
			g.drawString("�ĵ��� X " + adogen, 450, 290);
			g.drawString("���� X " + Jump, 450, 330);
			g.drawString("�ð����� X " + saveTime, 450, 370);
			for (int j = 0; j < 100; j++)
				g.drawImage(images[StageMap[j / 10][j % 10]],
						(j % 10) * 40 + 40, (j / 10) * 40 + 40, this);
		}
	}
	public void FileLoad(){
	
		try {
			reader = new FileReader("Rank.txt");
			for (int i = 0; i < 20; i++) {
				int data = reader.read(); // char�� �д� ���� �ƽ�Ű�ڵ尪�̱� ������ int������ ����
				if (data == -1)
					break; // -1�� ������ while���� ���
				Rank[i / 4][i % 4] = (char) data; // char���·� ����ȯ
			}
		}
		catch(FileNotFoundException fnfe){ //������ ã�� �� ���� Exception
			System.out.println("������ ã�� �� �����ϴ�.");
			}
		catch(IOException ioe){//��Ÿ �߻��� �� �ִ� IO Exception ó��
			}
		finally{
			try{
				reader.close();//������ ������ ������� ������ �ݾ�����Ѵ�.
				}
			catch(Exception e){
				System.out.println("������ �ݴ� �߿� ������ �߻��߽��ϴ�.");
				}
			}
	}

	public void hall(int Score) {
		if (Stage == 3) {
			try {
				writer = new FileWriter("Rank.txt", true); //
				String arr = Score + "";
				writer.write(arr);
			} catch (IOException ioe) {
				System.out.println("���Ϸ� ����� �� �����ϴ�.");
			} finally {
				try {
					writer.close();
				} catch (Exception e) {

				}
			}
		}
	}
}
