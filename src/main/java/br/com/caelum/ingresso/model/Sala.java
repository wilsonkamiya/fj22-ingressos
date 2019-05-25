package br.com.caelum.ingresso.model;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Created by nando on 03/03/17.
 */
@Entity
public class Sala {

    @Id
    @GeneratedValue
    private Integer id;

    private String nome;
    
    private BigDecimal preco;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Lugar> lugares = new HashSet<>();

    /**
     * @deprecated hibernate only
     */
    @Deprecated
    public Sala() {

    }

    public Sala(String nome) {
        this.nome = nome;
    }
    
    public Sala(String nome, BigDecimal preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public void add(Lugar lugar) {
        this.lugares.add(lugar);
    }

    public Set<Lugar> getLugares() {
        return lugares;
    }

    public void setLugares(Set<Lugar> lugares) {
        this.lugares = lugares;
    }

    public Map<String, List<Lugar>> getMapaDeLugares() {
        if(!this.lugares.isEmpty()){
            return this.lugares.stream().collect(Collectors.groupingBy(Lugar::getFileira,Collectors.toList()));
        }
        return Collections.emptyMap();
    }

    public Integer lugar(String fileira, Integer posicao){
        Optional<Lugar> optional = this.lugares.stream().filter((x) -> fileira.equals(x.getFileira()) && posicao.equals(x.getPosicao())).findFirst();
        return optional.get().getId();
    }
}
