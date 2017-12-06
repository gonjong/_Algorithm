/*
 * 2017.10.18
 * BaekJoon 3085 : BruteForce
 * @author gonjong
 * 초기상태에서 최대 먹을 수 있는 사탕 개수가 초기값
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	int N;
	char arr[][];
	int max = 0;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			new Main().sol();
		}catch(IOException e) {
			System.out.println(e);
		}
	}

	void sol() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		arr = new char[N][N];
		
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0; j<N; j++) {
				arr[i][j] = str.charAt(j);
			}
		}
		for(int i=0; i<N; i++) {
			StringBuilder sb = new StringBuilder();
			
			for(int j=0; j<N; j++) {
				sb.append(arr[j][i]);
			}
			max = Integer.max(max, subMax(sb.toString()));
			sb.setLength(0);
			for(int j=0; j<N; j++) {
				sb.append(arr[i][j]);
			}
			max = Integer.max(max, subMax(sb.toString()));
			
		}
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				dfs(i,j);
			}
		}
		System.out.println(max);
	}
	
	void dfs(int r, int c) {
		
		int rr = r+1;
		int cc = c+1;
		char temp = '\0';
		
		if(rr<N && arr[r][c] != arr[rr][c]) {
			temp = arr[r][c];
			arr[r][c] = arr[rr][c];
			arr[rr][c] = temp;
			
//			System.out.println("----------");
			for(int i=r; i<=rr; i++) {
				StringBuilder sb = new StringBuilder();
				for(int j=0; j<N; j++) {
					sb.append(arr[i][j]);
				}
//				System.out.println(sb.toString());
				max = Integer.max(max, subMax(sb.toString()));
			}
			temp = arr[r][c];
			arr[r][c] = arr[rr][c];
			arr[rr][c] = temp;
			
		}
		
		
		if(cc<N && arr[r][c] != arr[r][cc]) {
			temp = arr[r][c];
			arr[r][c] = arr[r][cc];
			arr[r][cc] = temp;
//			System.out.println("----------");
			for(int i=c; i<=cc; i++) {
				StringBuilder sb = new StringBuilder();
				for(int j=0; j<N; j++) {
					sb.append(arr[j][i]);
				}
//				System.out.println(sb.toString());
				max = Integer.max(max, subMax(sb.toString()));
			}
			temp = arr[r][c];
			arr[r][c] = arr[r][cc];
			arr[r][cc] = temp;
		}	
		
	}
	
	int subMax(String ss) {
		int mm = 0;
		int cnt = 1;
		char c = ss.charAt(0);
		for(int i=1; i<ss.length(); i++) {
			if(ss.charAt(i)==c) {
				cnt++;
			}
			else {
				c = ss.charAt(i);
				cnt=1;
			}
			mm = Integer.max(mm, cnt);
		}
		return mm;
	}
}
