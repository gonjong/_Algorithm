/*
 * 2018.02.12
 * BaekJoon 1152 : 문자열 처리
 * @author gonjong
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new Main().sol();
	}

	void sol() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer("");
		try {
			st = new StringTokenizer(br.readLine());	
		}catch(IOException e) {
			System.out.println(e);
		}
		
		int cnt = 0;
		while(st.hasMoreTokens()) {
			st.nextToken();
			cnt++;
		}
		System.out.println(cnt);
	}
}
