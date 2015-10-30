package happycube;

import java.util.ArrayList;  
import java.util.EmptyStackException;  
import java.util.List;  

public class CubeStateStack <E extends Object> {

		private List<E> list = new ArrayList<E>();
		
		public CubeStateStack() {
			
		}

	    public boolean isEmpty(){  
	        return list.isEmpty();  
	    }  
	      
	    public void push(E e){  
	        list.add(e);  
	    }  
	      
	    public E pop(){  
	        if(list.isEmpty()) throw new EmptyStackException();  
	        else return list.remove(list.size()-1);  
	    }  
	      
	    public E peek(){  
	        if(list.isEmpty()) throw new EmptyStackException();  
	        else return list.get(list.size()-1);  
	    }  
	      
	}  
 
