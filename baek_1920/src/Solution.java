/*
 * 2017.12.06
 * BaekJoon 1920 : binary search
 * @gonjong
 * 검색 범위가 10000000000 정도인 경우 이진 탐색으로
 */
import java.util.Arrays;
import java.util.Scanner;

public class Solution {

	int N;
	int M;
	int[] A;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new Solution().sol();
	}

	void sol() {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		A = new int[N];
		for(int i=0; i<N; i++) {
			A[i] = sc.nextInt();
		}
		
		Arrays.sort(A);
		
		M = sc.nextInt();
		for(int i=0; i<M; i++) {
			binary_search(0, N-1, sc.nextInt());
		}
	}
	
	void binary_search(int left, int right, int x) {
		
		while(left<=right) {
			int mid = (left+right)/2;
			if(x==A[mid]) {
				System.out.println(1);
				return;
			}
			else if(x<A[mid]) {
				right = mid-1;
			}
			else {
				left = mid+1;
			}
		}
		System.out.println(0);
	}
}
