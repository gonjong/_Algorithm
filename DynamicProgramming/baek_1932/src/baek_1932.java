/*
 * 2017.05.19
 * baek_1932 : Dynamic Programming
 * @gonjong
 */


import java.util.Arrays;
import java.util.Scanner;

public class baek_1932 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		Scanner sc = new Scanner(System.in);
		
		int H = sc.nextInt();
		int size = H*(H+1)/2;
		
		int dp[] = new int[size+1];
		int first = 1;
		
		for(int i=1; i<=H; i++){
			first = first+i-1;
			for(int j=0; j<i; j++){
				int p = sc.nextInt();;
				
				if(j==0){
					dp[first+j] = dp[first+j-i+1] + p;
				}
				else if(j==i-1){
					dp[first+j] = dp[first+j-i] + p;
				}
				else{
					if(dp[first+j-i]<dp[first+j-i+1]){
						dp[first+j] = dp[first+j-i+1]+p;
					}
					else{
						dp[first+j] = dp[first+j-i] + p;
					}
				}
			}
		}

		
		int max = 0;
		
		for(int i=0; i<H; i++){
			if(max<dp[first+i]){
				max = dp[first+i];
			}
		}
		System.out.println(max);
	}

}
