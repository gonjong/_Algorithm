/*
 * 2017.04.24
 * baek_10610 : Greedy Algorithm, the number Theory
 * @author : gonjong
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class baek_10610 {

	static int sum = 0;	// 3의 배수인지 체크
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub


		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = null;
		
		try{
			
			s = br.readLine();
			
		}catch(IOException e){
			System.out.println(e);
		}
		
		ArrayList<Integer> N = new ArrayList<Integer>();
		
		countSort( N, s);
		
		
		/*
		 *  런타임 에러남, 이유가??
		ArrayList<Integer> N = new ArrayList<Integer>();
		
		for(int i=0; i<s.length(); i++){
			N.add((int)(s.charAt(i)-'0'));
			sum+=(int)(s.charAt(i)-'0');
		}
		
		if(sum%3!=0){
			System.out.println("-1");
			return;
		}
		
		descend d = new descend();
		
		Collections.sort(N, d);
		 */
		
		if(sum%3!=0){
			System.out.println("-1");
			return;
		}
		
		if(N.get(N.size()-1)==0){		//2의 배수이고 5의 배수인 경우, 즉 10의 배수인 경우

			for(int i=0; i<N.size(); i++){
				System.out.print(N.get(i));
			}
			System.out.println();
		
		}
		else{
			System.out.println("-1");
		}
	}

	/*
	 * Count Sort()
	 * 정수를 정렬하는 경우, 숫자가 0~9로 제한되므로 
	 * count sort()쓰는 것이 시간면에서 효과적.
	 */
	static void countSort( ArrayList<Integer> N, String s){
		int[] count = new int[10];
		
		for(int i=0; i<s.length(); i++){
			count[(int)(s.charAt(i)-'0')]++;
			sum+=(int)(s.charAt(i)-'0');
		}
		
		for(int i=9; i>=0; i--){
			while(count[i]!=0){
				N.add(i);
				count[i]--;
			}
		}
	}
}

