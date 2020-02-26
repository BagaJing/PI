import java.util.*;

public class majong{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int[] state = new int[9], helpArray = new int[9];
		ArrayList<Integer> result = new ArrayList<>();
		for(int i = 0 ; i < 13 ; ++i){
			int num = sc.nextInt();
			state[num-1]++;
		}
		for(int i = 0; i < 9; i++){
			if(state[i]<4){
				int num = i + 1;
				System.arraycopy(state,0,helpArray,0,9);
				helpArray[i]++;
				//尝试所有可能　把可以胡牌的可能加入列表
				if(helper(helpArray,14,false)) result.add(num);
			}
		}
		if(result.isEmpty()) System.out.println(0);
		else{
			StringBuffer sb = new StringBuffer();
			sb.append(result.get(0));
			for(int i = 1; i <result.size(); ++i){
				sb.append(' ');
				sb.append(result.get(i));
			}
			System.out.println(sb.toString());
		}
	}

	private static boolean helper(int[] helpArray,int rest, boolean hasHead){
		//数组为空　这种可能　可以胡牌
		if(rest == 0) return true;
		//雀头
		if(!hasHead){
			for(int i = 0; i < 9 ; ++i){
				//尝试所有雀头的可能
				if(helpArray[i]>=2){
					helpArray[i] -=2;
					if(helper(helpArray,rest-2,true)) return true;
					helpArray[i] +=2;
				}
			}
			return false;
		} else {
			for(int i = 0; i < 9 ; i++){
				if(helpArray[i] > 0){
					//刻子的所有情况
					if(helpArray[i]>=3){
						helpArray[i] -=3;
						if(helper(helpArray,rest-3,true)) return true;
						helpArray[i] +=3;
					}
					//顺子的所有情况
					if((i + 2 < 9)&&(helpArray[i+1] > 0)&&(helpArray[i+2] > 0 )){
						helpArray[i]--;
						helpArray[i+1]--;
						helpArray[i+2]--;
						if(helper(helpArray,rest-3,true)) return true;
						helpArray[i]++;
						helpArray[i+1]++;
						helpArray[i+2]++;
					}

				}
			}
		}
		return false;	

	}
}
