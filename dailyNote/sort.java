package com.bagaJing.interview;
import java.util.Arrays;
class sort{
	public static void main(String[] args){
		int[] test = {3,5,6,7,4,11,10,8,1,12,9};
		System.out.println(Arrays.toString(test));
//		bubbleSort(test);
//		selectSort(test);
//		insertSort(test);
		quickSort(test,0,test.length-1);
		System.out.println(Arrays.toString(test));

	}
	//冒泡排序 O(n2)
	private static void bubbleSort(int[] nums){
		int LEN = nums.length;
		for(int i = 0 ; i < LEN ; i++){
			for(int j = LEN - 1 ; j >i ; j--){
				if(nums[j-1] > nums[j])	
					swap(nums,j-1,j);
			}
		}
	}
	//选择排序 
	private static void selectSort(int[] nums){
		int LEN = nums.length;
		for(int i = 0 ; i < LEN ; i++){
			int minIndex = i;
			for(int j = i+1 ; j < LEN; j++){
				if(nums[j]<nums[minIndex])
					minIndex = j;
			}
			//若minIndex 等于 i，则说明i为从此点到末尾的最小值
			if(minIndex != i)
				swap(nums,i,minIndex);
		}
	}
	//插入排序
	private static void insertSort(int[] nums){
		int LEN = nums.length;
		//默认第一个值在正确的位置才能进行插入操作
		for(int i = 1; i < LEN ; i++){
			int j = i;
			int target = nums[i];
			//当前面的值大于待插入的值时，后移为目标值腾出位置
			while(j>0&&target < nums[j-1]){
				nums[j] = nums[j-1];
				j--;
			}
			nums[j] = target;
		}
	}
	//快速排序 O(n*logn) 冒泡+二分+递归分治
	private static void quickSort(int[] nums,int left,int right){
		if(left >= right)
			return;
		int pivotPos = partition(nums,left,right);
		quickSort(nums,left,pivotPos-1);
		quickSort(nums,pivotPos+1,right);
	}
	private static int partition(int[] nums, int left, int right){
		int pivotKey = nums[left]; //left的值以作为基准值保存，所以只需在交换指针时先后覆盖
		//不用多设一个temp变量，减少空间使用和赋值运算
		while(left < right){
			//right指针从pivot右边招pivot小的的值
			while(left < right && nums[right]>=pivotKey)
				right--;
			//先把小的移到左边
			nums[left] = nums[right];
			//left指针从pivot左边找比pivotKey打的值
			while(left < right && nums[left]<=pivotKey)
				left++;
			//再把打的移到右边
			nums[right] = nums[left];
		}
		nums[left] = pivotKey; //把标准值放在中间
		//这样左边的值就小于pivot，右边的值都大于pivot
		//返回中间值，以便于划分子区间进行递归
		return left;
	}
	private static void swap(int[] nums,int i,int j){
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}
}
