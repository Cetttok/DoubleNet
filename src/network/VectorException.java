package network;


class VectorOutOfRangeException extends Exception {

	public VectorOutOfRangeException (int x) {
		// TODO Auto-generated constructor stub
		super("VectorOutOfRangeException: index out of range, index "+x);
	}

	public VectorOutOfRangeException (String message, int x) {
		super("VectorOutOfRangeException: index out of range, index "+x+". Message - " + message);
		// TODO Auto-generated constructor stub
	}

}
class VectorMyltiplyException extends Exception{
	public VectorMyltiplyException() {
		super("VectorMyltiplyException : You cant multiply thats.");
	}
	public VectorMyltiplyException(String message) {
		super("VectorMyltiplyException : You cant multiply thats. Your message - "+ message);
	}
}
class VectorAddException extends Exception{
	public VectorAddException() {
		super("MatrixAddException: Vector dont identical!" );
	}
}
