/*
 * 2017.10.18
 * BaekJoon 11559 : simulation
 * @author gonjong
 * 한번에 터지는 것 주의!
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	int height = 12;
	int width = 6;
	char screen[][] = new char[height][width];
	int dr[] = {1,-1,0,0};
	int dc[] = {0,0,1,-1};
	int answer = 0;
	int cnt = 0;
	boolean visit[][] = new boolean[height][width];

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			new Main().solution();

		}catch(IOException e) {
			System.out.println(e);
		}
	}

	void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for(int i=11; i>=0; i--) {
			String s = br.readLine();
			for(int j=0; j<6; j++) {
				screen[i][j] = s.charAt(j);
			}
		}
		while(true) {
			boolean change = false;
			
			//비어있지 않은 경우 같은 것 탐색
			for(int i=0; i<12; i++) {
				for(int j=0; j<6; j++) {
					for(int k=0; k<12; k++) {
						Arrays.fill(visit[k], false);
					}
					if(!visit[i][j] && screen[i][j] != '.') {
						cnt = 0;
						dfs(i, j);
						//4개 이상 모여있으면 제거
						if(cnt>=4) {
							change = true;
							erase();	
						}
						//						ss();
					}
				}
			}	
			if(!change) {
				break;
			}
			else {
				answer++;
				for(int j=0; j<6; j++) {
					for(int i=0; i<12; i++) {

						//제거되어 없어진 부분을 메꿈
						if(screen[i][j] == '.') {
							for(int r=i+1; r<12; r++) {
								if(screen[r][j]!='.') {
									screen[i][j] = screen[r][j];
									screen[r][j] = '.';

//									ss();
									break;
								}
							}
						}
					}
				}	
			}
		}

		System.out.println(answer);
	}

	/*
	 * 4개 이상이 모여 없애는 함수
	 */
	void erase() {
		for(int r=11;r>=0;r--) {
			for(int c=0; c<6; c++) {
				if(visit[r][c]) {
					screen[r][c] = '.';
				}
			}
		}

	}
	
	/*
	 * 4개 이상 모여있는지 탐색하는 함수
	 */
	void dfs(int r, int c) {
		visit[r][c] = true;
		cnt++;
		for(int i=0; i<4; i++) {
			int rr = r+dr[i];
			int cc = c+dc[i];

			if(rr>=0 && rr<12 && cc>=0 && cc<6) {
				if(!visit[rr][cc] && screen[rr][cc] == screen[r][c]) {
					dfs(rr, cc);	
				}
			}
		}

	}
	void ss() {
		System.out.println("------------------");
		for(int i=11; i>=0; i--) {
			for(int j=0; j<6; j++) {
				System.out.print(screen[i][j]);
			}
			System.out.print("   ");
			//			for(int j=0; j<6; j++) {
			//				System.out.print(visit[i][j]+" ");
			//			}
			System.out.println();
		}
		System.out.println("------------------");
	}
	

}
