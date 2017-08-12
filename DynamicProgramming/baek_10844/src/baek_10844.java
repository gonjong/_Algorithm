/*
 * 2017.05.17
 * baek)10844 : Dynamic Programming
 * @author gonjong
 * Int형 범위 때문에 10억으로 각각 모듈러 연산을 해서 계산한다.
 */


import java.util.Arrays;
import java.util.Scanner;

public class baek_10844 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int dp[][] = new int[N+1][10];

		Arrays.fill(dp[1], 1);
		dp[1][0] = 0;
		
		for(int i=2; i<=N; i++){
			for(int j=0; j<10;j++){
				
				if(j==0){
					dp[i][1] = (dp[i][1]+dp[i-1][0])%1000000000;
				}
				else if(j==9){
					dp[i][8] = (dp[i][8]+dp[i-1][9])%1000000000;
				}
				else{
					dp[i][j-1] = (dp[i][j-1]+dp[i-1][j])%1000000000;
					dp[i][j+1] = (dp[i][j+1]+dp[i-1][j])%1000000000;
				}
			
			}
		}
		
		int total = 0;
		for(int i=0; i<10; i++){
			total = (total+dp[N][i])%1000000000;
		}
		System.out.println(total);
	}

}
