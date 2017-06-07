import java.io.*;
import java.util.*;

public class mainScript {


   public static void main(String[] args) throws FileNotFoundException {
      boolean userInput = true;
      int LAST = 100;
      int FIRST = 100;
      
      hmm languageModel = new hmm();
      
      ArrayList<String> allTags = languageModel.tagArray();
      double[][] evalMatrix = new double[allTags.size()][allTags.size()];
      
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
               if (goldTags.size() != tags.size()){
                  throw new IllegalStateException("Mismatch in length between Gold Standard and Tagged.");
               }             
               for( i = 0; i < goldTags.size(); i++) { //This assumes we tag everything
                  if (allTags.contains(goldTags.get(i))){
                     evalMatrix[allTags.indexOf(tags.get(i))][allTags.indexOf(goldTags.get(i))]++;
                  }else {
                     evalMatrix[allTags.indexOf(tags.get(i))][allTags.indexOf("UNK")]++;
                  }

                  
                  /* questions += questions;
                  if(tags.get(i).equals(goldTags.get(i))) {
                     */
               } 
            }
         }
      }
   }
   public static ArrayList<String> getStringFromUser() {
      ArrayList<String> sentence = new ArrayList<String>();
      Scanner console = new Scanner(System.in);
      System.out.print("Sentence? ");
      String uSentence = console.nextLine();
      Scanner user = new Scanner(uSentence);
      while (!user.hasNext("./.")){
         sentence.add(user.next());
      }
      String trashCan = user.next(); 
      return sentence;
   }
   
   public static void reportInputString(ArrayList<String> tagged) {
   
   }
   
   public static ArrayList<String> getSentence(Scanner read) {
      ArrayList<String> newSent = new ArrayList<String>();
      while (!read.hasNext("./.")){
         newSent.add(read.next());
      }
      String trashCan = read.next(); 
      return newSent;
   }
   
   public static ArrayList<String> process(ArrayList<String> sentence) {
      ArrayList<String> goldTags = new ArrayList<String>();
         for (int i = 0; i < sentence.size(); i++){
            int splitter = sentence.get(i).lastIndexOf("/");
            String tag = sentence.get(i).substring(splitter);
            String word = sentence.get(i).substring(0,splitter);
            sentence.set(i, word);  // Puts untagged word back into the sentence ArrayList
            goldTags.add(tag);  // Puts tag into 
         }
      return goldTags;
   }
   
}