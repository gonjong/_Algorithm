/*
 * 2017.08.13
 * baek_1012 : dfs, bfs
 * @author : gonjong
 */

import java.util.Scanner;

public class baek_1012 {

	int T;
	int N;	//세로, row
	int M;	//가로, col
	int K;	//배추 위치 개수
	int land[][];
	int cnt = 0;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new baek_1012().solution();
	}

	void solution() {
		
		Scanner sc = new Scanner(System.in);
		
		T = sc.nextInt();
		
		for(int i=0; i<T; i++) {
			M = sc.nextInt();
			N = sc.nextInt();
			K = sc.nextInt();
			land = new int[N][M];
			cnt = 0;
			
			for(int j=0; j<K; j++) {
				int x = sc.nextInt();
				int y = sc.nextInt();
				
				land[y][x] = 1;
			}
			
			for(int j=0; j<N; j++) {
				for(int k=0; k<M; k++) {
					if(land[j][k]==1) {
						cnt++;
						dfs(j, k);
					}
				}
			}
			
			System.out.println(cnt);
		}
	}
	
	/*
	 * 4방향으로 갈 때
	 * dx = [1,-1,0,0]	//우, 좌
	 * dy = [0,0,1,-1]	//위, 아래
	 *
	 * for(i=1 : 4)
	 * 	nx = x+dx[i];
	 * 	ny = y+dy[i];
	 * 	if(nx 범위 내, ny 범위내){
	 * 		코드	
	 * 	}
	 */
	void dfs(int r, int c) {
		land[r][c] = 0;
		
		if(r+1<N && land[r+1][c] == 1) {
			dfs(r+1, c);
		}
		if(r-1>=0 && land[r-1][c] == 1) {
			dfs(r-1, c);
		}
		if(c+1<M && land[r][c+1] == 1) {
			dfs(r, c+1);
		}
		if(c-1>=0 && land[r][c-1] == 1) {
			dfs(r, c-1);
		}
	}
}
