public class Course{
   private String courseID;
   private String title;
   private String dateStart;
   private String dateEnd;
   private String timeStart;
   private String timeEnd;
   public Course(String courseID){
      this.courseID = courseID;
   }
   public Course(String courseID, String title, String dateStart, String dateEnd, String timeStart, String timeEnd){
      
      if(courseID.length() != 8){
         throw new IllegalArgumentException("The input must be 8 character");
      }
      for(int x = 0; x < courseID.length()-4; x++){
         if(!Character.isLetter(courseID.charAt(x))){
            throw new IllegalArgumentException("The the first 4 character must be letter");
         }
      }
      for(int x = 4; x < courseID.length(); x++){
         if(!Character.isDigit(courseID.charAt(x))){
            throw new IllegalArgumentException("The the last 4 character must be number");
         }
      }
      this.courseID = courseID;
      this.title = title;
      this.dateStart = dateStart;
      this.dateEnd = dateEnd;
      this.timeStart = timeStart;
      this.timeEnd = timeEnd;
   }
   public String getCourseID(){
      return courseID;
   }
   public String getTitle(){
      return title;
   }
   public String getDateStart(){
      return dateStart;
   }
   public String getDateEnd(){
      return dateEnd;
   }
   public String getTimeStart(){
      return timeStart;
   }
   public String getTimeEnd(){
      return timeEnd;
   }
   public void setTitle(String title){
      this.title = title;
   }
   public void setDateStart(String dateStart){
      this.dateStart = dateStart;
   }
   public void setDateEnd(String dateEnd){
      this.dateEnd = dateEnd;
   }
   public void setTimeStart(String timeStart){
      this.timeStart = timeStart;
   }
   public void setTimeEnd(String timeEnd){
      this.timeEnd = timeEnd;
   }
   public void setCourseID(String courseID){
      if(courseID.length() != 8){
         throw new IllegalArgumentException("The input must be 8 character");
      }
      for(int x = 0; x < courseID.length()-4; x++){
         if(!Character.isLetter(courseID.charAt(x))){
            throw new IllegalArgumentException("The the first 4 character must be letter");
         }
      }
      for(int x = 3; x < courseID.length(); x++){
         if(!Character.isDigit(courseID.charAt(x))){
            throw new IllegalArgumentException("The the first 4 character must be number");
         }
      }
      this.courseID = courseID;
   }
 
   
   public String toString(){
      return "Course ID: " + this.getCourseID() + 
             "\nTitle: " + this.getTitle() + 
             "\nDate start: " + this.getDateStart() +
             "\nDate end: " + this.getDateEnd() +
             "\nTime start: " + this.getTimeStart() + 
             "\nTime end: " + this.getTimeEnd() + "\n";  
   }
   
}

