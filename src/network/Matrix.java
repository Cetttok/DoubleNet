package network;

public class Matrix {

	private int _width;
	private int _height;
	
	double[][] _values;
	
	public Matrix(int w, int h) {
		_width = w;
		_height = h;
		_values = new double[h][w];
		fill(0);
		// TODO Auto-generated constructor stub
	}
	int height() {
		return _height;
	}
	int width() {
		return _width;
	}
	void fill(double value) {
		if (Double.isNaN(value)) {
			System.out.println("Matrix. fill() Your value is NaN!");
			return;
		}
		for ( int h =0; h < _height; h++ ) {
			for (int w = 0; w < _width; w++) {
				_values[h][w] = value;
			}
		}
		
	}
	double get(int x, int y) throws MatrixOutOfRangeException{
		if (x >= _width || y >= _height) {
			System.out.println("Matrix. get() BadSizeError: "+x+"x" + y+ " - out of max:"+(_width-1) + "x" + (_height-1));
			throw new MatrixOutOfRangeException(x,y);
			
		}
		else {
			return _values[y][x];
		}
		
		
	}
	boolean set(int x, int y, double value) throws MatrixOutOfRangeException{
		if (Double.isNaN(value)) {
			System.out.println("Matrix. set() Your value is NaN!");
			return false;
		}
		if (x >= _width && y <= _height) {
			System.out.println("Matrix. set() BadSizeError: "+x+"x" + y+ " - out of max:"+_width + "x" + _height);
			throw new MatrixOutOfRangeException(x,y);
			
		}
		else {
			_values[y][x] = value;
			return true;
		}
		
	}
	boolean increase(int x, int y, double value) throws MatrixOutOfRangeException{
		if (Double.isNaN(value)) {
			System.out.println("Matrix. increase() Your value is NaN!");
			return false;
		}
		if (x >= _width && y <= _height) {
			System.out.println("Matrix. increase() BadSizeError: "+x+"x" + y+ " - out of max:"+_width + "x" + _height);
			throw new MatrixOutOfRangeException(x,y);
			
		}
		else {
			
			if (Double.isNaN(_values[y][x])) {
				System.out.println("Matrix. increase() Matrix value is NaN!");
				return false;
			}
			else{
				_values[y][x] += value;
				return true;
			}
			
		}
	
	}
	Vector multiply(Vector vector) throws MatrixMyltiplyException{
		if (_width == vector.len()) {
			Vector result = new Vector(vector.len());
			
			for (int res =0; res< vector.len(); res++) {
					for (int i =0 ; i < height(); i++) {
						try {
							result.increase(i, vector.get(i) *get(res,i)); 
						}
						catch(Exception ex) {
							System.out.println(ex.getMessage());
							System.out.println("Matrix. myltuply(vec) Ops! something was broked! It our error...");
							return result;
						}
						
					}
					
				
			}
			return result;
		}
		else {
			System.out.println("Matrix. myltyply(vec) Impossible!");
			throw new MatrixMyltiplyException();
			//return new Matrix(0,0);
		}
		
	}
	Matrix multiply(Matrix matrix) throws MatrixMyltiplyException{
		if (_width == matrix.height()) {
			Matrix result = new Matrix(_height, matrix.width());
			
			for (int resY =0; resY < result.height(); resY++) {
				for (int resX = 0; resX < result.width();resX ++) {
					for (int i =0 ; i < matrix.height(); i++) {
						try {
							result.increase(resX, resY, get(i,resX)*matrix.get(resY, i));
						}
						catch(MatrixOutOfRangeException ex) {
							System.out.println(ex.getMessage());
							System.out.println("Matrix. myltuply() Ops! something was broked! It our error...");
							return result;
						}
						
					}
				}
			}
			return result;
		}
		else {
			System.out.println("Matrix. myltyply() Impossible!");
			throw new MatrixMyltiplyException();
			//return new Matrix(0,0);
		}
		
	}
	public double [][] getValues() {
		double[][] result = new double[_height][_width];
		for (int y =0; y < _height; y++) {
			for (int x = 0; x < _width; x++) {
				try {
					result[y][x] = get(x,y);
				}
				catch(MatrixOutOfRangeException ex) {
					System.out.println("Matrix. getValues() WTF! "+ex.getMessage());
					return result;
				}
			}
		}
		return result;
	}
	Vector getVector(int y) throws MatrixOutOfRangeException{
		if (-1  <  y && y < _values.length) {
			return new Vector(_values[y]);	
		}
		else {
			throw new MatrixOutOfRangeException(0,y);
		}
		
	}
	public void set(double[][] values) {
		_values = values;
	}
	public Matrix add(Matrix matrix) throws MatrixAddException{
		if (matrix.width() != _width || matrix.height() != _height) {
			System.out.println("Matrix. add() Matrix dont identical!");
			throw new MatrixAddException();
		}
		else{
			Matrix result = copy();
			for (int y =0; y < _height; y++) {
				for (int x = 0; x < _width; x++) {
					try {
						result.increase(x, y, matrix.get(x, y));
					}
					catch(MatrixOutOfRangeException ex) {
						System.out.println("Matrix. add() Ops! it our error. " + ex.getMessage());
					}
				}
			}
			return result;
		}
		
	}
	public Matrix copy() {
		Matrix result = new Matrix(_width, _height);
		for (int y =0; y < _height; y++) {
			for (int x = 0; x < _width; x++) {
				try {
					result.set(x,y, get(x,y));
				}
				catch(MatrixOutOfRangeException ex) {
					System.out.println("Matrix. copy() WTF!" + ex.getMessage());
					return result;
				}
			}
		}
		return result;
	}
	public Matrix boost(double booster) {
		if (Double.isNaN(booster)) {
			System.out.println("Matrix. boost() The buster is NaN!");
			return copy();
		}
		else {
			Matrix result = copy();
			for (int y =0 ; y < _height; y++) {
				for (int x =0; x < _width; x++) {
					try {
						result.set(x, y, get(x,y)*booster);
					}
					catch(MatrixOutOfRangeException ex) {
						System.out.println("Matrix. boost() Ops!" + ex.getMessage());
						return result;
					}
				}
			}
			return result;
		}
		
		
	}
	public Matrix plus(double pluser) {
		if (Double.isNaN(pluser)) {
			System.out.println("Matrix. plus() The pluser is NaN!");
			return copy();
		}
		else {
			Matrix result = copy();
			for (int y =0 ; y < _height; y++) {
				for (int x =0; x < _width; x++) {
					try {
						result.increase(x, y, pluser);
					}
					catch(MatrixOutOfRangeException ex) {
						System.out.println("Matrix. plus() Ops!" + ex.getMessage());
						return result;
					}
				}
			}
			return result;
		}
		
	}
	
	public Matrix transpose() {
		Matrix result = new Matrix(_height, _width);
		for (int y =0; y < _height; y++) {
			for (int x =0; x < _width; x++) {
				try {
					result.set(y, x, get(x,y));
				}
				catch(MatrixOutOfRangeException ex){
					System.out.println("Matrix. transpose() Ops! it our error. " + ex.getMessage());
					return result;
					
				}
			}
		}
		return result;
	}
	@Override 
	public String toString() {
		
		String result= "Matrix(" + _width + "x" + _height+ "){\n";
		for (int y =0 ; y < _height; y++) {
			for (int x =0 ; x < _width; x++) {
				try {
					result += get(x,y);
				}
				catch(MatrixOutOfRangeException ex) {
					System.out.println("\n AAAAAAAAAAAAA! It nevozmozno!!" + ex.getMessage()+"\n");
				}
				if (x < _width-1) {
					result += ",";
				}
			}
			result += "\n";
		}
		result += "}";
		return result;
	}
	

}
