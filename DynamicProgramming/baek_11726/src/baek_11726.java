/*
 * 2017.04.24
 * baek_11726 : Dynamic Programming
 * @author : gonjong
 * - 런타임에러 : 배열 설정 시 n+1로 하면 n=1인 경우 n=2의 값이 없기 때문에 런타임에러
 * - 답 틀림 : %10007을 하지 않을 경우, 오버플로우 발생하여 답이 틀림
 * - 알고리즘이 맞다면, 자료형 등을 n이 문제에서 주어진 max 값을 넣어서 오버플로우가 발생하지 않는지 체크해볼 것
 */

import java.util.Scanner;


public class baek_11726 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		long dp[] = new long[1001];	
		dp[1] = 1;	
		dp[2] = 2;
		
		for(int i=3; i<=n; i++){
			
			dp[i] = (dp[i-1]+dp[i-2])%10007;
			
		}

		System.out.println(dp[n]);
	}
}
