import java.util.*;
import java.io.*;

public class hmm {
   private Map<String, Map<String, Double>> tranMaps;
   private Map<String, Map<String, Double>> emisMap;
   
   TransitionDistributions DistsPOSPOS = new TransitionDistributions(); 
      //read DistsPOSPOS as "Distributions over POS given a POS"
      
   EmissionsDistributions DistsWordPOS = new EmissionsDistributions(); 
      //read DistsWordPOS as "Distributions over Words given a POS"

   // Returns all POS tags. Important for confusion matrix interpretation.
   public ArrayList<String> tagArray(){
      ArrayList<String> array = new ArrayList<String>();
      for (String tag : this.tranMaps.keySet()){
         array.add(tag);
      }
      array.add("UNK");
      return array;
   }
   
   public ArrayList<String> tag(ArrayList<String> sentence) {
      ArrayList<String> tags = new ArrayList<String>();
      Matrix hmmMatrix = new Matrix(sentence, this.tranMaps, this.emisMap);
      tags = hmmMatrix.getTagged();
      return tags;
   }
}