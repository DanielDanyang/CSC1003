import java.util.*;

public class GaussianElimination {

    // here is the function you need to implement
    // given the augmented matrix as input, solve
    // the linear equations, and output the results
    public static void solve(double[][] augMatrix) {
        // create the solution vector
        int n = augMatrix.length;
        double[] x = new double[n];

        // your code starts here...
        
        //Let augMatrix be REF
        for(int i = 1; i < n; i++){
            int j = 0;
            while(j < n && augMatrix[i][j] == 0.0) {
                j++;
            }
            if(j >= n) continue;
            for(int l = 0; l < n; l++){
                if(l < i && augMatrix[i][l] != 0.0){
                        double coe = augMatrix[i][l] / augMatrix[l][l];     
                        for(int k = 0; k < n+1; k++)  augMatrix[i][k] -= augMatrix[l][k] * coe;
                }
            }
        }
        /*
        test code: 
        for(int i = 0; i < n+1; i++){  System.out.print(augMatrix[1][i]+" ");}
        System.out.println(" ");
        for(int i = 0; i < n+1; i++){  System.out.print(augMatrix[2][i]+" ");}
        System.out.println(" ");
        */

        //back-substitution
        x[n-1] = augMatrix[n-1][n] / augMatrix[n-1][n-1];
        for(int i = n-2; i >= 0; i--){
            double result = augMatrix[i][n];      
            for(int k = n-1; k > i; k--){
                result = result - x[k] * augMatrix[i][k];
                
            }
            x[i] = result / augMatrix[i][i];
        } 

        // end of your code
        
        // output the results, DO NOT change
        for (int i = 0; i < n - 1; i++) {
            System.out.print(String.format("%.3f", x[i]) + " ");
        }
        System.out.println(String.format("%.3f", x[n-1]));
    }

    // program entry, DO NOT change
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = Integer.parseInt(input.nextLine());
        double[][] augMatrix = new double[n][n + 1];
        for (int i = 0; i < n; i++) {
            String s = input.nextLine();
            String[] t = s.split(" ");
            for (int j = 0; j < n + 1; j++) {
                augMatrix[i][j] = Double.parseDouble(t[j]);
            }
        }
        solve(augMatrix);
        input.close();
    }

}