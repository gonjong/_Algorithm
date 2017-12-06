/*
 * 2017.10.20
 * BaekJoon 1018 : Simulation, bruteforce
 * 시작이 항상 W부터 시작한다고 볼 수 없음. 양쪽 경우 모두 고려해서 계산해야
 * 크기가 작은 것의 값을 비교할 때 비교대상을 미리 만들어놓고 탐색
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	int M;
	int N;
	char pan[][];
	char check[][] = {{'W','B','W','B','W','B','W','B'},
			{'B','W','B','W','B','W','B','W'},
			{'W','B','W','B','W','B','W','B'},
			{'B','W','B','W','B','W','B','W'},
			{'W','B','W','B','W','B','W','B'},
			{'B','W','B','W','B','W','B','W'},
			{'W','B','W','B','W','B','W','B'},
			{'B','W','B','W','B','W','B','W'}};
	char check2[][] = {
			{'B','W','B','W','B','W','B','W'},
			{'W','B','W','B','W','B','W','B'},
			{'B','W','B','W','B','W','B','W'},
			{'W','B','W','B','W','B','W','B'},
			{'B','W','B','W','B','W','B','W'},
			{'W','B','W','B','W','B','W','B'},
			{'B','W','B','W','B','W','B','W'},
			{'W','B','W','B','W','B','W','B'}	
	};
	int min = 2501;
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
		
		String[] s = br.readLine().split(" ");
		M = Integer.parseInt(s[0]);
		N = Integer.parseInt(s[1]);
		pan= new char[M][N];
		
		for(int i=0; i<M; i++) {
			String str = br.readLine();
			for(int j=0; j<N; j++) {
				pan[i][j] = str.charAt(j); 
			}
		}
		for(int i=0; i<M-7; i++) {
			for(int j=0; j<N-7; j++) {
				check(i, j);
			}
		}
		
		System.out.println(min);
	}
	
	
	
	void check(int startRow, int startCol) {
		
		int Wcnt = 0;
		int Bcnt = 0;
		
		for(int r=0; r<8; r++) {
			for(int c=0; c<8; c++) {
					if(pan[r+startRow][c+startCol] != check[r][c]) {
						Wcnt++;
					}	
					if(pan[r+startRow][c+startCol] != check2[r][c]) {
						Bcnt++;
					}			
			}
		}
		min = Integer.min(min, Bcnt<=Wcnt?Bcnt:Wcnt);
	}
	
}
