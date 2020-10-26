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
public class main {

    public static void main(String[] args) {
        int cantPacientes = 12;
        Sala_Espera sala = new Sala_Espera(6);

        Recepcionista recepcionista = new Recepcionista(sala);
        Thread r = new Thread(recepcionista);

        Entrevistador entrevistador = new Entrevistador(sala);
        Thread e = new Thread(entrevistador);

        Extractor extractor = new Extractor(sala);
        Thread ex = new Thread(extractor);

        r.start();
        e.start();
        ex.start();

        for (int i = 1; i <= cantPacientes; i++) {
            Paciente p = new Paciente(sala);
            Thread t = new Thread(p, "Paciente " + i);
            t.start();
        }

    }

}
