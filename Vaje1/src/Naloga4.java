
public class Naloga4 {
	public static double[][] zmnozi(double[][] a, double[][] b){
		double[][] zmnozek = new double[a.length][b[0].length];
		
		for (int row = 0; row < a.length; row++){
			for (int col = 0; col < b[0].length; col++){
				int element = 0;
				for (int i=0; i<b.length; i++){
					element += a[row][i] * b[i][col];
				}
				zmnozek[row][col] = element;
			}
		}
		return zmnozek;
	}
	
	public static void main(String[] args){
		double[][] matrika1 = {{1,2},{2,1},{0,4}};
		double[][] matrika2 = {{1,2,3},{3,4,5}};
		
		double[][] rezultat = zmnozi(matrika1, matrika2);
		
		for (int row = 0; row < rezultat.length; row++){
			for (int col = 0; col < rezultat[0].length; col++){
				System.out.println(rezultat[row][col]);
			}
		}
	}
}