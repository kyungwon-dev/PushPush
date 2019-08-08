package PushPush;


public class Model {
	private int[][][] GameMap;// 0은 사용자 1은 벽 2는 폭탄 3은 키 4는 길 5는 공들방 6 배경
	private int[][][] CopyMap;// 0은 사용자 1은 벽 2는 폭탄 3은 키 4는 길 5는 공들방 6 배경
	private int[] Store;
	private int stage, Move, Score, Time, adogen, Jump, saveTime;
	Model() {
		GameMap = new int[][][] {
				{ { 1, 1, 1, 1, 1, 6, 6, 6, 6, 6 },
						{ 1, 4, 4, 4, 1, 1, 1, 1, 1, 6 },
						{ 1, 4, 2, 2, 1, 1, 4, 4, 1, 6 },
						{ 1, 4, 4, 4, 4, 4, 4, 4, 1, 6 },
						{ 1, 1, 1, 1, 1, 4, 4, 4, 1, 6 },
						{ 6, 6, 6, 6, 1, 0, 4, 4, 1, 6 },
						{ 6, 6, 1, 1, 1, 4, 1, 1, 1, 6 },
						{ 6, 6, 1, 4, 4, 4, 4, 1, 6, 6 },
						{ 6, 6, 1, 3, 3, 4, 4, 1, 6, 6 },
						{ 6, 6, 1, 1, 1, 1, 1, 1, 6, 6 } },
				{ { 6, 6, 6, 6, 6, 6, 6, 6, 6, 6 },
						{ 6, 6, 6, 1, 1, 1, 6, 6, 6, 6 },
						{ 6, 6, 6, 1, 3, 1, 6, 6, 6, 6 },
						{ 6, 6, 6, 1, 4, 1, 1, 1, 1, 6 },
						{ 6, 1, 1, 1, 2, 4, 2, 3, 1, 6 },
						{ 6, 1, 3, 4, 2, 0, 1, 1, 1, 6 },
						{ 6, 1, 1, 1, 1, 2, 1, 6, 6, 6 },
						{ 6, 6, 6, 6, 1, 3, 1, 6, 6, 6 },
						{ 6, 6, 6, 6, 1, 1, 1, 6, 6, 6 },
						{ 6, 6, 6, 6, 6, 6, 6, 6, 6, 6 } },
				{ { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
						{ 1, 3, 1, 1, 1, 1, 1, 1, 1, 1 },
						{ 1, 4, 1, 1, 4, 4, 4, 1, 1, 6 },
						{ 1, 4, 1, 4, 4, 4, 4, 4, 1, 6 },
						{ 1, 4, 1, 4, 4, 0, 1, 4, 1, 6 },
						{ 1, 2, 4, 4, 4, 4, 4, 2, 3, 1 },
						{ 1, 4, 1, 4, 4, 4, 4, 4, 4, 1 },
						{ 1, 4, 4, 4, 2, 3, 4, 4, 4, 1 },
						{ 1, 4, 1, 1, 4, 1, 1, 4, 1, 6 },
						{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 } },
				{ { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
						{ 1, 4, 4, 4, 4, 4, 4, 4, 4, 3 },
						{ 1, 4, 1, 1, 4, 4, 4, 1, 1, 1 },
						{ 1, 4, 1, 4, 2, 4, 1, 1, 1, 4 },
						{ 1, 4, 1, 1, 0, 2, 3, 1, 4, 1 },
						{ 1, 3, 4, 4, 2, 4, 1, 1, 4, 1 },
						{ 1, 3, 4, 4, 2, 2, 1, 1, 4, 1 },
						{ 1, 1, 1, 1, 4, 4, 4, 4, 4, 1 },
						{ 1, 4, 4, 1, 4, 4, 1, 3, 4, 1 },
						{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 } },
				{ { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
						{ 1, 3, 2, 4, 1, 4, 1, 4, 3, 1 },
						{ 1, 4, 4, 1, 1, 4, 1, 4, 4, 1 },
						{ 1, 1, 1, 1, 4, 4, 1, 1, 4, 1 },
						{ 1, 3, 2, 1, 0, 2, 4, 4, 4, 1 },
						{ 1, 1, 1, 1, 4, 1, 1, 4, 4, 1 },
						{ 1, 1, 1, 1, 4, 1, 1, 1, 1, 1 },
						{ 1, 4, 2, 4, 4, 4, 4, 3, 1, 1 },
						{ 1, 4, 4, 4, 4, 4, 1, 1, 1, 1 },
						{ 4, 4, 4, 4, 4, 4, 4, 4, 4, 4 } } };
		Store = new int[100];
		stage = Move = Score = Time = adogen = Jump = saveTime = 0;
	}

	public void copyMap() {GameMap = CopyMap;}
	public int getTime() {	return Time;}
	public void setTime(int sec) {	Time = sec;}
	public int getStore(int index) {return Store[index];}
	public void SetStore(int index, int num) {Store[index] = num;}
	public int[][] getStageMap(int stage) {return GameMap[stage];}
	public int getMap(int x, int y, int z) {return GameMap[x][y][z];}
	public void setMap(int x, int y, int z, int chg) {GameMap[x][y][z] = chg;}
	public int getStage() {	return stage;}
	public void setStage(int x) {stage = x;}
	public int getMove() {return Move;}
	public void setMove(int i) {Move = i;}
	public int getScore() {	return Score;}
	public void setScore(int num) {	Score = num;}
	public int getadogen() {return adogen;}
	public void setadogen(int num) {adogen = num;}
	public int getJump() {	return Jump;}
	public void setJump(int num) {	Jump = num;}
	public int getsaveTime() {return saveTime;}
	public void setsaveTime(int num) {saveTime = num;}

}

