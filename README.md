Description:

My algorithm has three basic steps.
 
(1)The first step is to build an adjacency list out of the input file. The input file is a list of edges. I made an array of what I call adjacency list nodes to be the headers of the adjacency list. I made this array the size of the number of vertices specified at the top of the input file. The array is useful because it allows me to index into a vertex and update it in constant time. I loop through the input file and place the two vertices on that line into the adjacency list. 

(2) The second step is to push the first array adjacency list node vertex onto a stack and start a DFS through the graph. As I perform my depth first search I color each vertex in my array of adjacency list nodes a different color than its neighbors as I visit it (and I never un-color a previously visited node). Each vertex sets a pointer as it is pushed on the stack. This pointer is set to the node which will be traveled from to get to this vertex. This is very memory efficient because multiple nodes can share a link to the same path. In other words this is more memory efficient than having each vertex store a copy of its list of predecessors.
 
The caveat is when there are disconnected pieces of the graph. This can be dealt with in a very simple way. I just used a “for loop” to over all the vertices in the array of adjacency list headers. If a vertex has not already been visited be a previous DFS I start a new DFS from that vertex. 

(3) If a conflict occurs then the vertex just before where the conflict occurs looks at back at its predecessors list until it finds the vertex which created the conflict.  
	
**Correctness:**
	To show correctness I’ll explain why each of the 3 steps above are correct. 
	
Step one is correct because it take V time to make an array of vertex nodes of length V. I can then index to any of these lists which originate at an array slot in constant time. I can insert each of the V + E vertices in constant time.

We know DFS takes V + E time as this is in the book. The steps I do at each of these V + E steps is a constant number. The real question is how do I know depth first search will always detect a cycle? I think this is because depth first search visits and colors every node in the graph, differently than its neighbors so if there is an odd cycle it will eventually detect it. This can be thought of abstractly as doing a forward check but implemented through DFS.

Going back to the point of conflict requires traversing a linked list through at most V nodes. The question is: How do I that the path loops back to the point of conflict? The answer is that in DFS all previously visited nodes are stored on the path somewhere, so if I visited the node before and am trying to color it again, it must be on the path. 

Time Analysis:

Build Adjacency List: O(V + E)

Perform DFS: O(V+E)

Search Path Backwards From Conflict Vertex: O(V)

Total Time: O(V+E)
