public class Candidato {
	private int numero;
	private String nome;
	private String municipio;
	private int votos;
	private Partido partido;
	
	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public int getVotos() {
		return votos;
	}

	public void setVotos(int votos) {
		this.votos = votos;
	}

	public Partido getPartido() {
		return partido;
	}

	public void setPartido(Partido partido) {
		this.partido = partido;
	}

	public Candidato() {
		this.numero = numero;
		this.nome = nome;
		this.municipio = municipio;
		this.votos = votos;
		this.partido = partido;
	}

}
