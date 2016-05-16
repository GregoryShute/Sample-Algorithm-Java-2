
public class AdjNode {

	public AdjNode nextNode;
	public boolean inList = false;
	public AdjNode lastNode;
	public int node = -1; //value of this node
	public char color = 'w';
	public boolean visited = false;
	public boolean lastAdjNode = false;
	public String path = "";
	public int most_recent_node_in_path = -50;
	public AdjNode previous_node = null;
	
	//encapsulates varies items we want to keep track of about a vertex such as what color it is, from what vertex was traveled to get to this node. etc.
	public AdjNode(int node) {
		//set the value of this node
		this.node = node;
		lastNode = this;
		nextNode = this;
	}

	public char getColor() {
		return color;
	}

	public void setColor(char color) {
		this.color = color;
	}

	public AdjNode getNextNode() {
		return nextNode;
	}

	public void setNextNode(AdjNode nextNode) {
		this.nextNode = nextNode;
	}

	public boolean isInList() {
		return inList;
	}

	public void setInList(boolean inList) {
		this.inList = inList;
	}

	public AdjNode getLastNode() {
		return lastNode;
	}

	public void setLastNode(AdjNode lastNode) {
		this.lastNode = lastNode;
	}

}
