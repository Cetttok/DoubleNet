package network;

public class Vector {
	double[] values;
	
	public Vector(int size) {
		values = new double[size];
		fill(0);
		// TODO Auto-generated constructor stub
	}
	public Vector(int size, double filler) {
		values = new double[size];
		if (Double.isNaN(filler)) {
			System.out.println("Vector. Vector(size,filler) Too many mistakes, fatal mistakes. (fill at '0')");
			fill(0);
		}
		fill(filler);
		
	}
	public Vector(double[] data) {
		values = data.clone();
		for (int i = 0 ;i < values.length; i++) {
			if(Double.isNaN(values[i])) {
				System.out.println("Vecotor. Vector(data) once of your values is NaN! (I will set it to 0). Id " + i);
				values[i] = 0;
			}
		}


	}
	void fill(double value) {
		if (Double.isNaN(value)) {
			System.out.println("Vecotor. fill() WTF! your fill value is NaN. Walk around turning around. (and i of course fill it for '0')");
			value = 0;
		}
		for (int i = 0 ;i < values.length; i++) {
			values[i] = value;
		}
	}
	double sum() {
		double result = 0 ;
		for (int i = 0 ; i< values.length; i ++) {
			try {
				
				result += get(i);
			}
			catch(VectorOutOfRangeException ex) {
				System.out.println("Vector. sum() War with computer.. " + ex.getMessage() );
				return result;
			}
		}
		return result;
	}
	@Override
	public Vector clone() {
		return new Vector(values);
	}
	
	double get(int i) throws VectorOutOfRangeException{
		if (i < values.length) {
			return values[i];
		}
		else {
			throw new VectorOutOfRangeException(i);
		}
	}
	boolean set(int i, double value) throws VectorOutOfRangeException{
		if (i < values.length) {
			if (Double.isNaN(value)) {
				System.out.println("Vector. set() Ops! Your values is NaN!");
				return false;
			}
			else {
				values[i] = value;
				return true;
			}
		}
		else {
			throw new VectorOutOfRangeException(i);
		}
	}
	boolean increase(int i, double value) throws VectorOutOfRangeException{
		if (Double.isNaN(value)) {
			System.out.println("Vector. increase() Your value is NaN. Eat it yourself.");
			return false;
		}
		if(i < values.length) {
			values[i] += value;
			return true;
		}
		else {
			throw new VectorOutOfRangeException(i);
		}
	}
	Vector add(Vector b) throws VectorAddException{
		if (b.len() != values.length) {
			throw new VectorAddException();
		}
		Vector result = clone();
		for (int i = 0; i < values.length; i++) {
			try {
				result.increase(i, b.get(i));
			}
			catch(VectorOutOfRangeException ex) {
				System.out.println("Vector. add() Ops! it our error" + ex.getMessage());
				return result;
			}
			
		}
		return result;
	}
	Vector plus(double plusser) {
		if (Double.isNaN(plusser)) {
			System.out.println("Vector. plus() Your plusser is NaN. You've fucked me up.");
			return clone();
		}
		Vector result = clone();
		for (int i = 0 ; i < values.length; i++) {
			try {
				result.increase(i,plusser);
			}
			catch(VectorOutOfRangeException ex) {
				System.out.println("Vector. plus() I dont now that i think when write it.. "+ex.getMessage());
				return result;
			}
			
		}
		return result;
	}
	int len() {
		return values.length;
	}
	Vector boost(double booster) {
		if (Double.isNaN(booster)) {
			System.out.println("Vector. boost() Your booster is NaN. You've fucked me up.");
			return clone();
		}
		Vector result = clone();
		for (int i = 0 ; i < values.length; i++) {
			try {
				result.set(i,booster*get(i));
			}
			catch(VectorOutOfRangeException ex) {
				System.out.println("Vector. boost() I dont now that i think when write it.. Again.. "+ex.getMessage());
				return result;
			}
			
		}
		return result;
	}
	double multiply(Vector b) throws VectorMyltiplyException{
		if (values.length == b.len()) {
			double result = 0;
			for (int i = 0; i < values.length; i ++) {
				
				try{
					result += get(i) * b.get(i);
				}
				catch(VectorOutOfRangeException ex) {
					System.out.println("Vector. multiply() Oki joki. "+ex.getMessage());
					return 0;
				}
			}
			return result;
		}
		
		else {
			throw new VectorMyltiplyException();
		}
	}
	@Override
	public String toString() {
		String result = "Vector(" + values.length + "){";
		for (int i = 0 ; i < values.length; i ++) {
			try {
				result += get(i);	
			}
			catch(VectorOutOfRangeException ex) {
				System.out.println("Vector. toString() I'm silent. (Main developer 'jump' to 'window') "+ex.getMessage());
				return result;
			}
			
			if (i < values.length -1 ) {
				result+=",";
			}
			
		}
		return result+"}";
	}
	

}
