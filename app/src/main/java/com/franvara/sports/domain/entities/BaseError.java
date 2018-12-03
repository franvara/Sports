package com.franvara.sports.domain.entities;

public class BaseError {

    private int mCodigoError;
    private String mMensajeError;

    private static String ONE = "Error al guardar en la base de datos";

    public String getMensajeError() {
        return mMensajeError;
    }

    public BaseError(int codigoError, String mensajeError) {
        this.mCodigoError = codigoError;
        this.mMensajeError = mensajeError;
    }

    public static BaseError getErrorEstandar() {
        return new BaseError(-1, "Hubo un error inesperado");
    }

}
