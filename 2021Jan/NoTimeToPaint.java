import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class NoTimeToPaint {
	
	static int n;
	static String fence;
	static int[][] numOfColor;
	static int[] minColorSince;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());
		fence = br.readLine();
		
		int[][] queries = new int[2][q];
		for (int i = 0; i < q; i++) {
			st = new StringTokenizer(br.readLine());
			queries[0][i] = Integer.parseInt(st.nextToken());
			queries[1][i] = Integer.parseInt(st.nextToken());
		}
		
		int[][] answers = new int[2][q];
		for (int t = 0; t < 2; t++) {
			createPrefixSums();
			for (int i = 0; i < q; i++) {
				if (i == 0) {
					answers[t][i] = numStrokesRequired(1, queries[t][i]);	
				}
				else {
					answers[t][i] = numStrokesRequired(queries[t][i], n);	
				}
			}
			reverseFence();
		}

		for (int i = 0; i < q; i++) {
			System.out.println(answers[0][i] + answers[1][i]);
		}
	}

	private static void createPrefixSums() {
		numOfColor = new int[26][n + 1];
		for (int i = 0; i < n; i++) {
			numOfColor[fence.charAt(i) - 65][i + 1]++;
		}
		for (int i = 1; i <= n; i++) {
			for (int color = 0; color < 26; color++) {
				numOfColor[color][i] += numOfColor[color][i - 1];
			}
		}

		minColorSince = new int[n + 1];
		for (int i = n - 2; i >= 0; i--) {
			minColorSince[i] = Math.min(minColorSince[i + 1], fence.charAt(i) - 65);
		}
	}

	private static int numStrokesRequired(int start, int end) {
		int[] lastOccurenceOfColor = new int[26];
		for (int i = 0; i < 26; i++) {
			lastOccurenceOfColor[i] = -1;
		}
		
		int numStrokes = 0;
		for (int i = start; i <= end; i++) {
			int color = fence.charAt(i - 1) - 65;
			if (lastOccurenceOfColor[color] != -1 && minColorSince[lastOccurenceOfColor[color]] >= color) {
				continue;
			}

			numStrokes++;
		} 

		return numStrokes; 
	}

	private static void reverseFence() {
		StringBuilder str = new StringBuilder();
		for (int i = n - 1; i >= 0; i--) {
			str.append(fence.charAt(i));
		}

		fence = str.toString();
	}
}
