import java.util.*;
public class TestMathExpr {
    public static void strpermutation(double[] newstr){
        for(int i = 0; i+1 < newstr.length; i++){
            if(Double.isNaN(newstr[i])){
                newstr[i] = newstr[i+1];
                newstr[i+1] = Double.NaN;
            }
        }
    }
    public static void priorpermutaion(char[] operators){
        int count = 0;
        while(count < operators.length){
            if(operators[count] == '*' || operators[count] == '/'){
                for(int i = count; i < operators.length - 1; i++) operators[i] = operators[i+1];
                operators[operators.length - 1] = '\u0000';
                break;
            }
            count++;
        }
    }
    public static void commonpermutaion(char[] operators){
        int count = 0;
        while(count < operators.length){
            if(operators[count] == '+' || operators[count] == '-'){
                for(int i = count; i < operators.length - 1; i++) operators[i] = operators[i+1];
                operators[operators.length - 1] = '\u0000';
                break;
            }
            count++;
        }
    }
    public static double parse(String str) {
        //delete all possible blank place
        if(str.indexOf(' ') != -1){
            str = str.replaceAll("\\s", "");
        }
        if((str.indexOf('s') != -1 || str.indexOf('c') != -1 || str.indexOf('t') != -1) && str.indexOf('(') == -1){
            int i = 0;
            while(i < str.length() - 4){
                if(Character.isLetter(str.charAt(i))){
                    if(str.charAt(i+3) == 't'){
                        if(str.charAt(i+5) == '-'){
                            StringBuilder stringBuilder = new StringBuilder(str);
                            stringBuilder.insert(i+5, "0");
                            str = stringBuilder.toString();
                            i += 5;
                        }
                    }
                    else {
                        if(str.charAt(i+4) == '-'){
                            StringBuilder stringBuilder = new StringBuilder(str);
                            stringBuilder.insert(i+4, "0");
                            str = stringBuilder.toString();
                            i += 4;
                        }
                    }
                }
                i++;
            }
        }
        //compute all the numbers in the brackts
        while(str.indexOf('(') != -1){
            char[] brackts = new char[str.length()];
            for (int i = 0; i < brackts.length; i++) brackts[i] = str.charAt(i);
            int leftPlace = 0; int rightPlace = 0;
            int i = 0;
            while(i < brackts.length){
                if(brackts[i] == ('(')){
                    leftPlace = i;
                } 
                if(brackts[i] == (')')){
                    rightPlace = i;
                    break;
                } 
                i++;        
            }
            String modifystr = "";
            for(int j = leftPlace + 1; j < rightPlace; j++){
                modifystr += brackts[j];
            }
            String resultInBrackts = Double.toString(parse(modifystr));
            //modify str without brackts
            str = "";
            for(int j = 0; j < leftPlace; j++) str += brackts[j];
            str += resultInBrackts;
            for(int j = rightPlace+1; j < brackts.length; j++) str += brackts[j];
        }
        //divide str into an array by "+-*/"
        boolean mark = true;
        if(str.charAt(0) == '-'){
            str = str.substring(1);
            mark = false;
        }
        String[] newstr;
        if((str.indexOf('s') != -1 || str.indexOf('c') != -1 || str.indexOf('t') != -1) && str.indexOf('(') == -1){
            int i = 0;
            int length = 0;
            String point = "";
            boolean judge = false;
            while(i < str.length()){
                if(Character.isLetter(str.charAt(i))){
                    if(str.charAt(i+3) == 't'){
                        if(str.charAt(i+4) == '-'){
                            StringBuilder stringBuilder = new StringBuilder(str);
                            stringBuilder.deleteCharAt(i+4);
                            str = stringBuilder.toString();
                            point = str.substring(i, i+4);
                            judge = true;
                            length = 4;
                            i += 5;
                        }
                    }
                    else {
                        if(str.charAt(i+3) == '-'){
                            StringBuilder stringBuilder = new StringBuilder(str);
                            stringBuilder.deleteCharAt(i+3);
                            str = stringBuilder.toString();
                            point = str.substring(i, i+3);
                            judge = true;
                            length = 3;
                            i += 4;
                        }
                    }
                }
                i++;
            }
            newstr = str.split("[\\+\\-\\*\\/]");
            if(judge){
                for(i = 0; i < newstr.length; i++){
                    if(newstr[i].contains(point)){
                        String temp = newstr[i].substring(length);
                        newstr[i] = point + "-" + temp;
                    }
                }
            }
        } else{
            newstr = str.split("[\\+\\-\\*\\/]");
        }
        //compute sin, cos, tan, sqrt typess
        if((str.indexOf('s') != -1 || str.indexOf('c') != -1 || str.indexOf('t') != -1) && str.indexOf('(') == -1){
            for(int i = 0; i < newstr.length; i++){
                if(newstr[i].contains("sin") || newstr[i].contains("cos") || newstr[i].contains("tan") ||newstr[i].contains("sqrt")){
                    if(newstr[i].contains("sin")){
                        double sin = Double.parseDouble(newstr[i].substring(3));
                        newstr[i] = Double.toString(Math.sin(sin));
                    }
                    if(newstr[i].contains("cos")){
                        double cos = Double.parseDouble(newstr[i].substring(3));
                        newstr[i] = Double.toString(Math.cos(cos));
                    }
                    if(newstr[i].contains("tan")){
                        double tan = Double.parseDouble(newstr[i].substring(3));
                        newstr[i] = Double.toString(Math.tan(tan));
                    }
                    if(newstr[i].contains("sqrt")){
                        double sqrt = Double.parseDouble(newstr[i].substring(4));
                        newstr[i] = Double.toString(Math.sqrt(sqrt));
                    }
                }
            }
        }
        //compute the rest +-*/
        //divide str into doublestr and operators
        double[] doublestr = new double[newstr.length];
        for(int i = 0; i < newstr.length; i++) doublestr[i] = Double.parseDouble(newstr[i]);
        char[] operators = new char[newstr.length - 1];
        int point = 0;
        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) == '+' || str.charAt(i) == '-' || str.charAt(i) == '*' || str.charAt(i) == '/'){
                operators[point] = str.charAt(i);
                point++;
            }
        }
        if(!mark){
            doublestr[0] = -1 * doublestr[0];
        }
        //compute*/
        int i = 0;
        while(i+1 < doublestr.length){
            if(operators[i] == '*'){
                doublestr[i] = doublestr[i] * doublestr[i+1];
                doublestr[i+1] = Double.NaN;
                strpermutation(doublestr);
                priorpermutaion(operators);
                i--;
            }
            else if(operators[i] == '/'){
                doublestr[i] = doublestr[i] / doublestr[i+1];
                doublestr[i+1] = Double.NaN;
                strpermutation(doublestr);
                priorpermutaion(operators);
                i--;
            }
            boolean noprioroperation = true;
            for(int j = 0; j < operators.length; j++){
                if(operators[j] == '*' || operators[j] =='/'){
                    noprioroperation = false;
                }
            }
            if(noprioroperation) break;
            i++;
        }
        //compute +-
        i = 0;
        while(operators.length != 0 && operators[0] != '\u0000' && i+1 < doublestr.length){
            if(operators[i] == '+'){
                doublestr[i] = doublestr[i] + doublestr[i+1];
                doublestr[i+1] = Double.NaN;
                strpermutation(doublestr);
                commonpermutaion(operators);
                i--;
            }
            else if(operators[i] == '-'){
                doublestr[i] = doublestr[i] - doublestr[i+1];
                doublestr[i+1] = Double.NaN;
                strpermutation(doublestr);
                commonpermutaion(operators);
                i--;
            }
            i++;
        }
        return doublestr[0];
    }
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        while (input.hasNextLine()) {
            double result = parse(input.nextLine());
            System.out.println(String.valueOf(Math.round(result)));
        }
        input.close();
    }
}