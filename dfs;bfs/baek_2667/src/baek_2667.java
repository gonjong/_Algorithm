/*
 * 2017.08.13
 * baek_2667 : dfs, bfs
 * @author : gonjong
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;

public class baek_2667 {

	int N;
	int num = 1;
	int cnt = 0;
	int row;
	int col;
	int[][] map;
	ArrayList<Integer> house = new ArrayList<Integer>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			new baek_2667().solution();	
		}catch(IOException e) {
			System.out.println(e);
		}
	}
	
	void solution() throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		
		for(int i=0; i<N; i++) {
			String str = br.readLine();

			for(int j=0; j<str.length(); j++) {
				if(str.charAt(j) == '0') {
					map[i][j] = -1;
				}
				else {
					map[i][j] = 0;
				}
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j]==0) {
					cnt = 0;
					dfs(i,j);
					house.add(cnt);
					num++;
				}
			}
		}
		house.sort(new asc());
		System.out.println(num-1);
		for(int i=0; i<house.size(); i++) {
			System.out.println(house.get(i));
		}
		
	}
	
	/*
	 * map에 있는 것을 바꾸지 말고
	 * visit 이용해서 하는 게 더 깔끔?
	 */
	void dfs(int r, int c) {
		map[r][c] = num;
		cnt++;
		
		if(r+1<N && map[r+1][c] == 0) {
			dfs(r+1, c);
		}
		if(r-1>=0 && map[r-1][c] == 0) {
			dfs(r-1, c);
		}
		if(c+1<N && map[r][c+1] == 0) {
			dfs(r, c+1);
		}
		if(c-1>=0 && map[r][c-1] == 0) {
			dfs(r, c-1);
		}
	}
	
}

class asc implements Comparator<Integer>{

	@Override
	public int compare(Integer o1, Integer o2) {
		// TODO Auto-generated method stub
		return o1<o2? -1:1;
	}
	
}
