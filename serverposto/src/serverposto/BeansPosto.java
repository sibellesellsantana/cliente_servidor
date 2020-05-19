package serverposto;

import java.io.Serializable;

public class BeansPosto implements Serializable { //modelo beans serve para criar a classe, os atributos, encapsular e criar getters e setters
	
	// atributos privates, somente conseguiremos atribuir valores através dos métodos set e get
	
	private int codigo;
	private String nome;
	private String endereco;
	private String telefone;
	private String pesquisa; 
	
	public BeansPosto(int codigo, String nome, String endereco, String telefone) {
		this.codigo = codigo;
		this.nome = nome;
		this.endereco = endereco;
		this.telefone = telefone;
	}
	
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	} 
	
	public String getEndereco() {
		return endereco;
	}
	
	public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTelefone() {
    	return telefone;
    }
    
    public void setPesquisa(String pesquisa) {
        this.pesquisa = pesquisa;
    }

    public String getPesquisa() {
    	return pesquisa;
    }
    
    
    public String toString(){
    	  return codigo+" -- "+nome+" -- "+endereco+" -- "+telefone+" -- ";
    	}
    
    
	public boolean setVisible() {
	 	return true;
	}
	
}
