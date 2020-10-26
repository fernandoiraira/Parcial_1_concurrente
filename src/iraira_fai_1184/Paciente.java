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
public class Paciente implements Runnable {

    private final Sala_Espera sala;

    public Paciente(Sala_Espera sala) {
        this.sala = sala;
    }

    @Override
    public void run() {
        //Verifica que pueda entrar, sino se va, en el caso de que se quisiera, se podria poner en un while true para que 
        // el paciente trate de ingresar nuevamente
        
        if (sala.entrar()) {
            sala.asistir();
        } else{
            System.out.println(Thread.currentThread().getName() + " no encontro lugar y se fue.");
        }
    }
}


