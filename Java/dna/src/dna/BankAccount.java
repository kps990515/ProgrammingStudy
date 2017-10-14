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
          System.out.println("�Ա� �����Դϴ�. �ܰ�: " + balance + "��, ����: " + owner.getcashAmount() + "��");
          
          return false;
       } else {
       
       owner.setCashAmount(owner.getcashAmount() - amount);
        balance += amount;
          System.out.println(amount + "�� �Ա��Ͽ����ϴ�. �ܰ�: " + balance + "��, ����: " + owner.getcashAmount() + "��");
         return true;
   }
}
  boolean withdraw(int amount) {
    if (amount < 0 || amount > balance) {
      System.out.println("��� �����Դϴ�. �ܰ�: " + balance + "��, ����: " + owner.getcashAmount() + "��");
       
    return false;
    } else {
      
    owner.setCashAmount(owner.getcashAmount() + amount);
        balance -= amount;
        System.out.println(amount + "�� ��� �Ͽ����ϴ�. �ܰ�: " + balance + "��, ����: " + owner.getcashAmount() + "��");
        
        return true;
    }
}   
   // ù ��° �Ķ����: �޴� ��� (Person)
   // �� ��° �Ķ����: ��ü�� �ݾ� (����)
   // ���� : �������� (�Ҹ�)
   public boolean transfer(Person to, int amount) {
       return transfer(to.getAccount(), amount);
   }

   // ù ��° �Ķ����: �޴� ����� ���� (BankAccount)
   // �� ��° �Ķ����: ��ü�� �ݾ� (����)
   // ���� : �������� (�Ҹ�)
   public boolean transfer(BankAccount to, int amount) {
   boolean success;

   if (amount < 0 || amount > balance) {
       success = false;
   } else {
       balance -= amount;
       
       success = true;
   }

   // ����� ���
   System.out.println(success + " - from: " + owner.getName()
           + ", to: " + to.getOwner().getName()
           + ", amount: " + amount
           + ", balance: " + balance);

   return success;
   }
}
