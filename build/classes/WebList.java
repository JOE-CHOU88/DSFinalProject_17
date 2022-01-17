import java.util.ArrayList;

public class WebList extends ArrayList <WebTree>{
	
	private static final long serialVersionUID = 1L;
	
//	private String url;
//	private String title;
	private ArrayList<WebTree> lst;
	
	public WebList() {
		this.lst = new ArrayList<WebTree>();
	}
	
	@Override
	public boolean add(WebTree e) {
	  return super.add(e);
	}
	//quick sort
	public void sort(){
		if(lst.isEmpty()) {
			System.out.println("InvalidOperation");
		}
		else {
			for(int i=0;i<lst.size();i++) {
				if(lst.get(i).root.nodeScore<0) {
					lst.remove(lst.get(i));
					i--;
				}
			}
			quickSort(0, lst.size()-1);
		}	
	}
	private void quickSort(int leftbound, int rightbound){
		//1. implement quickSort algorithm
		if(leftbound<rightbound) {
			double pivot=lst.get(leftbound).root.nodeScore;
			int i = leftbound;
			int j = rightbound;
			while(true) {
				while(lst.get(i).root.nodeScore<=pivot&&i<j) {
					i++;
				}
				while(lst.get(j).root.nodeScore>pivot&&j>1) {
					j--;
				}
				if(i>=j) {
					break;
				}
				swap(i,j);
			}
			swap(leftbound,j);
			quickSort(leftbound, j-1);
			quickSort(j+1, rightbound);
		}
	}	
	
	private void swap(int aIndex, int bIndex){
		WebTree temp = lst.get(aIndex);
		lst.set(aIndex, lst.get(bIndex));
		lst.set(bIndex, temp);
	}
	
	public  void output(){
		//TODO: write output and remove all element logic here...
		if(lst.isEmpty()) {
			System.out.println("InvalidOperation");
		}
		else {
			String sb = "";
			for(int i=0; i<lst.size();i++){
				WebTree k = lst.get(i);
				if(i>0) {
					sb +=" ";
				}
				sb+=Double.toString(k.root.nodeScore);
			}
			
			System.out.println(sb.toString());	
		}	
		System.out.println(lst);

	}
	
	public ArrayList<WebTree> getLst() {
		return lst;
	}
}
