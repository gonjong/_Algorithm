/*
 * 2017.04.15
 * baek_1655 : Dynamic Programming
 * @author : gonjong
 */

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;


public class baek_1520 {

	static int M;
	static int N;
	static int cnt=0;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		
		M = sc.nextInt();
		N = sc.nextInt();
		
		int[][] map = new int[M][N];
		int[][] pathCount = new int[M][N];
	
		
		PriorityQueue<m> queue = new PriorityQueue<m>();
		
		for(int i=0; i<M; i++){
			for(int j=0; j<N; j++){
				map[i][j] = sc.nextInt();
			}
		}
		
		for(int i=0; i<M; i++){
			for(int j=0; j<N; j++){
				queue.add(new m(i,j,map[i][j]));
			}
		}
									// 높이가 높은 곳에서 낮은 곳으로 이동하므로 
									// 우선순위 큐를 이용하여 높은 곳에서 낮은 곳으로 정렬
									// 원하는 위치까지 동적프로그래밍 한다.
	
		for(int i=0; i<M; i++)
			Arrays.fill(pathCount[i], -1);
		pathCount[0][0] = 1;
		searchPath(map,pathCount, 0, 0);
		System.out.println(pathCount[M-1][N-1]);
//		search(map, queue, pathCount);
//		System.out.println(pathCount[M-1][N-1]);
	}
	
	/*
	 * search()
	 * @param : 높이 정보, 정렬된 자료, 경로 수를 세는 데이터
	 */
	static void search(int[][] map, PriorityQueue<m> path,int[][] pathCount){
		int currentVal = 0;
		pathCount[0][0] = 1;
		
		while(!path.isEmpty()){
			m node = path.poll();
			currentVal = map[node.row][node.col];
			if(node.row-1>=0 && pathCount[node.row][node.col]!=0 && map[node.row-1][node.col]<currentVal){
				pathCount[node.row-1][node.col] += pathCount[node.row][node.col];
			}
			if(node.row+1<M && pathCount[node.row][node.col]!=0 && map[node.row+1][node.col]<currentVal){
				pathCount[node.row+1][node.col] += pathCount[node.row][node.col];
			}
			if(node.col-1>=0 && pathCount[node.row][node.col]!=0 && map[node.row][node.col-1]<currentVal){
				pathCount[node.row][node.col-1] += pathCount[node.row][node.col];
			}
			if(node.col+1<N && pathCount[node.row][node.col]!=0 && map[node.row][node.col+1]<currentVal){
				pathCount[node.row][node.col+1] += pathCount[node.row][node.col];
			}
		}
								//우선순위 큐에 데이터가 없을 때까지 해당 노드에서 갈 수 있는 노드에 경로 수를 더해준다.
		
	}
	
	/*
	 * searchPath()
	 * @param : 높이 정보, 경로 수 데이터, 현재 행, 현재 열
	 * 백트래킹 방식을 이용해 모든 경로를 탐색한다.
	 * 시간초과 문제를 해결하기 위해 갈 수 없는 지역은 pruning 한다.
	 * (미완성)
	 */
	static void searchPath(int[][] map, int[][] pathCount, int currentRow, int currentCol){

		int currentVal = map[currentRow][currentCol];
		
		if(pathCount[currentRow][currentCol]!=-1){
			if(currentRow+1<M && map[currentRow+1][currentCol]<currentVal){
				if(pathCount[currentRow+1][currentCol]==-1)
					pathCount[currentRow+1][currentCol] = 0;
				pathCount[currentRow+1][currentCol]++;
				searchPath(map, pathCount, currentRow+1, currentCol);
			}
			if(currentCol+1<N && map[currentRow][currentCol+1]<currentVal){
				if(pathCount[currentRow][currentCol+1] == -1)
					pathCount[currentRow][currentCol+1] = 0;
				pathCount[currentRow][currentCol+1]++;	
				searchPath(map, pathCount, currentRow, currentCol+1);
			}
			if((currentRow-1)>=0 && map[currentRow-1][currentCol]<currentVal){
				if(pathCount[currentRow-1][currentCol]==-1)
					pathCount[currentRow-1][currentCol] = 0;
				pathCount[currentRow-1][currentCol]++;	
				searchPath(map, pathCount, currentRow-1, currentCol);
			}
			if((currentCol-1)>=0 && map[currentRow][currentCol-1]<currentVal){
				if(pathCount[currentRow][currentCol-1]==-1)
					pathCount[currentRow][currentCol-1] = 0;
				pathCount[currentRow][currentCol-1]++;	
				searchPath(map, pathCount, currentRow, currentCol-1);
			}
		}
		
	}
}

class m implements Comparable<m>{

	int row;
	int col;
	int height;
	int cnt=0;
	
	public m(int row, int col, int height){
		this.row = row;
		this.col = col;
		this.height = height;
	}
	
	@Override
	public int compareTo(m arg0) {
		// TODO Auto-generated method stub
		
		if(this.height<arg0.height){
			return 1;
		}
		else if(this.height>arg0.height){
			return -1;
		}
		else{
			if(this.row<arg0.row || this.col<arg0.col)
				return -1;
			else
				return 1;
		}
	}
	
}
