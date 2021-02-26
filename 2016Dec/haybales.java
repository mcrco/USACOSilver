import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class haybales {
	
	static int n;
	static int[] locations;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("haybales.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());

		locations = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			locations[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(locations);
		PrintWriter out = new PrintWriter(new FileWriter("haybales.out"));
		for (int i = 0; i < q; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());

			out.println(upperbound(end) - lowerbound(start));
		}
		out.close();
	}

	private static int lowerbound(int query) {
		int low = 0;
		int high = n;

		while (low < high) {
			int mid = (low + high) / 2;

			if (locations[mid] < query) {
				low = mid + 1;
			}
			else {
				high = mid;
			}
		}

		return low;
	}

	private static int upperbound(int query) {
		int low = 0;
		int high = n;

		while (low < high) {
			int mid = (low + high) / 2;

			if (locations[mid] <= query) {
				low = mid + 1;
			}
			else {
				high = mid;
			}
		}

		return low;
	}
}
