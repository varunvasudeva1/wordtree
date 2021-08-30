

public class Tester {

	public static void main(String[] args) {
		WordTree t = new WordTree();
		
		System.out.println("Node count: " + t.nodeCount()); // 1
		System.out.println("Word count: " + t.wordCount()); // 0
		
		System.out.println(t.add("car")); // true
		System.out.println("Node count: " + t.nodeCount()); // 4
		System.out.println("Word count: " + t.wordCount()); // 1
		
		
		System.out.println(t.toString());
		
		
		System.out.println(t.add("catchy")); // true
		System.out.println("Node count: " + t.nodeCount()); // 8
		System.out.println("Word count: " + t.wordCount()); // 2
		System.out.println(t.add("catchy")); // false
		
		System.out.println("Letters saved: " + t.lettersSaved()); // 9 - 7 = 2
		
		
		System.out.println(t.contains("catchy")); // true
		System.out.println(t.contains("cat")); // false
		System.out.println(t.contains("cart")); // false
		System.out.println(t.contains("car")); // true
		
		
		System.out.println(t.add("catalog")); // true
		System.out.println("Word count: " + t.wordCount()); // 3
		System.out.println(t.add("catalogs")); // true
		System.out.println("Word count: " + t.wordCount()); // 4
		System.out.println(t.add("barn")); // true
		System.out.println("Word count: " + t.wordCount()); // 5
		System.out.println(t.add("barns")); // true
		System.out.println("Word count: " + t.wordCount()); // 6
		
		System.out.println("Letters saved: " + t.lettersSaved()); // 33 - 17 = 16
		
		System.out.println("All words: " + t.allWords());
		
		System.out.println("Words starting with ca: " + t.allStartingWith("ca"));
		
		System.out.println("Word Map: " + t.wordMap());
		
		System.out.println(t.toString());
		
		
		
		//=========================================================================
		
		System.out.println(t.remove("catalog"));
		
		System.out.println("LAPEZ ADDED: " + t.add("lapez"));
		
		System.out.println("CATALOG REMOVED\n" + t.toString());
		
		System.out.println(t.remove("catalogs"));
		
		System.out.println("CATALOGS REMOVED\n" + t.toString());
		
		System.out.println(t.remove("lapez"));
		
		System.out.println("LAPEZ REMOVED\n" + t.toString());
		
		//=========================================================================
		
		t.add("cat");
		t.add("catch");
		t.add("catchable");
		
		System.out.println(t.toString());
		
		t.remove("cat");
		
		System.out.println("CAT REMOVED\n" + t.toString());
		
		t.remove("catchy");
		
		System.out.println("CATCHY REMOVED\n" + t.toString());
		
		t.remove("catchable");
		
		System.out.println("CATCHABLE REMOVED\n" + t.toString());
		
		t.clear();
		System.out.println("Node count: " + t.nodeCount()); // 1 for root
		System.out.println("Word count: " + t.wordCount()); // 0

	}

}
