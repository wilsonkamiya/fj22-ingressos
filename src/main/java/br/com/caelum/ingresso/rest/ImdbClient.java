package br.com.caelum.ingresso.rest;
 

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import br.com.caelum.ingresso.model.Filme;

@Component
public class ImdbClient {

    public <T>Optional<T> request(Filme filme,Class<T> tClass){

        RestTemplate client = new RestTemplate();
        
        String titulo = filme.getNome().replace(" ", "+");
        String url = String.format("https://omdb-fj22.herokuapp.com/movie?title=%s", titulo);
        System.out.println(url);
        
        try {
            //DetalhesDoFilme detalhesDoFilme = client.getForObject(url, DetalhesDoFilme.class);
        	  // Optional.ofNullable(detalhesDoFilme);
            return  Optional.of(client.getForObject(url, tClass)); 
        }catch (RestClientException e){
          //  logger.error(e.getMessage(), e);
            System.out.println("Erro saporra!"+ e.getMessage());
        	return Optional.empty();
            
        }
    }

}