package PushPush;
import java.util.*;
public class BaseBall {
	Scanner in_number;
	int[] player_number;
	int[] cpu_number;
	int count;
	Random rand;
	int ball = 0, strike = 0;
	BaseBall()
	{
		rand = new Random();
		in_number = new Scanner(System.in);
		count = 0;
		player_number = new int[3];
		cpu_number = new int[3];
	}
	public void startGame() {
		randomcpu();
		strikeNball();
	}

	void randomcpu() {
		for (int i = 0; i < 3; i++) 
		{
			cpu_number[i]=rand.nextInt(9)+1;
			for (int j = 0; j < i; j++) {
				if (cpu_number[j] == cpu_number[i]){
					i = --j;
					break;
				}
			}
		}
		System.out.println(" "+cpu_number[0]+" "+cpu_number[1]+" "+cpu_number[2]);
	}
	public void setPlayer(String num1,String num2,String num3){
			player_number[0]=Integer.parseInt(num1);
			player_number[1]=Integer.parseInt(num2);
			player_number[2]=Integer.parseInt(num3);
			
	}
	public int strike(){
		return strike;
	}
	public int ball(){
		return ball;
	}
	public int Count(){
		return count;
	}
	void strikeNball() {
			strike = 0;
			ball = 0;
			for (int i = 0; i < 3; i++) {
				strike += (player_number[i] == cpu_number[i]) ? 1 : 0;
				for (int j = 0; j < 3; j++)
					ball += ((player_number[i] == cpu_number[j]) && i != j) ? 1	: 0;
			}
			
	}
	public String announceStrkeNball(int strike, int ball) {
		return strike + " strike"+ (strike == 3 ? " " : ",") + ball + " ball";
	}
}

