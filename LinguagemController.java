package com.cristhian.linguagensApi;



import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/linguagens")
public class LinguagemController {
    
    @Autowired
    private LinguagemRepository repositorio;

    @GetMapping
    public List<Linguagem> obterLinguagens() {
        List<Linguagem> linguagens = repositorio.findByOrderByRanking();
        return linguagens;
    }

    @GetMapping("/buscar")
    public List<Linguagem> pesquisarLinguagem(@RequestParam(value="txt", defaultValue="") String txt){
        txt = URL.decomeParam(txt);
        List<Linguagem> linguagens = repositorio.findByTitleContainingIgnoreCase(txt);
        return linguagens;
    }

    @GetMapping("/{id}")
    public Linguagem obterLinguagemPorID(@PathVariable String id) {
        Optional<Linguagem> linguagem = repositorio.findById(id);
        return linguagem.orElseThrow(() -> new ObjectNotFoundException("linguagem não encontrada"));
    }

    @PostMapping
    public ResponseEntity<Linguagem> cadastrarLinguagem(@RequestBody Linguagem linguagem) {
        Linguagem linguagemSalva = repositorio.save(linguagem);
        return new ResponseEntity<Linguagem>(linguagemSalva, HttpStatus.CREATED);
    } 

   
    @DeleteMapping("/{id}")
    public void deletarLinguagem(@PathVariable String id){
        obterLinguagemPorID(id);
       repositorio.deleteById(id);

    }

    @DeleteMapping("/deleteT/{title}")
    public void deletarLinguagempornome(@PathVariable String title){
       repositorio.findByTitleContainingIgnoreCase(title);
       repositorio.deleteByTitleContainingIgnoreCase(title);
       

    }

    
    @PutMapping("{id}")
    public Linguagem update(@RequestBody Linguagem linguagem, @PathVariable String id) {
		Linguagem novalinguagem = obterLinguagemPorID(id);
		updateData(novalinguagem, linguagem);
		return repositorio.save(novalinguagem);
		}

    private void updateData(Linguagem novalinguagem, Linguagem linguagem) {
		novalinguagem.setTitle(linguagem.getTitle());
		novalinguagem.setImage(linguagem.getImage());
        novalinguagem.setRanking(linguagem.getRanking());
		
	}

}