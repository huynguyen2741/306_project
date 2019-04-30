public class Graduate extends Student{
   private String program;
   public Graduate(){
   }
   public Graduate(String firstName, String lastName, String phoneNumber, String address, String email,String userName,String password, boolean isMaintenance, String status, double grade){
      super(firstName,lastName,phoneNumber,address,email,userName,password,isMaintenance,status,grade);
   }
   public String getProgram(){
      return this.program;
   }
   public void setProgram(String program){
      this.program = program;
   }
   public String toString(){
      return "Graduate student\n"+ super.toString() + "\nGraduate student program " + this.getProgram();
   }
}



