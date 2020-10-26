/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iraira_fai_1184;

import java.util.concurrent.Semaphore;

/**
 *
 * @author Fernando Iraira <fmiraira@gmail.com>
 */
public class Sala_Espera {

    private final Semaphore mutex = new Semaphore(1);
    private final Semaphore semRecepcionista = new Semaphore(0);
    private final Semaphore semEntrevistador = new Semaphore(0, true);
    private final Semaphore entrevista = new Semaphore(0, true);
    private final Semaphore semExtractor = new Semaphore(0);
    private final Semaphore semDesayuno = new Semaphore(0);
    private int sillasDisp;

    public Sala_Espera(int cant) { //Constructor 
        this.sillasDisp = cant;
    }

    public boolean entrar() {
        boolean exito = false;
        //Se verifica que haya lugar
        try {
            mutex.acquire(); // Semaforo de acceso a sección critica (por la variable compartida)
            
            /////////////////////////////CODIGO AGREGADO/////////////////////////////
            this.semRecepcionista.release();
            System.out.println("La recepcionista esta atendiendo al paciente " + Thread.currentThread().getName());
            Thread.sleep(1000);
            System.out.println("La recepcionista termino de atender al paciente " + Thread.currentThread().getName());
            /////////////////////////////CODIGO AGREGADO/////////////////////////////
            
            if (this.sillasDisp > 0) {
                this.sillasDisp--;
                exito = true;
                System.out.println(Thread.currentThread().getName() + " pudo ingresar a la sala de espera.");
            }
        } catch (InterruptedException ex) {
        }
        mutex.release(); // fin de la sección critica

        return exito;
    }

    public void asistir() {
        //Se le avisa al entrevistador que ya se puede hacer la entrevista
        this.semEntrevistador.release();
        try {
            //Espera a que termine su entrevista
            this.entrevista.acquire();
        } catch (Exception e) {
        }

        try {
            //Avisa al extractor que ya esta listo para que le realicen la extraccion
            this.semExtractor.release();
        } catch (Exception e) {
        }

        this.desayunar();
    }

    public void atender() {
        try {
            System.out.println("La recepcionista esta realizando sus tareas...");
            this.semRecepcionista.acquire();
        } catch (Exception e) {
        }
    }

    public void entrevistar() {
        try {
            this.semEntrevistador.acquire();
        } catch (Exception e) {

        }
        System.out.println("El entrevistador esta realizando la entrevista al paciente.");
        try {
            Thread.sleep(((int)Math.random()*3 + 1)*1000);
        } catch (Exception e) {
        }
        System.out.println("Termino la entrevista al paciente.");
        this.entrevista.release();
    }

    public void extraer() {
        try {
            this.semExtractor.acquire();
        } catch (Exception e) {
        }

        System.out.println("El especialista esta realizando la extraccion de sangre.");

        try {
            Thread.sleep(((int)Math.random()*3 + 1)*1000);
        } catch (Exception e) {
        }
        System.out.println("Se termino la extraccion de sangre.");
        System.out.println("Se entrega el certificado al paciente.");

        this.semDesayuno.release();
    }

    public void desayunar() {
        try {
            //Espera a que termine la extraccion para ir a desayunar
            this.semDesayuno.acquire();
        } catch (Exception e) {
        }

        System.out.println(Thread.currentThread().getName() + " esta desayunando... ");
        try {
            Thread.sleep(((int)Math.random()*3 + 1)*1000);
        } catch (Exception e) {
        }
        System.out.println(Thread.currentThread().getName() + " termino de desayunar y se fue.");
        
        //Cuando se va un paciente, se libera una silla
        try {
            mutex.acquire();
        } catch (Exception e) {
        }
        this.sillasDisp++;
        mutex.release();        
    }

}
