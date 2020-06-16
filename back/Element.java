package back;

public class Element {
		private String path;
		private String nameFile;
		private String mode;
		private int grandezza;
		
		public Element() {}
		
		/**
		 * Costruttore
		 * @param p path del file
		 * @param n nome del file
		 * @param m modalità di split
		 * @param g grandezza dello split
		 */
		public Element(String p, String n, String m, int g) {
			this.path = p;
			this.nameFile = n;
			this.mode = m;
			this.grandezza = g;
		}
		
		/**
		 * @return the path
		 */
		public String getPath() {
			return path;
		}
		/**
		 * @param path the path to set
		 */
		public void setPath(String path) {
			this.path = path;
		}
		/**
		 * @return the nameFile
		 */
		public String getNameFile() {
			return nameFile;
		}
		/**
		 * @param nameFile the nameFile to set
		 */
		public void setNameFile(String nameFile) {
			this.nameFile = nameFile;
		}
		/**
		 * @return the mode
		 */
		public String getMode() {
			return mode;
		}
		/**
		 * @param mode the mode to set
		 */
		public void setMode(String mode) {
			this.mode = mode;
		}
		
		@Override
		public String toString() {
			return "ElementFile [path=" + path + ", nameFile=" + nameFile + ", mode=" + mode + "]";
		}

		/**
		 * @return the grandezza
		 */
		public int getGrandezza() {
			return grandezza;
		}

		/**
		 * @param grandezza the grandezza to set
		 */
		public void setGrandezza(int grandezza) {
			this.grandezza = grandezza;
		}
}
