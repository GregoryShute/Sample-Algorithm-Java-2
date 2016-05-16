import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Stack;


public class ColorGraph {
	public static int num_verticies = 0;
	public static String printout = "";
	public static String validColoring = "";
	public static int z = 0;
	private static char colorCode = 'r'; 
	private static char colorCodeOpposite = 'b';
	
	public ColorGraph() {
		
	}

	public static void main(String[] args) throws IOException {
			File nodePairs = new File(args[0]);
			BufferedReader br = new BufferedReader(new FileReader(nodePairs));
			BufferedReader br2 = new BufferedReader(new FileReader(nodePairs));
			String verticies;
			if((verticies = br2.readLine()) != null){
				num_verticies = Integer.parseInt(verticies);
			}
			br2.close();
			ColorGraph colorGraph = new ColorGraph();
			String nodeLine;
			int FileLength = 0;
			while((nodeLine = br.readLine()) != null){
				FileLength++;
			}
			//initialize an empty adjacency list object
			AdjList graphAdjList = new AdjList(nodePairs, FileLength);
			//build an adjacency list from the file using the adjlist object
			AdjNode[] myAdjList1 = graphAdjList.buildAdjList(nodePairs);
			graphAdjList.myAdjList = myAdjList1;
			//Do dfs on the adjlist object which is encapsulating the adjacency list
			DFSColor(graphAdjList);
			br.close();

	}
	
	
	
	
	public static void DFSColor(AdjList aL) throws FileNotFoundException, UnsupportedEncodingException{
		Stack<AdjNode> st = new Stack<AdjNode>();
		
		int start = -1;
		//find a start location that is not null. Not all 200000 slots may have something
		for(int i = 0; i < aL.myAdjList.length; i++){
			if(aL.myAdjList[i] == null){
			}else{
				
				start = i;
				break;
			}
		}
		
		if(DFS(aL, start) == true){
			System.out.println("hello");
			//there was a valid coloring
			PrintWriter writer2 = new PrintWriter("largegraph1.txt", "UTF-8");
			for(int i = 0; i < num_verticies-1; i++){
				if( aL.myAdjList[i] != null){
					String s = aL.myAdjList[i].node + " " + aL.myAdjList[i].color;
					writer2.println(s);
				}else{
				}
			}
			String s = aL.myAdjList[num_verticies-1].node + " " + aL.myAdjList[num_verticies-1].color;
			writer2.print(s);
			writer2.close();
			
		}else{
		}
			
		
	}
		
	public static boolean DFS(AdjList aL, int start) throws FileNotFoundException, UnsupportedEncodingException{
		
		//Loop through all vertices in the adjacency list that are not null and have not been visited
				for(int i= start; i < aL.myAdjList.length; i++){
					if(aL.myAdjList[i] == null || aL.myAdjList[i].visited == true){
						continue;
					}
					Stack<AdjNode> st = new Stack<AdjNode>();
					if(DFSVisit(aL, i, st) == false){
						//there was no valid coloring
						return false;
					}
					
				}
				
				//there was a valid coloring
				return true;
				
				
	}
	
	
	
	public static boolean DFSVisit(AdjList aL, int u, Stack<AdjNode> st) throws FileNotFoundException, UnsupportedEncodingException{

		aL.myAdjList[u].color = colorCode;
		aL.myAdjList[u].visited = true;
		aL.myAdjList[u].node = u;
		int n = u;
		//push the start vertex onto the stack
		st.push(aL.myAdjList[n]);
		while(!st.isEmpty()){
				
				
				int stackNodeVertex = st.peek().node;
				
				AdjNode stackNode = st.pop();
				AdjNode copyOfListNode = aL.myAdjList[stackNodeVertex];
				copyOfListNode.node = stackNodeVertex;
				aL.myAdjList[stackNodeVertex].visited = true;
				aL.myAdjList[stackNodeVertex].color = colorCode;
				
				if(stackNode.most_recent_node_in_path != -50){
					//if it did == -50 then the color was already set outside the loop
					if(aL.myAdjList[stackNode.most_recent_node_in_path].color == 'b'){
						aL.myAdjList[stackNodeVertex].color = 'r';
						colorCode = 'b';
					}else{
						aL.myAdjList[stackNodeVertex].color = 'b';
						colorCode = 'r';
					}
				}
				

				while(!aL.endCheck(copyOfListNode)){
					
				
					if(aL.myAdjList[copyOfListNode.nextNode.node].visited == false){
			
						AdjNode stackNodeVertexNode = copyOfListNode.nextNode;
						
						stackNodeVertexNode.previous_node = stackNode;
			
						stackNodeVertexNode.most_recent_node_in_path = stackNode.node;
						
						st.push(stackNodeVertexNode);
		
					}else{
						
						char clr = aL.myAdjList[copyOfListNode.nextNode.node].color;
						
						if(clr != colorCode){
							
							
							String cycle2 = "" + aL.myAdjList[copyOfListNode.nextNode.node].node + " " + stackNode.node;
							PrintWriter writer = new PrintWriter("largegraph2.txt", "UTF-8");
						
							while(stackNode.previous_node != null){
							
							
								if(stackNode.previous_node.node == aL.myAdjList[copyOfListNode.nextNode.node].node){
								
									writer.print(stackNode.previous_node.node);
								}else{
									writer.println(stackNode.previous_node.node);
								}
								if(stackNode.previous_node.node == aL.myAdjList[copyOfListNode.nextNode.node].node){
									break;
								}
								
								stackNode.previous_node = stackNode.previous_node.previous_node;
							}
							writer.close();
							printout = cycle2;
							return false;
						}
					}
					copyOfListNode = copyOfListNode.nextNode;
				}
				
				
		}
		//no cycle
		return true;
	}
		


	
	
	


}

