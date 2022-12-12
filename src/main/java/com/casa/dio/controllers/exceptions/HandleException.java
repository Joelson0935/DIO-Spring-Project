package com.casa.dio.controllers.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandleException {

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<Erro> objetoNaoEncontrado() {
		Erro erro = new Erro("Objeto Não Encontrado!", HttpStatus.NOT_FOUND.value(), LocalDateTime.now());
		return new ResponseEntity<Erro>(erro, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Erro> argumentoInvalido(MethodArgumentNotValidException e) {
		Erro erro = new Erro("Argumento Inválido!", HttpStatus.BAD_REQUEST.value(), LocalDateTime.now());
		e.getBindingResult().getFieldErrors().forEach((err) -> {
			String FieldName = err.getField();
			String errorMessage = err.getDefaultMessage();
			erro.setCamposDeErros(FieldName, errorMessage);
		});
		return new ResponseEntity<Erro>(erro, HttpStatus.BAD_REQUEST);
	}

}
