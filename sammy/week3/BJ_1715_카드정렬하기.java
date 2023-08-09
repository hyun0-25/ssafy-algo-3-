package study.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BJ_1715_카드정렬하기 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		PriorityQueue<Integer> minheapq = new PriorityQueue<Integer>();
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		
		int result=0;
		
		int N = Integer.parseInt(br.readLine());
		
		for(int i=0;i<N;i++) {
			minheapq.offer(Integer.parseInt(br.readLine()));
		}
		if(N==1) System.out.println(0); // N이 1이면 비교할 필요 x 
		else {
			while(minheapq.size()>1) { // 큐에 하나만 남을 때 까지 
				int totalNow = minheapq.poll()+minheapq.poll();
				result+=totalNow; // result에 더해가기 
				minheapq.offer(totalNow); // 두개 합 큐에 삽입 
			}
			System.out.println(result);
		}
	}
}
