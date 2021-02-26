import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class homework {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("homework.in"));
		int n = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] scores = new int[n + 1];
		int[] sumOfFirst = new int[n + 1]; 
		for (int i = 1; i <= n; i++) {
			scores[i] = Integer.parseInt(st.nextToken());
			sumOfFirst[i] = sumOfFirst[i - 1] + scores[i];
		}
		br.close();

		int[] minAfter = new int[n];
		minAfter[n - 1] = scores[n];
		for (int i = n - 2; i >= 1; i--) {
			minAfter[i] = Math.min(scores[i + 1], minAfter[i + 1]);
		}
		
		TreeMap<Double, ArrayList<Integer>> kValues = new TreeMap<>();
		for (int k = 1; k <= n - 2; k++) {
			double sumAfterRemovingFirstK = sumOfFirst[n] - sumOfFirst[k];
			double averageScore = (sumAfterRemovingFirstK - minAfter[k]) / (n - k);

			kValues.putIfAbsent(averageScore, new ArrayList<>());
			kValues.get(averageScore).add(k);
		}

		System.out.println(kValues.keySet());
		
		PrintWriter out = new PrintWriter(new FileWriter("homework.out"));
		for (Integer k : kValues.get(kValues.lastKey())) {
			out.println(k);
		}
		out.close();
	}
}
