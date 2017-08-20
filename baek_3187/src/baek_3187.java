/*
 * 2017.08.20
 * BaekJoon 3187 : dfs, bfs
 * @author gonjong
 */

import java.util.Arrays;
import java.util.Scanner;

public class baek_3187 {

	int R;
	int C;
	int fence[][];
	boolean visit[][];
	int dx[] = {1,-1,0,0};	//좌우
	int dy[] = {0,0,1,-1};	//상하
	int vCnt = 0;	//한 울타리 안에 있는 늑대, 양의 수
	int kCnt = 0;
	int vAns = 0;	//살아남은 늑대, 양의 수
	int kAns = 0;
	int or = 0;	//dfs 시작 좌표
	int oc = 0;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new baek_3187().solution();
	}
	
	void solution() {
		
		Scanner sc = new Scanner(System.in);
		
		R = sc.nextInt();
		C = sc.nextInt();
		fence = new int[R][C];
		visit = new boolean[R][C];
		for(int i=0; i<R; i++) {
			Arrays.fill(visit[i], false);	
		}
		for(int i=0; i<R; i++) {
			String s = sc.next();
			
			for(int j=0; j<C; j++) {
				
				if(s.charAt(j) == '#') {
					fence[i][j] = -1; 
				}
				else if(s.charAt(j) == 'v') {
					fence[i][j] = 2;
				}
				else if(s.charAt(j) == 'k') {
					fence[i][j] = 1;
				}
			}
		}
		
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(!visit[i][j]&&(fence[i][j] == 2 || fence[i][j] == 1)){
					or = i;
					oc = j;
					vCnt = 0;
					kCnt = 0;
					dfs(i,j);
				}
			}
		}
		System.out.println(kAns + " " + vAns);
	}
	
	void dfs(int r, int c) {
	
		visit[r][c] = true;
		if(fence[r][c]==2) {
			vCnt++;
		}
		else if(fence[r][c]==1) {
			kCnt++;
		}
		
		for(int i=0; i<4; i++) {
			int dr = r+dx[i];
			int dc = c+dy[i];
			
			if(!visit[dr][dc] && fence[dr][dc]!=-1) {
				dfs(dr, dc);
			}
		}
		
		if(r==or && c == oc) {
			if(vCnt<kCnt) {
				kAns += kCnt;
			}
			else {
				vAns += vCnt;
			}
		}
	
	}

}
