import java.util.*;
 
public class Tic_Tac_Toe_Main {
static char userLetter;
static char computerLetter;
static char [] board;
public static void main(String[] args) {
// TODO Auto-generated method stub
 
board= createBoard();
 
Scanner userInput = new Scanner(System.in);  
 
 
//toss
boolean tossWinUser=toss();
if(tossWinUser){
System.out.println("user win the toss");
userLetter= chooseLetter(userInput);}
 
else
{System.out.println("user loss the toss");
    userLetter='X';
}
 
 
 
computerLetter=(userLetter=='X')?'O':'X';
System.out.println("userLetter"+userLetter);
System.out.println("computterletter"+computerLetter);
 
print(board);
if(tossWinUser){
    userTurn(board,1);
}
else
smartComputerTurn(board,1);
 
 
 
}
private static char[] createBoard() {
char[]board = new char[10];
for(int i=0;i<board.length;i++) {
board[i]=' ';
}
return board;
}
private static char chooseLetter(Scanner userInput) {
System.out.print("Input a character: ");  
return userInput.next().toUpperCase().charAt(0);
}
 
private static void print(char [] board) {
 
 
System.out.print("\n"+board[1]+"|"+board[2]+"|"+board[3]);
System.out.print("\n"+"------");
System.out.print("\n"+board[4]+"|"+board[5]+"|"+board[6]);
System.out.print("\n"+"------");
System.out.print("\n"+board[7]+"|"+board[8]+"|"+board[9]);
 
 
}
private static boolean toss(){
    Random rand = new Random();
 
        // Generate random integers in range 0 to 1
        int x = rand.nextInt(2);
 
    return x%2==0;
}
 
private static char winner(char[] board){
 
    //horizontal
    if(board[1]==board[2]&&board[2]==board[3]&&board[3]!=' ')
    return board[1];
    if(board[4]==board[5]&&board[5]==board[6]&&board[6]!=' ')
    return board[4];
    if(board[7]==board[8]&&board[8]==board[9]&&board[9]!=' ')
    return board[7];
 
    //vertical
    if(board[1]==board[4]&&board[4]==board[7]&&board[7]!=' ')
    return board[1];
    if(board[2]==board[5]&&board[5]==board[8]&&board[8]!=' ')
    return board[4];
    if(board[3]==board[6]&&board[6]==board[9]&&board[9]!=' ')
    return board[3];
    //digonal
    if(board[1]==board[5]&&board[5]==board[9]&&board[9]!=' ')
    return board[1];
    if(board[3]==board[5]&&board[5]==board[7]&&board[7]!=' ')
    return board[3];
    int countFilled=0;
    for(int i=1;i<10;i++){
        if(board[i]!=' ')
        countFilled++;
    }
    if(countFilled==9)
    return 'T';
    else
return ' ';
 
}
private static void userTurn(char[] board,int x){
    if(x>9)
    {
        System.out.println("ENd");
        return;
    }
    System.out.println();
 
   System.out.println("USER TURN");
    Scanner userInput = new Scanner(System.in);  
    System.out.print("enter the next turn position");
    int p=userInput.nextInt();
    while(!valid(p))
    {
        System.out.println("enter the correct position");
        p=userInput.nextInt();
 
    }
 
        board[p]=userLetter;
 
    System.out.println();
    System.out.println("After user turn");
    print(board);
    char c=winner(board);
    System.out.println();
 
if(c==userLetter){
System.out.println("user Win");
return;}
if(c=='T'){
System.out.println("TIE");
return;}    
smartComputerTurn(board,x+1);
}
 
private static void smartComputerTurn(char[] board,int x){
   // System.out.println("computer turns");
    int bestScore=-1000;
    int y=0;
    for(int i=1;i<=9;i++){
        if(board[i]==' '){
        board[i]=computerLetter;
        int score=minmax(board,0,false);
        board[i]=' ';
        if(score>bestScore)
        {
            bestScore=score;
            y=i;
        }
 
         }
    }
 
    board[y]=computerLetter;
 
   System.out.println();
     System.out.println("after computer turn");
    print(board);
   System.out.println();
    char c=winner(board);
        if(c==computerLetter){
    System.out.println("computer Win");
    return;}
    if(c=='T'){
    System.out.println("TIE");
    return;}    
 
 
        userTurn(board,x+1);
}
public static boolean  valid(int x){
    if(board[x]==' ')
    return true;
    return false;
}
public static int scores(char m){
    if(m==userLetter)
    return -1;
    if(m==computerLetter)
    return 1;
    if(m=='T')
    return 0;
    else
    return 0;
 
}
public static int minmax(char[] board,int depth,boolean max){
char result=winner(board);
 
 
if(result!=' '){
    int score=scores(result);
    return score;
 
}
// System.out.println(max+" "+depth);
// System.out.println("|||");
// print(board);
// System.out.println();
// System.out.println("|||");
    if(max){
        int bestScore=-10000;
        for(int i=1;i<=9;i++){
            if(board[i]==' '){
                board[i]=computerLetter;
                int score1=minmax(board,depth+1,false);
                board[i]=' ';
                if(score1>bestScore){
                    bestScore=score1;
 
                }
            }
        }
        return bestScore;
 
    }
    else{
         int bestScore=10000;
        for(int i=1;i<=9;i++){
            if(board[i]==' '){
                board[i]=userLetter;
                int score1=minmax(board,depth+1,true);
                board[i]=' ';
                if(score1<bestScore){
                    bestScore=score1;
 
                }
            }
        }
        return bestScore;
 
    }
}
 
}