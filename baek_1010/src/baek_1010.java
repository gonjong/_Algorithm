import java.util.Scanner;

public class baek_1010 {

	int T;
	int N;
	int M;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new baek_1010().solution();
	}

	void solution() {
		
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
	
		for(int i=0; i<T; i++) {
			N = sc.nextInt();
			M = sc.nextInt();
			
			System.out.println(combination(M, N));;
		}
	}
	
	long combination(int n, int m) {
		double x = 1;
		double y = 1;
	
		for(int j=0; j<m; j++) {
			x *= n-j;
			y *= j+1;
		}
		
		return (long)(x/y);
	}
}
