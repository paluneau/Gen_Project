package modele.genome;

import java.util.HashSet;
import java.util.Set;

public enum Allele {
	
	A('A', 'a', false), C('C', 'c', false), T('T', 't', false), G('G', 'g', false), N('N', 'n', true), Y('Y', 'y',
			true), K('K', 'k', true), M('M', 'm', true), S('S', 's', true), W('W', 'w', true), R('R', 'r',
					true), B('B', 'b', true), D('D', 'd', true), H('H', 'h', true), V('V', 'v', true);

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
		return this.wildCard;
	}

	private Set<Allele> convertToSet(Allele[] tab) {
		Set<Allele> set = new HashSet<Allele>();

		for (Allele a : tab) {
			set.add(a);
		}

		return set;
	}

	@Override
	public String toString() {
		return new Character(primarySymbol).toString();
	}

}
