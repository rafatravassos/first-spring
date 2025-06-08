package br.com.alura.screenmatch.services;

public interface iConverteDados {
    <T> T obeterDados(String json, Class<T> classe);
}
