package br.com.trainee.MoovieNight.domain.enuns;

public enum TipoConta {
    CLIENTE("Cliente"),
    ADMINISTRADOR("Administrador");

    private String tipoConta;


   TipoConta(String tipoConta)
    {
        this.tipoConta = tipoConta;
    }

    public String getTipoConta()
    {
        return tipoConta;
    }
}
