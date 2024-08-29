import java.util.ArrayList;

public class Partido {
	private int numero;
	private String nome;
	private ArrayList<Candidato> candidatos;

	public Partido() {
		this.numero = numero;
		this.nome = nome;
		this.candidatos = new ArrayList<>();
	}

	public void adicionaCandidato(Candidato c) {
		if(c != null) {
			candidatos.add(c);
		}
	}

	// Métodos getters e setters
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

    public ArrayList<Candidato> getCandidatos() {
        return candidatos;
    }
}

