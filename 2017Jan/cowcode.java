import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class cowcode {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("cowcode.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		String code = st.nextToken();
		long n = Long.parseLong(st.nextToken()) - 1;
		br.close();

		while (n >= code.length()) {
			long length = code.length();
			while (length * 2 <= n) length *= 2;
			
			n = (n % length - 1 + length) % length;
		}

		PrintWriter out = new PrintWriter(new FileWriter("cowcode.out"));
		out.println(code.charAt((int)n));
		out.close();
	}
}

