package com.algaworks.osworks.exceptionHandler;

import com.algaworks.osworks.exception.NegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;

@ControllerAdvice
public class ExecptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<Object> handleNegocio(NegocioException negocioException,WebRequest webRequest){
        var status = HttpStatus.BAD_REQUEST;

        var erro = new Erro();
        erro.setStatus(status.value());
        erro.setTitulo(negocioException.getMessage());
        erro.setDataHora(LocalDateTime.now());

        return super.handleExceptionInternal(negocioException,erro,new HttpHeaders(),status,webRequest);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        var campos = new ArrayList<Erro.Campo>();

        for(ObjectError error : ex.getBindingResult().getAllErrors()){
            campos.add(new Erro.Campo(((FieldError) error).getField(),messageSource.getMessage(error, LocaleContextHolder.getLocale())));
        }

        var erro = new Erro();
        erro.setStatus(status.value());
        erro.setTitulo("A operação não pode ser concluída!");
        erro.setDataHora(LocalDateTime.now());
        erro.setCampos(campos);

        return super.handleMethodArgumentNotValid(ex, headers, status, request);
    }
}
