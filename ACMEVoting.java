import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.Scanner;

public class ACMEVoting {
	private Candidatura candidatura;
	private CadastroPartido cadastroPartido;
	private Scanner entrada = new Scanner(System.in);  // Atributo para entrada padrao (teclado)
    private PrintStream saidaPadrao = System.out;   // Guarda a saida padrao - tela (console)
    private final String nomeArquivoEntrada = "input.txt";  // Nome do arquivo de entrada de dados
    private final String nomeArquivoSaida = "output.txt";  // Nome do arquivo de saida de dados


	public ACMEVoting() {
		redirecionaEntrada();    // Redireciona Entrada para arquivos
        redirecionaSaida();    // Redireciona Saida para arquivos
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
		mostrarDadosPartidosPeloNum();
		//5
		mostrarDadosCandidato();
		//6
		mostrarVotosDosPrefeitosDeCertoPartido();
		//7
		//mostrarDadosDoPartidoComMaisCandidatos();
		//8
		//mostrarDadosDoPrefeitoEDoVereadorMaisVotado();
	}

	public void cadastrarPartidos (){
		//Scanner tec = new Scanner(System.in);
		while(true){
			//System.out.println("Digite o número do partido:");
			int num = entrada.nextInt();
			entrada.nextLine();
				if (num == -1) {
				break;
				}

			//System.out.println("Digite o nome do partido:");
			String nome = entrada.nextLine();
			
			Partido partido = new Partido(num, nome);
			if(cadastroPartido.cadastraPartido(partido)){ //Verifica se o paratido já existe
				System.out.println("1:"+ num + ","+ nome);
			}else {
				System.out.println("1: Partido com mesmo número já cadastrado");
			}
		}
	}

	public void cadastrarCandidatos(){
		//Scanner tec = new Scanner(System.in);
		while (true) {
			//System.out.println("Digite o número do candidato:");
			int num = entrada.nextInt();
			if (num == -1) break;
			entrada.nextLine();	
			
			//System.out.println("Digite o nome do candidato:");
			String nome = entrada.nextLine();
			
			//System.out.println("Digite o municipio do candidato:");
			String municipio = entrada.nextLine();
			
			Candidato candidato = new Candidato(num, nome, municipio, null);
			if(candidatura.cadastraCandidato(candidato) == true){ //Verifica se o já existe no município
				System.out.println("2:" + num + "," + nome +"," + municipio);
			}else {
				System.out.println("2: Candidato já cadastrado no município");
			}	
		}
	}

	public void cadastrarVotos(){
		//Scanner tec = new Scanner(System.in);
		while (true) {
			//System.out.println("Digite o numero do candidato:");
			int numCandidato = entrada.nextInt();
			if (numCandidato == -1) break;
			entrada.nextLine();

			//System.out.println("Digite a cidade do candidato:");
			String cidadeCandidato = entrada.nextLine();
			
			//System.out.println("Digite o número de votos do candidato:");
			int numVotos = entrada.nextInt();

			//verificar se o candidato é válido
			if(candidatura.verificaCandidato(numCandidato,cidadeCandidato) == true){
				if (candidatura.adicionarVotos(numCandidato, cidadeCandidato, numVotos) == true && candidatura.mostrarVotos(numCandidato, cidadeCandidato) != -1) {
					System.out.println("3:" + numCandidato + "," + cidadeCandidato + "," + candidatura.mostrarVotos(numCandidato, cidadeCandidato));
				}
			} else {
				System.out.println("3: Votos não puderam ser cadastrados");
			}
		}
	}

	public void mostrarDadosPartidosPeloNum () {
		//Scanner tec = new Scanner(System.in);
			//System.out.println("Digite o número do partido:");
			int numPartido = entrada.nextInt();
			entrada.nextLine();

			cadastroPartido.printaPartido(numPartido);
		
	}

	public void mostrarDadosCandidato () {
		//Scanner tec = new Scanner(System.in);		
			//System.out.println("Digite o numero do candidato: ");
			int numCandidato = entrada.nextInt();
			entrada.nextLine();

			//System.out.println("Digite o município do candidato: ");
			String municipioCandidato = entrada.nextLine();
				
			candidatura.printaCandidato(numCandidato, municipioCandidato);
	}

	public void mostrarVotosDosPrefeitosDeCertoPartido() {
		//Scanner tec = new Scanner(System.in);
		
		
			//System.out.println("Digite o número do partido:");
			String nomePartido = entrada.nextLine();
			entrada.nextLine();
				
			Partido partido = cadastroPartido.consultaPartido(nomePartido);
			
			if (partido == null) {
				System.out.println("6:Nenhum partido encontrado.");
			} else {
				candidatura.printaPrefeitos(partido);
			
		}
	}

	 // Redireciona Entrada de dados para arquivos em vez de teclado
    // Chame este metodo para redirecionar a leitura de dados para arquivos
    private void redirecionaEntrada() {
        try {
            BufferedReader streamEntrada = new BufferedReader(new FileReader(nomeArquivoEntrada));
            entrada = new Scanner(streamEntrada);   // Usa como entrada um arquivo
        } catch (Exception e) {
            System.out.println(e);
        }
        Locale.setDefault(Locale.ENGLISH);   // Ajusta para ponto decimal
        entrada.useLocale(Locale.ENGLISH);   // Ajusta para leitura para ponto decimal
    }

    // Redireciona Saida de dados para arquivos em vez da tela (terminal)
    // Chame este metodo para redirecionar a escrita de dados para arquivos
    private void redirecionaSaida() {
        try {
            PrintStream streamSaida = new PrintStream(new File(nomeArquivoSaida), Charset.forName("UTF-8"));
            System.setOut(streamSaida);             // Usa como saida um arquivo
        } catch (Exception e) {
            System.out.println(e);
        }
        Locale.setDefault(Locale.ENGLISH);   // Ajusta para ponto decimal
    }

    // Restaura Entrada padrao para o teclado
    // Chame este metodo para retornar a leitura de dados para o teclado
    private void restauraEntrada() {
        entrada = new Scanner(System.in);
    }

    // Restaura Saida padrao para a tela (terminal)
    // Chame este metodo para retornar a escrita de dados para a tela
    private void restauraSaida() {
        System.setOut(saidaPadrao);
    }
}