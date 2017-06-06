import java.util.*;
import java.io.*;

public class hmm {
   private Map<String, Map<String, Double>> tranMaps;
   private Map<String, Double> emisMap;
   
   TransitionDistributions DistsPOSPOS = new TransitionDistributions(); 
      //read DistsPOSPOS as "Distributions over POS given a POS"
      
   EmissionsDistributions DistsWordPOS = new EmissionsDistributions(); 
      //read DistsWordPOS as "Distributions over Words given a POS"

   public ArrayList<String> tag(ArrayList<String> sentence) {
      ArrayList<String> tags = new ArrayList<String>();
      matrix hmmMatrix = new matrix(sentence, this.tranMaps, this.emisMap);
      tags = hmmMatrix.getTagged();
      return tags;
   }    

}