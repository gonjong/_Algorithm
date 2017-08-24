/*
 * 2017.08.21
 * BaekJoon 2589 : bfs
 * @author gonjong
 * dfs말고 bfs 사용하는 경우 
 * 여러가지로 갈 수 있는 경우의 수 중 최단거리로 가는 것을 찾을 떄
 * 우선순위 큐와 결합해서 해결
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class baek_2589 {
	
	int max = 0;
	int row = 0;
	int col = 0;
	int map[][];
	boolean visit[][];
	int dx[] = {1,-1,0,0};
	int dy[] = {0,0,1,-1};
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			new baek_2589().solution();
		}catch(IOException e) {
			System.out.println(e);
		}
	}
	
	void solution() throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = null;
		
		str = br.readLine();
		String[] split = str.split(" ");
		row = Integer.parseInt(split[0]);
		col = Integer.parseInt(split[1]);
		map = new int[row][col];
		visit = new boolean[row][col];
		
		for(int i=0; i<row; i++) {
			str = br.readLine();
			
			for(int j=0; j<col; j++) {
				if(str.charAt(j) == 'W')
					map[i][j] = 0;
				else
					map[i][j] = 1;
			}
		}
		
		for(int i=0; i<row; i++) {
			for(int j=0; j<col; j++) {
				if(map[i][j]==1) {
					for(int k=0; k<row; k++) {
						Arrays.fill(visit[k], false);
					}
					
					bfs(i, j);
					
				}
			}
		}
		
		System.out.println(max);
	}
	
	void bfs(int r, int c) {
		
		PriorityQueue<position> q = new PriorityQueue<position>();
		
		q.add(new position(r, c, 0));
		visit[r][c] = true;
		
		while(!q.isEmpty()) {
			
			int x = q.peek().row;
			int y = q.peek().col;
			int d = q.peek().dist;
			q.poll();
			
			boolean move = false;
			
			for(int i=0; i<4; i++) {
				int dr = x+dx[i];
				int dc = y+dy[i];
				
				if(dr>=0 && dr<row && dc>=0 && dc<col) {
					if(!visit[dr][dc] && map[dr][dc]==1) {
						move = true;
						visit[dr][dc] = true;
						q.add(new position(dr, dc, d+1));
					}
				}
			}
			
			if(!move) {
				if(max<d) {
					max = d;
				}
			}
		}
		
	}
	

	class position implements Comparable<position>{
		int row;
		int col;
		int dist;
		
		position(int r, int c, int dist){
			this.row = r;
			this.col = c;
			this.dist = dist;
		}

		@Override
		public int compareTo(position arg0) {
			// TODO Auto-generated method stub
			return this.dist<arg0.dist?-1:1;
		}
	}
}
