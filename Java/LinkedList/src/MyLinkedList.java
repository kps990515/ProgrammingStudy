
public class MyLinkedList {
	private Node header;
	private int size;
	private Node tail;
	
	public MyLinkedList() {
		header = new Node(null);
		size=0;
	}
	
	private class Node{
		private Object data;
		private Node nextNode;
		
		Node(Object data){
			this.data = data;
			this.nextNode = null;
		}
	}
	
	public void addFirst(Object data) {
		Node newNode = new Node(data);
		newNode.nextNode = header.nextNode;
		header.nextNode = newNode;
		size++;
	}
	
	public void setLast(Node lastnode) {
		lastnode.nextNode=null;
	}
	
}
