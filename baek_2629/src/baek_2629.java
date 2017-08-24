/*
 * 2017.08.25
 * BaekJoon 2629 :  Dynamic Programming, BackTracking
 * @author gonjong
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class baek_2629 {

	int nChoo;
	int nGu;
	ArrayList<Integer> a = new ArrayList<Integer>();
	ArrayList<Integer> b = new ArrayList<Integer>();
	boolean dp[];
	boolean change[];
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		new baek_2629().solution();
	}
	
	void solution() {
		
		Scanner sc = new Scanner(System.in);
		
		nChoo = sc.nextInt();
		for(int i=0; i<nChoo; i++) {
			a.add(sc.nextInt());
		}
		
		dp = new boolean[250001];
		Arrays.fill(dp, false);
		dp[0] = true;
		change = new boolean[250001];
		nGu = sc.nextInt();
		for(int i=0; i<nGu; i++) {
			b.add(sc.nextInt());
		}
		
		check();
	}
	
	void check() {
		
		for(int i=0; i<a.size();i++) {
			Arrays.fill(change, false);
			
			//이전 추의 무게들로 만들 수 있는  g에 현재 추의 무게로 만들 수 있는 경우의 수를 추가
			//경우의 수는 두 무게를 더하거나 차이에 해당하는 값
			//change는 이번 추를 추가하여 새로운 무게가 생기는 경우  true, 그렇지 않으면 false
			for(int j=0; j<dp.length; j++) {
				if(dp[j] && !change[j]) {
					int plus = a.get(i)+j;
					int minus = (int)Math.abs(a.get(i)-j);
									
					if(!dp[plus]) {
						change[plus] = true;
					}
					dp[plus] = true;
					if(!dp[minus]) {
						change[minus] = true;
					}
					dp[minus] = true;
					
				}
			}
		}
		
		for(int i=0; i<b.size();i++) {
			if(dp[b.get(i)]) {
				System.out.print("Y ");
			}
			else {
			System.out.print("N ");
			}
		}
		
	}
}
