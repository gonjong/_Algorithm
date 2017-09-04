/*
 * 2017.08.31
 * BaekJoon 2156 : DynamicProgramming
 * @author gonjong
 * 안 마셨을 때를 생각하지 못함
 * dp[0], dp[1]인 경우 설정 주의 
 */

import java.util.Scanner;

public class baek_2156 {

	int n;
	int amount[][];	//amount[0] : 현재 포도주량, amount[1] : 현재 것과 이전 것 마신 량
	int dp[];	//현재까지 마실 수 있는 최대 포도주량
	int max = 0;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new baek_2156().solution();
	}

	void solution() {
	
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		amount = new int[2][n];
		dp = new int[n];
		
		for(int i=0; i<n; i++) {
			amount[0][i] = sc.nextInt();
			if(i-1>=0) {
				amount[1][i] = amount[0][i-1]+amount[0][i];
			}
			
		}
		
		//초기값 설정
		dp[0] = amount[0][0];
		max = dp[0];
		if(n>=2) {
			dp[1] = amount[0][1]+amount[0][0];
			if(dp[1]>max) {
				max = dp[1];
			}
		}
		
		for(int i=2; i<n; i++) {
				int x1 = amount[0][i]+dp[i-2];
				int x2 = amount[1][i];
	
				if(i-3>=0) {	//등호 안해서 52%에서 계속 틀림ㅠㅠ 등호주의!!
					x2 = amount[1][i]+dp[i-3];		
				}
				//마시지 않은 경우는 dp[i-1], 비교해서 더 큰 값을 설정
				if(x2<dp[i-1]) {
					x2 = dp[i-1];
				}

//				System.out.print(x1+","+x2+",");
				
				if(x1>x2) {
					dp[i] = x1;
				}
				else {
					dp[i] = x2;
				}
				
//				System.out.println(dp[i]);
				if(dp[i]>max) {
					max = dp[i];
				}
			
		}
		
		System.out.println(max);
	}
}
