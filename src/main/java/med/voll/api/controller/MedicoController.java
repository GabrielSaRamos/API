package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.aspectj.apache.bcel.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("medicos") //Mapeando a URL /medicos
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    //Codigo para enviar as informações, metodo post. Ou seja cadastrar as informações
    @PostMapping //O metodo post, enviando os dados para API
    @Transactional //É necessario uma transação
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados){
        repository.save(new Medico(dados));
    }

    //Codigo para pegar as informações, metodo get. Ou seja listar as informações
    @GetMapping
    public Page<DadosListagemMedico> listar(@PageableDefault(size = 10) Pageable paginacao) { //Publico pois ira mostrar os dados cadastrados dos medicos
        return repository.findAll(paginacao).map(DadosListagemMedico::new);
    }
    @PutMapping //Quando quero atualizar(alterar) dados na minha API
    @Transactional //É necessario uma transação
    public void atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados) {
            var medico = repository.getReferenceById(dados.id());
            medico.atualizarInformacoes(dados);

    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id){
        repository.deleteById(id);
    }

}
