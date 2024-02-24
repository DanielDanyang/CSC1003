import java.util.*;
public class RegionFill{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        char[][] input = new char[20][20];
        for(int i = 0; i < 20; i++) input[i] = scanner.nextLine().toCharArray();
        String[] temp = scanner.nextLine().split(", ");
        int x = Integer.parseInt(temp[0]);
        int y = Integer.parseInt(temp[1]);
        scanner.close();
        fill(input, x, y);
        for(int i = 0; i < 20; i++){
            for(int j = 0; j < 20; j++){
                System.out.print(input[i][j]);
            }
            System.out.println();
        }
    }
    public static void fill(char[][] input, int x, int y){
        if(x < 0 || y < 0 || x > 19 || y > 19 || input[x][y] == '-') return;
        input[x][y] = '-';
        fill(input, x-1, y);
        fill(input, x, y-1);
        fill(input, x+1, y);
        fill(input, x, y+1);
    }
}
