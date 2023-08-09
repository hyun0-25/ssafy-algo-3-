package study.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * x,y 따로 구하기
 * bufferedReader 사용시
 * @author SSAFY
 *
 */

public class BJ_14400_편의점2 {
	public static int N,arrX[],arrY[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine().trim());
		long result = 0;
		arrX = new int[N];
		arrY = new int[N];

		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			arrX[i] = Integer.parseInt(st.nextToken());
			arrY[i] = Integer.parseInt(st.nextToken());
		}

		// 오름차순 정렬 
		Arrays.sort(arrX); 
		Arrays.sort(arrY);

		int midX=0;
		int midY=0;
		// 중앙값 찾기 
		midX=arrX[N/2];
		midY=arrY[N/2];


		for(int j=0;j<N;j++){
			result+=Math.abs(midX-arrX[j])+Math.abs(midY-arrY[j]);
		}

		System.out.println(result);
	}

}