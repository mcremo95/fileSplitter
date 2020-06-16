package back;

public class Generic<T, K> {
	private T path;
	private T nameFile;
	private T mode;
	private K grandezza;
	
	public Generic(T p, T n, T m, K g) {
		this.path = p;
		this.nameFile = n;
		this.mode = m;
		this.grandezza = g;
	}

	public T getPath() {
		return path;
	}
	
	public void setPath(T path) {
		this.path = path;
	}
	
	public T getNameFile() {
		return nameFile;
	}
	
	public void setNameFile(T nameFile) {
		this.nameFile = nameFile;
	}
	
	public T getMode() {
		return mode;
	}
	
	public void setMode(T mode) {
		this.mode = mode;
	}
	
	public K getGrandezza() {
		return grandezza;
	}
	
	public void setGrandezza(K grandezza) {
		this.grandezza = grandezza;
	}

	@Override
	public String toString() {
		return "Generic [path=" + path + ", nameFile=" + nameFile + ", mode=" + mode + ", grandezza=" + grandezza + "]";
	}
}
