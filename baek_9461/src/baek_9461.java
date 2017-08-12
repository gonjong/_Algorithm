/*
 * 2017.07.06
 * BackJoon 9461 : 파도반 수열
 * @author gonjong
 * 알고리즘은 맞는데 결과가 틀리다? 당황하지 말고 검토
 * 검토 : 맨 끝 경우의 수. 결과값 범위가 integer or long 체크
 */
import java.util.ArrayList;
import java.util.Scanner;


public class baek_9461 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		new baek_9461().solution();
	}
	
	void solution(){
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		ArrayList<Long> p = new ArrayList<Long>();
		p.add((long)1);
		p.add((long)1);
		p.add((long)1);
		p.add((long)2);
		p.add((long)2);
		for(int j=5; j<100;j++){
			p.add(j, p.get(j-1)+p.get(j-5));
		}
		
		for(int i=0; i<T; i++){
			int x = sc.nextInt();
			System.out.println(p.get(x-1));
		}
	}

}
