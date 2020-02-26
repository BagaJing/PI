import java.util.*;
public class stick{
	public static void main(String[] args){
		new stick().helper();
	}

	public static void helper(){
		Scanner sc = new Scanner(System.in);
        
            int count = sc.nextInt();
			long [][] a = new long[count][2];	
			for(int i = 0; i < count ; ++i){
				a[i][0] = sc.nextLong();
				a[i][1] = sc.nextLong();
			}
			stickCheck(a);
        

		sc.close();
	}
	public static void stickCheck(long[][] a){
		List<Long> arr = new ArrayList<>();
		for(int i = 0 ; i < a.length ; ++i){
			if(a[i][0] == 1){
				arr.add(a[i][1]);
			} else {
				arr.remove(a[i][1]);				
			}

			if(isValid(arr)){
				System.out.println("Yes");
			} else{
				System.out.println("No");
			}
		}
	}
	public static boolean isValid(List<Long> arr){
		int len = arr.size();
		for(int i = 0 ; i < len ; i++){
			long sample = arr.remove(i);

			int sum = 0;
			for(Long iter : arr){
				sum += iter;
			}
			if(sum <= sample){
				arr.add(i,sample);
				return false;
			}
			arr.add(i,sample);
		}
		return true;
	}
}
