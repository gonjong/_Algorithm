/*
 * 2017.07.10
 * BaekJoon 2096 : Dynamic Programming, Sliding Window
 * @author gonjong
 */
import java.util.Scanner;

public class baek_2096 {

	int max = 0;
	int min = 9000000;

	int N;	
	int dpMax[][];
	int dpMin[][];

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new baek_2096().solution();
	}
	
	void solution() {
		
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		dpMax = new int[2][3];
		dpMin = new int[2][3];

		
		int arr[][] = new int[N][3];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<3; j++) {
				arr[i][j] = sc.nextInt();
			}
		}

		//dp �ʱⰪ ����.
		dpMax[0][0] = arr[0][0];
		dpMax[0][1] = arr[0][1];
		dpMax[0][2] = arr[0][2];
		
		dpMin[0][0] = arr[0][0];
		dpMin[0][1] = arr[0][1];
		dpMin[0][2] = arr[0][2];
		
		
		for(int i=1;i<N;i++) {
			
			//�ִ밪 ���ϱ�
			if(dpMax[0][0]>dpMax[0][1]) {
				dpMax[1][0] = dpMax[0][0]+arr[i][0];
			}
			else {
				dpMax[1][0] = dpMax[0][1]+arr[i][0];
			}

			//�� �� �� ���� �� ������ �ٸ� 2������ ���� ������ ���ϴ� ���X(if~else if~else if)
			int t = dpMax[0][0];
			if(dpMax[0][1]>t) {
				t = dpMax[0][1];
			}
			if(dpMax[0][2]>t) {
				t = dpMax[0][2];
			}
			dpMax[1][1] = t+arr[i][1];
													/*
													 * ���� 0,1 �����ؼ� üũ
													 */
			
			if(dpMax[0][1]>dpMax[0][2]) {
				dpMax[1][2] = dpMax[0][1]+arr[i][2];
			}
			else {
				dpMax[1][2] = dpMax[0][2]+arr[i][2];
			}
			dpMax[0][0] = dpMax[1][0];
			dpMax[0][1] = dpMax[1][1];
			dpMax[0][2] = dpMax[1][2];


			//�ּҰ� ���ϱ�
			if(dpMin[0][0]<dpMin[0][1]) {
				dpMin[1][0] = dpMin[0][0]+arr[i][0];
			}
			else {
				dpMin[1][0] = dpMin[0][1]+arr[i][0];
			}

			t = dpMin[0][0];
			if(dpMin[0][1]<t) {
				t = dpMin[0][1];
			}
			if(dpMin[0][2]<t) {
				t = dpMin[0][2];
			}
			dpMin[1][1] = t+arr[i][1];
			
			if(dpMin[0][1]<dpMin[0][2]) {
				dpMin[1][2] = dpMin[0][1]+arr[i][2];
			}
			else {
				dpMin[1][2] = dpMin[0][2]+arr[i][2];
			}
			dpMin[0][0] = dpMin[1][0];
			dpMin[0][1] = dpMin[1][1];
			dpMin[0][2] = dpMin[1][2];

		}

		max = dpMax[0][0];
		min = dpMin[0][0];
		
		for(int i=1; i<3; i++) {
			if(max<dpMax[0][i]) {
				max = dpMax[0][i];
			}
			if(min>dpMin[0][i]) {
				min = dpMin[0][i];
			}
		}
		
		System.out.println(max+" "+min);
	}
	
}
