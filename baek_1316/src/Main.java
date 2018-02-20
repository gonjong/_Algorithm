/*
 * 2018.02.07
 * BaekJoon 1316 : 문자열 처리, 탐색
 * @author gonjong
 * 해시로 처리하면 더 효율적
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	int N;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new Main().solution();
	}
	
	
	void solution() {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = null;
		int ans = 0;
		
		try {
			N = Integer.parseInt(br.readLine());
				
			for(int i=0; i<N; i++) {
				str = br.readLine();	
				if(isGroup(str)) {
					ans++;
				}
			}
			
		}catch(IOException e) {
			System.out.println(e);
		}
		
		System.out.println(ans);
	}

	boolean isGroup(String str) {
		boolean checkAlpha[] = new boolean[26];
		
		checkAlpha[str.charAt(0)-'a'] = true;
		
		for(int i=1; i<str.length(); i++) {
			
			if(str.charAt(i)!=str.charAt(i-1) && checkAlpha[str.charAt(i)-'a']) {
				return false;
			}
			else {
				checkAlpha[str.charAt(i)-'a'] = true;
			}
		}
		
		return true;
	}
}
