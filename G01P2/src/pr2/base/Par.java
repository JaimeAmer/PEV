package pr2.base;

public class Par<K, V> {

	private K key;
	private V value;
	
	public Par(K key, V value){
		this.key = key;
		this.value = value;
	}
	
	public K getKey(){
		return key;
	}
	
	public V getValue(){
		return value;
	}
	
	public boolean equals(Par<K, V> otro){		
		if(otro == null)
			return false;
		else{
			if(key.equals(otro.key) && value.equals(otro.value))
				return true;
			else return false;
		}
	}
	
	public String toString(){
		return "<" + key + ", " + value + ">";
	}
	
}
