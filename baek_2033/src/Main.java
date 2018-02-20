/*
 * 2018.02.11
 * BaekJoon 2033 : simulation
 * @author gonjong
 * 반올림에 대한 문제 파악이 틀린 것의 원인
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

	ArrayList<Integer> N; 
	int bias=0;
	double ans;
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new Main().sol();
	}
	
	int cal(int e) {
		e = e+bias;
		if(bias>0) bias--;
		if(e>=5) {
			bias++;
		}
		return e;
	}
	
	void cc(int comp) {
		
		if(ans>Math.pow(10, comp)) {
//			System.out.println(Math.floor(ans*Math.pow(10, comp*(-1))+0.5));
//			System.out.println(Math.pow(10, comp));
			ans = Math.floor(ans*Math.pow(10, comp*(-1))+0.5)*Math.pow(10, comp);
//			System.out.println(comp+":"+ans);
		}
	}
	void sol() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = new ArrayList<Integer>();
		try {
			String s = br.readLine();
			ans = Integer.parseInt(s);
			
			for(int i=0; i<s.length(); i++) {
				N.add(Integer.parseInt(s.substring(i, i+1)));
			}
		}catch(IOException e) {
			System.out.println(e);
		}
	
//		int b = 1;
//		for(int i=0; i<7; b++, i++) {
//			cc(b);
//		}
//		
//		System.out.println((int)ans);
		
		for(int i=N.size()-1; i>=0; i--) {
			int t = cal(N.get(i)); 
			if(i>0) t=0;
			N.set(i, t);
		}
		for(int i=0; i<N.size(); i++) {
			System.out.print(N.get(i));
		}		
		System.out.println();
	
	}

}
