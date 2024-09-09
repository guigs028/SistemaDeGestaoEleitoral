import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.ArrayList;

public class ACMEVoting {
    private Candidatura candidatura;
    private CadastroPartido cadastroPartido;
    private Scanner entrada = new Scanner(System.in);
    private PrintStream saidaPadrao = System.out;
    private final String nomeArquivoEntrada = "input.txt";
    private final String nomeArquivoSaida = "output.txt";

    public ACMEVoting() {
        redirecionaEntrada();
        redirecionaSaida();
        this.candidatura = new Candidatura();
        this.cadastroPartido = new CadastroPartido();
    }

    public void executar() {
        cadastrarPartidos();
        cadastrarCandidatos();
        cadastrarVotos();
        mostrarDadosPartidosPeloNum();
        mostrarDadosCandidato();
        mostrarVotosDosPrefeitosDeCertoPartido();
        mostrarPartidoComMaisCandidatos();
        mostrarPrefeitoEVereadorMaisVotados();
    }

    public void cadastrarPartidos() {
        while (true) {
            int num = entrada.nextInt();
            entrada.nextLine();
            if (num == -1) {
                break;
            }
            String nome = entrada.nextLine();
            Partido partido = new Partido(num, nome);
            if (cadastroPartido.cadastraPartido(partido)) {
                System.out.println("1:" + num + "," + nome);
            }
        }
    }

    public void cadastrarCandidatos() {
        while (true) {
            int num = entrada.nextInt();
            if (num == -1) break;
            entrada.nextLine();
            String nome = entrada.nextLine();
            String municipio = entrada.nextLine();

            int partidoNumero = Integer.parseInt(Integer.toString(num).substring(0, 2)); 
            Partido partido = cadastroPartido.consultaPartido(partidoNumero);

            if (partido != null) {
                Candidato candidato = new Candidato(num, nome, municipio, partido);
                if (candidatura.cadastraCandidato(candidato)) {
                    System.out.println("2:" + num + "," + nome + "," + municipio);
                }
            }
        }
    }

    public void cadastrarVotos() {
        while (true) {
            int numCandidato = entrada.nextInt();
            if (numCandidato == -1) break;
            entrada.nextLine();
            String cidadeCandidato = entrada.nextLine();
            int numVotos = entrada.nextInt();
            entrada.nextLine();  

            if (candidatura.verificaCandidato(numCandidato, cidadeCandidato)) {
                if (candidatura.adicionarVotos(numCandidato, cidadeCandidato, numVotos)) {
                    System.out.println("3:" + numCandidato + "," + cidadeCandidato + "," + candidatura.mostrarVotos(numCandidato, cidadeCandidato));
                }
            }
        }
    }

    public void mostrarDadosPartidosPeloNum() {
        int numPartido = entrada.nextInt();
        entrada.nextLine();
        cadastroPartido.printaPartido(numPartido);
    }

    public void mostrarDadosCandidato() {
        int numCandidato = entrada.nextInt();
        entrada.nextLine();
        String municipioCandidato = entrada.nextLine();
        candidatura.printaCandidato(numCandidato, municipioCandidato);
    }

    public void mostrarVotosDosPrefeitosDeCertoPartido() {
        String nomePartido = entrada.nextLine();
        Partido partido = cadastroPartido.consultaPartido(nomePartido);

        if (partido == null) {
            System.out.println("6:Nenhum partido encontrado.");
        } else {
            candidatura.printaPrefeitos(partido);
        }
    }

    public void mostrarPartidoComMaisCandidatos() {
        ArrayList<PartidoCandidatos> listaPartidos = new ArrayList<>();
    
        for (Candidato candidato : candidatura.getCandidatos()) {
            int numeroPartido = Integer.parseInt(Integer.toString(candidato.getNumero()).substring(0, 2));
            PartidoCandidatos partidoCandidatos = null;
    
            for (PartidoCandidatos pc : listaPartidos) {
                if (pc.getNumeroPartido() == numeroPartido) {
                    partidoCandidatos = pc;
                    break;
                }
            }
    
            if (partidoCandidatos == null) {
                partidoCandidatos = new PartidoCandidatos(numeroPartido);
                listaPartidos.add(partidoCandidatos);
            }
    
            partidoCandidatos.incrementaCandidatos();
        }
    
        Partido partidoComMaisCandidatos = null;
        int maxCandidatos = 0;
    
        for (Partido partido : cadastroPartido.getPartidos()) {
            int numeroPartido = partido.getNumero();
            int quantidadeCandidatos = 0;
    
            for (PartidoCandidatos pc : listaPartidos) {
                if (pc.getNumeroPartido() == numeroPartido) {
                    quantidadeCandidatos = pc.getQuantidadeCandidatos();
                    break;
                }
            }
    
            if (quantidadeCandidatos > maxCandidatos) {
                maxCandidatos = quantidadeCandidatos;
                partidoComMaisCandidatos = partido;
            }
        }
    
        if (partidoComMaisCandidatos != null) {
            System.out.println("7:" + partidoComMaisCandidatos.getNumero() + "," + partidoComMaisCandidatos.getNome() + "," + maxCandidatos);
        } else {
            System.out.println("7:Nenhum partido com candidatos.");
        }
    }    

    public void mostrarPrefeitoEVereadorMaisVotados() {
        Candidato prefeitoMaisVotado = null;
        Candidato vereadorMaisVotado = null;
    
        for (Candidato candidato : candidatura.getCandidatos()) {
            int numeroCandidato = candidato.getNumero();
            int votos = candidato.getVotos();
    
            if (numeroCandidato >= 10 && numeroCandidato <= 99) {
                if (prefeitoMaisVotado == null || votos > prefeitoMaisVotado.getVotos()) {
                    prefeitoMaisVotado = candidato;
                }
            } else if (numeroCandidato >= 10000 && numeroCandidato <= 99999) {
                if (vereadorMaisVotado == null || votos > vereadorMaisVotado.getVotos()) {
                    vereadorMaisVotado = candidato;
                }
            }
        }
    
        boolean encontrouCandidato = false;
    
        if (prefeitoMaisVotado != null) {
            System.out.println("8:" + prefeitoMaisVotado.getNumero() + "," + prefeitoMaisVotado.getNome() + "," + prefeitoMaisVotado.getMunicipio() + "," + prefeitoMaisVotado.getVotos());
            encontrouCandidato = true;
        }
    
        if (vereadorMaisVotado != null) {
            System.out.println("8:" + vereadorMaisVotado.getNumero() + "," + vereadorMaisVotado.getNome() + "," + vereadorMaisVotado.getMunicipio() + "," + vereadorMaisVotado.getVotos());
            encontrouCandidato = true;
        }
    
        if (!encontrouCandidato) {
            System.out.println("8:Nenhum candidato encontrado.");
        }
    }
    
    private void redirecionaEntrada() {
        try {
            BufferedReader streamEntrada = new BufferedReader(new FileReader(nomeArquivoEntrada));
            entrada = new Scanner(streamEntrada);
        } catch (Exception e) {
            System.out.println(e);
        }
        Locale.setDefault(Locale.ENGLISH);
        entrada.useLocale(Locale.ENGLISH);
    }

    private void redirecionaSaida() {
        try {
            PrintStream streamSaida = new PrintStream(new File(nomeArquivoSaida), Charset.forName("UTF-8"));
            System.setOut(streamSaida);
        } catch (Exception e) {
            System.out.println(e);
        }
        Locale.setDefault(Locale.ENGLISH);
    }

    private void restauraEntrada() {
        entrada = new Scanner(System.in);
    }

    private void restauraSaida() {
        System.setOut(saidaPadrao);
    }
}
