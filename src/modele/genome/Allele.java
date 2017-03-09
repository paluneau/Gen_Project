package modele.genome;

public enum Allele {
	A('A'), C('C'), T('T'), G('G');
	
	private char symbol = ' ';
	
	private Allele(char symbol){
		this.symbol = symbol;
	}
	
	public char getSymbol(){
		return this.symbol;
	}
}
