public class Endereco {
    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;

    public Endereco(String cep) {
        this.cep = cep;
    }

    public Endereco(CepEndereco meuEndereco) {
        this.cep = meuEndereco.cep();
        this.logradouro = meuEndereco.logradouro();
        this.complemento = meuEndereco.complemento();
        this.bairro = meuEndereco.bairro();
        this.cidade = meuEndereco.localidade();
        this.estado = meuEndereco.uf();
    }

    public String getCep() {
        return cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    @Override
    public String toString() {
        return "Endereco{" +
                "cep='" + cep + '\'' +
                ", logradouro='" + logradouro + '\'' +
                ", complemento='" + complemento + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cidade='" + cidade + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}
