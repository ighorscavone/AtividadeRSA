/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rsa_algoritmo;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Scanner;
/**
 *
 * @author ighor
 */
public class RSA_Algoritmo {

    
    public static String cifrar(BigInteger valor, BigInteger e, BigInteger n){
        return valor.modPow(e,n).toString();
    }
    
    public static String decifrar(BigInteger valor, BigInteger d, BigInteger n){
        return new String(valor.modPow(d,n).toByteArray());
    }
    
    public static void main(String[] args) {
        String mensagem = "The information security is of significant importance to ensure the privacy of communications"; //Mensagem que será usada para o RSA;
        
        String msgCifrada = null;
        String msgDecifrada = null;
        
        BigInteger n, d, e;
        int bitLength = 4096; //Tamanho = 4096 bits;
        
        SecureRandom r = new SecureRandom();
        BigInteger p = new BigInteger(bitLength/2, 100, r); //Gerando um valor aleatório de 4096 bits com 100% de chance de ser primo para 'p';
        BigInteger q = new BigInteger(bitLength/2, 100, r); //Gerando um valor aleatório de 4096 bits com 100% de chance de ser primo para 'q';
        
        
        n = p.multiply(q); // n = p*q
        
        BigInteger m = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE)); //Calcula a função totiente;
        
        e = new BigInteger("3"); //Setando um valor para e onde  1 < e < totiente | 'e' e o totiente devem ser primos entre si
        
        while(m.gcd(e).intValue() > 1) e = e.add(new BigInteger("2"));
        
        d = e.modInverse(m);
        
        
        //DEBUGS
        System.out.println("p: " + p);
        System.out.println("q: " + q);
        System.out.println("n: " + n);
        System.out.println("d: " + d);
        System.out.println("e: " + e);
        
        //Cifrando a mensagem
        msgCifrada = cifrar(new BigInteger(mensagem.getBytes()), e, n);
        System.out.println("Mensagem Cifrada: " + msgCifrada);
        
        //Decifrando a mensagem
        msgDecifrada = decifrar(new BigInteger(msgCifrada), d, n);
        System.out.println("Mensagem Decifrada: " + msgDecifrada);
        
        
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("");
        System.out.println("Digite uma mensagem para ser cifrada!");
        
        String mensagemNova = scanner.nextLine();
        
        msgCifrada = cifrar(new BigInteger(mensagemNova.getBytes()), e, n);
        System.out.println("Mensagem Cifrada Nova: " + msgCifrada);
        
        msgDecifrada = decifrar(new BigInteger(msgCifrada), d, n);
        System.out.println("Mensagem Decifrada Nova: " + msgDecifrada);
    }
    
}
