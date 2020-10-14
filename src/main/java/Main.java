public class Main {
    //15 P7
    public static void main(String[] args) {
        int n = 15;
        int sum ;
        int begin;
        for(int i=1;;i++){
            begin=i;
            sum=0;
            for(int j=i;;j++){
                sum+=j;
                if(sum==n && begin!=j){
                    System.out.println(begin + "-" + j);
                }
                if(sum>n){
                    break;
                }
            }
            if (i>n){
                break;
            }
        }
    }
}
