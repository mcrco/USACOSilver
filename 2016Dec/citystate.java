import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class citystate {

	static int n;
	static String[] citystates;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader("citystate.in"));
		n = Integer.parseInt(br.readLine());

		HashMap<String, Integer> count = new HashMap<>();
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String citystate = st.nextToken().substring(0, 2) + st.nextToken();

			count.putIfAbsent(citystate, 0);
			count.put(citystate, count.get(citystate) + 1);
		}
		br.close();

		int ans = 0;
		for (String citystate : count.keySet()) {
			String reversed = reverse(citystate);
			if (!citystate.equals(reversed) && count.containsKey(reversed)) {
				ans += count.get(citystate) * count.get(reversed);
			}
		}

		PrintWriter out = new PrintWriter(new FileWriter("citystate.out"));
		out.println(ans / 2);
		out.close();
	}


	private static String reverse(String citystate) {
		return citystate.substring(2, 4) + citystate.substring(0, 2);
	}

}
