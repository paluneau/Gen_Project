package modele.genome.data;

import utils.Mapable;

public enum Wildcard implements Mapable<Allele[], Allele> {
	Y(Allele.Y, Allele.YREPLACE), K(Allele.K, Allele.KREPLACE), M(Allele.M, Allele.MREPLACE), S(Allele.S,
			Allele.SREPLACE), W(Allele.W, Allele.WREPLACE), R(Allele.R, Allele.RREPLACE), B1(Allele.B,
					Allele.B1REPLACE), B2(Allele.B, Allele.B2REPLACE), B3(Allele.B, Allele.B3REPLACE), D1(Allele.D,
							Allele.D1REPLACE), D2(Allele.D, Allele.D2REPLACE), D3(Allele.D, Allele.D3REPLACE), H1(
									Allele.H, Allele.H1REPLACE), H2(Allele.H, Allele.H2REPLACE), H3(Allele.H,
											Allele.H3REPLACE), V1(Allele.V, Allele.V1REPLACE), V2(Allele.V,
													Allele.V2REPLACE), V3(Allele.V, Allele.V3REPLACE);

	private Allele wildCard = null;
	private Allele[] replacement = null;

	private Wildcard(Allele wildCard, Allele[] replace) {
		this.wildCard = wildCard;
		this.replacement = replace;
	}

	@Override
	public Allele[] getKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Allele getValue() {
		// TODO Auto-generated method stub
		return null;
	}

}
