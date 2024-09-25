package br.com.trainee.MoovieNight.domain.constante;

public enum ErrorMessage {
    ;
    public static final String EMAIL_NAO_ENCONTRADO = "Email não cadastrado";
    public static final String FALHA_LOGIN = "Email e/ou Cartao incorreto(s) ";
    public static final String FILME_NOME_INVALIDO =  "Nome do filme é inválido";
    public static final String FILME_GENERO_INVALIDO = "Gênero inválido";
    public static final String FILME_CLASS_INDICATIVA_INVALIDA = "Classifiação indicativa inválida";
    public static final String FILME_QUANTIDADE_INVALIDA =  "Quantidade inválida";
    public static final String FALHA_SALVAR_FILME = "Falha ao salvar o filme";
    public static final Object NEGOCIO_EXCEPTION = "Erro de negócio: ";
    public static final String CONTA_NOME_INVALIDO = "Nome para a conta é inválido";
    public static final String EMAIL_INVALIDO = "Email inválido";
    public static final String IDADE_INVALIDA = "Idade inserida é inválida";
    public static final String FILME_NAO_ENCONTRADO = "Nenhum filme com esse nome foi encontrado";
    public static final String IDADE_NAO_AUTORIZADA = "Usuário não possui idade mínima para alugar este filme";
    public static final String FILME_INDISPONIVEL = "Filme indisponível para alugar";
    public static final String PEDIDO_JA_REALIZADO = "Pedido já realizado";
    public static final String PEDIDO_INVALIDO = "Pedido inválido";
    public static final String PEDIDO_NAO_EXISTENTE = "O pedido não existe";
    public static final String EMAIL_JA_CADASTRADO = "Email já cadastrado";
    public static final String PEDIDO_JA_FINALIZADO = "Pedido já foi finalizado";
}
