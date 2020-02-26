# linkedlist(链表)
[链表随机节点](#1)
<h3 id="1">链表随机节点</h3>
定一个单链表，随机选择链表的一个节点，并返回相应的节点值。保证每个节点被选的概率一样。

进阶:
如果链表十分大且长度未知，如何解决这个问题？你能否使用常数级空间复杂度实现？

示例:
```
// 初始化一个单链表 [1,2,3].
ListNode head = new ListNode(1);
head.next = new ListNode(2);
head.next.next = new ListNode(3);
Solution solution = new Solution(head);

// getRandom()方法应随机返回1,2,3中的一个，保证每个元素被返回的概率相等。
solution.getRandom();
```
来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/linked-list-random-node
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
```Java
//哈希表做法
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {

    /** @param head The linked list's head.
        Note that the head is guaranteed to be not null, so it contains at least one node. */
        //一个坐标对一个节点,再用random每回从范围里取一个int,再从哈希表中get结点
        //由于哈希表的get复杂度为常数级别,所以getRandom可以达到常数级
    private Map<Integer,ListNode> map;
    private int max;
    private Random ran;
    public Solution(ListNode head) {
        this.map = new HashMap<>();
        this.max = 0;
        ListNode iter = head;
        while(iter != null){
            this.map.put(this.max,iter);
            this.max++;
            iter = iter.next;
        }
        this.ran = new Random();
        
    }
    
    /** Returns a random node's value. */
    public int getRandom() {
        return this.map.get(this.ran.nextInt(this.max)).val;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(head);
 * int param_1 = obj.getRandom();
 */
```