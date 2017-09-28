import java.util.Arrays;

public class Test {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] m= new int[]{1,2,3};
        int[] n=new int[]{1,2,3};
        int[] mm=m;
        
        int x= Arrays.hashCode(m);
        int y= Arrays.hashCode(n);
        int z= Arrays.hashCode(mm);
        System.out.println(x+" "+y+" "+z);
        System.out.println(m.equals(n));
        System.out.println(m.equals(n));
    }

}