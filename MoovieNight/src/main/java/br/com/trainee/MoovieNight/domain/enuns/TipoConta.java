package br.com.trainee.MoovieNight.domain.enuns;

import br.com.trainee.MoovieNight.domain.constante.ErrorMessage;
import br.com.trainee.MoovieNight.domain.exception.NegocioException;

public enum TipoConta {
    CLIENTE("CLIENTE"),
    ADMINISTRADOR("ADMINSTRADOR");

    private String tipoConta;


   TipoConta(String tipoConta)
    {
        this.tipoConta = tipoConta;
    }

    public String getTipoConta()
    {
        return tipoConta;
    }

    public static TipoConta fromString(String input) {
        for (TipoConta tipo : TipoConta.values()) {
            if (tipo.getTipoConta().equalsIgnoreCase(input.trim())) {
                return tipo;
            }
        }
        throw new NegocioException(ErrorMessage.TIPO_CONTA_INVALIDO);
    }
}
