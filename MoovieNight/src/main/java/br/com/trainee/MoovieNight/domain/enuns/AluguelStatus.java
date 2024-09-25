package br.com.trainee.MoovieNight.domain.enuns;

public enum AluguelStatus {
    ATIVO("Ativo"),
    ENCERRADO("Encerrado");

    private String msg;

    AluguelStatus(String msg)
    {
        this.msg=msg;
    }
    public String getAluguelStatus()
    {
        return msg;
    }

}
