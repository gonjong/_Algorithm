/*
 * 2017.04.15
 * baek_2193 : Dynamic Programming
 * N자리 이친수 구하기
 * 이진수가 0으로 시작하지 않고, 이진수 내에 1이 2개 연속하지 않는 수
 * @author : gonjong
 */

import java.util.Scanner;


public class baek_2193 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		find(N);
	}
	
	
	static void find(int N){
		long dp[][] = new long[N+1][2];
		
		dp[1][0] = 0;
		dp[1][1] = 1;
		
		for(int i=2; i<N+1; i++){
			dp[i][0] = dp[i-1][0]+dp[i-1][1];
			dp[i][1] = dp[i-1][0];
		}
		
		System.out.println(dp[N][0]+dp[N][1]);
	}

}
