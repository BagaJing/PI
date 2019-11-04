# HASH
* [LRU缓存 (哈希表+设计)](#1)
* [复制带随即指针的链表](#2)
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

