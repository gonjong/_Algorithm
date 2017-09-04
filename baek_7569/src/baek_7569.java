/*
 * 2017.09.04
 * BaekJoon 7569 : bfs
 * @author gonjong
 * 시간이 지날 때마다 익은 토마토를 만들면서 진행하면 시간초과
 * bfs 하면서 레벨을 증가, 최대레벨을 출력
 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class baek_7569 {

	int M;	//가로 칸
	int N;	//세로 칸
	int H;	//높이
	int box[][][] = new int[100][100][100];
	boolean visit[][][] = new boolean[100][100][100];
	int change = 0;	//각 일마다 변화한 토마토 개수
	int vacant = 0;	//처음 공간이 없는 곳
	int mature = 0;	//처음 익은 토마토 개수 
	int unmature = 0;	//처음 안익은 토마토 개수
	int dx[] = {1,-1,0,0,0,0};
	int dy[] = {0,0,1,-1,0,0};
	int dz[] = {0,0,0,0,1,-1};
	Queue<pos> q = new LinkedList<pos>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new baek_7569().solution();
	}

	void solution() {

		Scanner sc = new Scanner(System.in);
		M = sc.nextInt();
		N = sc.nextInt();
		H = sc.nextInt();
		for(int h=0; h<H; h++) {
			for(int n=0; n<N; n++) {
				for(int m=0;m<M;m++) {
					box[h][n][m] = sc.nextInt();
					if(box[h][n][m]==-1) {
						vacant++;
					}
					if(box[h][n][m]==0) {
						unmature++;
					}
					if(box[h][n][m]==1) {
						mature++;
						q.add(new pos(m, n, h, 0));
						visit[h][n][m] = true;
					}
				}
			}
		}

		//처음 상태에 대한 예외
		if(N*M*H-vacant==mature) {
			System.out.println(0);
			return;
		}
		if(N*M*H-vacant==unmature || N*M*H-vacant==0) {
			System.out.println(-1);
			return;
		}

		int max = bfs();
		int ans = N*M*H-vacant-mature-change;

		if(ans==0) {
			System.out.println(max);
		}
		else {
			System.out.println(-1);
		}

	}

	int bfs() {

		pos t = new pos();
		
		while(!q.isEmpty()) {

			t = q.poll();

			for(int i=0; i<6; i++) {
				int z = t.z+dz[i];
				int y = t.y+dy[i];
				int x = t.x+dx[i];

				if(x>=0 && x<M && y>=0 && y<N && z>=0 && z<H) {
					if(!visit[z][y][x] && box[z][y][x]==0) {
//						System.out.println(x+","+y+","+(t.l+1));
						visit[z][y][x] = true;
						change++;
						q.add(new pos(x,y,z, t.l+1));
					}
				}
			}
		}
		return t.l;
	}

}

class pos {
	int x;
	int y;
	int z;
	int l;
	pos(){
	
	}
	pos(int x, int y, int z, int l){
		this.x = x;
		this.y = y;
		this.z = z;
		this.l = l;
	}
}
