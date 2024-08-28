public class Candidato {
	private int numero;
	private String nome;
	private String municipio;
	private int votos;
	private Partido partido;
	
	public Candidato(int numero, String nome, String municipio, int votos, Partido partido) {
		this.numero = numero;
		this.nome = nome;
		this.municipio = municipio;
		this.votos = votos;
		this.partido = partido;
	}

}
