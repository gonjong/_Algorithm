/*
 * 2017.04.25
 * baek_1094 : Greedy Algorithm
 * @author : gonjong
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

class ascend implements Comparator<Integer>{

	@Override
	public int compare(Integer arg0, Integer arg1) {
		// TODO Auto-generated method stub
		return arg0<arg1 ? -1:1;
	}
	
}

public class baek_1094 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();

		ArrayList<Integer> single = new ArrayList<Integer>();
		ArrayList<Integer> set = new ArrayList<Integer>();
	
		int min = 0;
		
		ascend a = new ascend();
		for(int i=0; i<M; i++){
			set.add(sc.nextInt());
			single.add(sc.nextInt());
		}
		
		Collections.sort(single, a);
		Collections.sort(set, a);
									/*
									 * 패키지 가격, 낱개 가격이 동시에 주어져 있지만
									 * 구하고자 하는 목적은 N개의 기타줄을 갈 수 있는 최소비용이므로
									 * 각각의 최소값에 대해서만 비교하면 된다.
									 */
		while(N>6){
			if(single.get(0)*6<set.get(0)){
				min+=single.get(0)*6;
			}
			else{
				min+=set.get(0);		
			}
			N = N-6;
		}
		if(single.get(0)*N<=set.get(0)){
			min += single.get(0)*N;
			System.out.println(min);
		}
		else{
			min += set.get(0);
			System.out.println(min);
		}	
		
	}

}

