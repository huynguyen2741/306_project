import java.util.Comparator;
/**
 * The UserCompareName class takes in two separate User objects and compares their usernames to determine which
 * is higher. It returns a value to the calling class that indicates which User object entered had the higher 
 * username.
 * @author Ben Dahlhauser
*/
public class SortPersonName implements Comparator<Person> {
   
   /**
    * This method compares two User objects to determine which contains the higher username and then returns
    * to the user an integer that indicates which one did.
    * @param u1 The first User object that's entered
    * @param u2 The second User object that's entered
    * @return int An integer to indicate which has the higher username
   */
   public int compare(Person u1, Person u2){
      //Determines if first User object is same as second    
      if(u1.getFirstName().compareToIgnoreCase(u2.getFirstName()) == 0){
         return 0;
      }else{
         if(u1.getFirstName().compareToIgnoreCase(u2.getFirstName()) < 0){
            return -1;
         }else{
            return 1;
         }
      }  
   }
}