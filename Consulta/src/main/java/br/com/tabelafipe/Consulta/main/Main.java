package br.com.tabelafipe.Consulta.main;

import br.com.tabelafipe.Consulta.model.Dados;
import br.com.tabelafipe.Consulta.model.Modelos;
import br.com.tabelafipe.Consulta.model.Veiculo;
import br.com.tabelafipe.Consulta.service.ConsumoApi;
import br.com.tabelafipe.Consulta.service.ConverteDados;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    Scanner leitor = new Scanner(System.in);

    ConsumoApi consumoApi = new ConsumoApi();
    ConverteDados converteDados = new ConverteDados();

    private final String URL = "https://parallelum.com.br/fipe/api/v1/";

    public void exibeDados() {

        System.out.println("\n***OPÇÕES***");
        System.out.println("\n- Carro");
        System.out.println("- Moto");
        System.out.println("- Caminhão");
        System.out.print("\nEscolha uma opção: ");
        var opcao = leitor.next();
        String endereco;

        if (opcao.toLowerCase().contains("carr")) {
            endereco = URL + "carros/marcas";
        } else if (opcao.toLowerCase().contains("mot")) {
            endereco = URL + "motos/marcas";
        } else {
            endereco = URL + "caminhoes/marcas";
        }

        var json = consumoApi.obterDados(endereco);

        var arquivoDesserializado = converteDados.obterLista(json, Dados.class);
        arquivoDesserializado.stream()
                .sorted(Comparator.comparing(Dados::codigo))
                .forEach(System.out::println);

        System.out.print("\nInforme o código da marca: ");
        var codigoMarca = leitor.next();

        endereco = endereco + "/" + codigoMarca + "/modelos";
        json = consumoApi.obterDados(endereco);

        var enderecoComMarca = converteDados.obterDados(json, Modelos.class);
        enderecoComMarca.modelos().stream()
                .sorted(Comparator.comparing(Dados::codigo))
                .forEach(System.out::println);

        System.out.print("\nInforme o código do modelo para consulta: ");
        var codigoModelo = leitor.next();

        endereco = endereco + "/" + codigoModelo + "/anos";
        json = consumoApi.obterDados(endereco);
        List<Dados> anos = converteDados.obterLista(json, Dados.class);
        List<Veiculo> listaDePrecos = new ArrayList<>();


        for (int i = 0; i < anos.size(); i++) {
            endereco = endereco + "/" + anos.get(i).codigo();
            json = consumoApi.obterDados(endereco);
            Veiculo veiculo = converteDados.obterDados(json, Veiculo.class);
            listaDePrecos.add(veiculo);
        }
        listaDePrecos.forEach(System.out::println);
    }
}
