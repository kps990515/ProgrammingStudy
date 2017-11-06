import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

public class LocationManager {
	private HashMap<String, Location> map = new HashMap<String, Location>();
	   
	   private void read() {
	      System.out.println("도시,경도,위도를 입력하세요.");
	      Scanner scanner = new Scanner(System.in);
	      
	      for (int i=0; i<2; i++) {//2번하는 걸로 변경함.
	         System.out.print(">> "); //콤마를 기준을 스트링 분리해서 받을 것 _ StringTokenizer 사용_도시변수.위도변수.경도변수
	         
	         String str = scanner.nextLine();
	         
	         StringTokenizer st = new StringTokenizer(str, ", ");
	         
	         String city = st.nextToken();
	         String la = st.nextToken();
	         String lo = st.nextToken();
	         
	         double longitude = Double.parseDouble(lo); //경도
	         double latitude = Double.parseDouble(la); //위도

	         Location loc = new Location(city, latitude, longitude);	      
	         loc.setCity(city);
	         loc.setLatitude(latitude);
	         loc.setLogitude(longitude);
	         map.put(city, loc); //hashMap 에 저장
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
	         System.out.print("도시 이름 >> ");
	         String name = scanner.nextLine();
	         
	         if(name.equals("그만")) {
	            break;
	         }
	         if(map.containsKey(name)==false) {
		       System.out.println(name+"은/는 없습니다.");
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
