/*
 * 2017.10.08
 * BaekJoon 2268 : Segment tree
 * @author gonjong
 * 구간 내 수들의 합을 구하는 경우 : dp 등으로 하는 경우 시간 초과
 */

import java.util.ArrayList;
import java.util.Scanner;

public class baek_2268 {

	int N;
	int k;
	ArrayList<Long> tree;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new baek_2268().solution();
	}
	
	void solution() {
		
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		int M = sc.nextInt();
		
		//segment tree size
		for(k=1;k<N;k*=2);
		tree = new ArrayList<Long>(k*2);
		
		//initialize segment tree
		tree.add((long)0);
		for(int i=1; i<2*k; i++) {
			tree.add((long)0);
		}
		
		//input data
		for(int i=1; i<=M; i++) {
			int isSum = sc.nextInt();
			int n = sc.nextInt();
			int m = sc.nextInt();
			
			if(isSum==1) {
				tree.set(k+n-1, (long)m);
				
				for(int j=(k+n-1)/2; j>=1; j/=2) {
					
					tree.set(j, tree.get(j*2)+tree.get(j*2+1));
					
				}
			}
			else {
				//항상 구간 범위 n<m이 되도록 교정
				if(m<n) {
					int temp = n;
					n = m;
					m = temp;
				}
				System.out.println(find_sum(1, k, 2*k-1, n+k-1, m+k-1));
			}
		}
	
	}
	
	long find_sum(int index, int left, int right, int start, int end) {
		
		if(left>end || right<start) {
			return 0;
		}
		
		if(left>=start && right<=end) {
			return tree.get(index);
		}
		int mid = (left+right)/2;
	
		long lVal = find_sum(index*2, left, mid, start, end);
		long rVal = find_sum(index*2+1, mid+1, right, start, end);
		
		return lVal+rVal;
	}
	

}

