package br.com.caelum.ingresso.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Sessao {

    @Id
    @GeneratedValue
    private Integer id;
    
    @NotNull
    @DateTimeFormat(pattern="HH:mm")
    private LocalTime horario;

    @ManyToOne
    private Sala sala;

    @ManyToOne
    private Filme filme;
    
    @ManyToOne
    private Lugar lugar;
    
    @NotNull  
    private BigDecimal preco = new BigDecimal("0.0");
    
    @OneToMany(mappedBy = "sessao", fetch = FetchType.EAGER)
    private Set<Ingresso> ingressos = new HashSet<>();
    
    public Lugar getLugar() {
		return lugar;
	}

	public void setLugar(Lugar lugar) {
		this.lugar = lugar;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalTime getHorario() {
		return horario;
	}

	public void setHorario(LocalTime horario) {
		this.horario = horario;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public Filme getFilme() {
		return filme;
	}

	public void setFilme(Filme filme) {
		this.filme = filme;
	}
	
	public BigDecimal getPreco() {
		return preco.setScale(2,RoundingMode.HALF_UP);
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	/**
    * @deprecated hibernate only
    */
    public Sessao() {
    }
    
    public Sessao(LocalTime horario, Filme filme, Sala sala) {
        this.horario = horario;
        this.setFilme(filme);
        this.sala = sala;
        this.preco = sala.getPreco().add(filme.getPreco());
    }
    
    public Sessao(LocalTime horario, Filme filme, Sala sala,BigDecimal preco) {
        this.horario = horario;
        this.setFilme(filme);
        this.sala = sala;
        this.preco = sala.getPreco().add(filme.getPreco());
    }

    public LocalTime getHorarioTermino() {
        return this.horario.plusMinutes(filme.getDuracao().toMinutes());
    }
 
    public Map<String, List<Lugar>> getMapaDeLugares(){
    	return sala.getMapaDeLugares();
    }
    
    public boolean isDisponivel(Lugar lugarSelecionado) {
        return ingressos.stream().map(Ingresso::getLugar).noneMatch(lugar -> lugar.equals(lugarSelecionado));
    }
    
	public void setIngressos(Set<Ingresso> ingresso) {
		this.ingressos = ingresso;
	}

	public Set<Ingresso> getIngressos() {
		return ingressos;
	}
    
}