import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class AdjMatrix {

	public File myFile;
	public int fileLength = 0;
	public int[][] myMatrix;
	
	public AdjMatrix(File f, int FileLength) throws IOException {
		myFile = f;
		this.fileLength = FileLength;
		myMatrix = buildAdjMatrix(f);
	}
	
	public int[][] buildAdjMatrix(File f) throws IOException{
		//starts at 0 so I have to increment everything. Top row of matrix is wasted
		myMatrix = new int[fileLength+1][fileLength+1];
		BufferedReader br = new BufferedReader(new FileReader(f));
		String nodeLine = "";
		nodeLine = br.readLine(); // read over the file length
		while((nodeLine = br.readLine()) != null){
			String[] nodes = nodeLine.split(" ");
			int node1 = Integer.parseInt(nodes[0]);
			int node2 = Integer.parseInt(nodes[1]);
			myMatrix[node1][node2] = 1; //java arrays are init to zero so I'm ok in the other case
			myMatrix[node2][node1] = 1;
		}
		br.close();
		return myMatrix;
	}

}
