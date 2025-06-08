package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.models.DadosSerie;
import br.com.alura.screenmatch.models.DadosTemporada;
import br.com.alura.screenmatch.services.ConsumoApi;
import br.com.alura.screenmatch.services.ConverteDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private Scanner leitor = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "http://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=60482227" ;
    private final String SEASON = "&season=";

    public void exibeMenu() {
        System.out.print("Digite o nome da serie: ");
        var nomeSerie = leitor.nextLine();
        var json = consumo.obterDados(ENDERECO +
                nomeSerie.replace(" ", "+") +
                API_KEY);

        DadosSerie dados = conversor.obeterDados(json, DadosSerie.class);
        System.out.println(dados);

        List<DadosTemporada> temporadas = new ArrayList<>();
        for (int i = 1; i <= dados.totalDeTemporadas(); i++) {
            json = consumo.obterDados(ENDERECO +
                    nomeSerie.replace(" ", "+") +
                    SEASON + i +
                    API_KEY);
            DadosTemporada dadosTemporada = conversor.obeterDados(json, DadosTemporada.class);
            temporadas.add(dadosTemporada);
        }
        temporadas.forEach(System.out::println);

    }
}
