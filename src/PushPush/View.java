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
	/***********************텍스트필드*********************/
	
	public JTextField[] GetJTextField() {return text;}
	public JLabel[] GetJLabel() {return label;}
	public void GetString(String str) {label[0].setText(str);}
	
	public void InsertNum() {
		button[9] = new Button("입력");
	}
	/***********************프레임*********************/
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
	/***********************미니게임*********************/
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
		label[0] = new JLabel("3개의 숫자를 입력하세요.");
		contentPane.add(panel,BorderLayout.CENTER);
		contentPane.add(label[0],BorderLayout.SOUTH);
	}
	
	/***********************랭킹*********************/
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
		contentPane.add(new JLabel("조작법:방향키를 눌러서 사용."));
		contentPane.add(new JLabel("미니게임:숫자 야구게임.6번 이내로 성공해야 함"));
		contentPane.add(new JLabel("아이템은 잘못 누를 경우 사라지므로 유의."));
		contentPane.add(new JLabel("아이템은 케릭터 방향으로 나감"));
		contentPane.add(new JLabel("시간은 60초가 되면 사라짐"));
		contentPane.add(new JLabel("점수:(60-time)*50 + Move*-5"));
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
	
	/***********************프레임반환*********************/
	
	/***********************버튼********************/
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
		button[0] = new Button("▶시작하기◀");
		button[0].setForeground(Color.BLACK);
		button[0].setBackground(Color.white);
		button[0].setBounds(450,40,150,40); 
	}
	public void EndButton() {
		button[1] = new Button("▶종료하기◀");
		button[1].setForeground(Color.WHITE);
		button[1].setBackground(Color.BLACK);
		button[1].setBounds(620, 400, 150, 40); 
	}
	public void miniGame() {
		button[2] = new Button("★미니 게임★");
		button[2].setForeground(Color.yellow);
		button[2].setBackground(Color.black);
		button[2].setBounds(450, 220,150, 40); 
	}
	public void Rank() {
		button[3] = new Button("순위 보기");
		button[3].setVisible(true);
		button[3].setForeground(Color.black);
		button[3].setBackground(Color.CYAN);
		button[3].setBounds(620, 100, 150, 40); 
	}
	public void Help() {
		button[4] = new Button("도움말");
		button[4].setForeground(Color.YELLOW);
		button[4].setBackground(Color.BLUE);
		button[4].setBounds(450, 400, 150, 35);
	}
	public void Stop() {
		button[5] = new Button("일시정지");
		button[5].setForeground(Color.RED);
		button[5].setBackground(Color.GRAY);
		button[5].setBounds(620, 40, 150, 40); 
	}
	public void Useadogen() {
		button[6] = new Button("사용하기");
		button[6].setForeground(Color.YELLOW);
		button[6].setBackground(Color.BLACK);
		button[6].setBounds(620, 260, 150, 35); 
	}
	public void UseJump() {
		button[7] = new Button("사용하기");
		button[7].setForeground(Color.YELLOW);
		button[7].setBackground(Color.BLACK);
		button[7].setBounds(620, 300, 150, 35); 
	}
	public void UseTime() {
		button[8] = new Button("사용하기");
		button[8].setForeground(Color.YELLOW);
		button[8].setBackground(Color.BLACK);
		button[8].setBounds(620, 340, 150, 35); 
	}
	/***********************값 받아오기*********************/
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
	/***********************이미지*********************/
	public void MapImage() {
		images[0] = toolkit.getImage("../images/케릭터(0).jpg");
		images[1] = toolkit.getImage("../images/벽돌(1).jpg");
		images[2] = toolkit.getImage("../images/폭탄(2).jpg");
		images[3] = toolkit.getImage("../images/키(3).jpg");
		images[4] = toolkit.getImage("../images/길(4).jpg");
		images[5] = toolkit.getImage("../images/확인(5).gif");
		images[6] = toolkit.getImage("../images/배경(6).jpg");
		images[7] = toolkit.getImage("../images/배경화면.jpg");
		images[8] = toolkit.getImage("../images/Boom.jpg");
		images[9] = toolkit.getImage("../images/초기화면.jpg");
		}
	public void paintMap() {
		this.MapImage();
		Container contentPane = frame[0].getContentPane();
		contentPane.add(this);
	}
	/***********************그리기 및 그림 출력*********************/
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
			g.setFont(new Font("돋움", 0, 40));
			g.drawString("STAGE" + (Stage + 1), 180, 32);
			g.setFont(new Font("견고딕", 0, 25));
			g.drawString("MOVE:\n" + Move, 450, 110);
			g.drawString("SCORE : " + Score, 450, 150);
			g.drawString("TIME", 448, 190);
			g.drawString("파동권 X " + adogen, 450, 290);
			g.drawString("점프 X " + Jump, 450, 330);
			g.drawString("시간증가 X " + saveTime, 450, 370);
			for (int j = 0; j < 100; j++)
				g.drawImage(images[StageMap[j / 10][j % 10]],
						(j % 10) * 40 + 40, (j / 10) * 40 + 40, this);
		}
	}
	public void FileLoad(){
	
		try {
			reader = new FileReader("Rank.txt");
			for (int i = 0; i < 20; i++) {
				int data = reader.read(); // char를 읽는 값이 아스키코드값이기 때문에 int형으로 선언
				if (data == -1)
					break; // -1을 만나면 while문을 벗어남
				Rank[i / 4][i % 4] = (char) data; // char형태로 형변환
			}
		}
		catch(FileNotFoundException fnfe){ //파일을 찾을 수 없는 Exception
			System.out.println("파일을 찾을 수 없습니다.");
			}
		catch(IOException ioe){//기타 발생할 수 있는 IO Exception 처리
			}
		finally{
			try{
				reader.close();//오류의 유무의 상관없이 파일을 닫아줘야한다.
				}
			catch(Exception e){
				System.out.println("파일을 닫는 중에 오류가 발생했습니다.");
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
				System.out.println("파일로 출력할 수 없습니다.");
			} finally {
				try {
					writer.close();
				} catch (Exception e) {

				}
			}
		}
	}
}
