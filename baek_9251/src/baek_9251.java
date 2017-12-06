/*
 * 2017.10.10
 * BaekJoon 9251 : Dynamic Programming
 * @author gonjong
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class baek_9251 {

	int lcs[][];
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new baek_9251().solution();
	}

	void solution() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str1 = null;
		String str2 = null;
		
		try {
			str1 = br.readLine();
			str2 = br.readLine();
			
		}catch(IOException e) {
			e.getStackTrace();
		}

		lcs = new int[str1.length()+1][str2.length()+1];
		
		for(int i=1; i<=str1.length(); i++) {
			for(int j=1; j<=str2.length(); j++) {
				if(str1.charAt(i-1)==str2.charAt(j-1)) {
					lcs[i][j] = lcs[i-1][j-1]+1;
				}
				else {
					lcs[i][j] = Integer.max(lcs[i-1][j], lcs[i][j-1]);
				}
			}
		}
		
		System.out.println(lcs[str1.length()][str2.length()]);
	}
}
