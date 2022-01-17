public class Keyword {
	public String name;
    //public int count;
    public double weight;
    
    //public Keyword(String name,int count, double weight){
    public Keyword(String name, double weight){
		this.name = name;
		//this.count = count;
		this.weight =weight;
    }
    
    @Override
    public String toString(){
    	//return "["+name+"," + count +","+weight+"]";
    	return "["+name+","+weight+"]";
    }
    
/*
    public int getCount()
    {
    	return count;
    }
*/
    public String getName()
    {
    	return name;
    }
    public double getWeight()
    {
    	return weight;
    }
}