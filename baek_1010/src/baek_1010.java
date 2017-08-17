/*
 * 2017.08.17
 * BaekJoon 1010 : 조합 계산, dynamic programming
 * @author gonjong
 * 조합을 곱셈 계산으로 하면 부동소수점 문제로 오류값이 발생 ; 곱셈 방식은 미해결
 */

import java.util.Scanner;

public class baek_1010 {

	int T;
	int N;
	int M;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new baek_1010().solution();
	}

	void solution() {
		
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
	
		for(int i=0; i<T; i++) {
			N = sc.nextInt();
			M = sc.nextInt();
			
			System.out.println(dp(M, N));
		}
	}
	
	long dp(int n, int m) {

		long dp[][] = new long[n+1][n+1];
		
		for(int i=1; i<=n; i++) {
			for(int j=0;j<=i; j++) {
				if(j==0||j==i) {
					dp[i][j] = 1;
				}
				else {
					dp[i][j] = dp[i-1][j-1]+dp[i-1][j];		
				}
			}
		}
		
		
		return dp[n][m];	
		
	}
	
	long combination(int n, int m) {
		long x = 1;
		long y = 1;
	
		for(int j=0; j<m; j++) {
			x *= n-j;
			y *= j+1;
		}
		
		return (x/y);
	}
}
