/*
 * 2017.05.09
 * baek_2143 : 투 포인터, Olympiad > 한국정보올림피아드 > KOI 2001 > 고등부 1번
 * 두 배열의 합
 * @author : gonjong
 * 1000,000,000 이상의 수가 나오는 경우 long 단위로 풀 것.
 * 자료구조 배열리스트 쓰기 전 해쉬맵 먼저 체크.
 */


import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;


public class baek_2143 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		int n = sc.nextInt();
				
		int[] A = new int[n+1];
		
		HashMap<Long, Long> rangeA = new HashMap<Long, Long>();	//value, count
		HashMap<Long, Long> rangeB = new HashMap<Long, Long>();
		
		for(int i=1; i<=n; i++){
			A[i] = sc.nextInt();
		
		}
	
		int m = sc.nextInt();
		int[] B = new int[m+1];
		for(int i=1; i<=m;i++){
			B[i] = sc.nextInt();
		}
		
		long ans = 0;
	
		long fromHash = 0;
		long x = 0;
		
		//문제 조건에 따라 부 배열의 범위를 구한 후 해쉬맵에 저장시킨다.
		for(int i=0; i<n; i++){
			for(int j=1; j<=n; j++){
				x = 0;
				if(j+i<=n){
					for(int k=0; k<=i; k++){
						x+=A[j+k];
					}
					if(rangeA.containsKey(x)){
						fromHash = rangeA.get(x);
					}
					else{
						fromHash = 0;
					}
					rangeA.put(x, fromHash+1);

				}
			}
		}
		
		for(int i=0; i<m; i++){
			for(int j=1; j<=m; j++){
				x = 0;
				if(j+i<=m){
					for(int k=0; k<=i; k++){
						x+=B[j+k];
					}
					if(rangeB.containsKey(x)){
						fromHash = rangeB.get(x);
					}
					else{
						fromHash = 0;
					}
					rangeB.put(x, fromHash+1);
				}
			}
		}
		

		// T를 만드는 A, B 개수를 곱해 쌍의 개수를 구한다.
		for(long key : rangeA.keySet()){
			if(rangeB.containsKey(T-key)){
				ans+=rangeA.get(key)*rangeB.get(T-key);
			}
		}
		
		System.out.println(ans);
	}

}

class ascend implements Comparator<data>{

	@Override
	public int compare(data o1, data o2) {
		// TODO Auto-generated method stub
		return o1.value<o2.value? -1:1;
	}

	
	
}
class data{
	int value;
	int cnt = 0;
	data(){
		
	}
	data(int x, int y){
		this.value = x;
		this.cnt = y;
	}
	
}
