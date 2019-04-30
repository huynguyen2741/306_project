public class UnderGraduate extends Student{
   private String degree;
   public UnderGraduate(){
   }
   public UnderGraduate(String firstName, String lastName, String phoneNumber, String address, String email,String userName,String password, boolean isMaintenance, String status, double grade){
      super(firstName,lastName,phoneNumber,address,email,userName,password,isMaintenance,status,grade);
   }
   public String getDegree(){
      return this.degree;
   }
   public void setDegree(String degree){
      this.degree = degree;
   }
   public String toString(){
      return "Undergraduate student\n" + super.toString() + "\nUndergraduate student degree " + this.getDegree();
   }
}


