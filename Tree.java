
public class Tree {

	private Node root;
	
	public void insert(int key, Object value) {
		this.root = this.insert(this.root, key, value);
	}
	
	private Node insert(Node root, int key, Object value) {
		if(root == null) {
			Node node = new Node();
			node.setKey(key);
			node.setValue(value);
			return node;
		
		} else {
			if(key < root.getKey()) {
				root.setLeft(this.insert(root.getLeft(), key, value));
			} else if(key > root.getKey()) {
				root.setRight(this.insert(root.getRight(), key, value));
			}
			return root;
		}
	}
	
	public Object get(int key) {
		return this.get(this.root, key);
	}
	
	private Object get(Node root, int key) {
		if(root != null) {
			if(key < root.getKey()) {
				return this.get(root.getLeft(), key);
			} else if(key > root.getKey()) {
				return this.get(root.getRight(), key);
			} else {
				return root.getValue();
			}			
		}
		return null;
	}
	
	public void delete(int key) {
		this.root = deleteNode(this.root, key);
	}
	
	private Node deleteNode(Node root, int key) {
		if (root == null) {
			return null;
		}
		
		if (key < root.getKey()) {
			root.setLeft(deleteNode(root.getLeft(), key));
		} else if (key > root.getKey()) {
			root.setRight(deleteNode(root.getRight(), key));
		} else {
			// Caso 1: Nó sem filhos
			if (root.getLeft() == null && root.getRight() == null) {
				root = null;
			}
			// Caso 2: Nó com um filho
			else if (root.getLeft() == null) {
				root = root.getRight();
			} else if (root.getRight() == null) {
				root = root.getLeft();
			}
			// Caso 3: Nó com dois filhos
			else {
				Node minValueNode = findMinValueNode(root.getRight());
				root.setKey(minValueNode.getKey());
				root.setValue(minValueNode.getValue());
				root.setRight(deleteNode(root.getRight(), minValueNode.getKey()));
			}
		}
		
		return root;
	}
	
	private Node findMinValueNode(Node root) {
		Node current = root;
		while (current.getLeft() != null) {
			current = current.getLeft();
		}
		return current;
	}
	
	private String print(Node root, int lvl) {
		String out = "";
		for(int i=0; i<lvl; i++) {
			out += "\t";
		}
		out += root.getKey() + ": " + (root.getValue() != null ? root.getValue() : "null");
		out += "\n";
		out += (root.getLeft() != null ? print(root.getLeft(), lvl + 1) : "");
		out += (root.getRight() != null ? print(root.getRight(), lvl + 1) : "");
		return out;
	}
	
	@Override
	public String toString() {
		return this.print(this.root, 0);
	}
	
	public static void main(String[] args) {
		Tree t = new Tree();
		
		t.insert(20, "20");
		t.insert(5, "5");
		t.insert(40, "40");
		t.insert(0, "0");
		t.insert(10, "10");
		t.insert(30, "30");
		t.insert(50, "50");
		
		System.out.println(t.get(500));
		
		System.out.println("Árvore original:");
		System.out.println(t.toString());
		
		t.delete(40);
		
		System.out.println("Árvore após excluir o nó 40:");
		System.out.println(t.toString());
	}
	
}
