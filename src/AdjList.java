import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class AdjList {
	
	public File myFile;
	public int fileLength = 0;
	public AdjNode[] myAdjList;
	
	public AdjList(File f, int fileLength) throws IOException {
		myFile = f;
		this.fileLength = fileLength;
	}

	public AdjNode[] buildAdjList(File f) throws IOException{
		myAdjList = new AdjNode[2*fileLength+2]; // Order V
		
		BufferedReader br = new BufferedReader(new FileReader(f));
		String nodeLine = "";
		nodeLine = br.readLine(); // read over the file length
		while((nodeLine = br.readLine()) != null){
			String[] nodes = nodeLine.split(" ");
			int node1 = Integer.parseInt(nodes[0]);
			int node2 = Integer.parseInt(nodes[1]);
			if(myAdjList[node1] == null){
				//create new
				myAdjList[node1] = new AdjNode(-1);
				addNodeToList(node1, node2);
			}else if(myAdjList[node1] != null){
				//add
				addNodeToList(node1, node2);
			}
			if(myAdjList[node2] == null){
				//create new
				myAdjList[node2] = new AdjNode(-1);
				addNodeToList(node2, node1);
			}else if(myAdjList[node2] != null){
				//add
				addNodeToList(node2, node1);
			}
		}
		
		br.close();
		return myAdjList;
	}
	
	public void addNodeToList(int node1, int node2){
		AdjNode newNode = new AdjNode(node2);
		//add the next node to the end of the list
		myAdjList[node1].lastNode.nextNode = newNode;
		//the old last node is no longer the last node
		myAdjList[node1].lastNode.lastAdjNode = false;
		
		myAdjList[node1].lastNode = myAdjList[node1].lastNode.nextNode;
		myAdjList[node1].lastNode.lastAdjNode = true;
	}
	
	public boolean endCheck(AdjNode node){
		if(node.lastAdjNode == true){
			return true;
		}else{
			return false;
		}
	}
	
	
}
