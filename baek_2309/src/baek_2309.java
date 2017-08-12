/*
 * 2017.08.06
 * BaekJoon 2309 : brute force
 * @gonjong
 * 
 * 경우의 수가 많으면 경우의 수가 작은 방향으로 생각 전환
 * 7개의 조합을 다루는 것이 아니라 2개의 조합만 다룸
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class baek_2309 {

	int N = 9;
	ArrayList<Integer> x = new ArrayList<Integer>();
	int[] list = new int[N];
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		new baek_2309().solution();
	}
	
	
	void solution() {
		
		Scanner sc = new Scanner(System.in);
		int sum = 0;
		int sub = 0;
		
		for(int i=0; i<9;  i++) {
			list[i] = sc.nextInt();
			sum += list[i];
		}
		Arrays.sort(list);
		
		for(int i=0; i<9; i++) {
			for(int j=i+1; j<9; j++) {
				sub = list[i]+list[j];
				
				if(sum-sub==100) {
					for(int k=0; k<9; k++) {
						if(k!=i && k!=j) {
							System.out.println(list[k]);
						}
					}
					return;
				}
			}
		}
	}
	/*
	int rec(int num, int cnt) {
		boolean[] check = new boolean[N];
		
		if(x.size()==7) {
			int sum = 0;
			for(int i=0; i<x.size(); i++) {
				sum += x.get(i);
			}
			if(sum==100) {
				for(int i=0; i<x.size();i++) {
					System.out.println(x.get(i));
				}
				return -1;
			}
			return 0;
		}
		
		Arrays.fill(check, false);
		
		for(int i=num; i<9; i++) {
			x.add(list[i]);
			check[i] = true;
			
			if((i+1 != 9)&&!check[i+1]) {
				int a = 0;
				a = rec(num+1, i+1);

				x.remove(x.size()-1);	
				
				if(a==-1) {
					return -1;
				}
			}
			
		}
		return 0;
	}
	*/
}
