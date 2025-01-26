package network;

import java.util.ArrayList;
import java.util.List;

public class Netowork {

	public Netowork() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Matrix mat = new Matrix(4,4);
		
		mat.fill(1);
		for (int i = 0; i < 4; i++) {
			for (int ii = 0; ii < 4; ii++) {
				if (ii==i) {
					
					mat.set(ii, i,  1);
					
					
					
				}
				
			}
		}
		Vector doubles =new  Vector(new double[]{1,1,1,1}) ;
		System.out.println(mat);
		//ystem.out.println(doubleMat);
		System.out.println("result:");
		//System.out.println(mat.multiply(doubleMat));
		//System.out.println(mat.add(doubleMat));
		System.out.println(mat.multiply(mat.multiply(doubles)).multiply(doubles));
		

	}

}
