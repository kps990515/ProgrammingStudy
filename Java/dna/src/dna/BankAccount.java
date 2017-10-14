package dna;

public class BankAccount {
    private int balance;
    private Person owner;

   public void setBalance(int pBalance){
     balance = pBalance;
   }
  
   public int getBalance() {
    return balance;
   }
   
   public void setOwner(Person pOwner){
    owner = pOwner;
   }
   
   public Person getOwner() {
     return owner;
   }
   
   boolean deposit(int amount) {
      if (amount < 0 || owner.getcashAmount() < amount) {
          System.out.println("입금 실패입니다. 잔고: " + balance + "원, 현금: " + owner.getcashAmount() + "원");
          
          return false;
       } else {
       
       owner.setCashAmount(owner.getcashAmount() - amount);
        balance += amount;
          System.out.println(amount + "원 입금하였습니다. 잔고: " + balance + "원, 현금: " + owner.getcashAmount() + "원");
         return true;
   }
}
  boolean withdraw(int amount) {
    if (amount < 0 || amount > balance) {
      System.out.println("출금 실패입니다. 잔고: " + balance + "원, 현금: " + owner.getcashAmount() + "원");
       
    return false;
    } else {
      
    owner.setCashAmount(owner.getcashAmount() + amount);
        balance -= amount;
        System.out.println(amount + "원 출금 하였습니다. 잔고: " + balance + "원, 현금: " + owner.getcashAmount() + "원");
        
        return true;
    }
}   
   // 첫 번째 파라미터: 받는 사람 (Person)
   // 두 번째 파라미터: 이체할 금액 (정수)
   // 리턴 : 성공여부 (불린)
   public boolean transfer(Person to, int amount) {
       return transfer(to.getAccount(), amount);
   }

   // 첫 번째 파라미터: 받는 사람의 계정 (BankAccount)
   // 두 번째 파라미터: 이체할 금액 (정수)
   // 리턴 : 성공여부 (불린)
   public boolean transfer(BankAccount to, int amount) {
   boolean success;

   if (amount < 0 || amount > balance) {
       success = false;
   } else {
       balance -= amount;
       
       success = true;
   }

   // 결과값 출력
   System.out.println(success + " - from: " + owner.getName()
           + ", to: " + to.getOwner().getName()
           + ", amount: " + amount
           + ", balance: " + balance);

   return success;
   }
}
