package mfir2142.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

import mfir2142.exception.DuplicateIntrebareException;
import mfir2142.exception.InputValidationFailedException;
import mfir2142.exception.NotAbleToCreateTestException;
import mfir2142.model.Intrebare;
import mfir2142.model.Statistica;

import mfir2142.controller.AppController;
import mfir2142.exception.NotAbleToCreateStatisticsException;
import mfir2142.model.Test;

//functionalitati
//F01.	 adaugarea unei noi intrebari pentru un anumit domeniu (enunt intrebare, raspuns 1, raspuns 2, raspuns 3, raspunsul corect, domeniul) in setul de intrebari disponibile;
//F02.	 crearea unui nou test (testul va contine 5 intrebari alese aleator din cele disponibile, din domenii diferite);
//F03.	 afisarea unei statistici cu numarul de intrebari organizate pe domenii.

public class StartApp {

	private static final String file = "intrebari.txt";
	private static final AppController appController = new AppController();
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		appController.loadIntrebariFromFile(file);
		boolean activ = true;
		String optiune = null;
		
		while(activ){
			
			System.out.println("");
			System.out.println("1.Adauga intrebare");
			System.out.println("2.Creeaza test");
			System.out.println("3.Statistica");
			System.out.println("4.Toate intrebarile");
			System.out.println("5.Exit");
			System.out.println("");
			System.out.println("Dati optiunea: ");
			
			optiune = console.readLine();
			
			switch(optiune){
			case "1" :
				AdaugaIntrebare();
				break;
			case "2" :
				CreeazaTest();
				break;
			case "3" :
				//appController.loadIntrebariFromFile(file);
				Statistica statistica;
				try {
					statistica = appController.getStatistica();
					System.out.println(statistica);
				} catch (NotAbleToCreateStatisticsException e) {
					// TODO 
				}
				
				break;
				case "4":
					List<Intrebare> intrebari = appController.getAll();
					for(Intrebare intrebare : intrebari) {
						System.out.println(intrebare.toString());
					}
					break;
			case "5" :
				activ = false;
				break;
			default:
				break;
			}
		}
		
	}

	private static void AdaugaIntrebare() {
		Scanner in = new Scanner(System.in);
		System.out.println("Dati numele intrebarii: ");
		String enunt=in.nextLine();
		System.out.println("Dati prima varianta de raspuns: ");
		String varianta1=in.nextLine();
		System.out.println("Dati a doua varianta de raspuns: ");
		String varianta2=in.nextLine();
		System.out.println("Dati a treia varianta de raspuns: ");
		String varianta3=in.nextLine();
		System.out.println("Dati varianta corecta: ");
		String variantaCorecta=in.nextLine();
		System.out.println("Dati domeniul intrebarii: ");
		String domeniu=in.nextLine();
		try {
			Intrebare intrebare=new Intrebare(enunt,varianta1,varianta2,varianta3,variantaCorecta,domeniu);
			appController.addNewIntrebare(intrebare);
		} catch (InputValidationFailedException | DuplicateIntrebareException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void CreeazaTest() {
		try {
			Test test = appController.createNewTest();
			List<Intrebare> intrebariTest = test.getIntrebari();
			for(Intrebare intrebare : intrebariTest) {
				System.out.println(intrebare.toString());
			}
		} catch (NotAbleToCreateTestException e){
			System.out.println(e.getMessage());
		}
	}

}
