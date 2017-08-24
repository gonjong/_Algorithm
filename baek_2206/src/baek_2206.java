/*
 * 2017.08.25
 * BaekJoon 2206 : bfs
 * @author pinch3773
 * bfs+우선순위큐를 이용하여 최단거리를 계산하면 57%에서 틀렸다고 나옴
 * visit을 벽을 부수고 방문하는 경우와 벽을 부수지 않고 방문하는 경우로 나눠서 코딩해야 올바르게 결과가 나옴
 * 2가지의 차이가 어디에서 생기는 것인지 아직 정리 못함.
 */

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class baek_2206 {

	int N;
	int M;
	int map[][];
	boolean visit[][][];
	boolean visit2[][];
	int dx[] = {1,-1,0,0};
	int dy[] = {0,0,1,-1};
	int min = 1000001;
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new baek_2206().solution();
	}

	void solution() {

		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		map = new int[N][M];
		visit = new boolean[N][M][2];
		visit2 = new boolean[N][M];

		for(int i=0; i<N; i++) {
			String s = sc.next();
			Arrays.fill(visit2[i], false);

			for(int j=0; j<M; j++) {

				Arrays.fill(visit[i][j], false);

				if(s.charAt(j)=='0') {
					map[i][j]=0;
				}
				else {
					map[i][j]=1;
				}
			}
		}

		if(N==1 && M==1) {
			System.out.println(1);
		}
		else {
			System.out.println(bfs());		
		}
	}


	int bfs() {

		Queue<pos> q = new LinkedList<pos>();

		q.add(new pos(0, 0, 1, 0));
		visit[0][0][0] = true;

		while(!q.isEmpty()) {
			int row = q.peek().row;
			int col = q.peek().col;
			int dist = q.peek().dist;
			int bc = q.peek().break_cnt;
			q.poll();


			//			System.out.println("*("+row+","+ col+"),"+bc);
			for(int i=0; i<4; i++) {
				int dr = row+dy[i];
				int dc = col+dx[i];

				if(dr==N-1 && dc==M-1) {
					return dist+1;
				}

				//				System.out.println("("+dr+","+ dc+")," +dist);

				if(dr>=0 && dr<N && dc>=0 && dc<M) {
					if(bc==0) {
						if(!visit[dr][dc][0]&&map[dr][dc]==0) {
							visit[dr][dc][0] = true;
							q.add(new pos(dr, dc, dist+1,0));
							//							System.out.println("v");
						}
						else if(!visit[dr][dc][1] && map[dr][dc]==1) {
							visit[dr][dc][1] = true;
							q.add(new pos(dr, dc, dist+1, 1));	
							//							System.out.println("v");
						}

					}
					else {
						if(!visit[dr][dc][1]&&map[dr][dc]==0) {
							visit[dr][dc][1] = true;
							q.add(new pos(dr, dc, dist+1,1));
							//							System.out.println("v");
						}
					}
				}
			}

		}
		return -1;
	}
	
	int bfs2() {
		PriorityQueue<pos> q = new PriorityQueue<pos>();

		q.add(new pos(0, 0, 1, 0));
		visit2[0][0] = true;

		while(!q.isEmpty()) {
			int row = q.peek().row;
			int col = q.peek().col;
			int dist = q.peek().dist;
			int bc = q.peek().break_cnt;
			q.poll();


//			System.out.println("*("+row+","+ col+"),"+bc);
			for(int i=0; i<4; i++) {
				int dr = row+dy[i];
				int dc = col+dx[i];

				if(dr==N-1 && dc==M-1) {
					return dist+1;
				}
				
//				System.out.println("("+dr+","+ dc+")");

				if(dr>=0 && dr<N && dc>=0 && dc<M) {
					if(!visit2[dr][dc] && map[dr][dc]==0) {
						visit2[dr][dc] = true;
						q.add(new pos(dr, dc, dist+1,bc));
//						System.out.println("v");
					}
					else if(!visit2[dr][dc] && map[dr][dc]==1 && bc==0) {
						visit2[dr][dc] = true;
						q.add(new pos(dr, dc, dist+1, bc+1));	
//						System.out.println("v");
					}
				}
			}

		}
		return -1;
	}
}

class pos implements Comparable<pos>{
	int row;
	int col;
	int dist;
	int break_cnt;

	pos(int r, int c, int d, int b){
		this.row = r;
		this.col = c;
		this.dist = d;
		this.break_cnt = b;
	}

	@Override
	public int compareTo(pos o) {
		// TODO Auto-generated method stub
		if(this.break_cnt<o.break_cnt) {
			return -1;
		}
		else if(this.break_cnt>o.break_cnt) {
			return 1;
		}
		else{
			return this.dist<o.dist?-1:1;	
		}
	}
}