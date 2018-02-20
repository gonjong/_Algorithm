/*
 * 2017.12.08
 * BaekJoon 7576 : bfs
 * @author gonjong
 */

import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

	int N;
	int M; 
	int[][] arr;
	int dr[] = {1, -1, 0, 0};
	int dc[] = {0, 0, 1, -1};
	int min = 1000001;
	int vac = 0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new Main().sol();
	}

	void sol() {
		Scanner sc = new Scanner(System.in);

		M = sc.nextInt();
		N = sc.nextInt();

		arr = new int[N][M];
		int mature = 0;

		PriorityQueue<pos> q = new PriorityQueue<pos>();

		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				arr[i][j] = sc.nextInt();
				if(arr[i][j]==1) {
					mature++;
					q.add(new pos(i, j, 0));
					
				}
				else if(arr[i][j]==-1) {
					vac++;
				}
			}
		}
		if(mature==M*N-vac) {
			System.out.println(0);
			return;
		}

		boolean visit[][] = new boolean[N][M];
		int max = 0;
		while(!q.isEmpty()) {
			pos curr = q.poll();

			if(max<curr.d) {
				max = curr.d;
			}
			for(int i=0; i<4; i++) {
				int rr = curr.r+dr[i];
				int cc = curr.c+dc[i];
				int dd = curr.d;

				if(rr>=0 && rr<N && cc>=0 && cc<M) {
					if(!visit[rr][cc] && arr[rr][cc]==0) {
						q.add(new pos(rr, cc, dd+1));
						mature++;
						visit[rr][cc] = true;
					}
				}
			}	
		}
		
		if(mature == N*M-vac) {
			System.out.println(max);
		}
		else {
			System.out.println(-1);
		}
	}

	class pos implements Comparable<pos>{
		int r;
		int c;
		int d;

		pos(int r, int c, int d){
			this.r = r;
			this.c = c;
			this.d = d;
		}

		@Override
		public int compareTo(pos arg0) {
			// TODO Auto-generated method stub
			return this.d<arg0.d?-1:1;
		}
	}
}
