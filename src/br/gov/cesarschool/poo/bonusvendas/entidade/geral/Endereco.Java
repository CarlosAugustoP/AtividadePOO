
public class Endereco {
    String logradouro;
    int numero;
    String complemento;
    String cep;
    String cidade;
    String estado;
    String pais;

    public Endereco(String logradouro, int numero, String complemento, String cep, String cidade, String estado, String pais){
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.cep = cep;
        this.cidade = cidade;
        this.estado = estado;
        this.pais = pais;
    }

    // crete getters and setters
    public String getLogradouro(){
        return this.logradouro;
    }
    public void setLogradouro(String logradouro){
        this.logradouro = logradouro;
    }
    public int getNumero(){
        return this.numero;
    }
    public void setNumero(int numero){
        this.numero = numero;
    }
    public String getComplemento(){
        return this.complemento;
    }   
    public void setComplemento(String complemento){
        return this.complemento;
    }
    public String getCep(){
        return this.cep;
    }
    public void setCep(String cep){
        return this.cep;
    }
    public void getCidade(String cidade){
        return this.cidade;
    }
    public String setCidade(){
        return this.cidade;
    }
    public String getEstado(){
        return this.estado;
    }
    public void setEstado(String estado){
        return this.estado;
    }
    public String getPais(){
        return this.pais;
    }
    public void setPais(String pais){
        return this.pais;
    }