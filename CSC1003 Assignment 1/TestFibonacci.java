import java.util.*;

public class TestFibonacci {

    static Scanner input = new Scanner(System.in);

    // here is the function you need to implement
	public static void parse_line(int n, int d) {
        if (d == 0) {
            System.out.println();
            return;
        }
        long[] a = new long [n + d];
        a[0] = 1;
        a[1] = 1;
        for(int i = 2; i <= n; i++ ){
                a[i] = a[i - 1] + a[i - 2];     
        }
        if(d > 0){
        System.out.print(a[n - 1]);
        while(d > 1){
            System.out.print(", " + a[n - 2]);
            d--;
            n--;
        }
        }
        System.out.println();
    }

    public static void main(String[] args) throws Exception {
        int line_number = Integer.parseInt(input.nextLine()); 
        for(int i=0; i<line_number; i++) {
            String s = input.nextLine();
            String t[] = s.split(", ");
            int n = Integer.parseInt(t[0]);
            int d = Integer.parseInt(t[1]);
            TestFibonacci.parse_line(n, d);
        }
    }
}