/*
 * 2017.08.21
 * BaekJoon 2178 : bfs
 * @author gonjong
 * dfs로 하면 시간초과?
 * 일반 큐를 사용하여 bfs를 하면 시간초과
 * 갈 수 있는 최소의 거리를 구할 때 우선순위 큐를 이용한 bfs를 해야 빠른 시간에 최적의 값을 찾을 수 있음.
 */
import java.util.PriorityQueue;
import java.util.Scanner;

public class baek_2178 {

	int N;
	int M;
	int miro[][];
	boolean visit[][];
	int dx[] = {1,-1, 0, 0};
	int dy[] = {0, 0, 1, -1};
	int d = 1;
	int min = 10001;
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new baek_2178().solution();
	}

	void solution() {
		
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		miro = new int[N][M];
		visit = new boolean[N][M];
		
		for(int i=0; i<N; i++) {
			String s = sc.next();
			for(int j=0; j<M; j++) {
				if(s.charAt(j)=='1') {
					miro[i][j]=1;
				}
				else {
					miro[i][j]=0;
				}
			}
		}
	
		bfs(0, 0);
//		dfs(0, 0);
//		System.out.println(min);
	}
	
	void dfs(int r, int c) {
		visit[r][c]=true;
		
		if(r==N-1 && c==M-1) {
			if(min>d) {
				min = d;
			}

			visit[r][c] = false;
			return;
		}
		
		for(int i=0; i<4; i++) {
			 int dr = r+dx[i];
			 int dc = c+dy[i];
			 if(dr>=0 && dr<N && dc>=0 && dc<M) {
				 if(!visit[dr][dc] &&miro[dr][dc] == 1) {
					 d++;
					 dfs(dr, dc);
					 d--;
				 } 
			 }
		}
		visit[r][c] = false;
	}
	
	void bfs(int r, int c) {
		
		PriorityQueue<pos> q = new PriorityQueue<pos>();
		int dist = 0;
		
		q.add(new pos(r, c, dist));
		
		while(!q.isEmpty()) {
			
			int x = q.peek().x;
			int y = q.peek().y;
			dist = q.peek().dist;
			q.poll();
			
			if(x==N-1 && y==M-1) {
				break;
			}
			
			dist++;
			
			for(int i=0; i<4; i++) {
				 int dr = x+dx[i];
				 int dc = y+dy[i];
				 if(dr>=0 && dr<N && dc>=0 && dc<M) {
					 if(!visit[dr][dc] && miro[dr][dc] == 1) {
						 visit[dr][dc] = true;
						 q.add(new pos(dr, dc, dist));
					 } 
				 }
			}
			
		}
		
		System.out.println(dist+1);
	}
}

class pos implements Comparable<pos>{
	int x;
	int y;
	int dist;
	
	pos(int x, int y, int d){
		this.x = x;
		this.y = y;
		this.dist = d;
	}

	@Override
	public int compareTo(pos o) {
		// TODO Auto-generated method stub
		return this.dist<o.dist?-1:1;
	}


}