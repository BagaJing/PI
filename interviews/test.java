class test{
	public void remove(int[] input){
		Arrays.sort(input);
		int len = input.length;
		int quick = 0, slow = 0;
		while(quick < len&&slow < quick){
			while(input[quick]!=input[slow]) quick++;
			fills(input,slow,quick-1,input[slow]);
			slow = quick;
			quick++;
		}
	}
	// length == 0;
	// length == 1000
	// Integer.MAX_VALUE MINVALUE
	// lin: -100+100
	// 
}