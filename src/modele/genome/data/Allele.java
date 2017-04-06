package modele.genome.data;

public enum Allele{

	A('A', 'a', false), C('C', 'c', false), T('T', 't', false), G('G', 'g', false), N('N', 'n', true), Y('Y', 'y',
			true), K('K', 'k', true), M('M', 'm', true), S('S', 's', true), W('W', 'w', true), R('R', 'r',
					true), B('B', 'b', true), D('D', 'd', true), H('H', 'h', true), V('V', 'v', true);

	public static final Allele[] HOMOA = { Allele.A, Allele.A };
	public static final Allele[] HOMOG = { Allele.G, Allele.G };
	public static final Allele[] HOMOC = { Allele.C, Allele.C };
	public static final Allele[] HOMOT = { Allele.T, Allele.T };
	public static final Allele[] HETEROAG = { Allele.A, Allele.G };
	
	public static final Allele[] YREPLACE = {Allele.C, Allele.T};
	public static final Allele[] RREPLACE = {Allele.A, Allele.G};
	public static final Allele[] KREPLACE = {Allele.G, Allele.T};
	public static final Allele[] MREPLACE = {Allele.C, Allele.A};
	public static final Allele[] SREPLACE = {Allele.G, Allele.C};
	public static final Allele[] WREPLACE = {Allele.A, Allele.T};
	public static final Allele[] B1REPLACE = {Allele.Y, Allele.G};
	public static final Allele[] B2REPLACE = {Allele.S, Allele.T};
	public static final Allele[] B3REPLACE = {Allele.K, Allele.C};
	public static final Allele[] D1REPLACE = {Allele.R, Allele.T};
	public static final Allele[] D2REPLACE = {Allele.G, Allele.W};
	public static final Allele[] D3REPLACE = {Allele.K, Allele.A};
	public static final Allele[] H1REPLACE = {Allele.M, Allele.T};
	public static final Allele[] H2REPLACE = {Allele.W, Allele.C};
	public static final Allele[] H3REPLACE = {Allele.Y, Allele.A};
	public static final Allele[] V1REPLACE = {Allele.R, Allele.C};
	public static final Allele[] V2REPLACE = {Allele.G, Allele.M};
	public static final Allele[] V3REPLACE = {Allele.S, Allele.A};

	private char primarySymbol = ' ';
	private char secondarySymbol = ' ';
	private boolean wildCard;

	private Allele(char p_symbol, char s_symbol, boolean wildCard) {
		this.primarySymbol = p_symbol;
		this.secondarySymbol = s_symbol;
		this.wildCard = wildCard;
	}

	public char getPrimarySymbol() {
		return this.primarySymbol;
	}

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
