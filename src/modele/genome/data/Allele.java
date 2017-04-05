package modele.genome.data;

public enum Allele implements BaseAzote {

	A('A', 'a', false), C('C', 'c', false), T('T', 't', false), G('G', 'g', false), N('N', 'n', true), Y('Y', 'y',
			true), K('K', 'k', true), M('M', 'm', true), S('S', 's', true), W('W', 'w', true), R('R', 'r',
					true), B('B', 'b', true), D('D', 'd', true), H('H', 'h', true), V('V', 'v', true);

	public static final Allele[] HOMOA = { Allele.A, Allele.A };
	public static final Allele[] HOMOG = { Allele.G, Allele.G };
	public static final Allele[] HOMOC = { Allele.C, Allele.C };
	public static final Allele[] HOMOT = { Allele.T, Allele.T };
	public static final Allele[] HETEROAG = { Allele.A, Allele.G };

	private char primarySymbol = ' ';
	private char secondarySymbol = ' ';
	private boolean wildCard;

	private Allele(char p_symbol, char s_symbol, boolean wildCard) {
		this.primarySymbol = p_symbol;
		this.secondarySymbol = s_symbol;
		this.wildCard = wildCard;
	}

	@Override
	public char getPrimarySymbol() {
		return this.primarySymbol;
	}

	@Override
	public char getSecondarySymbol() {
		return this.secondarySymbol;
	}

	public boolean isWildCard() {
		return wildCard;
	}

	@Override
	public String toString() {
		return new Character(primarySymbol).toString();
	}

}
