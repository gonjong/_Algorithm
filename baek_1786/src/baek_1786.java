/*
 * 2017.04.14 : baek_1786 ; KMP알고리즘
 * @author : gonjong
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class baek_1786 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = null;
		
		String str = null;
		String pattern = null;
		int[] fail;
		
		try{

			str = br.readLine();
			pattern = br.readLine();
			
		}catch(IOException e){
			System.out.println(e);
		}
		
		fail = new int[pattern.length()];
		
		fail(pattern, fail);
		findPattern(str, pattern, fail);
	}
	
	/*
	 * findPattern()
	 * @param : str, pattern, failFuntion 
	 */
	
	static void findPattern(String str, String pattern, int[] fail){
		int cnt = 0;
		ArrayList<Integer> startPosition = new ArrayList<Integer>();
		
		for(int i=0,j=0; i<str.length();){
			if(str.charAt(i)==pattern.charAt(j)){
				i++;
				j++;						
				if(j==pattern.length()){
					startPosition.add(i-pattern.length()+1);
					j=fail[j-1]+1;
					cnt++;
				}						
									// 값이 같은 경우 위치를 1씩 증가
									// j가 패턴 길이가 된 경우 패턴이 존재하므로 시작위치 저장
			}
			else if(j==0)
					i++;			//패턴이 처음부터 일치하지 않는 경우
			else{
				j=fail[j-1]+1;
									// 값이 같지 않은 경우, 패턴 내에서 이전 반복패턴의 다음 값과 비교
			}
		}
		
		System.out.println(cnt);
		for(int i=0; i<startPosition.size(); i++)
			System.out.println(startPosition.get(i));
	}
	
	/*
	 * fail()
	 * @param : pattern, failFuntion 
	 */
	
	static void fail(String pattern, int fail[]){
		
		fail[0] = -1;
		
		for(int i=1; i<pattern.length(); i++){
			
			for(int j=i; j>0; j=fail[j-1]+1 ){

				if(pattern.charAt(fail[j-1]+1)==pattern.charAt(i)){
					fail[i] = fail[j-1]+1;
					break;
				}
				else if(fail[j-1]!=-1)
					continue;
				else 
					fail[i] = -1;
										//반복되는 패턴이 없는 경우 -1, 있는 경우 해당 패턴 위치+1
			}
		}
	}
}


