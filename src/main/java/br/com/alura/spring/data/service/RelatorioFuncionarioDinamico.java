package br.com.alura.spring.data.service;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import br.com.alura.spring.data.specification.SpecificationFuncionario;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Service
public class RelatorioFuncionarioDinamico {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private FuncionarioRepository repository;

    public RelatorioFuncionarioDinamico(FuncionarioRepository repository) {
        this.repository = repository;
    }

    public void inicial(Scanner scanner){
        System.out.println("Digite o nome");
        String nome = scanner.next();
        if(nome.equalsIgnoreCase("NULL")){
            nome = null;
        }

        System.out.println("Digite o cpf");
        String cpf = scanner.next();
        if(cpf.equalsIgnoreCase("NULL")){
            cpf = null;
        }

        System.out.println("Digite o salario");
        Double salario = scanner.nextDouble();
        if(salario == 0){
            salario = null;
        }

        System.out.println("Digite a data de contratacao");
        String data = scanner.next();
        LocalDate dataContratacao;
        if(data.equalsIgnoreCase("NULL")){
            dataContratacao = null;
        } else {
            dataContratacao = LocalDate.parse(data, formatter);
        }

        List<Funcionario> funcionarios = repository
                .findAll(Specification.where(
                        SpecificationFuncionario.nome(nome))
                        .or(SpecificationFuncionario.cpf(cpf))
                        .or(SpecificationFuncionario.salario(salario))
                        .or(SpecificationFuncionario.dataContratacao(dataContratacao))
                );
        funcionarios.forEach(System.out::println);
    }
}
