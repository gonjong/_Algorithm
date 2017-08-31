/*
 * 2017.08.20
 * BaekJoon 2293 : Dynamic Programming
 * 각 코인별로 10정도까지 나열해서 규칙성을 발견해볼 것.
 * 배열을 1차원으로도 작성해보기.
 */

import java.util.Arrays;
import java.util.Scanner;

public class baek_2293 {

	int n;
	int k;
	int coin[][];	//k값까지의 각 코인 개수, 행별로 코인 종류를 나타냄
	int c[];	//코인 종류를 표현, 오름차순으로 정렬
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new baek_2293().solution();
	}

	void solution() {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		k = sc.nextInt();
		c = new int[n+1];
		int dp[] = new int[10001];
		
		for(int i=1; i<=n; i++) {
			c[i] = sc.nextInt();
		}
		dp[0] = 1;
		
		//바깥 반복문이 코인에 대한 것이 되어야 함
		//하나의 코인 종류에 대해서 모든 k까지 갈 수 있는 경우의 수 계산 후
		//다음 코인에 대해 반복적으로 실시
		
		for(int i=1; i<=n; i++) {
			for(int j=0; j<=k; j++) {
				if(j-c[i]>=0) {
					dp[j] += dp[j-c[i]];
				}
			}
		}
		System.out.println(dp[k]);
		
		Arrays.fill(dp, 0);
		dp[0] = 1;
		for(int i=1; i<=k; i++) {
			for(int j=0; j<=n; j++) {
				if(i-c[j]>=0) {
					dp[i] += dp[i-c[j]];
				}
			}
		}
		System.out.println(dp[k]);
	}
	
	void sol() {
		
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		k = sc.nextInt();
		c = new int[n];
		coin = new int[n][k+1];
		for(int i=0; i<n; i++) {
			int x = sc.nextInt();
			if(i==0) {
				c[i] = x;
			}
			//삽입정렬로 오름차순으로 정리
			if(i>=1) {
				int j=i;
				for(;j>0;j--) {
					if(x<c[j]) {
						c[j] = c[j-1];
					}
					else {
						break;
					}
				}
				c[j] = x;
			}	
		}
	
		for(int i=1; i<=k; i++) {
			for(int j=0;j<n;j++) {
				int x = i-c[j];	//구하고자 하는 k원에서 코인 값을 뺀 값
				
				// 코인 종류 j보다 작은 코인들을 이용해서 x를 만들 수 있는 경우의 수를 구한다.
				if(x>0) {
					for(int h=0; h<=j;h++) {
						coin[j][i] += coin[h][x];
					}
				}
				if(x==0) {
					coin[j][i] = 1;
				}
			}
		}
		
		int sum = 0;
		for(int i=0; i<n; i++) {
			sum+=coin[i][k];
		}
		
		System.out.println(sum);
	}
	
}
