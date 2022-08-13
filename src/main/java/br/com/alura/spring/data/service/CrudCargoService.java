package br.com.alura.spring.data.service;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.repository.CargoRepository;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class CrudCargoService {

    private final CargoRepository repository;
    private boolean system = true;

    public CrudCargoService(CargoRepository repository) {
        this.repository = repository;
    }

    public void inicial(Scanner scanner){
        System.out.println("0 - Sair");
        System.out.println("1 - Salvar");
        System.out.println("2 - Alterar");
        while (system){
            int action = scanner.nextInt();
            switch (action){
                case 1:
                    salvar(scanner);
                    break;
                case 2:
                    atualizar(scanner);
                    break;
                default:
                    system = false;
                    break;
            }
        }
    }

    private void salvar(Scanner scanner){
        System.out.println("Descrição do cargo");
        String descricao = scanner.next();
        Cargo cargo = new Cargo();
        cargo.setDescricao(descricao);
        repository.save(cargo);
        System.out.println("Salvo");
    }

    private void atualizar(Scanner scanner){
        System.out.println("Id");
        int id = scanner.nextInt();
        System.out.println("Nova descrição do cargo");
        String descricao = scanner.next();

        Cargo cargo = new Cargo();
        cargo.setId(id);
        cargo.setDescricao(scanner.next());
        repository.save(cargo);
        System.out.println("Atualizado");
    }
}
