
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DB db = new DB();
		
		db.createTable("apple");
		
		for(int i = 0 ; i<10 ; i++)
			db.insert(i);
		
		db.printAll();
	}

}
