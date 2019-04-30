

public class Person{
   private String firstName;
   private String lastName;
   private String phoneNumber;
   private String address;
   private String email;
   private String userName;
   private String password;
   private boolean isMaintenance;
  
   public static int numPerson;
   
   public Person(){
      
   }
   
   public Person (String firstName, String lastName, String phoneNumber, String address, String email,String userName,String password, boolean isMaintenance){
      int symbolCount = 0;
      char VALID_PHONE_CHAR = '-';
      char VALID_USERNAME_CHAR_1 = 'S';
      char VALID_USERNAME_CHAR_2 = 'E';
      boolean hasLower = false;
      boolean hasUpper = false;
      boolean hasNumber = false;
      boolean hasSpecial = false;
      //Validate Name
      if(firstName == null || firstName.equals("")){
         throw new IllegalArgumentException("--Can't be blank--");
      }
      if(lastName == null || lastName.equals("")){
         throw new IllegalArgumentException("--Can't be blank--");
      }
      //Validate Phone Number
      if(phoneNumber == null || phoneNumber.equals("")){
         throw new IllegalArgumentException("--Can't be blank--");
      }
      if(phoneNumber.length()!=12){
         throw new IllegalArgumentException("--Not in the right format, or not enough digits--");
      }
      for(int x = 0; x < phoneNumber.length(); x++){
         if(!Character.isDigit(phoneNumber.charAt(x)) && !(phoneNumber.charAt(x)==VALID_PHONE_CHAR)){
            throw new IllegalArgumentException("--Has to contain only numbers and '-' symbols--");
         }   
      }
      if(phoneNumber.charAt(3)!=VALID_PHONE_CHAR || phoneNumber.charAt(7)!=VALID_PHONE_CHAR){
         throw new IllegalArgumentException("--Has to be in format ###-###-####");
      }      
      phoneNumber = phoneNumber.replace("-","");
      if(phoneNumber.length()!=10){
         throw new IllegalArgumentException("--Not enough digits--");
      }
      
      //Validate Address
      if(address == null || address.equals("")){
         throw new IllegalArgumentException("--Must not be blank--");
      }
      //Validate Email
      if(!validateEmail(email)){
         throw new IllegalArgumentException("--Characters Allowed in email: Letters, Digits, '@', '_', '.'\n--Must be in format example@classic.com--");
      }
      
      //Validate Username
      if(userName == null || userName.equals("")){
         throw new IllegalArgumentException("--Username cannot be blank--");
      }
      if(!(userName.charAt(0)==VALID_USERNAME_CHAR_1) && !(userName.charAt(0)==VALID_USERNAME_CHAR_2)){
         throw new IllegalArgumentException("--First character must be 'S' or 'E'--");
      }
      String sub = userName.substring(1,userName.length());
      if(sub.length()!=8){
         throw new IllegalArgumentException("--Has to be 8 digits after first character. Ex: S########");
      }
      for(int x = 0; x < sub.length(); x++){
         if(!Character.isDigit(sub.charAt(x))){
            throw new IllegalArgumentException("--Has to be 8 digits--");
         }
      }
      //Validate Password
      if(password == null || password.equals("")){
         throw new IllegalArgumentException("--Password cannot be blank--");
      }
      for(int x = 0; x < password.length(); x++){
         if(Character.isDigit(password.charAt(x))){
            hasNumber = true;
         }
         if(Character.isUpperCase(password.charAt(x))){
            hasUpper = true;
         }
         if(Character.isLowerCase(password.charAt(x))){
            hasLower = true;
         }
         if(!Character.isDigit(password.charAt(x)) && !Character.isLetter(password.charAt(x))){
            hasSpecial = true;
         }
      }
      if( (hasNumber) && (hasUpper) && (hasLower) && (hasSpecial) ){
         this.password = password;
      }else{
         throw new IllegalArgumentException("--Must contain an upper case letter, a lower case letter, a number, and a special character--");
      }
      
      //Set Values 
      this.firstName = firstName;
      this.lastName = lastName;
      this.phoneNumber = phoneNumber;
      this.address = address;
      this.email = email; 
      this.userName = userName;
      this.isMaintenance = isMaintenance;
      numPerson++;    
   }
   
  
   
   public String getFirstName(){
      return this.firstName;
   }
   public String getLastName(){
      return this.lastName;
   }
   public String getPhoneNumber(){
      return this.phoneNumber;
   }
   public String getAddress(){
      return this.address;
   }
   public String getEmail(){
      return this.email;
   }
   public String getUserName(){
      return this.userName;
   }
   public String getPassword(){
      return this.password;
   }
   public static int getNumPerson(){
      return numPerson;
   }
   public void setFirstName(String firstName){
      if(firstName == null || firstName.equals("")){
         throw new IllegalArgumentException("--Can't be blank--");
      }
      this.firstName = firstName;
   }
    public void setLastName(String lastName){
      if(lastName == null || lastName.equals("")){
         throw new IllegalArgumentException("--Can't be blank--");
      }
      this.lastName = lastName;
   }
   public void setPhoneNumber(String phoneNumber){
      int symbolCount = 0;
      char VALID_PHONE_CHAR = '-';
      if(phoneNumber == null || phoneNumber.equals("")){
         throw new IllegalArgumentException("--Can't be blank--");
      }
      if(phoneNumber.length()!=12){
         throw new IllegalArgumentException("--Not in the right format, or not enough digits--");
      }
      for(int x = 0; x < phoneNumber.length(); x++){
         if(!Character.isDigit(phoneNumber.charAt(x)) && !(phoneNumber.charAt(x)==VALID_PHONE_CHAR)){
            throw new IllegalArgumentException("--Has to contain only numbers and '-' symbols--");
         }   
      }
      if(phoneNumber.charAt(3)!=VALID_PHONE_CHAR || phoneNumber.charAt(7)!=VALID_PHONE_CHAR){
         throw new IllegalArgumentException("--Has to be in format ###-###-####");
      }      
      phoneNumber = phoneNumber.replace("-","");
      if(phoneNumber.length()!=10){
         throw new IllegalArgumentException("--Not enough digits--");
      }
      this.phoneNumber = phoneNumber;
   }
   public void setAddress(String address){
      if(address == null || address.equals("")){
         throw new IllegalArgumentException("--Must not be blank--");
      }
      this.address = address;
   } 
   public void setEmail(String email){
      if(validateEmail(email)){
         this.email = email;
      }
      else {
         throw new IllegalArgumentException("--Characters Allowed in email: Letters, Digits, '@', '_', '.'\n--Must be in format example@classic.com--");
      }
   }
   public boolean validateEmail(String email) {
      char VALID_EMAIL_CHAR_1 = '@';
      char VALID_EMAIL_CHAR_2 = '.';
      char VALID_EMAIL_CHAR_3 = '_';
      int HIGH_LVL_DOMAIN_CHAR_COUNT = 2;
      boolean atTrue = false;
      boolean periodTrue = false;
      boolean validEmail = false;
      int atCount = 0;
      int atMax = 1;
      int atIndex;
      int domainIndex;
      if (email == null || email.equals("")) {
         throw new IllegalArgumentException("--Email cannot be blank--");
      }
      if(!Character.isDigit(email.charAt(0)) && !Character.isLetter(email.charAt(0))){
         return validEmail = false;
      }
      for(int i = 0; i < email.length(); i++){         
         if( !(Character.isDigit(email.charAt(i))) && !(Character.isLetter(email.charAt(i))) && !(email.charAt(i)==VALID_EMAIL_CHAR_1) && !(email.charAt(i)==VALID_EMAIL_CHAR_2) && !(email.charAt(i)==VALID_EMAIL_CHAR_3)){
            return validEmail = false;
         }        
         if(email.charAt(i)==VALID_EMAIL_CHAR_1){
            atCount++;
            atTrue = true;
            if(atCount > atMax){
               return validEmail = false;
            }
         }
         if(email.charAt(i)==VALID_EMAIL_CHAR_2){
            periodTrue = true;
         }         
      }
      if(!atTrue || !periodTrue){
         return validEmail = false;
      }
      atIndex = email.lastIndexOf(VALID_EMAIL_CHAR_1);
      periodTrue = false;
      for(int i = atIndex; i < email.length(); i++){
         if(email.charAt(i)==VALID_EMAIL_CHAR_2){
            periodTrue = true;
         }
      }
      if(!periodTrue){
         return validEmail = false;
      }
      domainIndex = email.lastIndexOf(VALID_EMAIL_CHAR_2);
      if(!(email.length() - (domainIndex + 1) >= HIGH_LVL_DOMAIN_CHAR_COUNT)){
         return validEmail = false;
      }     
      return validEmail = true;
   }
   public void setUserName(String userName){
      char VALID_USERNAME_CHAR_1 = 'S';
      char VALID_USERNAME_CHAR_2 = 'E';
      if(userName == null || userName.equals("")){
         throw new IllegalArgumentException("--Username cannot be blank--");
      }
      if(!(userName.charAt(0)==VALID_USERNAME_CHAR_1) && !(userName.charAt(0)==VALID_USERNAME_CHAR_2)){
         throw new IllegalArgumentException("--First character must be 'S' or 'E'--");
      }
      String sub = userName.substring(1,userName.length());
      if(sub.length()!=8){
         throw new IllegalArgumentException("--Has to be 8 digits after first character. Ex: S########");
      }
      for(int x = 0; x < sub.length(); x++){
         if(!Character.isDigit(sub.charAt(x))){
            throw new IllegalArgumentException("--Has to be 8 digits--");
         }
      }
      this.userName = userName;
   }
   public void setPassword(String password){
      boolean hasLower = false;
      boolean hasUpper = false;
      boolean hasNumber = false;
      boolean hasSpecial = false;
//       char SPECIAL_1 = '!';
//       char SPECIAL_2 = '@';
//       char SPECIAL_2 = '#';
//       char SPECIAL_2 = '$';
//       char SPECIAL_2 = '%';
      if(password == null || password.equals("")){
         throw new IllegalArgumentException("--Password cannot be blank--");
      }
      for(int x = 0; x < password.length(); x++){
         if(Character.isDigit(password.charAt(x))){
            hasNumber = true;
         }
         if(Character.isUpperCase(password.charAt(x))){
            hasUpper = true;
         }
         if(Character.isLowerCase(password.charAt(x))){
            hasLower = true;
         }
         if(!Character.isDigit(password.charAt(x)) && !Character.isLetter(password.charAt(x))){
            hasSpecial = true;
         }
      }
      if( (hasNumber) && (hasUpper) && (hasLower) && (hasSpecial) ){
         this.password = password;
      }else{
         throw new IllegalArgumentException("--Must contain an upper case letter, a lower case letter, a number, and a special character--");
      }
   }
   
   public boolean getIsMaintenance(){
      return this.isMaintenance;
   }
   public void setIsMaintenance(boolean isMaintenance){
      this.isMaintenance = isMaintenance;
   }
   public String toString(){
      return "First Name: " + this.getFirstName() + "\n" + 
             "Last Name: " + this.getLastName() + "\n" + 
             "Address: " + this.getAddress() +  "\n" + 
             "Phone Number: " + this.getPhoneNumber() +  "\n" + 
             "Email: " + this.getEmail() +"\n";
             
   }
   

   
   
}

