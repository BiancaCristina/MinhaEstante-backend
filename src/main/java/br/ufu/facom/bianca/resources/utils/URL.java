package br.ufu.facom.bianca.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class URL {

	public static String decodeParam(String text) {
		// Esse metodo codifica o text recebido como parametro e o converte para os padroes UTF-8
		try {
			return URLDecoder.decode(text, "UTF-8");

		} catch (UnsupportedEncodingException e) {
			// Caso de erro, retorna vazio
			return "";
		}
	}
}

