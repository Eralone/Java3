package Lesson6;


import org.jetbrains.annotations.NotNull;

public class Main {
//    public static void main(String[] args) {
//        int [] in = {1,3,4,2,5,6,1,5,7};
//        int[] sout = iteratMS(in);
//        for (int i = 0; i < sout.length; i++) {
//            System.out.println(sout[i]);
//        }

public static int[] iteratMS(@NotNull int[] ms){

    int b = 0;
    for (int i = ms.length - 1; i !=0 ; i--) {
        if(ms[i] == 4){
            int [] out = new int[ms.length-i-1];
            for (int j = i+1; j < ms.length; j++) {
                out[b]=ms[j];
                b++;
            }
            return out;
        }
    }
    throw new RuntimeException();
    }
}

class Bool{
    public static boolean bool (int[] one){
        for (int i = 0; i < one.length; i++) {
            if(one[i] == 1 || one[i] == 4){
                return false;
            }
        }
        return true;
    }
}


