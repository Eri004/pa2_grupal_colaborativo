package ec.edu.uce.application.service;

import ec.edu.uce.domain.model.Garantia;

public class TareaProcesarGarantia implements Runnable{

    private GarantiaService garantiaService;
    private Garantia garantia;

    public TareaProcesarGarantia(GarantiaService garantiaService, Garantia garantia) {
        this.garantiaService = garantiaService;
        this.garantia = garantia;
    }

    @Override
    public void run() {
        System.out.println(">>> [HILO SECUNDARIO EXECUTOR] Nombre: " 
            + Thread.currentThread().getName() 
            + " | ID: " + Thread.currentThread().threadId());
        this.garantiaService.procesarGarantia(garantia);
    }

}
