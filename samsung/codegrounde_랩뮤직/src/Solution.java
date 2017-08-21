/*
You should use the statndard input/output

in order to receive a score properly.

Do not use file input and output

Please be very careful. 
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
   As the name of the class should be Solution , using Solution.java as the filename is recommended.
   In any case, you can execute your program by running 'java Solution' command.
 */
public class Solution {
	int Answer = 5001;
	int cnt = 0;
	int T;
	int N;
	HashMap<Character, ArrayList<String>> list;
	HashMap<Character, ArrayList<String>> wordList;

	HashMap<String, String> checkWord;
	HashMap<String, String> check;

	public static void main(String args[]){
		/*
		   The method below means that the program will read from input.txt, instead of standard(keyboard) input.
		   To test your program, you may save input data in input.txt file,
		   and call below method to read from the file when using nextInt() method.
		   You may remove the comment symbols(//) in the below statement and use it.
		   But before submission, you must remove the freopen function or rewrite comment symbols(//).
		 */		

		Solution sol = new Solution();
		sol.sol();

	}

	void sol() {

		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();

		for(int test_case = 0; test_case < T; test_case++) {

			list = new HashMap<Character, ArrayList<String>>();
			wordList = new HashMap<Character, ArrayList<String>>();
			checkWord = new HashMap<String, String>();
			check = new HashMap<String, String>();
			Answer = 0;
			cnt = 0;
			N = sc.nextInt();
			String ss = sc.next();

			Find(ss, 0);
			System.out.println("Case #"+(test_case+1));
			
			System.out.println(Answer);
		}
	}

	void Find(String s, int index) {

		boolean hasWord = false;
		
		if(index == s.length()) {
			if(cnt==1) {
				cnt = 0;
			}
			Answer = cnt;
			return;
		}
		
		int lastIndex = s.lastIndexOf(s.charAt(index));
		int range = s.length()-lastIndex-1;

		if(lastIndex == index) {
			return;
		}
		
		
		int i=lastIndex;
		for(; i>=index+1; i--) {
			hasWord = false;
			int j=1;
			for(; j<=range+(lastIndex-i)+1; j++) {
				if(s.substring(index, index+j).equals(s.substring(i, i+j))) {
					hasWord = true;
					continue;
				}
				else
					break;
			}
			if(hasWord) {
				cnt++;
				Find(s, i+j-1);	
				cnt--;	
			}
		}
		
	}

	void find(String s, int index) {

		if(index == s.length()) {
			Answer = cnt;
			return ;
		}

		for(int i=s.length(); i>index; i--){
			if(Answer!=5001) {
				return;
			}
			boolean isWord = false;
			for(int j=1; j<=(i-index)/2;j++) {

				if(s.substring(index, index+j).equals(s.substring(i-j, i))) {
					isWord = true;
					break;
				}
			}
			if(isWord) {
				cnt++;
				find(s, i);
				cnt--;
			}

		}	
	}
	/*
	int match(String s, int index) {
		ArrayList<String> word = new ArrayList<String>();
		if(index == s.length()) {
			if(cnt==1) {
				cnt = 0;
			}
			Answer = cnt;
			return 1;
		}
		else if(index>s.length()) {
			return -1;
		}

		word = wordList.get(s.charAt(index));

		for(int j=word.size()-1; j>=0;j--) {
			String x = word.get(j);
			if(index+x.length()<=s.length()) {
				if(s.substring(index, index+x.length()).equals(x)) {
					cnt++;
					int a = match(s, index+x.length());
					if(a==-1) {
						cnt--;
					}
					else {
						return 1;
					}
				}
			}
		}
		return -1;
	}

	void makeWord(String s) {
		ArrayList<String> word = new ArrayList<String>();
		ArrayList<String> ww = new ArrayList<String>(); 

		for(int i=1; i<s.length(); i++) {
			for(int j=0; j+i<=s.length(); j++) {
				if(!list.containsKey(s.charAt(j))) {
					word = new ArrayList<String>();
				}
				else {
					word = list.get(s.charAt(j));
				}

				if(!checkWord.containsKey(s.substring(j, j+i))) {
					checkWord.put(s.substring(j, j+i), "");
					word.add(s.substring(j, j+i));
					list.put(s.charAt(j), word);
				}

				if(!wordList.containsKey(s.charAt(j))) {
					ww = new ArrayList<String>();
				}
				else {
					ww = wordList.get(s.charAt(j));
				}

				String x = word.get(word.size()-1);

				if(x.length()==1) {
					continue;
				}

				if(x.charAt(0)==x.charAt(x.length()-1)) {
					if(!check.containsKey(x)) {
						ww.add(x);	
						check.put(x, x);
					}
				}
				else {
					for(int k=0; k<word.size()-2;k++) {
						String sub = word.get(k);
						if(x.length()>=sub.length() && x.substring(0, sub.length()).equals(sub) 
								&& x.substring(x.length()-sub.length(), x.length()).equals(sub)){

							if(!check.containsKey(x)) {
								ww.add(x);	
								check.put(x, x);
								break;
							}
						}
					}
				}
				wordList.put(s.charAt(j), ww);

			}
		}

		Iterator key = list.keySet().iterator();
		checkWord = new HashMap<String, String>();

		while(key.hasNext()) {
			char k = (char)key.next();
			word = list.get(k);
			ArrayList<String> ww = new ArrayList<String>(); 
			for(int i=word.size()-1; i>=0; i--) {
				String x = word.get(i);

				if(x.length()==1) {
					x += x;
					if(!checkWord.containsKey(x)) {
						checkWord.put(x, x);
						ww.add(x);	
					}
					continue;
				}
				if(x.charAt(0)==x.charAt(x.length()-1)) {
					if(!checkWord.containsKey(x)) {
						checkWord.put(x, x);
						ww.add(x);	
					}
				}
				else {
					for(int j=i+1; j<word.size();j++) {
						String sub = word.get(j);
						if(x.length()>=sub.length() && x.substring(0, sub.length()).equals(sub) 
								&& x.substring(x.length()-sub.length(), x.length()).equals(sub)){
							if(!checkWord.containsKey(x)) {
								checkWord.put(x, x);
								ww.add(x);	
							}
						}
					}
				}
			}

			list.put(k, ww);
		}

	}
	 */
}

class pos {
	int index;
	int cnt;

	pos(int index, int cnt){
		this.index = index;
		this.cnt = cnt;
	}
}