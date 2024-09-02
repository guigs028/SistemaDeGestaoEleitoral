import java.util.Scanner;

public class ACMEVoting {
	private Candidatura candidatura;
	private CadastroPartido cadastroPartido;

	public ACMEVoting() {
		this.candidatura = new Candidatura();
		this.cadastroPartido = new CadastroPartido();
	}

	public void executar() {
		//1
		cadastrarPartidos();
		//2
		cadastrarCandidatos();
		//3
		cadastrarVotos();
		//4
		//mostrarDadosPartidoPeloNum();
		//5
		//mostrarDadosCandidato();
		//6
		//mostrarVotosDosPrefeitosDeCertoPartido();
		//7
		//mostrarDadosDoPartidoComMaisCandidatos();
		//8
		//mostrarDadosDoPrefeitoEDoVereadorMaisVotado();
	}

	public void cadastrarPartidos (){
		Scanner tec = new Scanner(System.in);
		while(true){
			System.out.println("Digite o número do partido:");
			int num = tec.nextInt();
			tec.nextLine();
			if (num == -1) break;
			
			System.out.println("Digite o nome do partido:");
			String nome = tec.nextLine();
			
			Partido partido = new Partido(num, nome);
			if(cadastroPartido.cadastraPartido(partido)){ //Verifica se o paratido já existe
				System.out.println("1:"+ num + ","+ nome);
			}else {
				System.out.println("Partido com mesmo número já cadastrado");
			}
		}
	}

	public void cadastrarCandidatos(){
		Scanner tec = new Scanner(System.in);
		while (true) {
			System.out.println("Digite o número do candidato:");
			int num = tec.nextInt();
			if (num == -1) break;
			tec.nextLine();	
			
			System.out.println("Digite o nome do candidato:");
			String nome = tec.nextLine();
			
			System.out.println("Digite o municipio do candidato:");
			String municipio = tec.nextLine();
			
			Candidato candidato = new Candidato(num, nome, municipio, null);
			if(candidatura.cadastraCandidato(candidato) == true){ //Verifica se o já existe no município
				System.out.println("2:" + num + "," + nome +"," + municipio);
			}else {
				System.out.println("Candidato já cadastrado no município");
			}	
		}
	}

	public void cadastrarVotos(){
		Scanner tec = new Scanner(System.in);
		while (true) {
			System.out.println("Digite o número do candidato:");
			int num = tec.nextInt();
			if (num == -1) break;
			tec.nextLine();

			System.out.println("Digite o nome do candidato:");
			String nome = tec.nextLine();
			
			//verificar se o candidato é válido
		}
	}
}
