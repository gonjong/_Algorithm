/*
 * 2017.07.05
 * BaekJoon 1912 : Dynamic Programming
 * @author gonjong
 */

import java.util.Scanner;

public class baek_1912 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new baek_1912().solution();
	}
	
	/*
	 * 연속 합을 구하는 문제
	 * 앞에서부터 max값을 찾으려면 경우의 수가 많아져서 거꾸로 생각.
	 */
	void solution(){
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int[] arr = new int[n];
		
		for(int i=0; i<n; i++){
			arr[i] = sc.nextInt();
		}
		
		int[] dp = new int[n];
		
		dp[n-1] = arr[n-1];
	
		//이전 최대값과 현재값을 더했을 때 현재값과 비교하여
		//더 큰 수를 dp[]에 입력한다.
		for(int i = n-2; i>=0; i--){
			if(dp[i+1]+arr[i]<arr[i]){
				dp[i] = arr[i];
			}
			else{
				dp[i] = dp[i+1]+arr[i];
			}
		}
		
		int max = dp[0];
		
		for(int i=1; i<n; i++){
			if(max<dp[i]){
				max = dp[i];
			}
		}
		
		System.out.println(max);
	}

}
