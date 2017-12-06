/*
 * 2017.10.10
 * BaekJoon 9252 : Dynamic Programming
 * @author gonjong
 */
import java.util.Scanner;

public class Main {

	final int left = 1;
	final int up = 2;
	final int diagonal = 3;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new Main().solution();
	}
	
	void solution() {
		Scanner sc = new Scanner(System.in);
		
		String str1 = sc.nextLine();
		String str2 = sc.nextLine();
		
		int[][] lcs = new int[str1.length()+1][str2.length()+1];
		int[][] path = new int[str1.length()+1][str2.length()+1];
		
		for(int i=1; i<=str1.length();i++) {
			for(int j=1; j<=str2.length(); j++) {
				if(str1.charAt(i-1)==str2.charAt(j-1)) {
					lcs[i][j] = lcs[i-1][j-1]+1;
					path[i][j] = diagonal;
				}
				else {
					if(lcs[i-1][j]>lcs[i][j-1]) {
						lcs[i][j] = lcs[i-1][j];
						path[i][j] = up;
					}
					else {
						lcs[i][j] = lcs[i][j-1];
						path[i][j] = left;
					}
				}
			}
		}
		int i = str1.length();
		int j = str2.length();
		StringBuilder sb = new StringBuilder();
		
		while(i>=1 && j>=1) {
			if(path[i][j]==diagonal) {
				sb.insert(0, str1.charAt(i-1));
				i--;
				j--;
			}
			else if(path[i][j]==up) {
				i--;
			}
			else if(path[i][j]==left) {
				j--;
			}
		}
		System.out.println(lcs[str1.length()][str2.length()]);
		System.out.println(sb.toString());
	}

}
