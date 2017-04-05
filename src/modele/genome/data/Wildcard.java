package modele.genome.data;

public enum Wildcard implements BaseAzote{
	N('N', 'n'), Y('Y', 'y'), K('K', 'k'), M('M', 'm'), S('S', 's'), W('W', 'w'), R('R', 'r'), B('B', 'b'), D('D',
			'd'), H('H', 'h'), V('V', 'v');

	private char primarySymbol = ' ';
	private char secondarySymbol = ' ';

	private Wildcard(char p_symbol, char s_symbol) {
		this.primarySymbol = p_symbol;
		this.secondarySymbol = s_symbol;
	}

	@Override
	public char getPrimarySymbol() {
		return this.primarySymbol;
	}

	@Override
	public char getSecondarySymbol() {
		return this.secondarySymbol;
	}

	@Override
	public String toString() {
		return new Character(primarySymbol).toString();
	}

}
