/*
 * 2017.10.29
 * union, find를 하지 않고 같은 범위인 경우 카운트를 줄여서 최적화
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

	int N;
	ArrayList<pos> arr;
	int map[][];
	int dx[] = {1, -1, 0, 0};
	int dy[] = {0, 0, 1, -1};
	int ans;
	boolean visit[];
	boolean v2[][];
	ArrayList<ArrayList<Integer>> tt;
	
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub

		new Main().sol();
	}

	void sol() throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=0; tc<T; tc++) {
			N = Integer.parseInt(br.readLine());
			arr = new ArrayList<pos>();
			tt  = new ArrayList<ArrayList<Integer>>(3001);
			visit = new boolean[3001];
			v2 = new boolean[5001][5001];
			for(int i=0; i<3001; i++) {
				tt.add(new ArrayList<Integer>());
			}
			
			for(int i=0; i<N; i++) {
				String[] str = br.readLine().split(" ");
				arr.add(new pos(Integer.parseInt(str[0]), Integer.parseInt(str[1]), Integer.parseInt(str[2])));
			}
			
//			for(int i=0 ; i<N; i++) {
//				for(int j=i+1; j<N; j++) {
//					if(sameRange(arr.get(i), arr.get(j))) {
//						if(!tt.get(i).contains(j))
//							tt.get(i).add(j);
//						if(!tt.get(j).contains(i))
//							tt.get(j).add(i);
//					}
//				}
//			}
//			int cnt = 0;
//			for(int i=0; i<N; i++) {
//				if(!visit[i]) {
//					dfs(i);	
//					cnt++;
//				}
//			}
//
//			System.out.println(cnt);

			int cnt = (arr.size());
			
			for(int i=0; i<arr.size(); i++) {
				for(int j=i+1; j<arr.size(); j++) {
					if(sameRange(arr.get(i), arr.get(j))) {
						cnt--;
					}
				}
			}
			System.out.println(cnt);
		}
		
	}
	void dfs() {
		
	}
	void dfs(int v) {
		visit[v] = true;
		if(tt.get(v).isEmpty()) {
			return;
		}
		for(int i=0; i<tt.get(v).size(); i++) {
			if(!visit[tt.get(v).get(i)]) {
				dfs(tt.get(v).get(i));
			}
		}
	}
	
	int find(int ans[], int a) {
		
		while(ans[a]!=-1) {
			a = ans[a];
		}
		
		return a;
	}
	void union(int ans[], int a, int b) {
		
		ans[find(ans, a)] = find(ans, b);
	}
	
	boolean sameRange(pos a, pos b) {
		
		int d = (a.x-b.x)*(a.x-b.x)+(a.y-b.y)*(a.y-b.y);
		int r = (a.r+b.r)*(b.r+a.r);
		
		if(d<=r) {
			return true;
		}
		else 
			return false;
	}
	
	
	class pos{
		int x;
		int y;
		int r;
		
		pos(int x, int y, int r){
			this.x = x;
			this.y = y;
			this.r = r;
		}
	}
}
