/*
 * 2017.10.23
 * BaekJoon 1890 : Dynamic Programming
 * @author gonjong
 * dfs, bfs 시간초과
 * pow(2,63)-1 은 long 범위
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

	int N;
	int arr[][];
	long dp[][];
	int answer = 0;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		new Main().sol();
	}

	void sol() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		dp = new long[N][N];
		for(int i=0; i<N; i++) {
			String[] s = br.readLine().split(" ");
			for(int j=0; j<N ;j++) {
				arr[i][j] = Integer.parseInt(s[j]);
			}
		}

		dp[0][0] = 1;
		for(int r=0; r<N; r++) {
			for(int c=0; c<N; c++) {
				int jump = arr[r][c];
//				ss();
				if(r==N-1 && c==N-1) {
					System.out.println(dp[N-1][N-1]);
					return;			
				}
				if(r+jump<N) {
					dp[r+jump][c] += dp[r][c];
				}				
				if(c+jump<N) {
					dp[r][c+jump] += dp[r][c];
					
				}	
			}
		}
		
	}	
	
	void bfs() {
		
		Queue<pos> q = new LinkedList<pos>();
		q.add(new pos(0,0,0));
		while(!q.isEmpty()) {
			pos t = q.poll();
			int jump = arr[t.r][t.c];
//			System.out.println(t.r+", "+t.c +", "+t.cnt);
			if(arr[t.r][t.c] == 0) {
				System.out.println(t.cnt);
				break;
			}
			if(t.r+jump<N) {
				q.add(new pos(t.r+jump, t.c, t.cnt+1));
			}
			if(t.c+jump<N) {
				q.add(new pos(t.r, t.c+jump, t.cnt+1));
			}
		}
	}
	class pos {
		int r;
		int c;
		int cnt;
		pos(){
			
		}
		pos(int r,int c, int cnt){
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}
	
	void dfs(int r, int c) {
		int jump = arr[r][c];
		ss();
		if(arr[r][c] == 0) {
			return;
		}
		if(r+jump<N) {
			dp[r+jump][c] += 1;
			dfs(r+jump, c);
		}
		if(c+jump<N) {
			dp[r][c+jump] += 1;
			dfs(r, c+jump);
		}		
	}
	
	void ss() {
		System.out.println("---------------");
		for(int r=0; r<N; r++) {
			for(int c=0; c<N; c++) {
				System.out.print(dp[r][c]+" ");
			}
			System.out.println();
		}
		System.out.println("---------------");
		
	}
}
