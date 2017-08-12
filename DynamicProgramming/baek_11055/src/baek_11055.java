
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;



public class baek_11055 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int A[] = new int[N];
		int dp[][] = new int[N][3];
		
		for(int i=0; i<N; i++){
			A[i] = sc.nextInt();
		}
		
		int subSum = A[0];
		int lastMax = A[0];
		int subMax = A[0];
		int result[] = new int [N];
		int a[] = new int[N];
		result[0] = A[0];
		a[0] = A[0];
		for(int i=1; i<N; i++){
			lastMax = 0;
			subSum = A[0];
			for(int j=0; j<i; j++){
				if(A[i]>A[j] && lastMax<a[j]){
					lastMax = a[j];
				}
			}
			subSum = A[i]+lastMax;
			a[i] = subSum;
			result[i] = subSum;
		}


		Arrays.sort(result);
		
		System.out.println(result[N-1]);
		
		/*
		ArrayList<Integer> subList = new ArrayList<Integer>();
		subList.add(A[0]);
		for(int index=0; index<N;index++){
			
			if(index>0 && lastMax<A[index]){
				subSum += A[index];
				subMax = subSum;
				lastMax = A[index];
			}
			else{
				if(index-1>0 && !(A[index-1]<A[index])){
					subSum = A[index];	
				}
				while(index+1<N && A[index]>=A[index+1] && lastMax>A[index+1]){
					index++;
				}
				
				while(index+1<N && lastMax>A[index+1]){
					subSum += A[++index];
					if(subSum>lastMax){
						subMax-=lastMax;
						lastMax = subSum;
						subMax += lastMax;
						
						break;
					}
				}
				if(!(index+1<N && lastMax>A[index+1])){
					subSum = subMax;
				}
			}	
		}

		
		
		System.out.println(subMax);
		*/
	}

}
