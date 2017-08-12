/*
 * 2017.04.03
 * #baek_1655 : 중간값 구하기, 힙 구조 이용
 */

import java.util.Collections;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Scanner;


public class baek_1655 {
	static PriorityQueue<Integer> minHeap = new PriorityQueue<>();	//최소 힙 구조 : mid값보다 큰 그룹
	static PriorityQueue<Integer> maxHeap = new PriorityQueue<>(1,Collections.reverseOrder());	//최대 힙 구조 : mid값보다 작은 그룹

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int mid;
		int x;
		
		Scanner sc = new Scanner(System.in);
		int cnt = 1;
		int a[] = new int[3];
		
		int N = sc.nextInt();
		mid = sc.nextInt();

		System.out.println(mid);
		
		while(cnt<N){
			cnt++;
			x = sc.nextInt();
			
			if(cnt%2==0){
				if(x>mid){
					minHeap.add(x);
				}
				else{
					maxHeap.add(x);
				}						//숫자 크기에 맞추어 mid보다 큰 그룹/작은 그룹으로 삽입시킨다.
				
				if(minHeap.size()>maxHeap.size()){
					System.out.println(mid);
				}
				else if(minHeap.size()<maxHeap.size()){
					System.out.println(maxHeap.peek());
				}										//들어온 개수가 짝수인 경우 : minHeap과 maxHeap 크기 차이는 1이므로
														//minHeap이 더 큰 경우 mid값이, maxHeap이 더 큰 경우 maxHeap의 루트가 중간값이다.
			}
			else{
				mid = oddCase(mid, x);
				System.out.println(mid);	
			}
			
		}
		
	}
	
	/*
	 * 입력된 개수가 홀수인 경우 heap에 삽입 후 크기를 조정하는 함수이다.
	 */
	static int oddCase(int mid, int x){
		if(x>mid){
			minHeap.add(x);
		}
		else{
			maxHeap.add(x);
		}					//숫자 크기에 맞추어 mid보다 큰 그룹/작은 그룹으로 삽입시킨다.
				
		if(minHeap.size()>maxHeap.size()){
			maxHeap.add(mid);
			mid = minHeap.poll();
		}					
		else if(minHeap.size()<maxHeap.size()){
			minHeap.add(mid);
			mid = maxHeap.poll();
		}					
							//minHeap과 maxHeap의 크기에 따라 mid값을 수정한다.
							//크기 차이는 2인 경우와 같은 경우 2가지로 나뉜다.
							//2인경우 더 큰 그룹의 루트를 mid로 만들어 크기를 조정한다.
							//0인경우 현재 mid값이 중간값이다.
		return mid;
	}

}
