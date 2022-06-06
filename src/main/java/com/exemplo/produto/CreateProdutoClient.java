package com.exemplo.produto;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CreateProdutoClient {

	public static void main(String[] args) {
		
		ProdutoInputDTO input = new ProdutoInputDTO();
		input.setNome("Maracujá");
		input.setDescricao("Maracujá amarelo");
		String inputJson = toJson(input);
		
		HttpRequest request = HttpRequest.newBuilder().uri(newUri("http://localhost:8081/v1/Produto"))
												      .header("Accept", "application/json")
												      .header("Content-Type", "application/json")
												      .POST(BodyPublishers.ofString(inputJson))
												      .build();
		HttpClient client = HttpClient.newHttpClient();
		HttpResponse<String> response = send(client, request);
		
		ProdutoDTO dto = toDto(response);
		System.out.println(dto);
		
	}

	private static ProdutoDTO toDto(HttpResponse<String> response) {
		try {
			return new ObjectMapper().readValue(response.body(), ProdutoDTO.class);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	private static String toJson(ProdutoInputDTO input) {
		try {
			return new ObjectMapper().writeValueAsString(input);
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
