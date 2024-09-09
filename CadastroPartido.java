import java.util.ArrayList;
public class CadastroPartido {
	private ArrayList<Partido> partidos;

	public CadastroPartido() {
		this.partidos = new ArrayList<>();
	}

	public boolean cadastraPartido(Partido p) {
		if(p != null){
			for (Partido partido : partidos) {
				if(partido.getNumero() == p.getNumero()){
					return false;
				}
			}
			partidos.add(p);
			return true;
		}
		return false;
		
	}

	public Partido consultaPartido(String nome) {
		for (Partido partido : partidos) {
			if(partido.getNome().equals(nome)){
				return partido ;
			}
		}
		return null;
	}

	public Partido consultaPartido(int numero) {
		for(Partido partido : partidos){
			if(partido.getNumero() == numero){
				return partido;
			}
		}
		return null;
	}

	public void printaPartido(int numPartido) {
        Partido partido = consultaPartido(numPartido);
        if (partido != null) {
            System.out.println("4: " + numPartido + "," + partido.getNome());
        } else {
            System.out.println("4:Nenhum partido encontrado.");
        }
    }
}

