package check;
import constants.Constants;

public class Check  {
	
	private String firstName="田岡";
	private String lastName="真輝";
	
	private void printName(String firstName,String lastName){
	System.out.println("printlnメソッド⇨"+this.firstName+this.lastName);
	}
	
	public static void main(String[] args) { 
		
		//String型の変数firstNameに"姓"を代入
		//String型の変数lastNameに"名"を代入　
		//getNameメソッド(戻り値)
		Check check = new Check();
		check.printName(check.firstName,check.lastName);
    	
    	Pet pet = new Pet(Constants.CHECK_CLASS_JAVA, Constants.CHECK_CLASS_HOGE);
	    pet.introduce();
	    
	    RobotPet robotpet = new RobotPet(Constants.CHECK_CLASS_R2D2,Constants.CHECK_CLASS_LUKE);
	    robotpet.introduce();
	    
	}
	//return文：public static 戻り値の型　メソッド名(引数リスト){return 戻り値}
	 public static String getName(String firstName, String lastName) {
	    	return firstName + lastName;
	    }
}
