package network;

public class Tensor implements BaseTensor{
	private TensorSize _size;
	double[][][] _values;
	Tensor(TensorSize size){
		_size = size;
		_values = new double[_size.depth()][_size.height()][_size.width()];
	}
	public TensorSize getSize(){
		return _size;
	}
	public double get(int x,int y,int z) {
		if (x < _size.width() &&  y < _size.height() && z < _size.depth()) {
			return _values[z][y][x];
		}
		else {
			System.out.println("Tensor. BadSizeError: "+x+"x" + y +"," +z +" - out of max:" + _size );
			return 0;
		}
		
	}
	public boolean set(int x, int y , int z , double value) {
		if (x < _size.width() &&  y < _size.height() && z < _size.depth()) {
			if (Double.isNaN(value)) {
				System.out.println("Tensor. set() Your number is NaN!");
				return false;
			}
			_values[z][y][x] = value;
			return true;
		}
		else {
			System.out.println("Tensor. set() BadSizeError: "+x+"x" + y +"," +z +" - out of max:" + _size );
			return false;
		}
	}
	public void fill(double value) {
		if (Double.isNaN(value)) {
			System.out.println("Tensor. fill() Your number is NaN!");
			return;
		}
		for(int d= 0; d < _size.depth(); d++) {
			for(int y = 0; y < _size.height(); y++) {
				for(int x=0; x < _size.width();x ++) {
					_values[d][y][x] = value;
				}
			}
		}
	}
	
}

interface BaseTensor {
	public TensorSize getSize();
	public double get(int x,int y,int z);
	public boolean set(int x, int y , int z , double value);
	public void fill(double value);
}

class TensorSize{
	private final int _width;
	private final int _height;
	private final int _depth;
	
	TensorSize(int w, int h, int d){
		_width = w;
		_height = h;
		_depth  = d;
		
	}
	TensorSize(){
		_width = 0;
		_height = 0;
		_depth  = 0;
	}
	int depth(){
		return _depth;
	}
	int height(){
		return _height;
	}
	int width(){
		return _width;
	}
	@Override
	public String toString() {
		return new String("size(" + String.valueOf(_width) + "x" + String.valueOf(_height)
		+","+String.valueOf(_depth)+");");

	}
}
