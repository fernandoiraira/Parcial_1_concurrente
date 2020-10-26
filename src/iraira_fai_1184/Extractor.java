/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iraira_fai_1184;

/**
 *
 * @author Fernando Iraira <fmiraira@gmail.com>
 */
public class Extractor implements Runnable {
    //NOTA: Se ve una clara herencia para las clases Extractor, Entrevistador y Recepcionista, pero por motivos
    //de tiempo, no alcance a implementarlo

    private final Sala_Espera sala;

    public Extractor(Sala_Espera sala) { //Constructor
        this.sala = sala;
    }

    public void run() {
        while (true) {
            sala.extraer();
        }
    }

}
