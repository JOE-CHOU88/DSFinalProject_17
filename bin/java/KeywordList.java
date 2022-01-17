import java.util.ArrayList;
public class KeywordList {
	
	
	private ArrayList<Keyword> lst;
	
	public KeywordList(){
		this.lst = new ArrayList<Keyword>();
		
	}
	public void add(Keyword keyword){
		//add keyword to proper index base on its count . DECENDING SORT BY COUNT AND WEIGHT
		//printKeywordList(lst) : you can check if elements are sorted 
		/*
		for(int i=0; i<lst.size();i++){
			Keyword k= lst.get(i);	
			if(keyword.count <= k.count){
				if(keyword.count < k.count) {
					lst.add(i,keyword);
//					printKeywordList(lst);
					return;
				}
				else if(keyword.count == k.count && keyword.weight <= k.weight) {
					lst.add(i,keyword);
//					printKeywordList(lst);
					return;
				}	
			}	
		}
		*/
		lst.add(keyword);
//		printKeywordList(lst);
	
	}
	public ArrayList<Keyword> lst() {
		return lst;
	}
}
/*
	public void find(String s){
		int maxValue = -1;
		int maxIndex = -1;
		for(int i=0; i<lst.size(); i++){
			int lcs = findLCS(lst.get(i).name, s);
//			System.out.println(lcs);
			if(lcs > maxValue){
				maxValue = lcs;
				maxIndex = i;
			}
		}
		System.out.println(s+": "+lst.get(maxIndex).toString());
	}
	
	public int findLCS(String x, String y){
		//1. fill this method
		int matrix[][] = new int[x.length()][y.length()];
		for(int i=0; i<x.length(); i++) {
			for(int j=0; j<y.length();j++) {
				if(i == 0 || j == 0) {
					matrix[i][j] = 0;
				}
				else if(x.charAt(i-1) == y.charAt(j-1)) {
					matrix[i][j] = matrix[i-1][j-1] + 1;
				}else {
					matrix[i][j] = Math.max(matrix[i-1][j], matrix[i][j-1]);
				}
			}
		}
		return matrix[x.length()-1][y.length()-1];
		
	}
	
	private void printMatrix(int[][] matrix){
		for(int i=0; i<matrix.length; i++){
			for(int j=0; j<matrix[0].length; j++){
				System.out.print(matrix[i][j] + " ");
				if(j==matrix[0].length-1)System.out.print("\n");
			}
		}
	}
	
	public void outputIndex(int i){
		if(i>lst.size()){
		    System.out.println("InvalidOperation");
		    return;
		}
		Keyword k = lst.get(i);	
		System.out.println(k);
		}
		
	public void outputCount(int c){
		LinkedList<Keyword> results = new LinkedList<>();
		for(int i=0;i<lst.size();i++){
		    Keyword k = lst.get(i);
		    if(k.count == c){
		    	results.add(k);
		    }
		}
		if(results.isEmpty()){
		    System.out.println("NotFound");
		}else{
		    printKeywordList(results);
		}
	}
	
	public void outputHas(String pattern){
		LinkedList<Keyword> results = new LinkedList<>();
		for(int i=0;i<lst.size();i++){
		    Keyword k = lst.get(i);
		    if(k.name.contains(pattern)){
		    	results.add(k);
		    }
		}
		if(results.isEmpty()){
		    System.out.println("NotFound");
		}else{
		    printKeywordList(results);
		}
	}
	
	public void outputName(String pattern){
		
		LinkedList<Keyword> results = new LinkedList<>();
		for(int i=0;i<lst.size();i++){
		    Keyword k = lst.get(i);
		    if(k.name.equals(pattern)){
		    	results.add(k);
		    }
		}
		if(results.isEmpty()){
		    System.out.println("NotFound");
		}else{
		    printKeywordList(results);
		}
	}
	
	public void outputFirstN(int n){
		if(n>lst.size()){
		    System.out.println("InvalidOperation");
		    return;
		}
		
		LinkedList<Keyword> found= new LinkedList<>();
		
		for(int i=0;i<n;i++){
			Keyword k =lst.get(i);
			found.add(k);
		}
		
		printKeywordList(found);	
	}

	public float outputScore(){
		float results = 0;
		for(int i=0;i<lst.size();i++){
		    Keyword k = lst.get(i);
		    //1.To calaulate all keyword's count*weight
		    results += k.count * k.weight;
		}
		
		System.out.printf("Total score: %.2f", results);
		System.out.println();
		return results;
	}
	
	public void deleteIndex(int i){
		
		if(i>=lst.size()){
		    return;
		}
			
		lst.remove(i);
	}

	public void deleteCount(int c){
		// 2. remove nodes that the count is equal to c
		LinkedList<Keyword> found= new LinkedList<>();		
		for(int i=0;i<lst.size();i++) {
			Keyword k = lst.get(i);
		    if(k.count == c){
		    	found.add(k);
		    }
		}
		
		if(!found.isEmpty()){
			lst.removeAll(found);			
		}
		
		
	}

	public void deleteHas(String pattern){
		// 3. remove nodes that the name contains input name
		LinkedList<Keyword> found= new LinkedList<>();		
		for(int i=0;i<lst.size();i++) {
			Keyword k = lst.get(i);
		    if(k.name.contains(pattern)){
		    	found.add(k);
		    }
		}
	
		if(!found.isEmpty()){
			lst.removeAll(found);
		}
		
	}
	
	public void deleteName(String name){
		// 4. remove nodes that the name is equal to input name
		LinkedList<Keyword> found= new LinkedList<>();		
		for(int i=0;i<lst.size();i++) {
			Keyword k = lst.get(i);
		    if(k.name.equals(name)){
		    	found.add(k);
		    }
		}
		
		if(!found.isEmpty()){
			lst.removeAll(found);
		}
		
	}
	
	public void deleteFirstN(int n){
		//5. remove first n nodes
		LinkedList<Keyword> found= new LinkedList<>();		
	
		for(int i=0;i<n;i++){
			Keyword k =lst.get(i);
			found.add(k);
		}
		
		if(!found.isEmpty()){
			lst.removeAll(found);
		}
		

	}
	
	public void deleteAll(){
		lst = new LinkedList<Keyword>();
	}
		
	private void printKeywordList(LinkedList<Keyword> kLst){
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<kLst.size();i++){
			Keyword k= kLst.get(i);
			if(i>0)sb.append(" ");
			sb.append(k.toString());
		}
		System.out.println(sb.toString());
	}
	
	//quick sort
		public void sort(){
			if(lst.size() == 0)
			{
				System.out.println("InvalidOperation");
			}
			else 
			{
				quickSort(0, lst.size()-1);
//				System.out.println("Done");
			}

		}
		
		
		private void quickSort(int leftbound, int rightbound){
			//1. implement quickSort algorithm
			if(leftbound > rightbound || leftbound == rightbound) {
				return;
			}else {
				Keyword pivot = lst.get(rightbound);
				int j = leftbound;
				int k = rightbound - 1;
				while (j < k) {
					while (lst.get(j).count < pivot.count || lst.get(j).count == pivot.count) {
						j++;
					}
					while (lst.get(k).count > pivot.count) {
						k--;
					}
					if (j < k) {
						swap(j, k);
					}
				}
				swap(lst.indexOf(pivot), j);
				quickSort(leftbound, j-1);
				quickSort(j+1, rightbound);
			}

		}

		
		
		private void swap(int aIndex, int bIndex){
			Keyword temp = lst.get(aIndex);
			lst.set(aIndex, lst.get(bIndex));
			lst.set(bIndex, temp);
		}
		
		public void output(){
			//TODO: write output and remove all element logic here...
			StringBuilder sb = new StringBuilder();
			
			for(int i=0; i<lst.size();i++){
				Keyword k = lst.get(i);
				if(i>0)sb.append(" ");
				sb.append(k.toString());
			}
			
			System.out.println(sb.toString());	
		}
		
		public int size() {
			return lst.size();
		}
		
}
*/
