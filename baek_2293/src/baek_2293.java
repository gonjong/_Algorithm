/*
 * 2017.08.20
 * BaekJoon 2293 : Dynamic Programming
 * 각 코인별로 10정도까지 나열해서 규칙성을 발견해볼 것.
 * 배열을 1차원으로도 작성해보기.
 */

import java.util.Scanner;

public class baek_2293 {

	int n;
	int k;
	int coin[][];	//k값까지의 각 코인 개수, 행별로 코인 종류를 나타냄
	int c[];	//코인 종류를 표현, 오름차순으로 정렬
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new baek_2293().sol();
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
