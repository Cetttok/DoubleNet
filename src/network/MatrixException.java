package network;

class MatrixOutOfRangeException extends Exception {

	public MatrixOutOfRangeException (int x , int y) {
		// TODO Auto-generated constructor stub
		super("MatrixOutOfRangeException: index out of range, index "+x+"," + "y");
	}

	public MatrixOutOfRangeException (String message, int x ,int y) {
		super("MatrixOutOfRangeException: index out of range, index "+x+"," + "y. Message - " + message);
		// TODO Auto-generated constructor stub
	}

}
class MatrixMyltiplyException extends Exception{
	public MatrixMyltiplyException() {
		super("MatrixMyltiplyException : You cant multiply these matrices.");
	}
	public MatrixMyltiplyException(String message) {
		super("MatrixMyltiplyException : You cant multiply these matrices. Your message - "+ message);
	}
}
class MatrixAddException extends Exception{
	public MatrixAddException() {
		super("MatrixAddException: Matrix dont identical!" );
	}
}