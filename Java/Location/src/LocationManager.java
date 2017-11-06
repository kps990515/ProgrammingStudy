import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

public class LocationManager {
	private HashMap<String, Location> map = new HashMap<String, Location>();
	   
	   private void read() {
	      System.out.println("����,�浵,������ �Է��ϼ���.");
	      Scanner scanner = new Scanner(System.in);
	      
	      for (int i=0; i<2; i++) {//2���ϴ� �ɷ� ������.
	         System.out.print(">> "); //�޸��� ������ ��Ʈ�� �и��ؼ� ���� �� _ StringTokenizer ���_���ú���.��������.�浵����
	         
	         String str = scanner.nextLine();
	         
	         StringTokenizer st = new StringTokenizer(str, ", ");
	         
	         String city = st.nextToken();
	         String la = st.nextToken();
	         String lo = st.nextToken();
	         
	         double longitude = Double.parseDouble(lo); //�浵
	         double latitude = Double.parseDouble(la); //����

	         Location loc = new Location(city, latitude, longitude);	      
	         loc.setCity(city);
	         loc.setLatitude(latitude);
	         loc.setLogitude(longitude);
	         map.put(city, loc); //hashMap �� ����
	      }        
	   }   
	   private void printAll() {
	      
		  Iterator<String> it = map.keySet().iterator();
	      //String city = it.next();
	      //Location loc = map.get(city);
	      
	      System.out.println("-------------------------------------");
	      
	      while (it.hasNext()) {
	         
	         String key = it.next();
	         System.out.println(key+"\t"+ map.get(key).getLatitude()+"\t" + map.get(key).getLongitude());
	         }
	         System.out.println("-------------------------------------");
	            
	      }

	   private void processQuery() {
	      Scanner scanner = new Scanner(System.in);	      
	      while(true) {
	         System.out.print("���� �̸� >> ");
	         String name = scanner.nextLine();
	         
	         if(name.equals("�׸�")) {
	            break;
	         }
	         if(map.containsKey(name)==false) {
		       System.out.println(name+"��/�� �����ϴ�.");
	         }
	         else
	            System.out.println(name+"\t"+ map.get(name).getLatitude()+"\t" + map.get(name).getLongitude());
	         }
	      scanner.close();
	      
	      }
	   
	   
	   public void run() {
	      read();
	      printAll();
	      processQuery();
	   }
	   
	   public static void main (String[] args) {
	      LocationManager man = new LocationManager();
	      man.run();
	   }

}
