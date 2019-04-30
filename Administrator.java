public class Administrator extends Employee {
   private String officeNumber;
   public Administrator() {}
   
   public Administrator (String firstName, String lastName, String phoneNumber, String address, String email,String userName,String password, boolean isMaintenance){
      super(firstName,lastName,phoneNumber,address,email,userName,password,isMaintenance);
      
   }
   public String getOfficeNumber(){
      return officeNumber;
   }
   public void setOfficeNumber(String officeNumber){
      if (officeNumber == null || officeNumber.equals("")){
         throw new IllegalArgumentException("Office Number cannot be blank.");
      }
      this.officeNumber = officeNumber;
   }
   public String toString(){
      return super.toString() + "\nOffice number: " + this.getOfficeNumber();
   }
   

}

