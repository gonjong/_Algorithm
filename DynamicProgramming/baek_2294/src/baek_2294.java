/*
 * 2017.08.28
 * BaekJoon 2294 : Dynamic Programming
 * @author gonjong
 *  동전 1은 경우의 수, 동전 2는 만들 수 있는 최소의 수
 *  동전 1 : for문할 때 경우의 수는 동전 1개를 k까지 확인 후 다음 동전에 대해 반복문 실행
 *  동전 2 : k마다 동전 3개를 탐색해서 최소의 것만 탐색
 */
import java.util.Arrays;
import java.util.Scanner;

public class baek_2294 {

	int n;
	int k;
	int coin[];
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new baek_2294().solution();
	}
	
	void solution() {
		
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		k = sc.nextInt();
		coin = new int[n];
		int dp[] = new int[k+1];
		
		for(int i=0; i<n; i++) {
			coin[i] = sc.nextInt();
		}
		
		Arrays.fill(dp, -1);
		dp[0]=0;
		
		for(int i=1; i<=k; i++) {
			int min = Integer.MAX_VALUE;
	
			for(int j=0;j<n; j++) {
				if(i-coin[j]>=0 && dp[i-coin[j]]>=0) {
					if(min>dp[i-coin[j]]) {
						min = dp[i-coin[j]]+1;
					}
				}	
			}
			
			//만들 수 있는 경우가 없는 경우 : -1
			if(min==Integer.MAX_VALUE) {
				min = -1;
			}
			dp[i] = min;
			
		}
		
		if(dp[k]==0) {
			System.out.println("-1");
		}
		else {
			System.out.println(dp[k]);	
		}
	}

}
