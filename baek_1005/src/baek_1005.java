/*
 * 2017.10.25
 * BaekJoon 1005 : Topology sort
 * @author gonjong
 * 위상 정렬 : 수행하는 것이 이전 노드의 순서에 의하는 경우
 * dfs를 이용해 풀이, 진입차수의 개수==0 이면 dfs하는 것!
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class baek_1005 {

	int T;
	int N, K, D;
	int W;
	int arr[];
	ArrayList<ArrayList<Integer>> forward;
	int dp[];
	
	public static void main(String[] args) {
		new baek_1005().solution();
	}
	
	void solution() {
		
		Scanner sc = new Scanner(System.in);
		
		T = sc.nextInt();
		
		for(int test_case=0; test_case<T; test_case++) {
			N = sc.nextInt();
			K = sc.nextInt();
			arr = new int[N];
			dp = new int[N];
			forward = new ArrayList<ArrayList<Integer>>(N);
			
			for(int i=0; i<N; i++) {
				forward.add(new ArrayList<Integer>());
				arr[i] = sc.nextInt();
			}
		
			int ind[] = new int[N];
			
			for(int i=0; i<K; i++) {
				int x = sc.nextInt();
				int y = sc.nextInt();
				x--; y--;
				
				forward.get(x).add(y);
				ind[y]++;
			}
			
			W = sc.nextInt();
			W--;
	
			Queue<Integer> q = new LinkedList<Integer>();
			int dp[] = new int[N];
			
//			for(int i=0; i<N; i++) {
//				System.out.print(ind[i]+" ");
//			}
//			System.out.println();
			for(int i=0; i<N; i++) {
				if(ind[i] == 0) {
					q.add(i);
					dp[i] = arr[i];
				}
			}
			while(!q.isEmpty()) {
				
				int v = q.poll();
				ArrayList<Integer> list = forward.get(v);
				
				for(int i=0; i<list.size(); i++) {
					ind[list.get(i)]--;
					dp[list.get(i)] = Integer.max(dp[list.get(i)], dp[v]+arr[list.get(i)]);
//					System.out.print(v+":::"+list.get(i)+":::");
//					
//					for(int k=0; k<dp.length; k++) {
//						System.out.print(dp[k]+" ");	
//					}
//					System.out.println();
					
					
					if(ind[list.get(i)]==0) {
						q.add(list.get(i));
					}
				}
			}
			
			
			System.out.println(dp[W]);
		}
	}

	
//	int dfs(int v) {
//
//		ArrayList<Integer> list = back.get(v);
//		
//		if(list.size()==0) {
//			return arr[v];
//		}
//		int max = 0;
//		for(int i=0; i<list.size(); i++) {
//			max = Integer.max(max, dfs(list.get(i)));
////			System.out.println(v+":" + max);
//		}
//		
//		return max+arr[v];	
//		
//	}
}
