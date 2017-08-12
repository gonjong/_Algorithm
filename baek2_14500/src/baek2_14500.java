/*
 *  @author : hslyjy 
 *  dfs를 활용해야 빠르고 코드도 간결하게 됨.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baek2_14500 {

	static int[][] map;
	static int max=0;
	static int n=0;
	static int m=0;
	static boolean[][] visit; 
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		map=new int[n][m];
		visit=new boolean[n][m];
		for(int i=0;i<n;i++){
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<m;j++){
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0;i<n;i++){
			for(int j=0;j<m;j++){
				visit[i][j]=true;
				dfs(i,j,map[i][j],1);
				visit[i][j]=false;
				specific(i,j);
			}
		}
		
		System.out.println(max);
		

	}
	static int[][] dir={{1,0},{-1,0},{0,1},{0,-1}};
	public static void dfs(int row, int col,int sum,int count){
		if(count==4){
			if(max<sum){
				max=sum;
			}
			return;
		}
		for(int d=0;d<4;d++){
			int newX=row+dir[d][0];
			int newY=col+dir[d][1];
			if(newX>=0&&newX<n&&newY>=0&&newY<m&&!visit[newX][newY]){
				visit[newX][newY]=true;
				dfs(newX,newY,sum+map[newX][newY],count+1);
				visit[newX][newY]=false;
			}
		}
	}
	
	public static void specific(int row,int col){
		int sum=map[row][col];
		if(row-1>=0&&col-1>=0&&col+1<m){
			sum+=map[row-1][col];
			sum+=map[row][col-1];
			sum+=map[row][col+1];
		}
		if(sum>max){
			max=sum;
		}
		sum=map[row][col];
		if(row+1<n&&col-1>=0&&col+1<m){
			sum+=map[row+1][col];
			sum+=map[row][col-1];
			sum+=map[row][col+1];
		}
		if(sum>max){
			max=sum;
		}
		sum=map[row][col];
		if(row-1>=0&&row+1<n&&col+1<m){
			sum+=map[row-1][col];
			sum+=map[row+1][col];
			sum+=map[row][col+1];
		}
		if(sum>max){
			max=sum;
		}
		sum=map[row][col];
		if(row-1>=0&&row+1<n&&col-1>=0){
			sum+=map[row-1][col];
			sum+=map[row+1][col];
			sum+=map[row][col-1];
		}
		if(sum>max){
			max=sum;
		}
	}
}
