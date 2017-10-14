package dna;

public class Person {
	    // 
	     private String name;
	     private int age;
	     private int cashAmount;
	     private BankAccount account;

	    public void setName(String pName) {
	        name = pName;
	    }
	    
	    public String getName() {
	        return name;   
	    }

	    public void setAge(int pAge) {
	       if (pAge >= 0) {
	          age = pAge;
	       }
	    }
	   
	    public int getAge() {
	        return age;
	    }

	    public void setCashAmount(int pCashAmount) {
	       if(pCashAmount>=0) {
	        cashAmount = pCashAmount;
	    }
	    }
	    public int getcashAmount() {
	      return cashAmount;
	    }

	    public void setAccount(BankAccount pAccount) {
	        account = pAccount;   
	    }

	 public BankAccount getAccount() {
	     return account;
	 	}
	    // ù ��° �Ķ����: �޴� ��� (Person)
	    // �� ��° �Ķ����: ��ü�� �ݾ� (����)
	    // ���� : �������� (�Ҹ�)
	    public boolean transfer(Person to, int amount) {
	    	   boolean success;
	    	   int balance = account.getBalance();
	    	   if (amount < 0 || amount > balance) {
	    	       success = false;
	    	   } else {
	    	        balance -= amount;
	    	        
	    	       
	    	       success = true;
	    	   }

	    	   // ����� ���
	    	   System.out.println(success + " - from: " + name
	    	           + ", to: " + to.getName()
	    	           + ", amount: " + amount
	    	           + ", balance: " + balance);

	    	   return success;
	    	   }

	    // ù ��° �Ķ����: �޴� ����� ���� (BankAccount)
	    // �� ��° �Ķ����: ��ü�� �ݾ� (����)
	    // ���� : �������� (�Ҹ�)
	    public boolean transfer(BankAccount to, int amount) {
	    	return transfer(to.getOwner(), amount);
	    }
	}