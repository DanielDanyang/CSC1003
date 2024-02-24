import java.util.*;

public class ClassVector {

    // The "main" part is used to test your Vector class, so do NOT modify this part!
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numOfVector = Integer.parseInt(scanner.nextLine());
        Vector[] vectors = new Vector[numOfVector];
        for (int i = 0; i < numOfVector; i++) {
            String nextVector = scanner.nextLine();
            String[] elements = nextVector.split(" ");
            int[] newVector = new int[elements.length];
            for (int j = 0; j < elements.length; j++) newVector[j] = Integer.parseInt(elements[j]);
            vectors[i] = new Vector(newVector);
        }
        while (scanner.hasNextLine()) {
            String nextLine = scanner.nextLine();
            String[] s = nextLine.split(" ");
            switch (s[0]) {
                case "plus":
                    System.out.println(Vector.plus(vectors[Integer.parseInt(s[1])], vectors[Integer.parseInt(s[2])]));
                    break;
                case "subtract":
                    System.out.println(Vector.subtract(vectors[Integer.parseInt(s[1])], vectors[Integer.parseInt(s[2])]));
                    break;
                case "multiply":
                    System.out.println(vectors[Integer.parseInt(s[1])].multiply(Integer.parseInt(s[2])));
                    break;
                case "norm":
                    if (!"infinity".equals(s[2])) System.out.printf("%.3f\n", vectors[Integer.parseInt(s[1])].norm(Integer.parseInt(s[2])));
                    else System.out.printf("%.3f\n", vectors[Integer.parseInt(s[1])].norm());
                    break;
                case "dot":
                    System.out.println(Vector.dot(vectors[Integer.parseInt(s[1])], vectors[Integer.parseInt(s[2])]));
                    break;
                case "angle":
                    System.out.printf("%.3f\n", Vector.angle(vectors[Integer.parseInt(s[1])], vectors[Integer.parseInt(s[2])]));
                    break;
            }
        }
        scanner.close();
    }

}

// This is the class you need to implement
class Vector {
    private int[] vector;
    public Vector(int[] vector){
        this.vector = vector;
    }
    public static Vector plus(Vector v1, Vector v2){
        int[] sum = new int[v1.vector.length];
        for(int i = 0; i < v1.vector.length; i++){
            sum[i] = v1.vector[i] + v2.vector[i];
        }
        return new Vector(sum);
    }
    public static Vector subtract(Vector v1, Vector v2){
        int[] difference = new int[v1.vector.length];
        for(int i = 0; i < v1.vector.length; i++){
            difference[i] = v1.vector[i] - v2.vector[i];
        }
        return new Vector(difference);
    }
    public Vector multiply(int a){
        int[] multiplication = new int[vector.length];
        for(int i = 0; i < vector.length; i++){
            multiplication[i] = a * vector[i];
        }
        return new Vector(multiplication);
    }
    public double norm(int p){
        double result = 0;
        for(int i : vector) result += Math.pow(Math.abs(i), p);
        return Math.pow(result, 1.0 / p); 
    }
    public double norm(){
        double result = 0;
        for(int i : vector){
            if(Math.abs(i) > result) result = Math.abs(i);
        }
        return result;

    }
    public static int dot(Vector v1, Vector v2){
        int dotproduct = 0;
        for(int i = 0; i < v1.vector.length; i ++){
            dotproduct += v1.vector[i] * v2.vector[i];
        }
        return dotproduct; 
    }
    public static double angle(Vector v1, Vector v2){
        return Math.acos(dot(v1, v2) / (v1.norm(2)*v2.norm(2)));
    }
    public String toString(){
        StringBuilder result = new StringBuilder("(");
        for(int i = 0; i < vector.length; i++){
            result.append(vector[i]);
            if(i < vector.length - 1){
                result.append(", ");
            }
        }
        result.append(")");
        return result.toString();
    }
}