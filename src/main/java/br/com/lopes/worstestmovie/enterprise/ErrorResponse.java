package br.com.lopes.worstestmovie.enterprise;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class ErrorResponse {
    private String message;
    private List<String> details;
}
