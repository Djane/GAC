package br.com.sw2.gac.socket.exception;

public class SocketLoginException extends SocketException {

    private static final long serialVersionUID = -712872926231782050L;
    
    private ExceptionCode exceptionCode;
    
    public SocketLoginException(ExceptionCode code) {        
        super(code.getMessage());
        this.exceptionCode = code;
    } 
    
    public SocketLoginException(ExceptionCode code, Throwable cause) {
        super(cause);
        this.exceptionCode = code;
    } 
    
    public SocketLoginException(String message) {
        super(message);        
    }     
    
    public enum ExceptionCode {
        
        INVALID_VERSION_OF_DGPHONE(1, "Versão inválida do DgPhone"), 
        USER_IS_NOT_DGPHONE(2, "Não é um usuário do DGPhone"), 
        USER_IS_NOT_ACTIVE(3, "Usuário não está ativo"), 
        INVALID_USER(4, "Usuário inválido"), 
        USER_IS_NULL(5, "Usuário é nulo"),
        AGENT_IS_NULL(5, "Agente é nulo"),
        USER_TIMEOUT(6, "Time out"),
        SEM_LISTA_MOTIVOS_PAUSA(7, "Não foi possível a lista d emotivos de pausa do agente"),
        UNDEFINED(99, "Exceção indefinida");

        int code;
        String message = "";

        ExceptionCode(int code, String message) {
            this.code = code;
            this.message = message;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    public ExceptionCode getExceptionCode() {
        return exceptionCode;
    }

    public void setExceptionCode(ExceptionCode exceptionCode) {
        this.exceptionCode = exceptionCode;
    }    
    
    
}

