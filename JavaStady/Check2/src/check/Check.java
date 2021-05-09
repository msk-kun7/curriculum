package check;

import constants.Constants;

public class Check extends Constants {
	
	public static void main(String[] args) { 
		
		//String型の変数firstNameに"姓"を代入
		//String型の変数lastNameに"名"を代入　
		//getNameメソッド(戻り値)
		String firstName="田岡";
    	String lastName="真輝";
    	System.out.println(getName(firstName, lastName));
    	
    	Pet pet = new Pet(CHECK_CLASS_JAVA, CHECK_CLASS_HOGE);
	    pet.introduce();
	    
	    RobotPet robotpet = new RobotPet(CHECK_CLASS_R2D2,CHECK_CLASS_LUKE);
	    robotpet.introduce();
	    
	}
	//return文：public static 戻り値の型　メソッド名(引数リスト){return 戻り値}
	 public static String getName(String firstName, String lastName) {
	    	return firstName + lastName;
	    }
}
