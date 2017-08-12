/*
 * 2017.04.15
 * baek_2579 : Dynamic Programming
 * @author
 * 문제의 경우의 수 분석 : 이전 계단을 밟은 경우 / 밟지 않은 경우
 * 밟은 경우 : current-3에서 밟은 경우 max 값+current-1 weight+ current weight
 * 밟지 않은 경우 : current-2 max값+current weight
 */


import java.util.Scanner;


public class baek_2579 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();

		int[] stair = new int[n+1];		//dp1의 계단 score
		int[][] max = new int[n+1][2];	//밟은 경우[][1], 밟지 않은 경우[][0]
		floor[] floorScore = new floor[n+1];	//dp2의 계단 score
		
		floorScore[0] = new floor(0);
		stair[0] = 0;
		for(int i=1; i<=n; i++){
			stair[i] = sc.nextInt();
			floorScore[i] = new floor(stair[i]);
		}
		

		//초기값 설정
		max[1][0] = stair[0];
		max[1][1] = stair[1];
				
		dp1(max, n, stair);
		dp2(floorScore, n);
	}

	static void dp1(int[][]max, int n, int[] stair){
		int current = 0;
		for(current=2; current<=n; current++){
			max[current][0] = max[current-1][1];
			max[current][1] = Math.max(max[current-2][0]+stair[current-1], max[current-2][1])+stair[current];
		}
		System.out.println(max[n][1]);
	}
	static void dp2(floor[] floorScore, int n){
		int current = 0;
		floorScore[1].max = floorScore[1].weight;
		floorScore[2].max = Math.max(floorScore[0].max, floorScore[1].max)+floorScore[2].weight;
		for(current=3; current<=n; current++){
			if(floorScore[current-2].max+floorScore[current].weight < floorScore[current-3].max+floorScore[current-1].weight+floorScore[current].weight){
				floorScore[current].max =floorScore[current-3].max+floorScore[current-1].weight+floorScore[current].weight;
			}
			else{
				floorScore[current].max = floorScore[current-2].max+floorScore[current].weight;
			}	
		}
		System.out.println(floorScore[n].max);
		
	}
	
}
class floor{
	int weight;
	int max;
	int continous = 0;
	
	floor(int w){
		this.weight = w;
	}
}
