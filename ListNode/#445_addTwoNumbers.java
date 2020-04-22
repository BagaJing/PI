/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int len1 = countLen(l1), len2 = countLen(l2);
        int lLen = len1 > len2 ? len1 : len2;
        int sLen = len1 > len2 ? len2 : len1;
        ListNode[] lList = new ListNode[lLen];
        ListNode[] sList = new ListNode[sLen];
        ListNode lIter = len1 > len2? l1 : l2;
        ListNode sIter = len1 > len2? l2 : l1;
        int index = 0;
        while(lIter!=null&&index < lList.length){
            lList[index] = lIter;
            lIter = lIter.next;
            index++;
        }
        index = 0;
        while(sIter!=null&&index < sList.length){
            sList[index] = sIter;
            sIter = sIter.next;
            index++;
        }
        int range = lLen - sLen;
        index = sList.length-1;
        while(index>=0){
            lList[index+range].val += sList[index].val;
            if(lList[index+range].val >= 10){
                if(index+range >0){
                    lList[index+range].val %= 10;
                    lList[index+range-1].val += 1;
                }
            }
            index--;
        }
        while(range > 1&&lList[range-1].val>=10){
            lList[range-1].val %= 10;
            lList[range-2].val += 1;
            range--;
        }
        if(lList[0].val>=10){
            lList[0].val %= 10;
            ListNode head = new ListNode(1);
            head.next = lList[0];
            return head;
        }else{
            return lList[0];
        }
        
    }
    private int countLen(ListNode l){
        ListNode iter = l;
        int len = 0;
        while(iter!=null){
            len++;
            iter = iter.next;
        }
        return len;
    }
}