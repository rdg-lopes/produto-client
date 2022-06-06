package com.exemplo.produto;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SearchProdutosClient {

	public static void main(String[] args) {
		
		HttpRequest request = HttpRequest.newBuilder().uri(newUri("http://localhost:8081/v1/Produto"))
				                                      .header("Accept", "application/json")
				                                      .GET()
				                                      .build();
		HttpClient client = HttpClient.newHttpClient();
		HttpResponse<String> response = send(client, request);
		List<ProdutoDTO> listProdutos = toListDto(response);
		listProdutos.forEach(System.out::println);
	}

	private static List<ProdutoDTO> toListDto(HttpResponse<String> response) {
		try {
			return new ObjectMapper().readValue(response.body(), new TypeReference<List<ProdutoDTO>>() {});
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	private static HttpResponse<String> send(HttpClient client, HttpRequest request) {
		try {
			return client.send(request, BodyHandlers.ofString());
		} catch (IOException | InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	private static URI newUri(String uri) {
		try {
			return new URI(uri);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
	}
	
}
