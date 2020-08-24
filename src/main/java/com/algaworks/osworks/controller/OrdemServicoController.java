package com.algaworks.osworks.controller;

import com.algaworks.osworks.dto.OrdemServicoDTO;
import com.algaworks.osworks.model.Cliente;
import com.algaworks.osworks.model.OrdemServico;
import com.algaworks.osworks.repository.OrdemServicoRepository;
import com.algaworks.osworks.service.OrdemServicoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ordens-servico")
public class OrdemServicoController {

    @Autowired
    private OrdemServicoService ordemServicoService;

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrdemServicoDTO criar(@Valid @RequestBody OrdemServico ordemServico){
        return toModel(ordemServicoService.criar(ordemServico));
    }

    @GetMapping
    public List<OrdemServicoDTO> getOrdensServico(){
        return toCollectionDTO(ordemServicoRepository.findAll());
    }

    @GetMapping("/{ordemServicoId}")
    public ResponseEntity<OrdemServicoDTO> getOrdensServicoById(@PathVariable Long ordemServicoId){
        Optional<OrdemServico> ordemServico = ordemServicoRepository.findById(ordemServicoId);

        if (ordemServico.isPresent()){
            return ResponseEntity.ok(toModel(ordemServico.get()));
        }

        return ResponseEntity.notFound().build();
    }

    private OrdemServicoDTO toModel(OrdemServico ordemServico){
        return modelMapper.map(ordemServico,OrdemServicoDTO.class);
    }

    private List<OrdemServicoDTO> toCollectionDTO(List<OrdemServico> ordensServico){
        return ordensServico.stream().map(ordemServico -> toModel(ordemServico)).collect(Collectors.toList());
    }
}
