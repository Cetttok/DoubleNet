package network;

import java.util.ArrayList;
import java.util.List;

public class Netowork {

	public Netowork() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Tensor a = new Tensor(new TensorSize(3,3,3));
		Tensor b = new Tensor(new TensorSize(3,3,3));
		a.fill(1);
		b.fill(2);
		System.out.println(a);
		System.out.println(b);
		System.out.println("a + 2b = ");
		System.out.println(a.add(b).add(b));
		
	}

}
