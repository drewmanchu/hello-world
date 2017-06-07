import java.util.*;

public class Matrix{
   private Map<String, Map<String, Double>> tranMaps;
   private Map<String, Map<String, Double>> emisMap;
   private Map<Integer, String> indexMap; // Exists to identify the POS selected by the Array y index.
   private ArrayList<String> sentence;
   private double[][] vector;
   private int edgeIndex = 0;

   public Matrix(ArrayList<String> toTag, Map<String, Map<String, Double>> transitions,
                 Map<String, Map<String, Double>> emissions){
      this.tranMaps = transitions;
      this.emisMap = emissions;
      this.sentence = toTag;
      this.sentence.add(0, "/.");   // Initializes the sequence probabilities with the start and end of sequence characters.
      this.sentence.add("/.");
      this.vector = new double[this.sentence.size() + 1][this.tranMaps.keySet().size()];   // Builds the matrix
      this.indexMap = new HashMap<Integer, String>();    // Builds the indexMap.
      int index = 0;
      for (String s : tranMaps.keySet()){    // Initializes the indexMap, saves the index of the final state.
         if (s.equals("/.")){
            this.edgeIndex = index;
         }
         this.indexMap.put(index, s);
         index++;
      }
      
      // Fills the matrix.
      for (int i = 0; i < this.vector.length; i++){
         for (int j = 0; i < this.vector[i].length; i++){
            if (i == 0){
               this.vector[i][j] = 1.0;
            }else {
               double max = 0.0;
               for (int k = 0; k < this.vector[i].length; j++){
                  if (i == this.vector.length){
                     max = Math.max( max, (this.vector[i - 1][k] * this.tranMaps.get(this.indexMap.get(k)).get(this.indexMap.get(j))));
                  }else {
                     max = Math.max( max, (this.vector[i - 1][k] * this.tranMaps.get(this.indexMap.get(k)).get(this.indexMap.get(j))
                                    * this.emisMap.get(this.sentence.get(i)).get(indexMap.get(j)))); 
                  }
               }
               this.vector[i][j] = max;
            }   
         }
      }
   }
   
   public ArrayList<String> getTagged(){
      ArrayList<String> tagged = new ArrayList<String>();
      Stack<Integer> path = backTrace();
      for (int i = 0; i < this.vector.length; i++){
         String mod = this.sentence.get(i);
         tagged.add(i, (mod + path.pop()));
      }
      return tagged;
   
   }
   
   // The private backTrace analysis. Called by getTagged.
   private Stack<Integer> backTrace(){
      Stack<Integer> traceStack = new Stack<Integer>();
      double backValue = this.vector[this.vector.length - 1][this.edgeIndex];
      int backIndex = 999999;
      for (int k = 0; k < this.vector[this.vector.length - 1].length; k++){
         if (backValue == this.vector[this.vector.length - 1][k]){
            traceStack.push(new Integer(k));
         }
      }   
      for (int i = this.vector.length - 2; i > 0; i--){
         backValue = this.vector[i][backIndex] / this.emisMap.get(this.sentence.get(i)).get(indexMap.get(backIndex));
         for (int j = 0; j < this.vector[i].length; j++){
            if (backValue == this.vector[i][j]){
               traceStack.push(new Integer(j));
            }
         }   

      }
      return traceStack;
      
   }

   
   
   
}