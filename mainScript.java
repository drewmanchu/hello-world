import java.io.*;
import java.util.*;

public class mainScript {


   public static void main(String[] args) throws FileNotFoundException {
      boolean userInput = true;
      int LAST = 100;
      int FIRST = 100;
      
      hmm languageModel = new hmm();
      
      
      if(userInput) {
         ArrayList<String> input = getStringFromUser();      
         languageModel.tag(input);
         reportInputString(input);
      } else {
         
         for(int i = FIRST; i <= LAST; i++ ) {
            File inFile = new File("test/childhood (" + i + ")");
            Scanner read = new Scanner(inFile);
            
            int questions = 0;
            int correct = 0;
            while(read.hasNext()) {
               ArrayList<String> sentence = getSentence(read);
               ArrayList<String> goldTags = process(sentence); //remove tags from sentence.
               //We will try reading sentence at a time. Sometimes we won't read proper sentences. ok.
               //The sentence in the array will still have pos tags.
               ArrayList<String> tags = languageModel.tag(sentence);
               for( i = 0; i < goldTags.size(); i++) { //This assumes we tag everything
                  questions += questions;
                  if(tags.get(i).equals(goldTags.get(i))) {
                     correct += correct;
                  }
               }  
            }
         }
      }
      
      
     
   }
   
   public static ArrayList<String> getStringFromUser() {
      ArrayList<String> sentence = new ArrayList<String>();
   
      return sentence;
   }
   
   public static void reportInputString(ArrayList<String> tagged) {
   
   }
   
   public static ArrayList<String> getSentence(Scanner read) {
      ArrayList<String> newSent = new ArrayList<String>();
      
      return newSent;
   }
   
   public static ArrayList<String> process(ArrayList<String> sentence) {
      ArrayList<String> goldTags = new ArrayList<String>();
   
      return goldTags;
   }
   
}