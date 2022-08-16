package br.com.alura.spring.data.service;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.FuncionarioProjecao;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Service
public class RelatoriosService {

    private boolean system = true;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final FuncionarioRepository repository;

    public RelatoriosService(FuncionarioRepository repository) {
        this.repository = repository;
    }

    public void inicial(Scanner scanner) {
        while(system) {
            System.out.println("Qual acao de cargo deseja executar");
            System.out.println("0 - Sair");
            System.out.println("1 - Busca funcionário nome");
            System.out.println("2 - Busca funcionário nome, data contratacao e salario maior que");
            System.out.println("3 - Busca funcionário data contratacao");
            System.out.println("4 - Busca funcionário salario");

            int action = scanner.nextInt();

            switch (action) {
                case 1:
                    buscaFuncionarioNome(scanner);
                    break;
                case 2:
                    buscaFuncionarioNomeSalarioMaiorData(scanner);
                    break;
                case 3:
                    buscaFuncionarioDataContratacao(scanner);
                    break;
                case 4:
                    pesquisaFuncionarioSalario();
                    break;
                default:
                    system = false;
                    break;
            }
        }
    }

    private void buscaFuncionarioNome(Scanner scanner){
        System.out.println("Qual nome deseja pesquisar?");
        String nome = scanner.next();
        List<Funcionario> list = repository.findByNome(nome);
        list.forEach(System.out::println);
    }

    private void buscaFuncionarioNomeSalarioMaiorData(Scanner scanner){
        System.out.println("Qual nome deseja pesquisar?");
        String nome = scanner.next();

        System.out.println("Qual data contratacao deseja pesquisar?");
        LocalDate data = LocalDate.parse(scanner.next(), formatter);

        System.out.println("Qual salario deseja pesquisar?");
        BigDecimal salario = new BigDecimal(scanner.next());

        List<Funcionario> list = repository.findNomeSalarioMaiorDataContratacao(nome, salario, data);
        list.forEach(System.out::println);
    }

    private void buscaFuncionarioDataContratacao(Scanner scanner){
        System.out.println("Qual data contratacao deseja pesquisar?");
        LocalDate data = LocalDate.parse(scanner.next(), formatter);

        List<Funcionario> list = repository.findDataContratacaoMaior(data);
        list.forEach(System.out::println);
    }

    private void pesquisaFuncionarioSalario(){
        List<FuncionarioProjecao> list = repository.findFuncionarioSalario();
        list.forEach(f -> System.out.println("Funcionario: id: " + f.getId()
            + " | nome: " + f.getNome() + " | salario: " + f.getSalario()));
    }
}
