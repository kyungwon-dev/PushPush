package PushPush;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class Controller extends TimerTask implements KeyListener,ActionListener {
	View view;
	BaseBall mini;
	Model model;
	Timer timer;
	private boolean Stop,limitgame;
	private int state;
	Controller() {
		state=0;
		Stop=limitgame= false;
		this.mini = new BaseBall();
		this.model = new Model();
		this.view = new View();
		timer = new Timer();
	}
	/***********************게임시작*********************/
	public void start(){
		view.Mainframe();
		view.Buttoninit();
		view.paintMap();
		view.getButton()[0].addActionListener(this);
		view.getButton()[1].addActionListener(this);
	}
	/***********************시간 세팅*********************/
	public void TimeSetting(){
		TimerTask task = this;
		timer.schedule(task,0,1000); 
	}
	/***********************시간이 경과될 때마다 하는 일*********************/
	public void run(){
		if(!Stop){
		model.setTime(model.getTime()+1);
		view.SetTime(model.getTime());
		view.repaint();
		}
		if(model.getTime()==60)
			view.GetFrame()[0].setVisible(false);
	}
	/***********************게임시작*********************/
	@SuppressWarnings("deprecation")
	public void GamePhase(){
		view.SetMap(model.getStageMap(0));
		this.StoreLackwheel(0);
		this.TimeSetting();
		for(int i=2;i<9;i++)
			view.getButton()[i].show();
		for(int i=1;i<9;i++)
			view.getButton()[i].addActionListener(this);
		view.GetFrame()[0].addKeyListener(this);
	}
	/***********************버튼 눌릴때*********************/
	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent event) {
		Object obj = (Button) event.getSource();
		/****************시작 버튼****************/
		if (obj.equals(view.getButton()[0])) {
			view.getButton()[0].disable();
			view.setPaint(true);
			this.GamePhase();
		}
		/****************종료 버튼****************/
		else if (obj.equals(view.getButton()[1]))
			view.GetFrame()[0].setVisible(false);
		/****************미니 게임***************/
		else if (obj.equals(view.getButton()[2])){
			view.miniGameframe();
			view.minigameSet();
			mini.randomcpu();
			view.getButton()[2].disable();
			view.getButton()[9].addActionListener(this);
		}
		/****************랭킹****************/
		else if (obj.equals(view.getButton()[3])) {
			view.Ranking();
			view.RankSet();
			view.hall(model.getScore());
		}
		/****************맵 만들기 ****************/
		else if (obj.equals(view.getButton()[4])) {
			view.Helping();
			view.Help_Ann();
		}
		/****************파동권 ITEM ****************/
		else if (obj.equals(view.getButton()[6])) {
			if(model.getadogen()>0)
			{
				if(state==0 && Check(1, 0)==1)
					Change(1,0,4,0,findCharacter(0));
				else if(state==1 && Check(-1,0)==1)
					Change(-1,0,4,0,findCharacter(0));
				else if(state==2 && Check(0, 1)==1)
					Change(0,1,4,0,findCharacter(0));
				else if(state==3 && Check(0, -1)==1)
					Change(0,-1,4,0,findCharacter(0));
			}
			model.setadogen(model.getadogen()>0 ? model.getadogen()-1 : model.getadogen());
			view.Setadogen(model.getadogen());
		} 
		/****************JUMP ITEM ****************/
		else if (obj.equals(view.getButton()[7])) {
			if(model.getJump()>0)
			{
				if(state==0 && Check(2, 0)!=1 && Check(2, 0)!=6)
					Change(2,0,0,4,findCharacter(0));
				else if(state==1 && Check(-2, 0)!=1&& Check(-2, 0)!=6)
					Change(-2,0,0,4,findCharacter(0));
				else if(state==2 && Check(0, 2)!=1&& Check(0, 2)!=6)
					Change(0,2,0,4,findCharacter(0));
				else if(state==3 && Check(0, -2)!=1 && Check(0, -2)!=6)
					Change(0,-2,0,4,findCharacter(0));
				model.setJump(model.getJump()>0 ? model.getJump()-1 : model.getJump());
			}
			view.SetJump(model.getJump());
		} 
		/****************TIME 아이템****************/
		else if (obj.equals(view.getButton()[8])) {
			if(model.getsaveTime()>0)
			{
				model.setTime(model.getTime()-30);
				model.setsaveTime(model.getsaveTime()>0 ? model.getsaveTime()-1 : model.getsaveTime());
			}
			view.SetTime(model.getTime());
			view.SetSaveTime(model.getsaveTime());} 
		/****************미니 게임****************/
		else if (obj.equals(view.getButton()[9])) 
		{
			mini.setPlayer(view.GetJTextField()[0].getText(),view.GetJTextField()[1].getText(),	view.GetJTextField()[2].getText());
			mini.strikeNball();
			limitgame=true;
			view.GetString(mini.announceStrkeNball(mini.strike(), mini.ball()));
			if (mini.strike() == 3) 
			{
				view.GetFrame()[1].setVisible(false);
				if (mini.Count() < 6) 
				{
					model.setJump(model.getJump()+1);
					model.setadogen(model.getadogen()+1);
					model.setsaveTime(model.getsaveTime()+1);
				}
			}
			view.Setadogen(model.getadogen());
			view.SetJump(model.getJump());
			view.SetSaveTime(model.getsaveTime());
		}
		NextStage();
		view.GetFrame()[0].requestFocus();
		/****************일시정지****************/
		if(obj.equals(view.getButton()[5]))
		{
			if(!Stop){
				view.getButton()[5].requestFocus();
				if(!limitgame)
					view.getButton()[2].disable();
				for(int i=3;i<9;i++)
					view.getButton()[i].disable();
				view.getButton()[5].enable();
				Stop=true;
			}else{
				view.GetFrame()[0].requestFocus();
				if(!limitgame)
					view.getButton()[2].enable();
				for(int i=3;i<9;i++)
					view.getButton()[i].enable();
				Stop=false;
			}
		}
	}
	/***********************Map요소 바꾸기*********************/
	public void Change(int i, int j, int x, int y,int Swap) {
		model.setMap(model.getStage(), Swap / 10 + i, Swap % 10 + j, x);
		model.setMap(model.getStage(), Swap / 10, Swap % 10, y);
	}
	/***********************다음스테이지 넘어갈 지 판단*********************/
	public boolean Next(int i) {
		for (; i < 100	&& model.getMap(model.getStage(), i / 10, i % 10) != 2; i++);
		return (i == 100 ? true : false);
	}
	/***********************캐릭터 찾기*********************/
	public int findCharacter(int i) {
		for (; i < 100 && model.getMap(model.getStage(), i / 10, i % 10) != 0; i++)	;
		return i;
	}
	/***********************Map요소에 뭐가 있는지 확인*********************/
	public int Check(int i, int j) {
		return model.getMap(model.getStage(), findCharacter(0) / 10 + i,findCharacter(0) % 10 + j);
	}
	/***********************다음 스테이지 넘어갈 때 Map변경*********************/
	@SuppressWarnings("deprecation")
	public void NextStage() {
		if(Next(0) == true)StoreLackwheel(1);
		limitgame=Next(0);
		view.getButton()[2].enable();
		model.setScore(Next(0) == true ? model.getScore()+(60-model.getTime())*50 + model.getMove()*(-5) : model.getScore());
		view.SetScore(model.getScore());
		model.setMove((Next(0) == true ? 0 : model.getMove()));
		view.SetMove(model.getMove());
		model.setTime(Next(0) == true ? 0 : model.getTime());
		model.setStage(model.getStage()	+ ((Next(0) == true && model.getStage()!=4) ? 1 : 0));
		view.SetStage(model.getStage());
		view.SetMap(model.getStageMap((model.getStage())));
		view.repaint();
		
		
	}
	/***********************2개톱니 위치 저장*********************/
	public void StoreLackwheel(int nextstage) {
		for (int i = 0; i < 100; i++)
			model.SetStore(i, model.getMap(model.getStage() + nextstage,i / 10, i % 10) == 3 ? i : 0);
	}
	/***********************톱니 위치 복원*********************/
	public void reStoreLackwheel() {
		for (int i = 0; i < 100; i++) {
			if (model.getMap(model.getStage(), model.getStore(i) / 10, model.getStore(i) % 10) == 4	&& model.getStore(i) != 0)
				model.setMap(model.getStage(), model.getStore(i) / 10, model.getStore(i) % 10, 3);
		}
	}
	
	public void Move(int dy,int dx){
		if( (2<=Check(dy,dx)) && (Check(dy,dx)<=5))
		{
			if(Check(dy,dx)==2 || Check(dy,dx)==5)
			{
				if(Check(2*dy,2*dx)==4 )
				{
					Change(2*dy,2*dx, 2, 0,findCharacter(0));
					Change(dy,dx,0,4,findCharacter(0));
				}
				else if(Check(2*dy,2*dx)==3)
				{
					Change(2*dy,2*dx, 5, 0,findCharacter(0));
					Change(dy,dx,0,4,findCharacter(0));
				}
			}
			else if(Check(dy,dx)==3 || Check(dy,dx)==4)
				Change(dy,dx,0,4,findCharacter(0));
			reStoreLackwheel();
			model.setMove(model.getMove()+1);
		}
	}
	/***********************키 눌렸을 때 *********************/
	public void keyPressed(KeyEvent key) {
		int dx=0,dy=0;
		switch (key.getKeyCode()){
		case KeyEvent.VK_UP:
			dy--;
			view.SetImg(0,"뒤.jpg");
			state=1;
			break;
		case KeyEvent.VK_DOWN:
			dy++;
			view.SetImg(0,"케릭터(0).jpg");
			state=0;
			break;
		case KeyEvent.VK_RIGHT:
			dx++;
			view.SetImg(0,"우.jpg");
			state=2;
			break;
		case KeyEvent.VK_LEFT:
			dx--;
			view.SetImg(0,"좌.jpg");
			state=3;
			break;
		}
		
		Move(dy,dx);
		NextStage();
	}
	@SuppressWarnings("deprecation")
	public void EndGame()
	{
		if(model.getStage()==1)
			view.GetFrame()[0].hide();
	}
	/***********************안쓰이는 오버라이딩 *********************/
	public void keyTyped(KeyEvent e) {}
	public void keyReleased(KeyEvent key) {	}
}




