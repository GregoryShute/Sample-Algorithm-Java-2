
public class PathNode {

	public int vertex;
	public PathNode nextPathNode = null;
	public PathNode lastPathn = this;
	
	public PathNode(int vertex) {
		this.vertex = vertex;
	}

	public int getVertex() {
		return vertex;
	}

	public void setVertex(int vertex) {
		this.vertex = vertex;
	}

}
