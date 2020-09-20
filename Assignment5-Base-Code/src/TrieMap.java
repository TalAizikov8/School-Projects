//Note: All of your TrieMapInterface method implementations must function recursively
//I have left the method signatures from my own solution, which may be useful hints in how to approach the problem
//You are free to change/remove/etc. any of the methods, as long as your class still supports the TrieMapInterface
import java.util.ArrayList;
public class TrieMap implements TrieMapInterface{
  TrieMapNode root;

  public TrieMap(){
    root = new TrieMapNode();
  }

  //Indirectly recursive method to meet definition of interface
  public void put(String key, String value){
    char first = key.charAt(0);
    if(root.getChildren().isEmpty() || !root.getChildren().containsKey(first)){
      TrieMapNode newNode = new TrieMapNode();
      root.getChildren().put(first, newNode);
      put(newNode, key.substring(1), value);
    }else {
      put(root.getChildren().get(first), key.substring(1), value);
    }
  }

  //Recursive method
  //Note: arguments are only a suggestion, you may use your own if you devise a different recursive solution
  public void put(TrieMapNode current, String curKey, String value){
    char first = curKey.charAt(0);
    if(curKey.length() == 1 && current.getChildren().containsKey(first)) {
      current.getChildren().get(first).setValue(value);
    } else if (curKey.length() == 1) {
      TrieMapNode newNode = new TrieMapNode();
      newNode.setValue(value);
      current.getChildren().put(first, newNode);
    } else if (current.getChildren().containsKey(first)) {
      put(current.getChildren().get(first), curKey.substring(1), value);
    } else {
      TrieMapNode newNode = new TrieMapNode();
      current.getChildren().put(first, newNode);
      this.put(newNode, curKey.substring(1), value);
    }

  }


  //Indirectly recursive method to meet definition of interface
  public String get(String key){
    char first = key.charAt(0);
    if (root.getChildren().containsKey(first)) {
      return get(root.getChildren().get(first), key.substring(1));
    } else {
      return null;
    }
  }

  //Recursive method
  //Note: arguments are only a suggestion, you may use your own if you devise a different recursive solution
  public String get(TrieMapNode current, String curKey){
    char first = curKey.charAt(0);
    if (curKey.length() == 1) {
      return current.getChildren().get(first).getValue();
    } else if (current.getChildren().containsKey(first)) {
      return get(current.getChildren().get(first), curKey.substring(1));
    } else {
      return null;
    }
  }

  //Indirectly recursive method to meet definition of interface
  public boolean containsKey(String key){
    if (key.length()==0){
      return true;
    }
    if (key.length()==1){
      if (root.getChildren().containsKey(key.charAt(0))&&root.getChildren().get(key.charAt(0)).getValue() != null){
          return true;
        } else {
        return false;
      }
    }
    if(root.getChildren().containsKey(key.charAt(0))){
      TrieMapNode child = root.getChildren().get(key.charAt(0));
      String curKey = key.substring(1);
      return containsKey(child, curKey);
    } else {
      return false;
    }

  }

  //Recursive method
  //Note: arguments are only a suggestion, you may use your own if you devise a different recursive solution
  public boolean containsKey(TrieMapNode current, String curKey){
    if (curKey.length()==1) {
      if (current.getChildren().containsKey(curKey.charAt(0)) && current.getChildren().get(curKey.charAt(0)).getValue() != null) {
        return true;
      } else {
        return false;
      }
    }else {
      if(current.getChildren().containsKey(curKey.charAt(0))){
        TrieMapNode child = current.getChildren().get(curKey.charAt(0));
        String key = curKey.substring(1);
        return containsKey(child, key);
      } else {
        return false;
      }
    }
  }
  ArrayList<String> keys = new ArrayList<String>();
  //Indirectly recursive method to meet definition of interface
  public ArrayList<String> getKeysForPrefix(String prefix){
    keys.clear();
    TrieMapNode prefixNode = findNode(root, prefix);
    if (prefixNode.getValue() != null && prefixNode.getValue().compareTo("0") == 0) {
      return new ArrayList<String>();
    }
    getSubtreeKeys(prefixNode);
    return keys;
  }

  //Recursive helper function to find node that matches a prefix
  //Note: only a suggestion, you may solve the problem is any recursive manner
  public TrieMapNode findNode(TrieMapNode current, String curKey){
    char first = curKey.charAt(0);
    if(curKey.length() == 1 && current.getChildren().containsKey(first)) {
      return current.getChildren().get(first);
    } else if (current.getChildren().containsKey(first)) {
      return findNode(current.getChildren().get(first), curKey.substring(1));
    } else {
      TrieMapNode newNode = new TrieMapNode();
      newNode.setValue("0");
      return newNode;
    }
  }
  //Recursive Helper Function To Get All Keys In A Node's Subtree
  public void getSubtreeKeys(TrieMapNode current){
    if (current.getValue() != null) {
      keys.add(current.getValue());
    }
    Object[] allWords = current.getChildren().keySet().toArray();
    for(int i = 0; i < current.getChildren().size(); i++) {
      getSubtreeKeys(current.getChildren().get(allWords[i]));
    }
  }
  public void print(){
    Object[] keys = root.getChildren().keySet().toArray();
    for(int i = 0; i < root.getChildren().size(); i++) {
      print(root.getChildren().get(keys[i]));
    }
  }
  //Recursive Method To Print Values In Tree
  public void print(TrieMapNode current){
    if(current.getValue() != null) {
      System.out.println(current.getValue());
    }
    if(current.getChildren().size() > 0) {
      Object[] keys = current.getChildren().keySet().toArray();
      for(int i = 0; i < current.getChildren().size(); i++) {
        print(current.getChildren().get(keys[i]));
      }
    }
  }
  public static void main(String[] args){
    //You can add some code in here to test out your TrieMap initially
    //The TrieMapTester includes a more detailed test
  }
}