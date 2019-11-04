import java.util.*;
public class chan{
	public static void main(String[] args){
		new chan().helper();
	}
	public static void helper(){
		Scanner sc = new Scanner(System.in);
		int price = sc.nextInt();
		if(price > 1024){
			System.out.println(-1);
			return;
		}

		price = 1024 - price;
		int count_c = 0;
		
		count_c += price/64;
		price  %= 64;

		count_c += price/16;
		price %= 16;

		count_c += price/4;
		price %= 4;

		count_c+= price;

		System.out.println(count_c);
	}
}
