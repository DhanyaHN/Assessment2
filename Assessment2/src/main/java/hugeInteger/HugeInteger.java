package hugeInteger;

import java.math.BigInteger;
import java.util.function.Predicate;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HugeInteger {
	
	Integer[] bigInt;
	
	@RequestMapping("/parse")
	public String parse(@RequestParam(value = "num") String inpInt){
		bigInt = new Integer[inpInt.length()];
		for (int i = 0; i < inpInt.length(); i++)
			bigInt[i] = Integer.parseInt(inpInt.charAt(i)+"");
		return "Number Parsed"; 
	}
	
	@RequestMapping("/add")
	public String add(@RequestParam(value = "num") String testInt){
		if ( testInt.length() > 40 &&  bigInt.length > 40) {
			return "Addition cannot be done. Limit exceeded";
		}
		else {
			return new BigInteger(extractString()).add(new BigInteger(testInt)).toString();
		}
		
	}
	
	
	@RequestMapping("/sub")
	public String sub(@RequestParam(value = "num") String testInt){
		if ( testInt.length() > 40 &&  bigInt.length > 40) {
			return "Cannot subtract";
		}
		else {
			return new BigInteger(extractString()).subtract(new BigInteger(testInt)).toString();
		}
		
	}
	
	@RequestMapping("/Equals")
	public boolean isEqualTo(@RequestParam(value = "num") String testInt){
		Integer[] inpNum = new Integer[testInt.length()];
		for (int i = 0; i < testInt.length(); i++)
			inpNum[i] = Integer.parseInt(testInt.charAt(i)+""); 
		return isequals.test(inpNum);
	}
	
	public Predicate<Integer[]> isequals = new Predicate<Integer[]>(){

		@Override
		public boolean test(Integer[] testNum) {
			if(bigInt.length!=testNum.length)
				return false;
			for(int i = 0; i < testNum.length;i++) {
				if(testNum[i] != bigInt[i]) {
					return false;
				}
			}
			return true;
			
		}
		
	};
	
	@RequestMapping("/NotEquals")
	public boolean isNotEqualTo(@RequestParam(value = "num") String testInt){
		Integer[] inpNum = new Integer[testInt.length()];
		for (int i = 0; i < testInt.length(); i++)
			inpNum[i] = Integer.parseInt(testInt.charAt(i)+"");
		return isNotequals.test(inpNum);
	}
	
	public Predicate<Integer[]> isNotequals = new Predicate<Integer[]>(){

		@Override
		public boolean test(Integer[] testNum) {
			if(bigInt.length!=testNum.length)
				return false;
			for(int i = 0; i < testNum.length;i++) {
				if(testNum[i] != bigInt[i]) {
					return true;
				}
			}
			return false;
			
		}
		
	};
	
	
	@RequestMapping("/GreaterThan")
	public boolean isGreaterThan(@RequestParam(value = "num") String testInt){
		Integer[] inpNum = new Integer[testInt.length()];
		for (int i = 0; i < testInt.length(); i++)
			inpNum[i] = Integer.parseInt(testInt.charAt(i)+"");
		
			return isgreaterthan.test(inpNum);
		}
	
	public Predicate<Integer[]> isgreaterthan = new Predicate<Integer[]>(){

		@Override
		public boolean test(Integer[] testNum) {
			if(bigInt.length>testNum.length)
				return false;
			else if(bigInt.length<testNum.length)
				return true;
			for(int i = 0; i < testNum.length;i++) {
				if(bigInt[i]>testNum[i])
					return false;
				else if(bigInt[i]<testNum[i])
					return true;
				}
			
			return false;
			
		}
		
	};
	
	@RequestMapping("/LesserThan")
	public boolean isLessThan(@RequestParam(value = "num") String testInt){
		Integer[] inpNum = new Integer[testInt.length()];
		for (int i = 0; i < testInt.length(); i++)
			inpNum[i] = Integer.parseInt(testInt.charAt(i)+"");
		return islesserthan.test(inpNum);
		
	}
	
	public Predicate<Integer[]> islesserthan = new Predicate<Integer[]>(){

		@Override
		public boolean test(Integer[] testNum) {
			if (bigInt.length < testNum.length) {
				return false;
			}
			else if (bigInt.length > testNum.length){
				return true;
			}
				
			for(int i = 0; i < testNum.length;i++) {
				
				if(bigInt[i] < testNum[i]) {
					return false;
				}
				else if(bigInt[i] > testNum[i]) {
					return true;
				}		
			}
			return false;
		}
		
	};
	@RequestMapping("/GreaterThanEqual")
	public boolean isGreaterThanOrEqualTo(@RequestParam(value = "num") String testInt){
		
		Integer[] inpNum = new Integer[testInt.length()];
		for (int i = 0; i < testInt.length(); i++) {
			inpNum[i] = Integer.parseInt(testInt.charAt(i)+"");
		}
		return isgreaterthanequal.test(inpNum);
		
	}
	
	public Predicate<Integer[]> isgreaterthanequal = new Predicate<Integer[]>(){

		@Override
		public boolean test(Integer[] testNum) {
			if(testNum.length > bigInt.length) {
				return true;
			}
			else if(testNum.length < bigInt.length) {
				return false;
			}
			for(int i=0;i < testNum.length;i++)
			{
				if(testNum[i] < bigInt[i]) {
					return false;
				}
			}
			return true;
		}
		
	};
	
	@RequestMapping("/LesserThanEqual")
	public boolean isLessThanOrEqualTo(@RequestParam(value = "num") String testInt){
		Integer[] inpNum = new Integer[testInt.length()];
		for (int i = 0; i < testInt.length(); i++) {
			inpNum[i] = Integer.parseInt(testInt.charAt(i)+"");
		}
		return islesserthanequal.test(inpNum);		
	}

	public Predicate<Integer[]> islesserthanequal = new Predicate<Integer[]>(){

		@Override
		public boolean test(Integer[] testNum) {
			if(testNum.length < bigInt.length)
				return true;
			else if(testNum.length > bigInt.length)
				return false;
			for(int i = 0;i < testNum.length; i++)
			{
				if(testNum[i] > bigInt[i])
					return false;
			}
			return true;
		}
		
	};
	
	
	@RequestMapping("/Zero")
	public boolean isZero(){
		return isZero.test(bigInt);
	}
	
	Predicate<Integer[]> isZero = new Predicate<Integer[]>() {
		   
		@Override
		public boolean test(Integer[] testNum) {
			for(Integer i: testNum) {
				if(i != 0) {
					return false;
				}
			}
			return true;
		}       
	};
	
	@RequestMapping("/toString")
	public String toString(){
		return extractString();
	}
	
	public String extractString(){
		StringBuilder builder = new StringBuilder();
		for (int i : bigInt)
			builder.append(i);
		return builder.toString();
	}
}