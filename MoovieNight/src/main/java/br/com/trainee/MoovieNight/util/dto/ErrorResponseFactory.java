package br.com.trainee.MoovieNight.util.dto;

public class ErrorResponseFactory {
    public static CustomErrorResponse createResponseError(String message, String path, Integer httpStatus) {
        return new CustomErrorResponse(message, path, httpStatus);
    }
}
