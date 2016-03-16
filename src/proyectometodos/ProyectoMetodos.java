/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectometodos;



public class ProyectoMetodos {
    double c= 18.00;//coeficiente de rozamiento
    double g=9.81; //
    double t=32;
    double euler= 0.367879441;
    double incognita;
    double a;
    double b;
    double fDea;
    double fDeb;
    boolean bandera=false;
    double numeroPositivo;
    double fDeResultado;
    public void reglaFalsa(double a, double b){
    this.a=a;
    this.b=b;
    double resultado;
    double aux;
    
    
    fDea=(((g*a)/c)*(1-(Math.pow(euler,(-c* a)/t))));
    fDeb=(((g*b)/c)*(1-(Math.pow(euler,(-c* b)/t))));
    
    resultado=((b*fDea)-(a *fDeb))/fDea-fDeb;
    fDeResultado=(((g*resultado)/c)*(1-(Math.pow(euler,(-c* resultado)/t))));
    
    
    if(!esPositivo(fDeResultado)){
    a=resultado;
    }
    else{
    
    b=resultado;
    }
    
    
   
    
    
    }
    public boolean esPositivo(double num){
    boolean positivo;
    numeroPositivo=num - num;
    if(numeroPositivo==0){
    positivo=true;
    }
    else {
    positivo=false;
    }
    return positivo;
    
    }
    
    public int numCifrasSignificativas(double error){
        
    
    
    }
    
    public double errorRelativo(double actual, double anterior){
        
    double error=(actual-anterior)/actual;
    return error;
    
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        
    }
   
    
}
