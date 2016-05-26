/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectometodos;

import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;

public class ProyectoMetodos {
    double incognita;
    double a;
    double b;
    double fDea;
    double fDeb;
    boolean bandera=false;
    double numeroPositivo;
    double fDeResultado;
    double relativo=0;
    double cAnterior=0;
    String iteraccion;
    int suma=0;
    int itera=0;
    int numIteraciones;
    int tipError;
    double errorAnterior=-1;
    String cifrasString;
    File archivo;
    FileWriter escribir;
    double resultado=0;
    int sumaL=0;
    String stringTipoError;
    DecimalFormat decimales = new DecimalFormat("0.000000000000");
    public ProyectoMetodos(double a, double b,int numIteraciones,int tipError) {
        this.tipError=tipError;
        if(tipError==1){
        //relativo
        stringTipoError="ER";
        }
        else{
        stringTipoError="EA";
        }
        reglaFalsa(a,b,numIteraciones);
    }
    public void reglaFalsa(double a, double b,int numIteraciones){
    this.a=a;
    this.b=b;
    this.numIteraciones=numIteraciones;
    
    
    boolean bandera= false;
    
    
    iteraccion= "\n iteraccion" + "\t" + "X+" + "\t\t\t" + "X-" + "\t\t\t" + "X" +"\t\t\t"+ "f(x)"+ "\t\t\t\t" +stringTipoError + "\n" ;
    guardarArchivo(iteraccion);
    if(numIteraciones==-1){ // por error
        while(!decimales.format(errorAnterior).equals(decimales.format(relativo))){
            metodo();
    
        }
    
        sumaL=numCifrasSignificativas(relativo);
        cifrasString="numero de cifras significativas: "+ sumaL +"\n";
    
        guardarArchivo(cifrasString);
    }
    else{       //por numero iteracciones
        while(itera<numIteraciones){
        metodo();
    
    }
    
        sumaL=numCifrasSignificativas(relativo);
        cifrasString="numero de cifras significativas: "+ sumaL +"\n";
    
   guardarArchivo(cifrasString);
    }
    
    abrirArchivo("calculosReglaFalsa.txt");
    
    }
    public void metodo(){
    
    fDea= ((Math.pow(a,3))-a-3);//sustitucion de funcion con valor negativo
    fDeb=(Math.pow(b,3))-b-3;//sustitucion de funcion  con valor positivo
  
    resultado=((b*fDea)-(a *fDeb))/(fDea-fDeb);// igual a C
    fDeResultado=(Math.pow(resultado, 3)-resultado - 3) ;//
    if(itera==0){
        iteraccion= " \t"+  itera + " \t" + decimales.format(b)  + "\t\t" + decimales.format(a) +"\t\t"+ decimales.format(resultado) +"\t\t" + decimales.format(fDeResultado) +"\t\t\t"+decimales.format(relativo)+"\n";
        guardarArchivo(iteraccion);
        errorAnterior=relativo;
   // itera++;
        if(!esPositivo(fDeResultado)){
            a=resultado;
           }
        else{
            b=resultado;
        }
   
    }
    else{
    
        if(!esPositivo(fDeResultado)){
          
                a=resultado;//negativo
           
        }
        else{
           b=resultado;
        
        }
    }
    if(tipError==1){//ERROR RELATIVO
        errorAnterior=relativo;
        relativo=errorRelativo(resultado,cAnterior);
    }
    else{//ERROR ABSOLUTO
        errorAnterior=relativo;
        relativo=errorAbsoluto(resultado,cAnterior);
    }
        
    if(bandera){
        iteraccion= " \t"+  itera + " \t" + decimales.format(b)  + "\t\t" + decimales.format(cAnterior) +"\t\t"+ decimales.format(resultado) +"\t\t" + decimales.format(fDeResultado) +"\t\t\t"+decimales.format(relativo)+"\n";
        guardarArchivo(iteraccion);
    
    
    }
    itera++;
    cAnterior=resultado;
    bandera=true;
    sumaL=numCifrasSignificativas(relativo);
    }
    
    
    public void guardarArchivo(String iteracion){
    try
    {
        if(itera == 0){
      archivo=new File("calculosReglaFalsa.txt");
     
        } 
      escribir=new FileWriter(archivo,true);
       
      escribir.write(iteracion);
      escribir.close();
    }
    catch(Exception e)
    {
    System.out.println("Error al escribir");
    }
    }
    
    public void abrirArchivo(String archivo){

     try {

            File objetofile = new File (archivo);
            Desktop.getDesktop().open(objetofile);

     }catch (IOException ex) {

            System.out.println(ex);

     }

}   
    
    public boolean esPositivo(double num){
    boolean positivo;
    //numeroPositivo=num - (1*num);
    if(num>0){
    positivo=true;
    }
    else {
    positivo=false;
    }
    
    return positivo;
    
    }
    
    public int numCifrasSignificativas(double error){
        String cifrasSig="";
        suma=0;
        double errorLocal=error;
        char mayorCuatro;
        boolean ban= false;
        cifrasSig= (decimales.format(errorLocal)) + '4';
        char[]cifraArray= cifrasSig.toCharArray();
        for (int i = 0; i < cifrasSig.length() ; i++) {
            if(!ban){
            
                if(cifraArray[i]=='.'){
                    ban=true;
                }
            }
            else{
            
            if(cifraArray[i]=='0'){
                if(cifraArray[i +1]=='0'||cifraArray[i +1]=='1'||cifraArray[i +1]=='2'||cifraArray[i +1]== '3'||cifraArray[i +1]== '4'){
                suma++;
                }
                else{
                break;
                }
            
            }
            else{
        
                
         //   break;
            }
            }
            
            }
        return suma;
        
    }   
    
    
    
    
    
    public double errorRelativo(double actual, double anterior){
        
    double error=(actual-anterior)/actual;
    return error;
    
    }
    public double errorAbsoluto(double actual,double anterior){
    return actual - anterior;
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner scan= new Scanner(System.in);
        int opcion;
        int tipoError;
        int cantidadIteracciones=0;
        
        
        System.out.println("Terminar metodo por:");
        System.out.println("1- numero de iteracciones");
        System.out.println("2- terminar por error:");
        opcion= scan.nextInt();
        switch(opcion){
            case 1:
                System.out.println("Cantidad de iteracciones");
                cantidadIteracciones= scan.nextInt();
                break;
            case 2: cantidadIteracciones=-1;   //por error
            break;
        }
         System.out.println("Elige el tipo de error");
        System.out.println("1- Error relativo:");
        System.out.println("2- Error absoluto");
        tipoError=scan.nextInt();
        
      ProyectoMetodos s=  new ProyectoMetodos(1,2,cantidadIteracciones,tipoError);
    }
   
    
}
