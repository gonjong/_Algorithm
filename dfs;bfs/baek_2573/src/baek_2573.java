/*
 * 2017.08.27
 * BaekJoon 2573 : bfs
 * @author gonjong
 * time이 Integer 범위 초과할 수 있음. Long으로 고침
 */
import java.util.Arrays;
import java.util.Scanner;

public class baek_2573 {

	int N;
	int M;
	int[][] map;
	boolean[][] visit;
	boolean[][] change;	//해당 타임에서 높이 변화가 있었는지
	int mass = 0;	//덩어리 개수
	int dx[] = {1,-1,0,0};
	int dy[] = {0,0,1,-1};
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new baek_2573().solution();
	}
	
	void solution() {
		
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];
		visit = new boolean[N][M];
		change = new boolean[N][M];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		long time = 0;	
		
		while(true) {
			
			mass = 0;
			
			//전부 바다로 바뀐 경우 : 덩어리가 2개로 나누어지지 않는 경우
			//time=0
			int sea_cnt = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(map[i][j]==0) {
						sea_cnt++;
					}
				}
			}		
			
			if(sea_cnt==N*M) {
				time = 0;
				break;
			}
			
			for(int i=0; i<N;i++) {
				Arrays.fill(change[i], false);
				Arrays.fill(visit[i], false);	
			}
			
			//dfs로 덩어리가 몇 개인지 체크, 2개 이상이면 시간 출력
			for(int i=0; i<N; i++) {
				for(int j=0;j<M; j++) {
					if(!visit[i][j] && map[i][j]!=0) {
						dfs(i,j);
						mass++;
					}
				}
			}
			
			if(mass>=2) {
				break;
			}
			
			//덩어리가 2개 이하인 경우 : 빙하의 높이 수정
			for(int i=0; i<N; i++) {
				for(int j=0;j<M; j++) {
					int cnt = 0;
					
					if(map[i][j]!=0){
						cnt = amount(i, j, 0);
						if(cnt>0) {
							change[i][j] = true;
						}
					}
					if(cnt>map[i][j]) {
						cnt=map[i][j];
					}
					
					map[i][j] -= cnt;
				}
			}
			time++;
		}
		
		System.out.println(time);
	
	}
	
	/*
	 * dfs : 한 개의 덩어리 탐색
	 */
	void dfs(int r, int c) {
		
		for(int i=0; i<4; i++) {
			int dr = r+dy[i];
			int dc = c+dx[i];
			
			if(dr>=0 && dr<N && dc>=0 && dc<M) {
				if(!visit[dr][dc] && map[dr][dc] != 0) {
					visit[dr][dc] = true;
					dfs(dr, dc);
				}
			}
		}
	}
	
	/*
	 * @param : row, col, cnt(주변 바다 개수)
	 * @return : cnt
	 */
	int amount(int r, int c, int cnt) {
		
		for(int i=0; i<4; i++) {
			int dr = r+dy[i];
			int dc = c+dx[i];
			
			if(dr>=0 && dr<N && dc>=0 && dc<M) {
				if(!change[dr][dc] && map[dr][dc] == 0) {
					cnt++;
				}
			}
		}
		return cnt;
	}

}
