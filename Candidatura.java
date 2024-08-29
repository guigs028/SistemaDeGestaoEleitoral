import java.util.ArrayList;

public class Candidatura {
	private ArrayList<Candidato> candidatos;

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
}
