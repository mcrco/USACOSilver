import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class pairup {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("pairup.in"));
		int n = Integer.parseInt(in.readLine());

		output[] outputs = new output[n];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			outputs[i] = new output(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		in.close();
		
		Arrays.sort(outputs);
		
		int low = 0;
		int high = n - 1;
		int ans = 0;
		while (outputs[low].numCows > 0 && outputs[high].numCows > 0) {
			ans = Math.max(ans, outputs[low].quantity + outputs[high].quantity);
			
			int minNumCows = Math.min(outputs[low].numCows, outputs[high].numCows);
			outputs[low].numCows -= minNumCows;
			outputs[high].numCows -= minNumCows;

			if (outputs[low].numCows == 0) low++;
			if (outputs[high].numCows == 0) high--;
		}
		
		PrintWriter out = new PrintWriter(new FileWriter("pairup.out"));
		out.println(ans);
		out.close();
	}

	static class output implements Comparable<output> {
		int numCows;
		int quantity;

		public output(int n, int q) {
			numCows = n;
			quantity = q;
		}

		public int compareTo(output other) {
			return quantity - other.quantity;
		}
	}
}
