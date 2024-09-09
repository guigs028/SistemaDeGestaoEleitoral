public class PartidoCandidatos {
        private int numeroPartido;
        private int quantidadeCandidatos;
    
        public PartidoCandidatos(int numeroPartido) {
            this.numeroPartido = numeroPartido;
            this.quantidadeCandidatos = 0;
        }
    
        public int getNumeroPartido() {
            return numeroPartido;
        }
    
        public int getQuantidadeCandidatos() {
            return quantidadeCandidatos;
        }
    
        public void incrementaCandidatos() {
            this.quantidadeCandidatos++;
        }  
}
