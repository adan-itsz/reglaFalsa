/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectometodos;

import java.io.File;
import java.io.FileWriter;

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
    double relativo=0;
    double xPrimero;
    String iteraccion;
    int suma=0;
    int itera=0;
    File archivo;
    FileWriter escribir;
    public ProyectoMetodos(double a, double b) {
        reglaFalsa(a,b);
    }
    public void reglaFalsa(double a, double b){
    this.a=a;
    this.b=b;
    double resultado;
    int sumaL=0;
    
    boolean bandera= false;
    
    
    iteraccion= "iteraccion" + "\t" + "X+" + "\t\t" + "X-" + "\t\t\t" + "X" +"\t\t"+ "f(x)"+ "\t\t\t\t" +"ER" + "\n" ;
    guardarArchivo(iteraccion);
    while(itera<8){//suma=0
    fDea=(((g*a)/c)*(1-(Math.pow(euler,(-c* a)/t))));
    fDeb=(((g*b)/c)*(1-(Math.pow(euler,(-c* b)/t))));
    
    resultado=((b*fDea)-(a *fDeb))/fDea-fDeb;
    fDeResultado=(((g*resultado)/c)*(1-(Math.pow(euler,(-c* resultado)/t))));
    if(itera==0){
    iteraccion= " \t"+  itera + " \t" + b + "\t\t" + a +"\t\t"+ resultado +"\t" + fDeResultado +"\t\t" + "\t\t"+relativo+"\n";
    guardarArchivo(iteraccion);
    }
    else{
        if(!esPositivo(fDeResultado)){
        a=resultado;//negativo
        }
        else{
        b=resultado;
        }
        if(bandera==false){
        bandera=true;
        }
        else{
        relativo=errorRelativo(resultado,xPrimero);
        sumaL=numCifrasSignificativas(relativo);
            }
    }
     iteraccion= " \t"+  itera + " \t" + b + "\t\t" + a +"\t\t"+ resultado +"\t" + fDeResultado +"\t\t" + "\t\t"+relativo+"\n";
    guardarArchivo(iteraccion);
    
    itera++;
    }
   
    
    
    }
    public void guardarArchivo(String iteracion){
    try
    {
        if(itera == 0){
      archivo=new File("calculosReglaFalsa.txt");
      escribir=new FileWriter(archivo,true);
        }
      
      escribir.write(iteracion);
      escribir.close();
    }
    catch(Exception e)
    {
    System.out.println("Error al escribir");
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
        String cifrasSig="";
        double errorLocal=error;
        char mayorCuatro;
        boolean ban= false;
        cifrasSig= Double.toString(errorLocal);
        char[]cifraArray= cifrasSig.toCharArray();
        for (int i = 0; i < cifrasSig.length() ; i++) {
            if(!ban){
            
                if(cifraArray[i]=='.'){
                    ban=true;
                }
            }
            else{
            
            if(cifraArray[i]=='0'){
                if(cifraArray[i +1]<5){
                suma++;
                }
            
            }
            else{
        
                
            break;
            }
            }
            
            }
        return suma;
        
    }   
    
    
    
    
    
    public double errorRelativo(double actual, double anterior){
        
    double error=(actual-anterior)/actual;
    return error;
    
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        new ProyectoMetodos(58,59);
    }
   
    
}
