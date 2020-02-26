import java.util.*;
class mic{
	public static void main(String[] args){
		new mic().helper();
	}

	public static void helper(){
		Scanner sc = new Scanner(System.in);
		int count = sc.nextInt();
		int a[] = new int[count];
		for(int i = 0 ; i < count; i++){
			a[i] = sc.nextInt();
		}
		sc.close();
		
		int result = 0;
		for(int j = 100 ; j >= 1; j--){ //遍历所有区间最小值的可能
			int sum = 0;
			int min = 101;
			for(int i = 0; i < count ; ++i){
				if(a[i]<j){
					result = Math.max(result,min*sum);
					//前一区间结束　重新计算
					sum = 0 ;
					min = 101;
				} else {
					sum += a[i];
					min = Math.min(min,a[i]);
				}
			}
		}
		System.out.println(result);

	}
	public static void helper_1(){
		Scanner sc = new Scanner(System.in);
		int count = sc.nextInt();
		int a[] = new int[count];
		for(int i = 0 ; i < count ; i++){
			int num = sc.nextInt();
			a[i] = num;
				
		}
		sc.close();
		int result = 0;
		Deque<Integer> stack = new LinkedList<>();
			







	}




}
