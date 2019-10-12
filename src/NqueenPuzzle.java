
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class NqueenPuzzle {
    static int SizeOfBoard;
    static Random randPos[];
    static int[]sol;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the size of board");
        SizeOfBoard=in.nextInt();

        if(SizeOfBoard<=8 || SizeOfBoard>=13)
        {
            System.out.print("Please enter a number between 8 and 13");

            System.exit(0);

        }
        else
        {
            sol=new int[SizeOfBoard];
            randPos = new Random[SizeOfBoard];
            for(int i=0;i<SizeOfBoard;i++) {
                Random rand = new Random();
                randPos[i] = rand;
                sol[i] = Random_Value(randPos[i]);

            }
        }
        Hill_Climbing(sol);
    }

    public static int Random_Value(Random rand)
    {

        return rand.nextInt(SizeOfBoard);
    }


    private static int Heuristic(int[] arr) {
        int Value=0;
        for(int i=0;i<SizeOfBoard;i++){
            for(int n=i+1;n<SizeOfBoard;n++){
                if(checkSteps(arr,i,n)){
                    Value++;
                }
            }
        }
        return Value;
    }

    public static boolean checkSteps(int[] array, int prevStep,int nextStep) {
        return array[prevStep] == array[nextStep] || (Math.abs(nextStep - prevStep) ==Math.abs(array[prevStep]-array[nextStep])) ;
    }
    public static void Hill_Climbing(int[] arr)

    {

        int heuritsic_value= Heuristic(arr);

        if(heuritsic_value ==0){

            for(int i=0;i<SizeOfBoard;i++){
                for(int j=0;j<SizeOfBoard;j++){
                    if(arr[i]!=j){
                        System.out.print("0 ");
                    }
                    else
                    {
                        System.out.print("1 ");
                    }
                }
                System.out.println();
            }
            System.exit(0);
        }else{
            int Row =SizeOfBoard+1;
            int Column =SizeOfBoard+1;
            int previous_heuristic=heuritsic_value;
            for(int r=0;r<SizeOfBoard;r++){
                int previous_column =arr[r];
                for(int c=0;c<SizeOfBoard;c++){
                    if(previous_column!=c){
                        arr[r]=c;
                        int next_heuristic =Heuristic(arr);
                        if(next_heuristic<previous_heuristic){
                            Row=r;
                            previous_heuristic =next_heuristic;
                            Column=c;

                        }
                    }
                }
                arr[r]=previous_column;
            }

            if(Row!=(SizeOfBoard+1) && Column!=(SizeOfBoard+1)){
                arr[Row] = Column;
                Hill_Climbing(arr);

            }else{

                for(int i=0;i<SizeOfBoard;i++) {
                    sol[i] = Random_Value(randPos[i]);
                }

                Hill_Climbing(sol);
            }

        }
    }


}