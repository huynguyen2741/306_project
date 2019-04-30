public class Employee extends Person{
   private String position;
   
   public Employee(){
      super();
   }
   
   public Employee (String firstName, String lastName, String phoneNumber, String address, String email,String userName,String password, boolean isMaintenance, String position){
      super(firstName,lastName,phoneNumber,address,email,userName,password,isMaintenance);
      this.position = position;
   }
   
   public Employee (String firstName, String lastName, String phoneNumber, String address, String email,String userName,String password, boolean isMaintenance){
      super(firstName,lastName,phoneNumber,address,email,userName,password,isMaintenance);
      //this.position = position;
   }
   
   public String getPosition(){
      return position;
   }
   
   public void setPosition(String position){
      this.position = position;
   }
   
 
}
