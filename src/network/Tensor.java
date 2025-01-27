package network;

class Tensor{
	Matrix[] values;
	final TensorSize mSize;
	Tensor[] parents;
	Tensor(Matrix[] data, Tensor[] creators ){
		values = data.clone();
		for (int i = 0; i < values.length; i++) {
			values[i] = values[i].clone();
		}
		parents = creators;
		if (values.length > 0) {			
			mSize = new TensorSize(values[0].width(), values[0].height(), values.length);
			for (int i = 0; i < values.length; i ++) {
				if (mSize.height() != values[i].height() | mSize.width() != values[i].width() ) {
					System.out.println("Tensor. Tensor(data,creators) your tensor(3d) is not cube for "+ mSize.toString());
				}
			}
		}
		else {
			System.out.println("Tensor. Tensor(data,creators) your data is null. Fuck about warning!");
			mSize = new TensorSize();
		}
	}
	Tensor(TensorSize size){
		values = new Matrix[size.depth()];
		for (int d =0; d < size.depth(); d++) {
			values[d] = new Matrix(size.width(), size.height());
		}
		mSize = new TensorSize(values[0].width(), values[0].height(), values.length);
		parents = new Tensor[0];
	}
	
	Matrix getMatrix(int i) throws TensorOutOfRangeException{
		if (i >= 0 && i < values.length) {
			return values[i];
		}
		else {
			throw new TensorOutOfRangeException(i);
		}
	}
	void fill(double value) {
		if (Double.isNaN(value)) {
			System.out.println("Tensor. fill() Your value is NaN. You doing it every day, but i cant fill only numbers (filled '0').");
			value = 0;
		}
		for (int i = 0 ; i < values.length; i++) {
			values[i].fill(value);
		}
	}
	boolean setMatrix(int index , Matrix matrix) throws TensorOutOfRangeException{
		if (index >= 0 && index < values.length) {
			if (matrix.height() == mSize.height() && matrix.width() == mSize.width()) {
				values[index] = matrix.clone();
				return true;
			}
			else {				
				System.out.println("Tensor. setMatrix() bad space(main and this size dont contans)");
				return false;
			}
		}
		else {
			throw new TensorOutOfRangeException(index);
		}
	}
	Tensor add(Tensor other) throws TensorAddException{
		if (other.mSize.compare(mSize)) {
			Tensor result = new Tensor (values.clone(),new Tensor[]{this,other});
			for (int i = 0 ; i < values.length; i++) {
				
				try{
					result.setMatrix(i, result.getMatrix(i).add(other.getMatrix(i)));
					
				}
				catch(MatrixAddException ex) {
					System.out.println("Tensor. add() Oh. I tired for check everything.. "+ ex.getMessage());
					return result;
				}
				catch(TensorOutOfRangeException ex) {
					System.out.println("Tensor. add() Oh. I tired for check everything for double.. "+ ex.getMessage());
					return result;
				}
			}			
			return result;
		}
		else {
			throw new TensorAddException();
		}
	}
	@Override
	public String toString() {
		String result = "Tensor " + mSize.toString() + "\n{\n";
		for (int i = 0 ; i < values.length; i++) {
			result += values[i].toString();
		}
		result += "\n}\n";
		return result;
	}

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
	boolean compare(TensorSize size) {
		if (_width == size._width & _height == size.height() & _depth == size.depth()) {
			return true;
		}
		else {
			return false;
		}
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
class TensorOutOfRangeException extends Exception{
	TensorOutOfRangeException(int i ){
		super("TensorOutOfRangeException: Index " + i + " out of range!");
	}
}
class TensorAddException extends Exception{
	TensorAddException(){
		super("TensorAddException: Your tensors is not compare. Go to sleep. Please!");
		
	}
}