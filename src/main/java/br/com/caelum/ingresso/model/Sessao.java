package br.com.caelum.ingresso.model;

import java.math.BigDecimal;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
    
    private BigDecimal preco;


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
		return preco;
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
 
}