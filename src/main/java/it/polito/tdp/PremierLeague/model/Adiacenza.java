package it.polito.tdp.PremierLeague.model;

public class Adiacenza
{
	private int m1;
	private int m2;
	private int peso;
	public Adiacenza(int m1, int m2, int peso)
	{
		super();
		this.m1 = m1;
		this.m2 = m2;
		this.peso = peso;
	}
	public int getM1()
	{
		return m1;
	}
	public void setM1(int m1)
	{
		this.m1 = m1;
	}
	public int getM2()
	{
		return m2;
	}
	public void setM2(int m2)
	{
		this.m2 = m2;
	}
	public int getPeso()
	{
		return peso;
	}
	public void setPeso(int peso)
	{
		this.peso = peso;
	}
}
