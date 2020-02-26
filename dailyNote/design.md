* [实现Trie(前缀树)](#1)
<h3>实现Trie(前缀树)</h3>
实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。

示例:

Trie trie = new Trie();

trie.insert("apple");
trie.search("apple");   // 返回 true
trie.search("app");     // 返回 false
trie.startsWith("app"); // 返回 true
trie.insert("app");   
trie.search("app");     // 返回 true
说明:

你可以假设所有的输入都是由小写字母 a-z 构成的。
保证所有输入均为非空字符串。
[一篇不错的前缀树讲解]((https://www.cnblogs.com/huangxincheng/archive/2012/11/25/2788268.html))
```Java
class Trie {
	//注释掉的是自己写的，用了递归，提交时最后一个一直没办法匹配
	//所以照着官方写了一遍
class TrieNode{
    private TrieNode[] childNodes;
    private char nodeVal;
    private boolean isEnd;
    public TrieNode(){
        //26个字母, 1个Trie节点有26个叉	
        this.childNodes = new TrieNode[26];
        this.isEnd = false;
    }
}
    private TrieNode root;
    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        //addNode(root,word);
        TrieNode node = root;
        for(int i = 0 ; i < word.length(); i++){
            int index = word.charAt(i) - 'a';
            if(node.childNodes[index] == null){
                node.childNodes[index] = new TrieNode();
                node.childNodes[index].nodeVal = word.charAt(i);
            }
            node = node.childNodes[index];
        }
        node.isEnd = true;
    }
    /*
    private void addNode(TrieNode root,String word){
        
        if(word.length() == 0) return;
        int index = word.charAt(0) -'a';
        if(root.childNodes[index] == null){
            root.childNodes[index] = new TrieNode();
            root.childNodes[index].nodeVal = word.charAt(0);
        }
        if(word.length() == 1) root.isEnd = true;
        addNode(root.childNodes[index],word.substring(1));
        
    }
    */
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
       // return searchTree(root,word);
        TrieNode node = searchPrefix(word);
        return node!=null&&node.isEnd;
    }
    /*
    private boolean searchTree(TrieNode root,String word){
        int len = word.length();
        //if(len == 0) return true;
        int index = word.charAt(0)-'a';
        if(root.childNodes[index] == null) return false;
        if(len == 1) return len==1&&root.isEnd;
        return searchTree(root.childNodes[index],word.substring(1));
    }
    */

    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
      //  return searchPrefix(root,prefix);
        return searchPrefix(prefix)!=null;
    }
    private TrieNode searchPrefix(String prefix){
              //  return searchPrefix(root,prefix);
      TrieNode node = root;
      for(int i = 0; i < prefix.length();i++){
          int index = prefix.charAt(i) - 'a';
          if(node.childNodes[index] == null) return null;
          node = node.childNodes[index];
      }
      return node;
    }
    /*
    private boolean searchPrefix(TrieNode root,String word){
        int len = word.length();
        int index = word.charAt(0)-'a';
        if(root.childNodes[index] == null) return false;
        if(len == 1) return len == 1;
        return searchPrefix(root.childNodes[index],word.substring(1));
    }
    */
}
```