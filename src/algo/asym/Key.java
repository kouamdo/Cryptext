/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algo.asym;

import java.math.BigInteger;

/**
 *
 * @author JESSICA
 */
public class Key {
    
    /**
	 * Exposant de chiffrement ou de dechiffrement.
	 */
	private BigInteger e ;
	
	/**
	 * Modulo
	 */
	private BigInteger n ;

	public Key(BigInteger e, BigInteger n){
		this.e = e ;
		this.n = n ;
	}
	
	/**
	 * Affecte un nouvel exposant a la cle.
	 * @param e Nouvel exposant
	 */
	public void setExponent(BigInteger e){ this.e = e; }
	
	/**
	 * @return L'exposant associe a la cle.
	 */
	public BigInteger getExponent() { return this.e; }
	
	/**
	 * Affecte un nouveau modulo a la cle.
	 * @param n Nouveau modulo.
	 */
	public void setModulo(BigInteger n) { this.n = n ; }
	
	/**
	 * @return Le modulo associe a la cle.
	 */
	public BigInteger getModulo() { return this.n; }
	
	public String toString() { return e+","+n; }
        
       
}
