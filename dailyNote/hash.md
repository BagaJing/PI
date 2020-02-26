# HASH
* [LRU缓存 (哈希表+设计)](#1)
* [复制带随即指针的链表](#2)
* [和为k的子数组](#3)
* [347.前 K 个高频元素](#4)
* [288. 单词的唯一缩写](#单词的唯一缩写)
* [380. 常数时间插入、删除和获取随机元素](#常数时间插入、删除和获取随机元素)
<h3 id="1">1.LRU缓存 (哈希表 设计)</h3>

Saturday, 02. November 2019 12:32PM 

运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。

获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。

写入数据 put(key, value) - 如果密钥不存在，则写入其数据值。当缓存容量达到上限时，它应该在写入新数据之前删除最近最少使用的数据值，从而为新的数据值留出空间。

进阶:

你是否可以在 O(1) 时间复杂度内完成这两种操作？

示例:

LRUCache cache = new LRUCache( 2 /* 缓存容量 */ );
```
cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // 返回  1
cache.put(3, 3);    // 该操作会使得密钥 2 作废
cache.get(2);       // 返回 -1 (未找到)
cache.put(4, 4);    // 该操作会使得密钥 1 作废
cache.get(1);       // 返回 -1 (未找到)
cache.get(3);       // 返回  3
cache.get(4);       // 返回  4
```

来源：力扣（LeetCode）

链接：https://leetcode-cn.com/problems/lru-cache

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
```Java
public class LRUCache {
    //哈希+双链表 相当于LinkedHashMap的简单实现
     class dNode{
        int key;
        int value;
        dNode prev;
        dNode next;
    }
    //前后两个哨兵
    private dNode dummyHead,dummyTail;
    //把节点加在表头
    private void addNode(dNode node){
        node.prev = dummyHead;
        node.next = dummyHead.next;
        
        dummyHead.next.prev = node;
        dummyHead.next = node;
    }
    private void removeNode(dNode node){
        dNode prev = node.prev;
        dNode next = node.next;
        
        prev.next = next;
        next.prev = prev;
    }
    //把一个存在的点移到表头
    private void moveToHead(dNode node){
        removeNode(node);
        addNode(node);
    }
    //弹出表尾
    private dNode popTail(){
        dNode tail = dummyTail.prev;
        removeNode(tail);
        return tail;
    }
    private int max;
    private Map<Integer,dNode> map;
    
    public LRUCache(int capacity) {
        this.max = capacity;
        this.map = new HashMap<>();
        dummyHead = new dNode();
        dummyTail = new dNode();
        dummyHead.next = dummyTail;
        dummyTail.prev = dummyHead;
    }
    public int get(int key) {
        dNode res = this.map.getOrDefault(key,null);
        if(res == null) return -1;
        moveToHead(res);
        return res.value;
    }
    
    public void put(int key, int value) {
        if(!this.map.containsKey(key)){
            dNode newNode = new dNode();
            newNode.key = key;
            newNode.value = value;
            addNode(newNode);
            this.map.put(key,newNode);
            if(this.map.size()>this.max) {
                dNode old = popTail();
                this.map.remove(old.key);
            }
        }else{
            dNode node = this.map.get(key);
            node.value = value;
            moveToHead(node);
            this.map.put(key,node);
        }
    }
}


//用LinkedHashMap
class LRUCache {
    private Map<Integer,Integer> linkedMap;
    private int max;
    public LRUCache(int capacity) {
        this.linkedMap = new LinkedHashMap(capacity,0.75F,true);
        this.max = capacity;
    }
    
    public int get(int key) {
        if(this.linkedMap.containsKey(key)) return this.linkedMap.get(key);
        else return -1;
    }
    
    public void put(int key, int value) {
        this.linkedMap.put(key,value);
        if(removeEldestEntry()){
            Map.Entry eldest = (Map.Entry)this.linkedMap.entrySet().iterator().next();
            this.linkedMap.remove(eldest.getKey());
        }
    }
    
    private boolean removeEldestEntry(){
        return this.linkedMap.size() > this.max;
    }
}
```
<h3 id="2">2.复制带随即指针的链表</h3>

Saturday, 02. November 2019 12:33PM 

给定一个链表，每个节点包含一个额外增加的随机指针，该指针可以指向链表中的任何节点或空节点。

要求返回这个链表的深拷贝。 


示例：

![linkedlist](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/02/23/1470150906153-2yxeznm.png)

输入：
{"$id":"1","next":{"$id":"2","next":null,"random":{"$ref":"2"},"val":2},"random":{"$ref":"2"},"val":1}

解释：
节点 1 的值是 1，它的下一个指针和随机指针都指向节点 2 。

节点 2 的值是 2，它的下一个指针指向 null，随机指针指向它自己。
 
提示：

你必须返回给定头的拷贝作为对克隆列表的引用。

来源：力扣（LeetCode）

链接：https://leetcode-cn.com/problems/copy-list-with-random-pointer

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
```Java
/*
// Definition for a Node.
class Node {
    public int val;
    public Node next;
    public Node random;

    public Node() {}

    public Node(int _val,Node _next,Node _random) {
        val = _val;
        next = _next;
        random = _random;
    }
};
*/
//哈希表
class Solution {
    public Node copyRandomList(Node head) {
        HashSet<Node> olds = new HashSet<>();
        Node iter = head;
        while(iter!=null){
            olds.add(iter);
            iter = iter.next;
        }
        //<老点,新点>
        Map<Node,Node> map = new HashMap<>();
        Node dummyHead = new Node(-1,null,null);
        //复制点
        for(Node old : olds){
            map.put(old,new Node(old.val,null,null));
        }
       // System.out.println(map);
        //复制边
        for(Node old : olds){
            Node newNode = map.get(old);
            newNode.next = map.get(old.next);
            newNode.random = map.get(old.random);
        }
        return map.get(head);
    }
}
```
<h3 id="3">和为k的子数组</h3>
给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。

示例 1 :

输入:nums = [1,1,1], k = 2
输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
说明 :

数组的长度为 [1, 20,000]。
数组中元素的范围是 [-1000, 1000] ，且整数 k 的范围是 [-1e7, 1e7]。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/subarray-sum-equals-k
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
```Java
class Solution {
    //哈希表
    public int subarraySum(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap<>();
        //当sum-k和为0时，说明当前序列和为k
        //初始化
        map.put(0,1);
        int sum = 0, count = 0;
        for(int i = 0 ; i < nums.length ; i++){
            sum += nums[i];
            //若表中有sum-k的值,说明从此坐标到那个坐标的子序列和为k (sum2 - sum1 = k)
            if(map.containsKey(sum-k))
                count += map.get(sum-k);
            map.put(sum,map.getOrDefault(sum,0)+1);
            
        }
        return count;
    }
}
```
<h3 id="4">前 K 个高频元素</h3>
给定一个非空的整数数组，返回其中出现频率前 k 高的元素。

示例 1:

输入: nums = [1,1,1,2,2,3], k = 2
输出: [1,2]
示例 2:

输入: nums = [1], k = 1
输出: [1]
说明：

你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
```Java
// 347 
// 利用优先队列与重载compare函数
class Solution {
    class Pair{
        int key;
        int value;
        public Pair(Integer key, Integer value){
            this.key = key;
            this.value = value;
        }
        public Integer getKey(){
            return this.key;
        }
        public Integer getValue(){
            return this.value;
        }
    }
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap<>();
        for(Integer i : nums)
            map.put(i,map.getOrDefault(i,0)+1);
        PriorityQueue<Pair> heap = new PriorityQueue<>(new Comparator<Pair>(){
            @Override
            public int compare(Pair o1,Pair o2){
                return o1.getValue() - o2.getValue();
            }
        });
        Set<Integer> set = map.keySet();
        for(Integer i : set){
            heap.add(new Pair(i,map.get(i)));
            if(heap.size() > k) heap.poll();
        }
        List<Integer> res = new ArrayList<>();
        for(Pair pair : heap){
            res.add(pair.getKey());
        }
        return res;
    }
}
```
<h3 id ="单词的唯一缩写">288.单词的唯一缩写</h3>
一个单词的缩写需要遵循 <起始字母><中间字母数><结尾字母> 这样的格式。

以下是一些单词缩写的范例：
```

a) it                      --> it    (没有缩写)

     1
     ↓
b) d|o|g                   --> d1g

              1    1  1
     1---5----0----5--8
     ↓   ↓    ↓    ↓  ↓    
c) i|nternationalizatio|n  --> i18n

              1
     1---5----0
     ↓   ↓    ↓
d) l|ocalizatio|n          --> l10n
```
假设你有一个字典和一个单词，请你判断该单词的缩写在这本字典中是否唯一。若单词的缩写在字典中没有任何 其他 单词与其缩写相同，则被称为单词的唯一缩写。

示例：

给定 dictionary = [ "deer", "door", "cake", "card" ]

isUnique("dear") -> false
isUnique("cart") -> true
isUnique("cane") -> false
isUnique("make") -> true
```Java
class ValidWordAbbr {
    private Map<String,Set<String>> map;
    public ValidWordAbbr(String[] dictionary) {
        map = new HashMap<>();
        for(String str : dictionary){
            String abbr = abbr(str);
            if(map.containsKey(abbr))
                map.get(abbr).add(str);
            else{
                Set<String> set = new HashSet<>();
                set.add(str);
                map.put(abbr,set);
            }
        }
        
    }
    
    public boolean isUnique(String word) {
        String abbr = abbr(word);
        if(map.containsKey(abbr)){
          Set<String> set = map.get(abbr);
            if(set.contains(word))
                return set.size() == 1;
            else 
                return false;
        } else return true;
    }
    
    public String abbr(String str){
        if(str.length()<=2) return str;
        return str.substring(0,1)+String.valueOf(str.length()-2)+str.substring(str.length()-1);
    }
}

/**
 * Your ValidWordAbbr object will be instantiated and called as such:
 * ValidWordAbbr obj = new ValidWordAbbr(dictionary);
 * boolean param_1 = obj.isUnique(word);
 */
```
<h3 id ="常数时间插入、删除和获取随机元素">380.常数时间插入、删除和获取随机元素</h3>
设计一个支持在平均 时间复杂度 O(1) 下，执行以下操作的数据结构。

insert(val)：当元素 val 不存在时，向集合中插入该项。
remove(val)：元素 val 存在时，从集合中移除该项。
getRandom：随机返回现有集合中的一项。每个元素应该有相同的概率被返回。
示例 :

// 初始化一个空的集合。
RandomizedSet randomSet = new RandomizedSet();

// 向集合中插入 1 。返回 true 表示 1 被成功地插入。
randomSet.insert(1);

// 返回 false ，表示集合中不存在 2 。
randomSet.remove(2);

// 向集合中插入 2 。返回 true 。集合现在包含 [1,2] 。
randomSet.insert(2);

// getRandom 应随机返回 1 或 2 。
randomSet.getRandom();

// 从集合中移除 1 ，返回 true 。集合现在包含 [2] 。
randomSet.remove(1);

// 2 已在集合中，所以返回 false 。
randomSet.insert(2);

// 由于 2 是集合中唯一的数字，getRandom 总是返回 2 。
randomSet.getRandom();
```Java
class RandomizedSet {
        //val   index
    Map<Integer,Integer> map;
        //val
    List<Integer> list;
    Random rand;
    /** Initialize your data structure here. */
    public RandomizedSet() {
        map = new HashMap<>();
        list = new ArrayList<>();
        rand = new Random();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if(map.containsKey(val)) return false;
        
        map.put(val,list.size());
        list.add(val);
        //list.add(list.size(),val);
        
        //System.out.println(list);
        //System.out.println(map);
        return true;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if(!map.containsKey(val)) return false;
        int idx = map.get(val);
        int last = list.get(list.size()-1);
        list.set(idx,last);
        list.remove(list.size()-1);
        map.put(last,idx);
        map.remove(val);
        return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        return list.get(rand.nextInt(list.size()));
        
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
```
