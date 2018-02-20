/*
 * 2018.02.12
 * BaekJoon 4963 : dfs
 * @author gonjong
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	int w, h;
	int[][] map;
	boolean[][] visit;
	int[] dr = {1, -1, 0, 0, 1, -1, 1, -1};
	int[] dc = {0, 0, 1, -1, 1, -1, -1, 1};
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new Main().sol();
	}

	void sol() {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			String str=null;
			while(true) {
				String[] s = br.readLine().split(" ");
				w = Integer.parseInt(s[1]);
				h = Integer.parseInt(s[0]);
				if(w==0 && h==0) break;
				map = new int[w][h];
				visit = new boolean[w][h];
				
				for(int i=0; i<w; i++) {
					s = br.readLine().split(" ");
					for(int j=0; j<s.length; j++) {
						map[i][j] = Integer.parseInt(s[j]);
					}
				}
				int ans = 0;
				for(int i=0; i<w; i++) {
					for(int j=0; j<h; j++) {
						if(!visit[i][j] && map[i][j]==1) {
							ans++;
							dfs(i,j);
						}
					}
				}
				System.out.println(ans);
			}	
		}catch(IOException e){
			System.out.println(e);
		}
		
	}
	
	void dfs(int r, int c) {
		visit[r][c] = true;
		
		for(int i=0; i<8; i++) {
			int rr = r+dr[i];
			int cc = c+dc[i];
			
			if(rr>=0 && rr<w && cc>=0 && cc<h) {
				if(!visit[rr][cc] && map[rr][cc] == 1) {
					dfs(rr, cc);	
				}
			}
		}
		
	}
}
