/*
 * 2017.10.09
 * BaekJoon 2468 : dfs
 * @author gonjong
 * 예외처리 : 아무것도 일어나지 않는 경우 고려x
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class baek_2468 {

	int N; 
	int arr[][];
	boolean visit[][];
	ArrayList<Integer> rain = new ArrayList<Integer>();
	int dr[] = {1,-1,0,0};
	int dc[] = {0,0,1,-1};
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new baek_2468().solution();
	}

	void solution() {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		arr = new int[N][N];
		visit = new boolean[N][N];
		int max = 0;
		rain.add(0);
		
		for(int i=0; i<N;i++) {
			for(int j=0; j<N; j++) {
				int x = sc.nextInt();
				arr[i][j] = x;
				if(!rain.contains(x)) {
					rain.add(x);
				}
			}
		}
		
		for(int i=0; i<rain.size(); i++) {
			int x = rain.get(i);
			for(int j=0; j<N; j++) {
				Arrays.fill(visit[j], false);				
			}
			
			for(int r=0; r<N; r++) {
				for(int c=0;c<N; c++) {
					if(arr[r][c]<=x) {
						visit[r][c] = true;
					}
				}
			}
			
			int cnt = 0;
			for(int r=0; r<N; r++) {
				for(int c=0; c<N; c++) {
					if(arr[r][c]>x && !visit[r][c]) {
						dfs(r,c,x);
						cnt++;
					}
				}
			}
			
			if(max<cnt) {
				max = cnt;
			}
		}
		System.out.println(max);
	}
	
	void dfs(int r, int c, int sink) {
		visit[r][c] = true;
		
		for(int i=0; i<4; i++) {
			int rr = r+dr[i];
			int cc = c+dc[i];
			
			if(rr>=0 && rr<N && cc>=0 && cc<N) {
				if(arr[rr][cc]>sink && !visit[rr][cc]) {
					dfs(rr, cc, sink);
				}
			}
		}
	}
}
