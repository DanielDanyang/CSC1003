public class Str {
    private char[] charArray;
    private int length;
    
    public Str(int maxLength){
        charArray = new char[maxLength];
        length = 0;
    }

    public boolean isEmpty(){
        return length == 0;
    }

    public boolean isFull(){
        return length == charArray.length;
    }

    public void appendChar(char c){
        if(!isFull()){
            charArray[length] = c;
            length++;
        } else{
            System.out.println("Error");
        }
    }
    public void deleteChar(){
        
    }
}
