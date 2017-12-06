/*
 * 2017.10.21
 * BaekJoon 1051 : Brute force
 * @author gonjong
 */
import java.util.Scanner;

public class Main {

	int N;
	int M;
	int arr[][];
	int answer = 0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new Main().sol();
	}

	void sol() {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		arr = new int[N][M];
		sc.nextLine();
		for(int i=0; i<N; i++) {
			String str = sc.nextLine();
			for(int j=0; j<M; j++) {
				arr[i][j] = (int)str.charAt(j);
			}
		}
		
		int x = Integer.min(N, M);
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				for(int k=0; k<x; k++) {
					if(i+k>=N || j+k>=M) {
						break;
					}
					if(arr[i+k][j]==arr[i][j] && arr[i][j+k]==arr[i][j]
							&& arr[i+k][j+k]==arr[i][j]) {
						if(answer<k+1) {
							answer = k+1;
						}
					}
				}
			}
		}
		System.out.println(answer*answer);
	}
}
