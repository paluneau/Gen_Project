package modele.genome;

public enum Allele {
	A('A', 'a'), C('C', 'c'), T('T', 't'), G('G', 'g'), N('N', 'n');

	private char primarySymbol = ' ';
	private char secondarySymbol = ' ';

	private Allele(char p_symbol, char s_symbol) {
		this.primarySymbol = p_symbol;
		this.secondarySymbol = s_symbol;
	}

	public char getPrimarySymbol() {
		return this.primarySymbol;
	}

	public char getSecondarySymbol() {
		return this.secondarySymbol;
	}

	@Override
	public String toString() {
		return new Character(primarySymbol).toString();
	}
}
