import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class NoPaintTimeStackSol {
	
	static int n;
	static String fence;
	static int[] firstHalfSolution;
	static int[] secondHalfSolution;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());

		firstHalfSolution = new int[n + 1];
		secondHalfSolution = new int[n + 1];

		fence = br.readLine();
		Stack<Character> activeColors = new Stack<>();
		for (int i = 1; i <= n; i++) {
			char currColor = fence.charAt(i - 1);
			firstHalfSolution[i] = firstHalfSolution[i - 1];

			while(!activeColors.isEmpty() && activeColors.peek() > currColor) activeColors.pop();
			if (activeColors.isEmpty() || activeColors.peek() != currColor) {
				firstHalfSolution[i]++;
				activeColors.push(currColor);
			} 
		}

		reverseFence();
		activeColors = new Stack<>();
		for (int i = 1; i <= n; i++) {
			char currColor = fence.charAt(i - 1);
			secondHalfSolution[i] = secondHalfSolution[i - 1];

			while(!activeColors.isEmpty() && activeColors.peek() > currColor) activeColors.pop();
			if (activeColors.isEmpty() || activeColors.peek() != currColor) {
				secondHalfSolution[i]++;
				activeColors.push(currColor);
			}
		}
		
		for (int i = 0; i < q; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken());

			System.out.println(firstHalfSolution[a] + secondHalfSolution[n - b]);
		}
	}

	private static void reverseFence() {
		String str = "";
		for (int i = n - 1; i >= 0; i--) {
			str += fence.charAt(i);
		}

		fence = str;
	}
}
