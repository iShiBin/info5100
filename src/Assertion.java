
public class Assertion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int i = 0;
		int j = function(i);
		assert j!=i+1: "Wrong";
		
		long l = 342L;
	}
	
	public static int function(int i){
		return i+1;
	}

}
