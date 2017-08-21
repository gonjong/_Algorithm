/*
 * 2017.05.09
 * samsung s/w test
 * @author : gonjong
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class baek_14502 {

	static ArrayList<virus> virus = new ArrayList<virus>();
	static int N;
	static int M;
	static int max = -1;
	static int change[][];
	static int check[][];
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		
		baek_14502 x = new baek_14502();
		
		N = sc.nextInt();
		M = sc.nextInt();
		int[][] p = new int[N][M];
		change = new int[N][M];
		check = new int[N][M];
		for(int i=0; i<N; i++){
			for(int j=0; j<M; j++){
				p[i][j] = sc.nextInt();
				if(p[i][j]==2){
					virus.add(new virus(i, j));
				}
			}
		}

		x.putWall(p, 0, 0);
		
		System.out.println(max);
	}
	
	/*
	 * 첫번째 벽을 놓는 위치
	 */
	void putFirst(int[][] p, int row, int col){
		p[row][col] = 1;
		check[row][col] = 3;
		
		for(int i=0; i<N; i++){
			for(int j=0; j<M; j++){
				if(p[i][j]==0 && check[i][j]<=1){
					putSecond(p, i, j);
				}
			}
		}
		
		for(int i=0; i<N; i++){
			for(int j=0; j<M; j++){
				if(check[i][j]==1){
					check[i][j]=0;
				}
			}
		}

		p[row][col] = 0;
	}
	
	/*
	 * 두번째 벽을 놓는 위치
	 */
	void putSecond(int[][] p, int row, int col){
		p[row][col] = 1;
		check[row][col] = 2;
		

		for(int i=0; i<N; i++){
			for(int j=0; j<M; j++){
				if(p[i][j]==0 && check[i][j]<=0){
					putThird(p, i, j);	
					
				}
			}
		}

		p[row][col] = 0;
		check[row][col] = 1;
		
	}
	
	/*
	 * 세번째벽을 놓는 위치
	 * 1) 벽을 놓은 후 바이러스 위치에서 전염을 시킨다.
	 * 2) 전염되지 않은 공간을 세서 max를 구한다.
	 */
	void putThird(int [][]p, int row, int col){
		int cnt = 0;
		
		p[row][col] = 1;
				
		for(int i=0; i<virus.size(); i++){
			propagation(p, virus.get(i).row, virus.get(i).col);	
		}
		
		for(int i=0; i<N; i++){
			for(int j=0; j<M; j++){
				if(p[i][j]==0){
					cnt++;
				}
			}
		}
		if(max<cnt){
			max = cnt;
		}
		
		for(int i=0; i<N; i++){
			for(int j=0; j<M; j++){
				if(change[i][j]==1){
					p[i][j] = 0;
				}
			}
		}
		for(int i=0; i<N;i++){
			Arrays.fill(change[i], 0);	
		}
		p[row][col] = 0;

	}
	void putWall(int[][] p, int row, int col){
		for(int i=0; i<N; i++){
			for(int j=0; j<M; j++){
				if(p[i][j]==0){
					putFirst(p, i, j);	
				}
			}
		}
	}
	
	/*
	 * 감염시키는 함수
	 * dfs를 이용해서 탐색해 나간다.
	 */
	void propagation(int[][] p, int row, int col){
		if(p[row][col]==0){
			change[row][col] = 1;
			p[row][col] = 2;
		}
		
		if(row>=0 && row<N && col>=0 && col+1<M && p[row][col+1]==0){
			propagation(p, row, col+1);	
		}
		if(row>=0 && row<N && col-1>=0 && col<M && (p[row][col-1]==0)){
			
			propagation(p, row, col-1);	
		}
		if(row>=0 && row+1<N && col>=0 && col<M && (p[row+1][col]==0)){
			
			propagation(p, row+1, col);	
		}
		if(row-1>=0 && row<N && col>=0 && col<M && (p[row-1][col]==0)){
			
			propagation(p, row-1, col);	
		}
	}
}

class virus{
	int row;
	int col;
	
	virus(int r, int c){
		this.row = r;
		this.col = c;
	}
}