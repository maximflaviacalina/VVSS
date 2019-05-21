package mfir2142.controller;

import java.util.LinkedList;
import java.util.List;

import mfir2142.model.Intrebare;
import mfir2142.model.Statistica;
import mfir2142.model.Test;
import mfir2142.repository.IntrebariRepository;
import mfir2142.exception.DuplicateIntrebareException;
import mfir2142.exception.NotAbleToCreateStatisticsException;
import mfir2142.exception.NotAbleToCreateTestException;

public class AppController {
	
	private IntrebariRepository intrebariRepository;
	
	public AppController() {
		intrebariRepository = new IntrebariRepository();
	}

	public List<Intrebare> getAll() { return intrebariRepository.getIntrebari();}
	
	public Intrebare addNewIntrebare(Intrebare intrebare) throws DuplicateIntrebareException{
		
		intrebariRepository.addIntrebare(intrebare);
		
		return intrebare;
	}

	public boolean exists(Intrebare intrebare){
		return intrebariRepository.exists(intrebare);
	}
	
	public Test createNewTest() throws NotAbleToCreateTestException{
		
		if(intrebariRepository.getIntrebari().size() < 5)
			throw new NotAbleToCreateTestException("Nu exista suficiente intrebari pentru crearea unui test!(5)");
		
		if(intrebariRepository.getNumberOfDistinctDomains() < 5)
			throw new NotAbleToCreateTestException("Nu exista suficiente domenii pentru crearea unui test!(5)");
		
		List<Intrebare> testIntrebari = new LinkedList<Intrebare>();
		List<String> domenii = new LinkedList<String>();
		Intrebare intrebare;
		Test test = new Test();
		
		while(testIntrebari.size() != 5){
			intrebare = intrebariRepository.pickRandomIntrebare();
			
			if(testIntrebari.isEmpty()||(!testIntrebari.contains(intrebare) && !domenii.contains(intrebare.getDomeniu()))){
				testIntrebari.add(intrebare);
				domenii.add(intrebare.getDomeniu());
			}
			
		}
		
		test.setIntrebari(testIntrebari);
		return test;
		
	}
	
	public void loadIntrebariFromFile(String f){
		intrebariRepository.setIntrebari(intrebariRepository.loadIntrebariFromFile(f));
	}
	
	public Statistica getStatistica() throws NotAbleToCreateStatisticsException{
		
		if(intrebariRepository.getIntrebari().isEmpty())
			throw new NotAbleToCreateStatisticsException("Repository-ul nu contine nicio intrebare!");
		
		Statistica statistica = new Statistica();
		for(String domeniu : intrebariRepository.getDistinctDomains()){
			statistica.add(domeniu, intrebariRepository.getNumberOfIntrebariByDomain(domeniu));
		}
		
		return statistica;
	}

}
