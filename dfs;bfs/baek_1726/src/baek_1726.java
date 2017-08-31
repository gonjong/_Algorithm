/*
 * 2017.08.29
 * BaekJoon 1726 : bfs
 * @author gonjong
 * (상) 
 * 1) 로봇이 최대 x칸 움직일 수 있는 경우 최단거리 : x칸을 모두 현재거리+1
 * 2) 방문했던 곳을 중복해서 갈 수 있는 경우 visit : 현재까지 이동할 수 있는 최단거리로 판단
 * 		현재 방문이 이전 방문보다 거리가 더 짧으면 visit
 * 		case : 최단거리, 한 칸씩 가는 것이 아니라 여러 칸 한번에 이동할 수 있는 경우
 */

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class baek_1726 {

	int m;
	int n;
	int ans=0;
	int[][] map;
	int dx[] = {0,1,-1,0,0};
	int dy[] = {0,0,0,1,-1};
	int visit[][];
	
	final int E = 1;
	final int W = 2;
	final int S = 3;
	final int N = 4;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new baek_1726().solution();
	}

	void solution() {
		
		Scanner sc = new Scanner(System.in);
		
		m = sc.nextInt();
		n = sc.nextInt();
		
		map = new int[m+1][n+1];
		visit = new int[m+1][n+1];
		
		for(int i=0; i<=m; i++) {
			map[i][0] = -1;
		}
		for(int i=1; i<=m; i++) {
			Arrays.fill(visit[i], 10001);

			for(int j=1; j<=n;j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		int startRow = sc.nextInt();
		int startCol = sc.nextInt();
		int startdir = sc.nextInt();
		int endRow = sc.nextInt();
		int endCol = sc.nextInt();
		int endDir = sc.nextInt();
		
		bfs(startRow, startCol, startdir, endRow, endCol, endDir);
		System.out.println(ans);
	}
	
	
	void bfs(int r, int c,int d, int er, int ec, int ed) {
		PriorityQueue<pos> q = new PriorityQueue<pos>();
		
		visit[r][c]=0;
		q.add(new pos(r, c, 0, d));
		
		while(!q.isEmpty()) {
			pos curr = q.poll();
//			System.out.println(curr.r+","+curr.c+":"+curr.cnt);
			
			//도착한 경우
			if(curr.r==er && curr.c==ec) {
				int t = turn(curr.dir, ed);
				ans=curr.cnt+t;
				break;
			}
			
			//4방향에 대해 갈 수 있으면 큐에 저장
			for(int i=1; i<=4; i++) {
				int t = turn(curr.dir, i);
				int g = Go(curr.r, curr.c, 0, i);
				for(int k=1; k<=g; k++) {
					int dr = curr.r+k*dy[i];
					int dc = curr.c+k*dx[i];
					if(dr>0 && dr<=m && dc>0 && dc<=n) {
						if(visit[dr][dc]>curr.cnt+t+1 && map[dr][dc]==0) {
							visit[dr][dc]=curr.cnt+t+1;
							q.add(new pos(dr,dc,curr.cnt+t+1, i));	
						}	
					}
					
		
				}
			}
			
		}
	}
	
	/*
	 * 회전하는 경우 이동하는 거리
	 * @param : 현재 방향, 바뀌는 방향
	 * @return : 0, 1, 2(E,W/S,N)
	 */
	int turn(int curr, int des) {
		if(curr!=des) {
			if(Math.abs(curr-des)==1 
					&& !((curr==W&&des==S)||(curr==S&&des==W))) {
				return 2;
			}
			else {
				return 1;
			}
		}
		return 0;
	}
	/*
	 * 현재 위치에서 갈 수 있는 최대 거리
	 * @param : 현재 위치(r,c), 이동 가능한 거리(cnt), 방향(d)
	 */
	int Go(int r, int c, int cnt, int d) {
		if(cnt==3) {
			return cnt;
		}
		
		int dr = r+dy[d];
		int dc = c+dx[d];
		if(dr>0 && dr<=m && dc>0 && dc<=n) {
			if(map[dr][dc]==0) {
				cnt = Go(dr,dc, cnt+1,d);
			}	
		}
		
		return cnt;
	}
}

class pos implements Comparable<pos>{
	int r;
	int c;
	int cnt;
	int dir;
	
	pos(int r, int c, int cnt, int dir){
		this.r = r;
		this.c = c;
		this.cnt = cnt;
		this.dir = dir;
	}

	@Override
	public int compareTo(pos arg0) {
		// TODO Auto-generated method stub
		return this.cnt<arg0.cnt?-1:1;
	}
}