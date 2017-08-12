/*
 * 2017.04.28
 * baek_10868 : Segment Tree
 */


import java.util.Scanner;

public class baek_10868 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		int A[] = new int[N+1];
		
		for(int i=1; i<=N; i++){
			A[i] = sc.nextInt();
		}

		int k=1;
		int x = 2;
		while(true){
			if(x>N){
				break;
			}
			x*=2;
			k++;
		}
		
//		int H = (int)Math.log(2*N);
//		int treeSize = (int)Math.pow(4, H)*2;
		int treeSize = (int)Math.pow(2, k)*2;
		int tree[] = new int[treeSize+1];
		
		init(tree, A, 1, 1, N);

		int left = 0;
		int right = 0;
		for(int j=0; j<M; j++){
			left = sc.nextInt();
			right = sc.nextInt();
			System.out.println(min(tree, 1, 1, N, left, right));
		}
	}
	
	/*
	 * 세그먼트 트리 생성 함수
	 * (start, end) : arr[]에서의 범위
	 */
	static int init(int []tree,int []arr,int node,int start,int end){
		if(start==end){
			return tree[node]=arr[start];
		}
		int mid=(start+end)/2;
		int l = init(tree, arr, node*2, start, mid);
		int r = init(tree, arr, node*2+1, mid+1, end);
		if(l>r){
			return tree[node] = r;
		}
		else{
			return tree[node] = l;
		}
	}

	/*
	 * 최소값을 구하는 함수
	 */
	static int min(int []tree,int node,int start,int end,int left,int right){
		if(end<left||start>right){
			return Integer.MAX_VALUE;
		}								//범위 만족하지 않는 곳
										
		if(left<=start&&end<=right){
//			System.out.println("//"+start+","+end + " " + left+","+right + ": "+ tree[node]);
			return tree[node];
		}								//범위 만족하는 곳
										//걸쳐있으면 더 들어가 본다.
		int mid = (start+end)/2;
		
		int l = min(tree, node*2, start, mid, left, right);
		int r = min(tree, node*2+1, mid+1, end, left, right);
		if(l>r){
//			System.out.println(start+","+end + " " + left+","+right+ ": "+ tree[node]);
			return r;
		}
		else{
//			System.out.println(start+","+end + " " + left+","+right+ ": "+ tree[node]);
			return l;
		}
	}

}
