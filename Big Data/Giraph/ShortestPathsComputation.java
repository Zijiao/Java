import org.apache.giraph.graph.BasicComputation;
import org.apache.giraph.conf.LongConfOption;
import org.apache.giraph.edge.Edge;
import org.apache.giraph.graph.Vertex;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import java.io.IOException;


public class ShortestPathsComputation extends BasicComputation<IntWritable, IntWritable, NullWritable, IntWritable> {   
    
    public static final LongConfOption SOURCE_ID =
        new LongConfOption("SimpleShortestPathsVertex.sourceId", 1, "The shortest paths id");
        // set up source vertex

    public boolean isSource(Vertex<IntWritable, ?, ?> vertex){
        return vertex.getId().get() == SOURCE_ID.get(getConf());
    }

    @Override
    public void compute(
        Vertex<IntWritable, IntWritable, NullWritable> vertex,
        Iterable<IntWritable> messages) throws IOException{
            
        if(getSuperstep() == 0){
            // initialization
            vertex.setValue(new IntWritable(Integer.MAX_VALUE));
        }
        
        int minDist = isSource(vertex) ? 0 : Integer.MAX_VALUE;
        for (IntWritable message : messages){
            // updata the minimum distance
            minDist = Math.min(minDist, message.get());
        }
        
        if (minDist < vertex.getValue().get()){
            // change current vertex's vertex data
            vertex.setValue(new IntWritable(minDist));
            
            for (Edge<IntWritable, NullWritable> edge : vertex.getEdges()){
                // propagate updated distance to its edges
                int distance = minDist + 1;
                sendMessage(edge.getTargetVertexId(), new IntWritable(distance));
            }
        }
        vertex.voteToHalt();
    }
}
