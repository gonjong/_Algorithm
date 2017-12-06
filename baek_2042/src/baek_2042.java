/*
 * 2017.10.08
 * BaekJoon 2042 : Segment tree
 * @author gonjong
 */

import java.util.ArrayList;
import java.util.Scanner;

public class baek_2042 {

	int N;
	int M;
	int K;
	int treeSize = 1;
	ArrayList<Long> tree;
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new baek_2042().solution();
	}

	void solution() {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();
		
		for(;treeSize<N; treeSize*=2);
		tree = new ArrayList<Long>(treeSize*2);

		tree.add((long)0);
		for(int i=1; i<treeSize*2; i++) {
			tree.add((long)0);
		}
		
		for(int i=0; i<N; i++) {
			long x = sc.nextLong();
			tree.set(treeSize+i, x);
			for(int j=(treeSize+i)/2; j>=1; j/=2) {
				tree.set(j, tree.get(j)+x);
			}
		}
		
		for(int i=0; i<M+K; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			int c = sc.nextInt();
			
			if(a==1) {
				long temp = tree.get(treeSize+b-1);
				tree.set(treeSize+b-1, (long)c);
				for(int j=(treeSize+b-1)/2; j>=1; j/=2) {
					tree.set(j, tree.get(j)-temp+(long)c);
				}
			}
			else if(a==2) {
				if(b>c) {
					int temp = b;
					b = c;
					c = temp;
				}
				
				//범위 체크 주의!
				System.out.println(find_sum(1, b+treeSize-1, c+treeSize-1, treeSize, treeSize*2-1));
			}
		}
	}
	
	long find_sum(int index, int find_left, int find_right, int l_Index, int r_Index) {
		
		if(l_Index>find_right||r_Index<find_left) {
			return 0;
		}
		if(l_Index>=find_left && r_Index<=find_right) {
			return tree.get(index);
		}
		
		int mid = (l_Index+r_Index)/2;
		long lVal = find_sum(index*2, find_left, find_right, l_Index, mid);
		long rVal = find_sum(index*2+1, find_left, find_right, mid+1, r_Index);
		
		return lVal+rVal;
	}
}
