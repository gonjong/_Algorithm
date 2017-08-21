/*
 * 2017.04.19
 * baek_14500 : samsung2017 IM/CE solution
 * @author : gonjong
 * 
 */


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;


public class baek_14500 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		int[][] p = new int[N][M];
		
		for(int i=0; i<N; i++){
			for(int j=0; j<M; j++){
				p[i][j] = sc.nextInt();
			}
		}
		
		System.out.println(polynomino(p, N, M));
	}
	
	static int polynomino(int[][] p, int N, int M){
		int max = 0;
		int temp = 0;
		
		for(int i=0; i<N-3; i++){
			for(int j=0; j<M; j++){
				temp = one_2(p, i, j);
				if(max<temp){
					max = temp;
				}
			}			//¼¼·Î
		}
		for(int i=0;i<N; i++){
			for(int j=0; j<M-3; j++){
				temp = one_1(p, i, j);
				if(max<temp){
					max = temp;
				}
			}			//°¡·Î
		}
		
		for(int i=0; i<N-1;i++){
			for(int j=0; j<M-1; j++){
				temp = two(p, i, j);
				if(max<temp){
					max = temp;
				}
			}
		}
		for(int i=0; i<N-1;i++){
			for(int j=0; j<M-2; j++){
				temp = three_1(p, i, j);
				if(max<temp){
					max = temp;
				}
				temp = four_1(p, i, j);
				if(max<temp){
					max = temp;
				}
				temp = five_1(p, i, j);
				if(max<temp){
					max = temp;
				}
			}				//°¡·Î
		}
		for(int i=0; i<N-2;i++){
			for(int j=0; j<M-1; j++){
				temp = three_2(p, i, j);
				if(max<temp){
					max = temp;
				}
				temp = four_2(p, i, j);
				if(max<temp){
					max = temp;
				}
				temp = five_2(p, i, j);
				if(max<temp){
					max = temp;
				}
			}			//¼¼·Î
		}
		return max;
	}
	
	static int one_1(int[][] p, int row, int col){
		int sum = 0;
		for(int i=0; i<4; i++){
			sum += p[row][col+i];
		}
		return sum;
	}
	static int one_2(int[][] p, int row, int col){
		int sum = 0;
		for(int i=0; i<4; i++){
			sum += p[row+i][col];
		}
		return sum;
	}
	static int two(int[][] p, int row, int col){
		int sum = 0;
		for(int i=0; i<2; i++){
			for(int j=0; j<2; j++){
				sum+=p[row+i][col+j];
			}
		}
		return sum;
	}
	static int three_1(int[][] p, int row, int col){
		int sum = 0;
		ArrayList<Integer> x = new ArrayList<Integer>();
		
		for(int i=0; i<2; i++){
			for(int j=0; j<3; j++){
				sum += p[row+i][col+j];
			}
		}
		for(int i=0; i<2; i++){
			for(int j=0; j<2; j++){
				x.add(p[row+i][col+j]+p[row+i][col+j+1]);
			}
		}
		
		
		asc a = new asc();
		Collections.sort(x,a);
		return sum-x.get(0);
	}
	static int three_2(int[][] p, int row, int col){
		int sum = 0;
		ArrayList<Integer> x = new ArrayList<Integer>();
		
		for(int i=0; i<3; i++){
			for(int j=0; j<2; j++){
				sum += p[row+i][col+j];
			}
		}
		for(int i=0; i<2; i++){
			for(int j=0; j<2; j++){
				x.add(p[row+i][col+j]+p[row+i+1][col+j]);
			}
		}
		
		
		asc a = new asc();
		Collections.sort(x,a);
		return sum-x.get(0);
	}
	
	static int four_2(int p[][], int row, int col){
		int sum = 0;
		ArrayList<Integer> x = new ArrayList<Integer>();
		
		for(int i=0; i<3; i++){
			for(int j=0; j<2; j++){
				sum += p[row+i][col+j];
			}
		}

		x.add(p[row][col]+p[row+2][col+1]);
		x.add(p[row][col+1]+p[row+2][col]);
				
		asc a = new asc();
		Collections.sort(x,a);
		return sum-x.get(0);
	}
	
	static int four_1(int[][] p, int row, int col){
		int sum = 0;
		ArrayList<Integer> x = new ArrayList<Integer>();
		
		for(int i=0; i<2; i++){
			for(int j=0; j<3; j++){
				sum += p[row+i][col+j];
			}
		}
		x.add(p[row][col]+p[row+1][col+2]);
		x.add(p[row+1][col]+p[row][col+2]);
		
				
		asc a = new asc();
		Collections.sort(x,a);
		return sum-x.get(0);
	}
	
	static int five_2(int[][] p, int row, int col){
		int sum = 0;
		ArrayList<Integer> x = new ArrayList<Integer>();
		
		for(int i=0; i<3; i++){
			for(int j=0; j<2; j++){
				sum += p[row+i][col+j];
			}
		}

		x.add(p[row][col]+p[row+2][col]);
		x.add(p[row][col+1]+p[row+2][col+1]);
				
		asc a = new asc();
		Collections.sort(x,a);
		return sum-x.get(0);
		
	}
	static int five_1(int[][] p, int row, int col){
		int sum = 0;
		ArrayList<Integer> x = new ArrayList<Integer>();
		
		for(int i=0; i<2; i++){
			for(int j=0; j<3; j++){
				sum += p[row+i][col+j];
			}
		}
		x.add(p[row][col]+p[row][col+2]);
		x.add(p[row+1][col]+p[row+1][col+2]);
		
				
		asc a = new asc();
		Collections.sort(x,a);
		return sum-x.get(0);
		
	}
	

}

class asc implements Comparator<Integer>{

	@Override
	public int compare(Integer arg0, Integer arg1) {
		// TODO Auto-generated method stub
		return arg0<arg1? -1:1;
	}
	
}
