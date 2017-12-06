import java.util.Scanner;

public class Main {

	int N;
	int M;
	int arr[][];
	boolean visit[][];
	final int NN = 0;
	final int EE = 1;
	final int SS = 2;
	final int WW = 3;
	pos robot;
	int dr[] = {-1, 0, 1, 0};
	int dc[] = {0, 1, 0, -1};
	int cnt=0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new Main().sol();
	}
	
	void sol() {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		arr = new int[N][M];
		visit = new boolean[N][M];
		int r = sc.nextInt();
		int c = sc.nextInt();
		robot = new pos(r, c, sc.nextInt());
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		dfs(robot.r, robot.c);
	}
	void ss() {

		System.out.println("--------------------");
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(visit[i][j] && arr[i][j]==0) {
					System.out.print(2+" ");
				}
				else {
					System.out.print(arr[i][j]+" ");
				}
			}
			System.out.println();
		}
		System.out.println("--------------------");
	}
	
	void dfs(int r,int c) {
		visit[r][c] = true;
		int rr = 0;
		int cc = 0;
		boolean v = false;
//		ss();
		int ct = 0;
		if(arr[r][c]==1) {
			System.out.println(cnt+1);
			return;
		}
		
		while(ct<4) {
			if(robot.dir==0) {
				robot.dir = 3;
			}
			else {
				robot.dir--;
			}
			
			rr = r+dr[robot.dir];
			cc = c+dc[robot.dir];
			if(rr>=0 && rr<N && cc>=0 && cc<M) {
				if(!visit[rr][cc] && arr[rr][cc]==0) {
					v = true;
					cnt++;
					dfs(rr, cc);
					return;
				}
			}
			
			ct++;
		}
		if(!v) {
			if(robot.dir==1) {
				robot.dir = 3;
			}
			else if(robot.dir==0) {
				robot.dir = 2;
			}
			else {
				robot.dir-=2;
			}
			rr = r+dr[robot.dir];
			cc = c+dc[robot.dir];
			if(rr>=0 && rr<N && cc>=0 && cc<M) {
				if(robot.dir==1) {
					robot.dir = 3;
				}
				else if(robot.dir==0) {
					robot.dir = 2;
				}
				else {
					robot.dir-=2;
				}
				dfs(rr, cc);	
			}
			

		}
	}

	class pos{
		int r;
		int c;
		int dir;
		
		pos(){
			
		}
		pos(int r, int c, int d){
			this.r = r;
			this.c = c;
			this.dir = d;
		}
	}
}
