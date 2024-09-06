import java.util.ArrayList;

public class Candidatura {
	private ArrayList<Candidato> candidatos;

	public Candidatura() {
		this.candidatos = new ArrayList<>();
	}

	public boolean cadastraCandidato(Candidato c) {
		if(c != null){
			for (Candidato candidato : candidatos) {
				if (candidato.getMunicipio().equals(c.getMunicipio()) && candidato.getNumero() == c.getNumero()) {
					return false;
					
					}
				}
				candidatos.add(c);
				return true;
			}
			return false;	
	}

	public boolean verificaCandidato (int c, String cidade) {
		for (Candidato candidato : candidatos) {
			if (candidato.getNumero() == c && candidato.getMunicipio().equals(cidade)){
				return true;
			}
		} return false;
	}

	public boolean adicionarVotos (int numCandidato, String cidade, int votos){
		for (Candidato candidato : candidatos) {
			if (candidato.getNumero() == numCandidato && candidato.getMunicipio().equals(cidade)) {
				candidato.setVotos(candidato.getVotos() + votos);
				return true;
			}
		}return false;
	} 

	public int mostrarVotos (int numCandidato, String cidade){
		for (Candidato candidato : candidatos) {
			if (candidato.getNumero() == numCandidato && candidato.getMunicipio().equals(cidade)) {
				 return candidato.getVotos();
			}
		} return -1;
	}

	public void printaCandidato(int numCandidato, String municipioCandidato) {
        for (Candidato candidato : candidatos) {
            if (candidato.getNumero() == numCandidato && candidato.getMunicipio().equals(municipioCandidato)) {
                System.out.println("5: " + candidato.getNumero() + ", " + candidato.getNome() + ", " + candidato.getMunicipio() + ", " + candidato.getVotos());
                return; 
            }
        } System.out.println("5: Nenhum candidato encontrado.");
    }

	public void printaPrefeitos(Partido partido) {
		boolean temPrefeito = false;
		int numPartido = partido.getNumero();
		String nomePartido = partido.getNome();
	
		for (Candidato candidato : candidatos) {
			int numeroCandidato = candidato.getNumero();
			if (numeroCandidato >= 10 && numeroCandidato < 100) { // Verifica se é um prefeito
				if (numeroCandidato == numPartido) { // Verifica se o número do prefeito é igual ao número do partido
					System.out.println("6: " + nomePartido + "," + candidato.getNumero() + "," + candidato.getNome() + "," + candidato.getMunicipio() + "," + candidato.getVotos());
					temPrefeito = true;
				}
			}
		}
		if (!temPrefeito) {
			System.out.println("Nenhum prefeito encontrado para o partido " + nomePartido);
		}
	}
	
}
